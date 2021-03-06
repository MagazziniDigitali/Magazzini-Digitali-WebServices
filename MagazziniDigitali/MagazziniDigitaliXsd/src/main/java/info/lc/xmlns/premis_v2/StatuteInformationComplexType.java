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
 * <p>Java class for statuteInformationComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statuteInformationComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}statuteJurisdiction"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}statuteCitation"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}statuteInformationDeterminationDate" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}statuteNote" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}statuteDocumentationIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}statuteApplicableDates" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statuteInformationComplexType", propOrder = {
    "statuteJurisdiction",
    "statuteCitation",
    "statuteInformationDeterminationDate",
    "statuteNote",
    "statuteDocumentationIdentifier",
    "statuteApplicableDates"
})
public class StatuteInformationComplexType {

    @XmlElement(required = true)
    protected String statuteJurisdiction;
    @XmlElement(required = true)
    protected String statuteCitation;
    protected String statuteInformationDeterminationDate;
    protected List<String> statuteNote;
    protected List<StatuteDocumentationIdentifierComplexType> statuteDocumentationIdentifier;
    protected StartAndEndDateComplexType statuteApplicableDates;

    /**
     * Gets the value of the statuteJurisdiction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatuteJurisdiction() {
        return statuteJurisdiction;
    }

    /**
     * Sets the value of the statuteJurisdiction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatuteJurisdiction(String value) {
        this.statuteJurisdiction = value;
    }

    /**
     * Gets the value of the statuteCitation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatuteCitation() {
        return statuteCitation;
    }

    /**
     * Sets the value of the statuteCitation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatuteCitation(String value) {
        this.statuteCitation = value;
    }

    /**
     * Gets the value of the statuteInformationDeterminationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatuteInformationDeterminationDate() {
        return statuteInformationDeterminationDate;
    }

    /**
     * Sets the value of the statuteInformationDeterminationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatuteInformationDeterminationDate(String value) {
        this.statuteInformationDeterminationDate = value;
    }

    /**
     * Gets the value of the statuteNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statuteNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatuteNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getStatuteNote() {
        if (statuteNote == null) {
            statuteNote = new ArrayList<String>();
        }
        return this.statuteNote;
    }

    /**
     * Gets the value of the statuteDocumentationIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statuteDocumentationIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatuteDocumentationIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatuteDocumentationIdentifierComplexType }
     * 
     * 
     */
    public List<StatuteDocumentationIdentifierComplexType> getStatuteDocumentationIdentifier() {
        if (statuteDocumentationIdentifier == null) {
            statuteDocumentationIdentifier = new ArrayList<StatuteDocumentationIdentifierComplexType>();
        }
        return this.statuteDocumentationIdentifier;
    }

    /**
     * Gets the value of the statuteApplicableDates property.
     * 
     * @return
     *     possible object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public StartAndEndDateComplexType getStatuteApplicableDates() {
        return statuteApplicableDates;
    }

    /**
     * Sets the value of the statuteApplicableDates property.
     * 
     * @param value
     *     allowed object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public void setStatuteApplicableDates(StartAndEndDateComplexType value) {
        this.statuteApplicableDates = value;
    }

}
