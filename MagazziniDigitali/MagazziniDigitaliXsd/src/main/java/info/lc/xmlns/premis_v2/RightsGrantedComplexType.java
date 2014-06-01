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
 * <p>Java class for rightsGrantedComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rightsGrantedComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}act"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}restriction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}termOfGrant" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}termOfRestriction" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}rightsGrantedNote" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rightsGrantedComplexType", propOrder = {
    "act",
    "restriction",
    "termOfGrant",
    "termOfRestriction",
    "rightsGrantedNote"
})
public class RightsGrantedComplexType {

    @XmlElement(required = true)
    protected String act;
    protected List<String> restriction;
    protected StartAndEndDateComplexType termOfGrant;
    protected StartAndEndDateComplexType termOfRestriction;
    protected List<String> rightsGrantedNote;

    /**
     * Gets the value of the act property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAct() {
        return act;
    }

    /**
     * Sets the value of the act property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAct(String value) {
        this.act = value;
    }

    /**
     * Gets the value of the restriction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the restriction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestriction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRestriction() {
        if (restriction == null) {
            restriction = new ArrayList<String>();
        }
        return this.restriction;
    }

    /**
     * Gets the value of the termOfGrant property.
     * 
     * @return
     *     possible object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public StartAndEndDateComplexType getTermOfGrant() {
        return termOfGrant;
    }

    /**
     * Sets the value of the termOfGrant property.
     * 
     * @param value
     *     allowed object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public void setTermOfGrant(StartAndEndDateComplexType value) {
        this.termOfGrant = value;
    }

    /**
     * Gets the value of the termOfRestriction property.
     * 
     * @return
     *     possible object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public StartAndEndDateComplexType getTermOfRestriction() {
        return termOfRestriction;
    }

    /**
     * Sets the value of the termOfRestriction property.
     * 
     * @param value
     *     allowed object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public void setTermOfRestriction(StartAndEndDateComplexType value) {
        this.termOfRestriction = value;
    }

    /**
     * Gets the value of the rightsGrantedNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rightsGrantedNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRightsGrantedNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRightsGrantedNote() {
        if (rightsGrantedNote == null) {
            rightsGrantedNote = new ArrayList<String>();
        }
        return this.rightsGrantedNote;
    }

}
