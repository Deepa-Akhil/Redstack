//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 05:48:17 PM GMT 
//


package com.enterprise.process.web.service.processservice;

import com.enterprise.common.xsd.Element;
import com.enterprise.web.service.globaldictionary.DateElement;
import com.enterprise.web.service.globaldictionary.StatusTypesElement;


/**
 * <p>Java class for ProcessActivityElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessActivityElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://com/enterprise/web/service/GlobalDictionary}StatusTypesElement"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://com/enterprise/web/service/GlobalDictionary}DateElement"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public interface ProcessActivityElementType
    extends Element
{


    /**
     * Gets the value of the taskName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getTaskName();

    /**
     * Sets the value of the taskName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setTaskName(String value);

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusTypesElement }
     *     
     */
    StatusTypesElement getStatus();

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusTypesElement }
     *     
     */
    void setStatus(StatusTypesElement value);

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getOwner();

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setOwner(String value);

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link DateElement }
     *     
     */
    DateElement getTimestamp();

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateElement }
     *     
     */
    void setTimestamp(DateElement value);

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getComments();

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setComments(String value);

}
