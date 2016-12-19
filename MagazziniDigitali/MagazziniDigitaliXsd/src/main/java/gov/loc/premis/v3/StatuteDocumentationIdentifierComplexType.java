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
 * <p>Classe Java per statuteDocumentationIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="statuteDocumentationIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}statuteDocumentationIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}statuteDocumentationIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}statuteDocumentationRole" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statuteDocumentationIdentifierComplexType", propOrder = {
    "statuteDocumentationIdentifierType",
    "statuteDocumentationIdentifierValue",
    "statuteDocumentationRole"
})
public class StatuteDocumentationIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority statuteDocumentationIdentifierType;
    @XmlElement(required = true)
    protected String statuteDocumentationIdentifierValue;
    protected StringPlusAuthority statuteDocumentationRole;

    /**
     * Recupera il valore della proprietà statuteDocumentationIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getStatuteDocumentationIdentifierType() {
        return statuteDocumentationIdentifierType;
    }

    /**
     * Imposta il valore della proprietà statuteDocumentationIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setStatuteDocumentationIdentifierType(StringPlusAuthority value) {
        this.statuteDocumentationIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà statuteDocumentationIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatuteDocumentationIdentifierValue() {
        return statuteDocumentationIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà statuteDocumentationIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatuteDocumentationIdentifierValue(String value) {
        this.statuteDocumentationIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà statuteDocumentationRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getStatuteDocumentationRole() {
        return statuteDocumentationRole;
    }

    /**
     * Imposta il valore della proprietà statuteDocumentationRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setStatuteDocumentationRole(StringPlusAuthority value) {
        this.statuteDocumentationRole = value;
    }

}
