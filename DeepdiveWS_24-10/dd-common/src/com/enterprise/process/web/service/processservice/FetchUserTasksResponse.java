//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 05:48:17 PM GMT 
//


package com.enterprise.process.web.service.processservice;

import java.util.List;
import com.enterprise.common.xsd.Element;
import com.enterprise.web.service.globaldictionary.RequestStatusElement;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="results" type="{http://com/enterprise/process/web/service/ProcessService}TaskSummaryElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requestStatus" type="{http://com/enterprise/web/service/GlobalDictionary}RequestStatusElement"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public interface FetchUserTasksResponse
    extends Element
{


    /**
     * Gets the value of the results property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the results property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaskSummaryElement }
     * 
     * 
     */
    List<TaskSummaryElement> getResults();

    /**
     * Gets the value of the requestStatus property.
     * 
     * @return
     *     possible object is
     *     {@link RequestStatusElement }
     *     
     */
    RequestStatusElement getRequestStatus();

    /**
     * Sets the value of the requestStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestStatusElement }
     *     
     */
    void setRequestStatus(RequestStatusElement value);

}