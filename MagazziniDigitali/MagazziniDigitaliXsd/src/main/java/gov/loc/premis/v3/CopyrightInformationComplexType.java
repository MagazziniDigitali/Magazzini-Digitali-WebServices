//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per copyrightInformationComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="copyrightInformationComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightStatus"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightJurisdiction"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightStatusDeterminationDate" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightNote" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightDocumentationIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightApplicableDates" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "copyrightInformationComplexType", propOrder = {
    "copyrightStatus",
    "copyrightJurisdiction",
    "copyrightStatusDeterminationDate",
    "copyrightNote",
    "copyrightDocumentationIdentifier",
    "copyrightApplicableDates"
})
public class CopyrightInformationComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority copyrightStatus;
    @XmlElement(required = true)
    protected CountryCode copyrightJurisdiction;
    protected String copyrightStatusDeterminationDate;
    protected List<String> copyrightNote;
    protected List<CopyrightDocumentationIdentifierComplexType> copyrightDocumentationIdentifier;
    protected StartAndEndDateComplexType copyrightApplicableDates;

    /**
     * Recupera il valore della proprietà copyrightStatus.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getCopyrightStatus() {
        return copyrightStatus;
    }

    /**
     * Imposta il valore della proprietà copyrightStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setCopyrightStatus(StringPlusAuthority value) {
        this.copyrightStatus = value;
    }

    /**
     * Recupera il valore della proprietà copyrightJurisdiction.
     * 
     * @return
     *     possible object is
     *     {@link CountryCode }
     *     
     */
    public CountryCode getCopyrightJurisdiction() {
        return copyrightJurisdiction;
    }

    /**
     * Imposta il valore della proprietà copyrightJurisdiction.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCode }
     *     
     */
    public void setCopyrightJurisdiction(CountryCode value) {
        this.copyrightJurisdiction = value;
    }

    /**
     * Recupera il valore della proprietà copyrightStatusDeterminationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyrightStatusDeterminationDate() {
        return copyrightStatusDeterminationDate;
    }

    /**
     * Imposta il valore della proprietà copyrightStatusDeterminationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyrightStatusDeterminationDate(String value) {
        this.copyrightStatusDeterminationDate = value;
    }

    /**
     * Gets the value of the copyrightNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copyrightNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopyrightNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCopyrightNote() {
        if (copyrightNote == null) {
            copyrightNote = new ArrayList<String>();
        }
        return this.copyrightNote;
    }

    /**
     * Gets the value of the copyrightDocumentationIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copyrightDocumentationIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopyrightDocumentationIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CopyrightDocumentationIdentifierComplexType }
     * 
     * 
     */
    public List<CopyrightDocumentationIdentifierComplexType> getCopyrightDocumentationIdentifier() {
        if (copyrightDocumentationIdentifier == null) {
            copyrightDocumentationIdentifier = new ArrayList<CopyrightDocumentationIdentifierComplexType>();
        }
        return this.copyrightDocumentationIdentifier;
    }

    /**
     * Recupera il valore della proprietà copyrightApplicableDates.
     * 
     * @return
     *     possible object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public StartAndEndDateComplexType getCopyrightApplicableDates() {
        return copyrightApplicableDates;
    }

    /**
     * Imposta il valore della proprietà copyrightApplicableDates.
     * 
     * @param value
     *     allowed object is
     *     {@link StartAndEndDateComplexType }
     *     
     */
    public void setCopyrightApplicableDates(StartAndEndDateComplexType value) {
        this.copyrightApplicableDates = value;
    }

}
