//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.25 at 07:16:08 AM CAT 
//


package com.enterprise.common.xsd.webelementcomplextypes;

import com.enterprise.common.jaxb.InputElement;


/**
 * <p>Java class for MandatoryEmailInputElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MandatoryEmailInputElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Style" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StyleClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StyleClassAlt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Valid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Value" type="{http://xsd.common.enterprise.com/GlobalDictionary}Email"/>
 *         &lt;element name="Attributes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public interface MandatoryEmailInputElement
    extends InputElement
{


    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getId();

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setId(String value);

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getName();

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setName(String value);

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getType();

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setType(String value);

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getStyle();

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setStyle(String value);

    /**
     * Gets the value of the styleClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getStyleClass();

    /**
     * Sets the value of the styleClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setStyleClass(String value);

    /**
     * Gets the value of the styleClassAlt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getStyleClassAlt();

    /**
     * Sets the value of the styleClassAlt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setStyleClassAlt(String value);

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getTitle();

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setTitle(String value);

    /**
     * Gets the value of the valid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    Boolean isValid();

    /**
     * Sets the value of the valid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    void setValid(Boolean value);

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getError();

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setError(String value);

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getValue();

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setValue(String value);

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    String getAttributes();

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    void setAttributes(String value);

}
