//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 05:48:17 PM GMT 
//


package com.enterprise.web.service.globaldictionary.impl;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.enterprise.web.service.globaldictionary.RequestStatusElement;
import com.enterprise.web.service.globaldictionary.StatusTypesElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestStatusElement", namespace = "http://com/enterprise/web/service/GlobalDictionary", propOrder = {
    "status",
    "error"
})
public class RequestStatusElementImpl
    implements Serializable, RequestStatusElement
{

    private final static long serialVersionUID = 3099325368888314454L;
    @XmlElement(required = true)
    protected StatusTypesElement status;
    protected String error;

    public StatusTypesElement getStatus() {
        return status;
    }

    public void setStatus(StatusTypesElement value) {
        this.status = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String value) {
        this.error = value;
    }

}
