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
 * <p>Classe Java per copyrightDocumentationIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="copyrightDocumentationIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightDocumentationIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightDocumentationIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}copyrightDocumentationRole" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "copyrightDocumentationIdentifierComplexType", propOrder = {
    "copyrightDocumentationIdentifierType",
    "copyrightDocumentationIdentifierValue",
    "copyrightDocumentationRole"
})
public class CopyrightDocumentationIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority copyrightDocumentationIdentifierType;
    @XmlElement(required = true)
    protected String copyrightDocumentationIdentifierValue;
    protected StringPlusAuthority copyrightDocumentationRole;

    /**
     * Recupera il valore della proprietà copyrightDocumentationIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getCopyrightDocumentationIdentifierType() {
        return copyrightDocumentationIdentifierType;
    }

    /**
     * Imposta il valore della proprietà copyrightDocumentationIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setCopyrightDocumentationIdentifierType(StringPlusAuthority value) {
        this.copyrightDocumentationIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà copyrightDocumentationIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyrightDocumentationIdentifierValue() {
        return copyrightDocumentationIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà copyrightDocumentationIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyrightDocumentationIdentifierValue(String value) {
        this.copyrightDocumentationIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà copyrightDocumentationRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getCopyrightDocumentationRole() {
        return copyrightDocumentationRole;
    }

    /**
     * Imposta il valore della proprietà copyrightDocumentationRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setCopyrightDocumentationRole(StringPlusAuthority value) {
        this.copyrightDocumentationRole = value;
    }

}
