package com.enterprise.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.util.CollectionUtils;

import com.enterprise.common.annotation.SessionObject;
import com.enterprise.common.entity.IMeta;
import com.enterprise.common.entity.ISubPkgMappingRule;
import com.enterprise.common.util.StringUtils;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  04 Mar 2014 6:34:56 AM
 * @author rafourie
 */
@SessionObject(id = "fileUploadSessionBean")
public class FileUploadSessionBean implements Serializable {
	private static final long serialVersionUID = 2449149568182856161L;
	private String fileName;
	private String content;
	private String javascript;
	private List<LabelSelectInputBean> mappedHeadingList;
	private List<HeadingColumnMappedBean> availableHeadingList;
	private List<String> errorList;
	private List<String> infoMsgList;
	private String requestMapping;
	private int currentLine;
	private int totalLines;
	private int batchSize;
	private int batchStartLine;
	private boolean headerLineProcessed;
	private Map<Integer,IMeta> indexMap;
	private Map<Long,ISubPkgMappingRule> ruleMap;
	private boolean fileProcessed;
	private List<String> warningList;
	private UUID uuid;
	private long packageId;
	private long subPackageId;
	private String lastEditUser;
	private String lastUpdateOn;
	private String pkgName;
	private double successShipments = 0f;
	private double failedShipment = 0f;
	//loadType = 1 for Shipment, 2 for Rate, 3 for Invoice, 4 for Order
	private int loadType = 1;
	private Set<Long> rateIds = new HashSet<Long>();
	private Set<Long> rateDetailIds = new HashSet<Long>();
	private boolean isAliaseMatchingEnabled = false;
	private boolean isSpecialChar = false;
	private boolean isLocationAliaseEnabled = false;
	private long userId;
	private boolean mailProcess = false;
	private boolean isConflictChecked = false;
	private boolean isRateProfileReset = false;
	private boolean isCSVFile = false;
	
	public void reset() {
		fileName = content = javascript = requestMapping = null;
		mappedHeadingList = null;
		availableHeadingList = null;
		errorList = infoMsgList = null;
		totalLines = batchSize = batchStartLine = currentLine = 0;
		headerLineProcessed = fileProcessed = false;
		indexMap = null;
		warningList = null;
		uuid = null;
		successShipments = 0f;failedShipment = 0f;
		fileProcessed = true;
		rateIds = new HashSet<Long>();
		rateDetailIds = new HashSet<Long>();
		//setPackageId(0L);
	}
	
	public void resetCollections() {
		mappedHeadingList = null;
		availableHeadingList = null;
		errorList = infoMsgList = null;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	public boolean isHasUnknownHeadings() {
		if (!CollectionUtils.isEmpty(this.mappedHeadingList))
			return true;
		return false;
	}
	
	public boolean isHasErrors() {
		if (!CollectionUtils.isEmpty(this.errorList))
			return true;
		return false;
	}
	
	public boolean isHasWarnings() {
		if (!CollectionUtils.isEmpty(this.warningList))
			return true;
		return false;
	}

	public List<LabelSelectInputBean> getMappedHeadingList() {
		return mappedHeadingList;
	}
	
	private List<HeadingColumnMappedBean> filterAvailable(
			List<String> columnHeadingList, List<HeadingColumnMappedBean> availableHeadingList) {
		List<String> mappedByDefaultList = new ArrayList<String>();
		for (String heading : columnHeadingList)
			for (HeadingColumnMappedBean bean : availableHeadingList)
				if (heading.equals(bean.getHeading()))
					mappedByDefaultList.add(heading);
		availableHeadingList.removeAll(mappedByDefaultList);
		return availableHeadingList;
	}
	
	public void init(List<String> columnHeadingList, List<String> columnDataList, List<HeadingColumnMappedBean> availableHeadingList) {
		this.availableHeadingList = availableHeadingList;
		this.mappedHeadingList = new ArrayList<LabelSelectInputBean>();
		//availableHeadingList = this.filterAvailable(columnHeadingList, availableHeadingList);
		for (int i = 0; i < columnHeadingList.size(); i++) {
			String column = columnHeadingList.get(i);
			String data = columnDataList.get(i);
			this.mappedHeadingList.add(new LabelSelectInputBean(column, data, availableHeadingList));
		}
		this.reorg();
		/*this.mappedHeadingList = new ArrayList<LabelSelectInputBean>();
		for (String column : columnHeadingList)
			this.mappedHeadingList.add(new LabelSelectInputBean(column, availableHeadingList));*/
		/*this.availableHeadingList = availableHeadingList;
		if (!CollectionUtils.isEmpty(unknownHeadingList)) {
			this.mappedHeadingList = new ArrayList<LabelSelectInputBean>();
			for (String heading : unknownHeadingList)
				this.mappedHeadingList.add(new LabelSelectInputBean(heading, availableHeadingList));
		}*/
	}
	
	public void reorg() {
		this.clearAllOptionsAndPopulateWithDefault();
		List<String> selectedValuesList = this.getAllSelectedValuesFromSelectInputBeanCollection();
		for (String string : selectedValuesList) {
			for (LabelSelectInputBean labelSelectInputBean : mappedHeadingList) {
				SelectInputBean selectInputBean = labelSelectInputBean.getSelectInputBean();
				MandatoryValueElement descValueElement = selectInputBean.getValueElement();
				String selectedDesc = descValueElement.getValue();
				if (!string.equals(selectedDesc))
					this.removeUnselectedOption(string);
			}
		}
	}
	
	private void clearAllOptionsAndPopulateWithDefault() {
		if (!CollectionUtils.isEmpty(mappedHeadingList)) {
			for (LabelSelectInputBean labelSelectInputBean : mappedHeadingList) {
				SelectInputBean selectInputBean = labelSelectInputBean.getSelectInputBean();
				String selectedOptionDesc = selectInputBean.getSelectedOptionDescription();
				selectInputBean = labelSelectInputBean.newSelectInputBean(availableHeadingList);
				if (!StringUtils.isEmpty(selectedOptionDesc))
					selectInputBean.setSelectedOption(selectedOptionDesc, selectedOptionDesc, false);
				labelSelectInputBean.setSelectInputBean(selectInputBean);
			}
		}
	}
	
	private List<String> getAllSelectedValuesFromSelectInputBeanCollection() {
		List<String> selectedValuesList = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(mappedHeadingList)) {
			for (LabelSelectInputBean labelSelectInputBean : mappedHeadingList) {
				SelectInputBean selectInputBean = labelSelectInputBean.getSelectInputBean();
				MandatoryValueElement descValueElement = selectInputBean.getValueElement();
				String descValue = descValueElement.getValue();
				if (!StringUtils.isEmpty(descValue)) 
					selectedValuesList.add(descValue);
			}
		}
		return selectedValuesList;
	}
	
	private void removeUnselectedOption(String value) {
		if (!CollectionUtils.isEmpty(mappedHeadingList)) {
			for (LabelSelectInputBean bean : mappedHeadingList)
				bean.removeUnselectedOption(value);
		}
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public void setRequestMapping(String requestMapping) {
		this.requestMapping = requestMapping;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getBatchStartLine() {
		return batchStartLine;
	}

	public void setBatchStartLine(int batchStartLine) {
		this.batchStartLine = batchStartLine;
	}

	public boolean isHeaderLineProcessed() {
		return headerLineProcessed;
	}

	public void setHeaderLineProcessed(boolean headerLineProcessed) {
		this.headerLineProcessed = headerLineProcessed;
	}

	public Map<Integer, IMeta> getIndexMap() {
		return indexMap;
	}

	public void setIndexMap(Map<Integer, IMeta> indexMap) {
		this.indexMap = indexMap;
	}

	public int getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}

	public boolean isFileProcessed() {
		return fileProcessed;
	}

	public void setFileProcessed(boolean fileProcessed) {
		this.fileProcessed = fileProcessed;
	}
	
	public void addErrorMsg(String error) {
		this.errorList = (this.errorList == null) ? new ArrayList<String>() : this.errorList;
		this.errorList.add(error);
	}
	
	public void addWarningMsg(String warning) {
		this.warningList = (this.warningList == null) ? new ArrayList<String>() : this.warningList;
		if (!StringUtils.isEmpty(StringUtils.trim(warning)))
			this.warningList.add(warning);
	}

	public List<String> getWarningList() {
		return warningList;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public List<String> getInfoMsgList() {
		return infoMsgList;
	}
	
	public void addInfoMsg(String msg) {
		this.infoMsgList = (this.infoMsgList == null) ? new ArrayList<String>() : this.infoMsgList;
		this.infoMsgList.add(msg);
	}

	public long getPackageId() {
		return packageId;
	}

	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}

	public String getLastEditUser() {
		return lastEditUser;
	}

	public void setLastEditUser(String lastEditUser) {
		this.lastEditUser = lastEditUser;
	}

	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public double getSuccessShipments() {
		return successShipments;
	}

	public void setSuccessShipments(double successShipments) {
		this.successShipments = successShipments;
	}

	public double getFailedShipment() {
		return failedShipment;
	}

	public void setFailedShipment(double failedShipment) {
		this.failedShipment = failedShipment;
	}

	public Set<Long> getRateIds() {
		return rateIds;
	}

	public void setRateIds(Set<Long> rateIds) {
		this.rateIds = rateIds;
	}

	public Set<Long> getRateDetailIds() {
		return rateDetailIds;
	}

	public void setRateDetailIds(Set<Long> rateDetailIds) {
		this.rateDetailIds = rateDetailIds;
	}

	public boolean isAliaseMatchingEnabled() {
		return isAliaseMatchingEnabled;
	}

	public void setAliaseMatchingEnabled(boolean isAliaseMatchingEnabled) {
		this.isAliaseMatchingEnabled = isAliaseMatchingEnabled;
	}

	public int getLoadType() {
		return loadType;
	}

	public void setLoadType(int loadType) {
		this.loadType = loadType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isMailProcess() {
		return mailProcess;
	}

	public void setMailProcess(boolean mailProcess) {
		this.mailProcess = mailProcess;
	}

	public long getSubPackageId() {
		return subPackageId;
	}

	public void setSubPackageId(long subPackageId) {
		this.subPackageId = subPackageId;
	}

	public boolean isConflictChecked() {
		return isConflictChecked;
	}

	public void setConflictChecked(boolean isConflictChecked) {
		this.isConflictChecked = isConflictChecked;
	}

	public Map<Long, ISubPkgMappingRule> getRuleMap() {
		return ruleMap;
	}

	public void setRuleMap(Map<Long, ISubPkgMappingRule> ruleMap) {
		this.ruleMap = ruleMap;
	}

	public void setRateProfileReset(boolean isRateProfileReset) {
		this.isRateProfileReset = isRateProfileReset;
	}

	public boolean isRateProfileReset() {
		return isRateProfileReset;
	}

	public void setLocationAliaseEnabled(boolean isLocationAliaseEnabled) {
		this.isLocationAliaseEnabled = isLocationAliaseEnabled;
	}

	public boolean isLocationAliaseEnabled() {
		return isLocationAliaseEnabled;
	}

	public void setSpecialChar(boolean isSpecialChar) {
		this.isSpecialChar = isSpecialChar;
	}

	public boolean isSpecialChar() {
		return isSpecialChar;
	}

	public void setCSVFile(boolean isCSVFile) {
		this.isCSVFile = isCSVFile;
	}

	public boolean isCSVFile() {
		return isCSVFile;
	}
}