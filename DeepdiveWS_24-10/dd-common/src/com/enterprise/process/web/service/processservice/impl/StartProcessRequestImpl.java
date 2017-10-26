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
import com.enterprise.process.web.service.processservice.ParameterElement;
import com.enterprise.process.web.service.processservice.StartProcessRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "processId",
    "parameters"
})
@XmlRootElement(name = "StartProcessRequest", namespace = "http://com/enterprise/process/web/service/ProcessService")
public class StartProcessRequestImpl
    implements Serializable, StartProcessRequest
{

    private final static long serialVersionUID = 3099325368888314454L;
    @XmlElement(required = true)
    protected String processId;
    @XmlElement(type = ParameterElementImpl.class)
    protected List<ParameterElement> parameters;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String value) {
        this.processId = value;
    }

    public List<ParameterElement> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<ParameterElement>();
        }
        return this.parameters;
    }

}