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
 * <p>Classe Java per otherRightsDocumentationIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="otherRightsDocumentationIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}otherRightsDocumentationIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}otherRightsDocumentationIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}otherRightsDocumentationRole" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "otherRightsDocumentationIdentifierComplexType", propOrder = {
    "otherRightsDocumentationIdentifierType",
    "otherRightsDocumentationIdentifierValue",
    "otherRightsDocumentationRole"
})
public class OtherRightsDocumentationIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority otherRightsDocumentationIdentifierType;
    @XmlElement(required = true)
    protected String otherRightsDocumentationIdentifierValue;
    protected StringPlusAuthority otherRightsDocumentationRole;

    /**
     * Recupera il valore della proprietà otherRightsDocumentationIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getOtherRightsDocumentationIdentifierType() {
        return otherRightsDocumentationIdentifierType;
    }

    /**
     * Imposta il valore della proprietà otherRightsDocumentationIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setOtherRightsDocumentationIdentifierType(StringPlusAuthority value) {
        this.otherRightsDocumentationIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà otherRightsDocumentationIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherRightsDocumentationIdentifierValue() {
        return otherRightsDocumentationIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà otherRightsDocumentationIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherRightsDocumentationIdentifierValue(String value) {
        this.otherRightsDocumentationIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà otherRightsDocumentationRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getOtherRightsDocumentationRole() {
        return otherRightsDocumentationRole;
    }

    /**
     * Imposta il valore della proprietà otherRightsDocumentationRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setOtherRightsDocumentationRole(StringPlusAuthority value) {
        this.otherRightsDocumentationRole = value;
    }

}
