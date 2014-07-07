//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.29 at 08:41:10 PM CEST 
//


package info.lc.xmlns.premis_v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hardwareComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hardwareComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}hwName"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}hwType"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}hwOtherInformation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hardwareComplexType", propOrder = {
    "hwName",
    "hwType",
    "hwOtherInformation"
})
public class HardwareComplexType {

    @XmlElement(required = true)
    protected String hwName;
    @XmlElement(required = true)
    protected String hwType;
    protected List<String> hwOtherInformation;

    /**
     * Gets the value of the hwName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHwName() {
        return hwName;
    }

    /**
     * Sets the value of the hwName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHwName(String value) {
        this.hwName = value;
    }

    /**
     * Gets the value of the hwType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHwType() {
        return hwType;
    }

    /**
     * Sets the value of the hwType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHwType(String value) {
        this.hwType = value;
    }

    /**
     * Gets the value of the hwOtherInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hwOtherInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHwOtherInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHwOtherInformation() {
        if (hwOtherInformation == null) {
            hwOtherInformation = new ArrayList<String>();
        }
        return this.hwOtherInformation;
    }

}
