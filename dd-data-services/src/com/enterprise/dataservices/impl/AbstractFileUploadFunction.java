package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import com.enterprise.common.Logger;
import com.enterprise.common.entity.IMeta;
import com.enterprise.common.entity.ISubPkgMappingRule;
import com.enterprise.common.enums.LoadType;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.pojo.HeadingColumnMappedBean;
import com.enterprise.common.pojo.LabelSelectInputBean;
import com.enterprise.common.pojo.SelectInputBean;
import com.enterprise.common.util.CSVFileContentUtils;
import com.enterprise.common.util.CSVParser;
import com.enterprise.common.util.Constants;
import com.enterprise.common.util.StringUtils;
import com.enterprise.domain.entity.Invoice;
import com.enterprise.domain.entity.Order;
import com.enterprise.domain.entity.Rate;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.SubPkgMappingRule;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.AirCarrierMeta;
import com.enterprise.domain.entity.meta.AirportMeta;
import com.enterprise.domain.entity.meta.CityMeta;
import com.enterprise.domain.entity.meta.ContainerStatusMeta;
import com.enterprise.domain.entity.meta.CustomMeta;
import com.enterprise.domain.entity.meta.InvoiceMeta;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.domain.entity.meta.OrderMeta;
import com.enterprise.domain.entity.meta.RateMeta;
import com.enterprise.domain.entity.meta.SeaCarrierMeta;
import com.enterprise.domain.entity.meta.SeaportMeta;
import com.enterprise.domain.entity.meta.ShipmentMeta;
import com.enterprise.user.DeepDiveUser;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 4:58:31 PM
 * @author rafourie
 */
public abstract class AbstractFileUploadFunction {
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected abstract Class<? extends Meta> getMetaClass();
	
	@SuppressWarnings("unchecked")
	public List<HeadingColumnMappedBean> fetchAvailableHeadings(List<String> columnHeadingList, long packageId, long subPkgId) {
		Map<String, Long> headingParentMetaIdMap = new HashMap<String, Long>();
		Map<Long, String> parentMetaIdColumnMap = new HashMap<Long, String>();
		for (String heading : columnHeadingList)
			headingParentMetaIdMap.put(heading, null);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(this.getMetaClass())
			.add(Restrictions.isNotNull("parent"))
			.add(Restrictions.eq("pkg.id", packageId))
			.add(Restrictions.in("columnName", columnHeadingList));
		List<IMeta> results = criteria.list();
		if (!CollectionUtils.isEmpty(results)) {
			for (IMeta imeta : results) {
				setSubPkgID(imeta.getId(), subPkgId, session);
				String metaColumnName = imeta.getColumnName();
				Long parentMetaId = headingParentMetaIdMap.get(metaColumnName);
				if (parentMetaId == null) {
					IMeta iparentMeta = imeta.getParent();
					parentMetaId = iparentMeta.getId();
					headingParentMetaIdMap.put(metaColumnName, parentMetaId);
					parentMetaIdColumnMap.put(parentMetaId, iparentMeta.getColumnName());
				}
			}
		}
		criteria = session.createCriteria(this.getMetaClass())
			.add(Restrictions.isNull("parent"));
		results = criteria.list();
		if (!CollectionUtils.isEmpty(results)) {
			for (IMeta imeta : results) {
				Long metaId = imeta.getId();
				if (!parentMetaIdColumnMap.containsKey(metaId)) {
					String metaColumnName = imeta.getColumnName();
					parentMetaIdColumnMap.put(metaId, metaColumnName);
					headingParentMetaIdMap.put(metaColumnName, metaId);
				}
			}
		}
		List<HeadingColumnMappedBean> availableHeadings = new ArrayList<HeadingColumnMappedBean>();
		Set<String> headingSet = headingParentMetaIdMap.keySet();
		for (String heading : headingSet) {
			Long parentMetaId = headingParentMetaIdMap.get(heading);
			if (parentMetaId != null) {
				String column = parentMetaIdColumnMap.get(parentMetaId);
				if (column != null)
					availableHeadings.add(new HeadingColumnMappedBean(heading,column));
			}
		}
		return availableHeadings;
	}
	
	public abstract void processLine(String[] columnArr, FileUploadSessionBean bean, Session session) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, DataUploadWarning;
	
	private int addedRows = 0, failedRows = 0;

	public FileUploadSessionBean processFileContent(String[] textStr, FileUploadSessionBean bean) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
				IllegalArgumentException, IllegalAccessException, InvocationTargetException, 
				DataUploadWarning, DataUploadException {
		Logger.debug("stepping into processFileContent: sessionFactory [" + sessionFactory + "] ...", this.getClass());
		Session session = sessionFactory.getCurrentSession();
		
		int batchStartLine = bean.getBatchStartLine();
		if(batchStartLine == 0){
			addedRows = 0;failedRows = 0;
		}
		if (batchStartLine < textStr.length) {
			int batchEndLine = batchStartLine + bean.getBatchSize();
			batchEndLine 
				= (batchEndLine > textStr.length) ? textStr.length : batchEndLine;
			for (int i = batchStartLine; i < batchEndLine; i++) {
				bean.setCurrentLine(i);
				String line = textStr[i];
				if (!StringUtils.isEmpty(line)) {
					String[] lineValues = null;
					if(!bean.isMailProcess()||bean.isCSVFile())
						lineValues = CSVParser.getInstance().parse(line);
					else {
						lineValues = line.split("#!#!");
					}
					if (lineValues != null && lineValues.length > 0) {
						boolean headerLineProcessed = bean.isHeaderLineProcessed();
						if (!headerLineProcessed) {
							CSVFileContentUtils csvFileContentUtils = new CSVFileContentUtils(textStr);
							String nextLine = csvFileContentUtils.getNextLineWithValues(i);
							String[] nextLineValues = null;
							if(!bean.isMailProcess()||bean.isCSVFile())
								nextLineValues = CSVParser.getInstance().parse(nextLine);
							else {
								nextLineValues = nextLine.split("#!#!");
								if(nextLine.endsWith("#!#!")){
									List<String> tempArr = new ArrayList<String>(Arrays.asList(nextLineValues));
									tempArr.add(null);
									nextLineValues = tempArr.toArray(new String[tempArr.size()]);
								}
							}
							if(bean.getSubPackageId()==0){
								newSubpkgdetails(bean, session);
							}
							Map<Integer,IMeta> indexMap = this.processHeaderLine(lineValues, nextLineValues, bean, session);
							bean.setIndexMap(indexMap);
							if (!bean.isHeaderLineProcessed())
								break;
						} else {
							boolean isSuccess = true;
							try {
								this.processLine(lineValues, bean, session);
								session.flush();
							} catch (DataUploadWarning e) {
								isSuccess = false;
								bean.addWarningMsg(e.getMessage());
							}
							if(!isSuccess) 
							   failedRows++;
							else 
							   addedRows++;
						}
					}
				}
				bean.setBatchStartLine(i + 1);
			}
			boolean isLast = false;
			if(bean.isMailProcess() && batchEndLine == bean.getTotalLines())
				isLast = true;
			if((addedRows>0 || failedRows>0) && (batchEndLine == (bean.getTotalLines() + 1) || isLast)){
				String loadType = "Shipment";
				if(bean.getLoadType() == 2) {
					loadType = "Rate";
					Rate.setRateDetailInactive(bean.getRateDetailIds(), bean.getRateIds(), session);
				} if(bean.getLoadType() == 3) {
					loadType = "Invoice";
					Invoice.setInvoiceDetailClearOld(bean.getRateDetailIds(), bean.getRateIds(), session);
				} if(bean.getLoadType() == 4) {
					loadType = "Order";
					Order.setOrderExpedLineClearOld(bean.getRateDetailIds(), bean.getRateIds(), session);
				} if(bean.getLoadType() == 5) {
					loadType = "Custom";
				} if(bean.getLoadType() == 6) {
					loadType = "Container status";
				}
				double percent = bean.getTotalLines()>0 ? (addedRows/Double.parseDouble(bean.getTotalLines()+""))*100 : 0;
				DecimalFormat df = new DecimalFormat("#.##"); 
				bean.addInfoMsg("Load Accuracy : <strong>"+df.format(percent)+"%</strong>");
				if(addedRows>0)
					bean.addInfoMsg("<strong>"+addedRows+"</strong> "+loadType+"(s) Loaded Successfully");
				if(failedRows>0)
					bean.addInfoMsg("<strong>"+failedRows+"</strong> "+loadType+"(s) Failed");
				bean.setSuccessShipments(addedRows);
				bean.setFailedShipment(failedRows);
			}
			bean.setFileProcessed(
				(batchEndLine == (bean.getTotalLines() + 1) || isLast) ? true : false
			);
		}
		return bean;
	}
	
	private Map<Integer,IMeta> processHeaderMappings(String[] columnArr, FileUploadSessionBean bean, Session session) {
		Map<Integer,IMeta> indexMap = new HashMap<Integer, IMeta>();
		List<LabelSelectInputBean> unknownHeadingMappingsList = bean.getMappedHeadingList();
		for (int i = 0; i < columnArr.length; i++) {
			String heading = columnArr[i];
			IMeta headingMetaEntity = null;
			for (LabelSelectInputBean labelSelectInputBean : unknownHeadingMappingsList) {
				String mappedColumnLabel = labelSelectInputBean.getLabel();
				if (heading.equals(mappedColumnLabel)) {
					SelectInputBean selectInputBean = labelSelectInputBean.getSelectInputBean();
					String selectedOptionDescription = selectInputBean.getSelectedOptionDescription();	
					if (!StringUtils.isEmpty(selectedOptionDescription)
							&& !Constants.DEFAULT_OPTION.equals(selectedOptionDescription)) {
						Criteria criteria = session.createCriteria(this.getMetaClass())
							.add(Restrictions.eq("columnName", mappedColumnLabel))
							.add(Restrictions.eq("pkg.id", bean.getPackageId()));
						//headingMetaEntity = (IMeta)criteria.uniqueResult();
						if(criteria.list() != null && !criteria.list().isEmpty())
							headingMetaEntity = (IMeta)criteria.list().get(0);
						if (headingMetaEntity == null) {
							criteria = session.createCriteria(this.getMetaClass())
								.add(Restrictions.eq("columnName", selectedOptionDescription))
								.add(Restrictions.eq("pkg.id", 1L));
							//headingMetaEntity = (IMeta)criteria.uniqueResult();
							if(criteria.list() != null && !criteria.list().isEmpty())
								headingMetaEntity = (IMeta)criteria.list().get(0);
							if (headingMetaEntity != null) {
								IMeta clonedHeadingMetaEntity = headingMetaEntity.clone();
								clonedHeadingMetaEntity.setColumnName(mappedColumnLabel);
								clonedHeadingMetaEntity.setParent(headingMetaEntity);
								this.savePackageMap(clonedHeadingMetaEntity, session, bean.getPackageId(), bean.getSubPackageId());
								//session.save(clonedHeadingMetaEntity);
								//getSubPkgMappingRules(clonedHeadingMetaEntity.getId(), bean, session);
							}
						} else {
							criteria = session.createCriteria(this.getMetaClass())
								.add(Restrictions.eq("columnName", selectedOptionDescription))
								.add(Restrictions.eq("pkg.id", 1L));
							//IMeta parentMeta = (IMeta)criteria.uniqueResult();
							IMeta parentMeta = null;
							if(criteria.list() != null && !criteria.list().isEmpty())
								parentMeta = (IMeta)criteria.list().get(0);
							if (parentMeta.getId() != headingMetaEntity.getId()) {
								headingMetaEntity.setParent(parentMeta);
								session.update(headingMetaEntity);
								//getSubPkgMappingRules(headingMetaEntity.getId(), bean, session);
							}
						}
						
						break;
					} else {
						Criteria criteria = session.createCriteria(this.getMetaClass())
							.add(Restrictions.eq("columnName", heading))
							.add(Restrictions.eq("pkg.id", bean.getPackageId()));
						//IMeta meta = (IMeta)criteria.uniqueResult();
						IMeta meta = null;
						if(criteria.list() != null && !criteria.list().isEmpty())
							meta = (IMeta)criteria.list().get(0);
						if (meta != null)
							session.delete(meta);
					}
				}
			}
			if (headingMetaEntity == null) {
				Criteria criteria = session.createCriteria(this.getMetaClass())
					.add(Restrictions.eq("columnName", heading))
					.add(Restrictions.eq("pkg.id", 1L));
				if(criteria.list() != null && !criteria.list().isEmpty())
					headingMetaEntity = (IMeta)criteria.list().get(0);
				//headingMetaEntity = (IMeta)criteria.uniqueResult();
			}
			indexMap.put(new Integer(i), headingMetaEntity);
		}
		bean.resetCollections();
		bean.setHeaderLineProcessed(true);
		return indexMap;
	}
	
	private Map<Integer,IMeta> processHeaderLine(String[] columnArr, String[] dataArr, FileUploadSessionBean bean, Session session) {
		Map<Integer,IMeta> indexMap = new HashMap<Integer, IMeta>();
		List<LabelSelectInputBean> mappedHeadingSelectInputBeanList = bean.getMappedHeadingList();
		if (!CollectionUtils.isEmpty(mappedHeadingSelectInputBeanList)) {
			return this.processHeaderMappings(columnArr, bean, session);
		} else {
			List<String> columnHeadingList = new ArrayList<String>();
			List<String> columnDataList = new ArrayList<String>();
			for (int i = 0; i < columnArr.length; i++) {
				columnHeadingList.add(columnArr[i]);
				try {
					columnDataList.add(dataArr[i]);
				} catch (Exception e) {
					columnDataList.add("");
				}
			}
			List<HeadingColumnMappedBean> availableHeadingList = this.fetchAvailableHeadings(columnHeadingList, bean.getPackageId(), bean.getSubPackageId());
			bean.init(columnHeadingList, columnDataList, availableHeadingList);
			bean.setHeaderLineProcessed(false);
		}
		return indexMap;
	}
	
	private void savePackageMap(IMeta headingMetaEntity, Session session, long packageId, long subPackageId){
		String dType = headingMetaEntity.getDiscriminatorValue();
		Meta meta = null;
		if(dType.equals("com.enterprise.domain.entity.meta.AirCarrierMeta")){
			meta = new AirCarrierMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.AirportMeta")){
			meta = new AirportMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.CityMeta")){
			meta = new CityMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.InvoiceMeta")){
			meta = new InvoiceMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.SeaCarrierMeta")){
			meta = new SeaCarrierMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.SeaportMeta")){
			meta = new SeaportMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.ShipmentMeta")){
			meta = new ShipmentMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.RateMeta")){
			meta = new RateMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.OrderMeta")){
			meta = new OrderMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.CustomMeta")){
			meta = new CustomMeta();
		} else if(dType.equals("com.enterprise.domain.entity.meta.ContainerStatusMeta")){
			meta = new ContainerStatusMeta();
		}
		meta.setColumnName(headingMetaEntity.getColumnName());
		meta.setEntityClass(headingMetaEntity.getEntityClass());
		meta.setEntityGetter(headingMetaEntity.getEntityGetter());
		meta.setEntitySetter(headingMetaEntity.getEntitySetter());
		meta.setParent(headingMetaEntity.getParent());
		meta.setSubType(headingMetaEntity.getSubType());
		com.enterprise.domain.entity.Package pkg = new com.enterprise.domain.entity.Package();
		pkg.setId(packageId);
		SubPackageDetail subPackage = new SubPackageDetail();
		subPackage.setId(subPackageId);
		//subPackage.setPkg(pkg);
		meta.setPkg(pkg);
		meta.setSubPkg(subPackage);
		session.save(meta);
	}
	
	private void newSubpkgdetails(FileUploadSessionBean bean, Session session){
		Session sessionNew = sessionFactory.openSession();
		SubPackageDetail subPackageDetail = new SubPackageDetail();
		//subPackageDetail.setId(0);
		com.enterprise.domain.entity.Package pkg = new com.enterprise.domain.entity.Package();
		pkg.setId(bean.getPackageId());
		subPackageDetail.setCreatedDate(new Date());
		subPackageDetail.setLastUpdated(new Date());
		long userId;
		if( SecurityContextHolder.getContext().getAuthentication() != null &&
				SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			try {
				DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				userId = sessionUser.getId();
			} catch (Exception e) {
				userId = bean.getUserId();
			}
		} else {
			userId = bean.getUserId();
		}
		User user = new User();
		user.setId(userId);
		subPackageDetail.setCreatedUser(user);
		subPackageDetail.setLastEditedUser(user);
		subPackageDetail.setPkg(pkg);
		if(bean.getLoadType() == 1)
			subPackageDetail.setLoadType(LoadType.S);
		else if(bean.getLoadType() == 2)
			subPackageDetail.setLoadType(LoadType.R);
		else if(bean.getLoadType() == 3)
			subPackageDetail.setLoadType(LoadType.I);
		else if(bean.getLoadType() == 4)
			subPackageDetail.setLoadType(LoadType.O);
		else if(bean.getLoadType() == 5)
			subPackageDetail.setLoadType(LoadType.C);
		else if(bean.getLoadType() == 6)
			subPackageDetail.setLoadType(LoadType.CS);
		sessionNew.saveOrUpdate(subPackageDetail);
		bean.setSubPackageId(subPackageDetail.getId());
		sessionNew.close();
	}
	
	public void setSubPkgID(long id, long subPkgID, Session session) {
		String hql = "UPDATE Meta set subPkg.id = :subPkgID "  + 
         "WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("subPkgID", subPkgID);
		query.executeUpdate();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public void getSubPkgMappingRules(Long metaID, FileUploadSessionBean bean, Session session){
		//System.out.println("-----       map       -----"+addedRows++);
		List<SubPkgMappingRule> subPkgMappingRule= null;
		Criteria criteria = session.createCriteria(SubPkgMappingRule.class)
		.createAlias("meta", "m")
		.setFetchMode("meta", FetchMode.EAGER)
		.add(Restrictions.eq("m.id", 96452L));
		subPkgMappingRule = criteria.list();
		if(subPkgMappingRule!=null && subPkgMappingRule.size()>0){
			Map<Long, ISubPkgMappingRule> ruleMap = new HashMap();
			SubPkgMappingRule mappingRule = subPkgMappingRule.get(0);
			ruleMap.put(mappingRule.getId(), mappingRule);
			bean.setRuleMap(ruleMap);
			//SubPkgMappingRule subPkgMappingRule2 = (SubPkgMappingRule)bean.getRuleMap();
			//System.out.println("----------"+subPkgMappingRule2);
		}
	}
}