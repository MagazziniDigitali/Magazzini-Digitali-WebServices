//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per licenseDocumentationIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="licenseDocumentationIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}licenseDocumentationIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}licenseDocumentationIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}licenseDocumentationRole" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "licenseDocumentationIdentifierComplexType", propOrder = {
    "licenseDocumentationIdentifierType",
    "licenseDocumentationIdentifierValue",
    "licenseDocumentationRole"
})
public class LicenseDocumentationIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority licenseDocumentationIdentifierType;
    @XmlElement(required = true)
    protected String licenseDocumentationIdentifierValue;
    protected StringPlusAuthority licenseDocumentationRole;

    /**
     * Recupera il valore della proprietà licenseDocumentationIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getLicenseDocumentationIdentifierType() {
        return licenseDocumentationIdentifierType;
    }

    /**
     * Imposta il valore della proprietà licenseDocumentationIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setLicenseDocumentationIdentifierType(StringPlusAuthority value) {
        this.licenseDocumentationIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà licenseDocumentationIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseDocumentationIdentifierValue() {
        return licenseDocumentationIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà licenseDocumentationIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseDocumentationIdentifierValue(String value) {
        this.licenseDocumentationIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà licenseDocumentationRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getLicenseDocumentationRole() {
        return licenseDocumentationRole;
    }

    /**
     * Imposta il valore della proprietà licenseDocumentationRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setLicenseDocumentationRole(StringPlusAuthority value) {
        this.licenseDocumentationRole = value;
    }

}
