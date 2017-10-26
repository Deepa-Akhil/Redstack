package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.IBean;

public class SurveyModuleDataBean implements IBean {
	private static final long serialVersionUID = 3312606357657381982L;
	private SurveyModuleInputBean surveyModuleInputBean = new SurveyModuleInputBean();
	private MultiPageDataContainerBean multiPageDataContainerBean = new MultiPageDataContainerBean(10,500,SurveyRecordBean.class);

	public void reset() {
		surveyModuleInputBean = new SurveyModuleInputBean();
		multiPageDataContainerBean = new MultiPageDataContainerBean(10,500,SurveyRecordBean.class);
	}

	public MultiPageDataContainerBean getMultiPageDataContainerBean() {
		return multiPageDataContainerBean;
	}

	public SurveyModuleInputBean getSurveyModuleInputBean() {
		return surveyModuleInputBean;
	}

}