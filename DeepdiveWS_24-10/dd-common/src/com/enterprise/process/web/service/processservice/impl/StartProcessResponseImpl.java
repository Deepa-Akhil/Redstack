//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 05:48:17 PM GMT 
//


package com.enterprise.process.web.service.processservice.impl;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.enterprise.process.web.service.processservice.StartProcessResponse;
import com.enterprise.web.service.globaldictionary.RequestStatusElement;
import com.enterprise.web.service.globaldictionary.impl.RequestStatusElementImpl;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "referenceNumber",
    "requestStatus"
})
@XmlRootElement(name = "StartProcessResponse", namespace = "http://com/enterprise/process/web/service/ProcessService")
public class StartProcessResponseImpl
    implements Serializable, StartProcessResponse
{

    private final static long serialVersionUID = 3099325368888314454L;
    @XmlElement(required = true)
    protected String referenceNumber;
    @XmlElement(required = true, type = RequestStatusElementImpl.class)
    protected RequestStatusElementImpl requestStatus;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    public RequestStatusElement getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusElement value) {
        this.requestStatus = ((RequestStatusElementImpl) value);
    }

}
