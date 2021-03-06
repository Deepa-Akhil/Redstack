//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 05:48:17 PM GMT 
//


package com.enterprise.process.web.service.processservice.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.enterprise.process.web.service.processservice.FetchUserTasksResponse;
import com.enterprise.process.web.service.processservice.TaskSummaryElement;
import com.enterprise.web.service.globaldictionary.RequestStatusElement;
import com.enterprise.web.service.globaldictionary.impl.RequestStatusElementImpl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "results",
    "requestStatus"
})
@XmlRootElement(name = "FetchUserTasksResponse", namespace = "http://com/enterprise/process/web/service/ProcessService")
public class FetchUserTasksResponseImpl
    implements Serializable, FetchUserTasksResponse
{

    private final static long serialVersionUID = 3099325368888314454L;
    @XmlElement(type = TaskSummaryElementImpl.class)
    protected List<TaskSummaryElement> results;
    @XmlElement(required = true, type = RequestStatusElementImpl.class)
    protected RequestStatusElementImpl requestStatus;

    public List<TaskSummaryElement> getResults() {
        if (results == null) {
            results = new ArrayList<TaskSummaryElement>();
        }
        return this.results;
    }

    public RequestStatusElement getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusElement value) {
        this.requestStatus = ((RequestStatusElementImpl) value);
    }

}
