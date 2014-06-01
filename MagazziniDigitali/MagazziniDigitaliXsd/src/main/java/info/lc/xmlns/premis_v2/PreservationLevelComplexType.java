//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.27 at 04:47:07 PM CEST 
//


package info.lc.xmlns.premis_v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preservationLevelComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="preservationLevelComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}preservationLevelValue"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}preservationLevelRole" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}preservationLevelRationale" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}preservationLevelDateAssigned" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preservationLevelComplexType", propOrder = {
    "preservationLevelValue",
    "preservationLevelRole",
    "preservationLevelRationale",
    "preservationLevelDateAssigned"
})
public class PreservationLevelComplexType {

    @XmlElement(required = true)
    protected String preservationLevelValue;
    protected String preservationLevelRole;
    protected List<String> preservationLevelRationale;
    protected String preservationLevelDateAssigned;

    /**
     * Gets the value of the preservationLevelValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreservationLevelValue() {
        return preservationLevelValue;
    }

    /**
     * Sets the value of the preservationLevelValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreservationLevelValue(String value) {
        this.preservationLevelValue = value;
    }

    /**
     * Gets the value of the preservationLevelRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreservationLevelRole() {
        return preservationLevelRole;
    }

    /**
     * Sets the value of the preservationLevelRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreservationLevelRole(String value) {
        this.preservationLevelRole = value;
    }

    /**
     * Gets the value of the preservationLevelRationale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preservationLevelRationale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreservationLevelRationale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPreservationLevelRationale() {
        if (preservationLevelRationale == null) {
            preservationLevelRationale = new ArrayList<String>();
        }
        return this.preservationLevelRationale;
    }

    /**
     * Gets the value of the preservationLevelDateAssigned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreservationLevelDateAssigned() {
        return preservationLevelDateAssigned;
    }

    /**
     * Sets the value of the preservationLevelDateAssigned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreservationLevelDateAssigned(String value) {
        this.preservationLevelDateAssigned = value;
    }

}
