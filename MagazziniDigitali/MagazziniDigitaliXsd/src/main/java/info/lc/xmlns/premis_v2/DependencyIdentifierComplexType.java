//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.29 at 08:41:10 PM CEST 
//


package info.lc.xmlns.premis_v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dependencyIdentifierComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dependencyIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}dependencyIdentifierType"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}dependencyIdentifierValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dependencyIdentifierComplexType", propOrder = {
    "dependencyIdentifierType",
    "dependencyIdentifierValue"
})
public class DependencyIdentifierComplexType {

    @XmlElement(required = true)
    protected String dependencyIdentifierType;
    @XmlElement(required = true)
    protected String dependencyIdentifierValue;

    /**
     * Gets the value of the dependencyIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependencyIdentifierType() {
        return dependencyIdentifierType;
    }

    /**
     * Sets the value of the dependencyIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependencyIdentifierType(String value) {
        this.dependencyIdentifierType = value;
    }

    /**
     * Gets the value of the dependencyIdentifierValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependencyIdentifierValue() {
        return dependencyIdentifierValue;
    }

    /**
     * Sets the value of the dependencyIdentifierValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependencyIdentifierValue(String value) {
        this.dependencyIdentifierValue = value;
    }

}
