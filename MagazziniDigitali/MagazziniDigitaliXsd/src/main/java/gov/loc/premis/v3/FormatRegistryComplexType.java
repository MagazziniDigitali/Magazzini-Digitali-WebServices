//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per formatRegistryComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="formatRegistryComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}formatRegistryName"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}formatRegistryKey"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}formatRegistryRole" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="simpleLink" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formatRegistryComplexType", propOrder = {
    "formatRegistryName",
    "formatRegistryKey",
    "formatRegistryRole"
})
public class FormatRegistryComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority formatRegistryName;
    @XmlElement(required = true)
    protected StringPlusAuthority formatRegistryKey;
    protected StringPlusAuthority formatRegistryRole;
    @XmlAttribute(name = "simpleLink")
    @XmlSchemaType(name = "anyURI")
    protected String simpleLink;

    /**
     * Recupera il valore della proprietà formatRegistryName.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getFormatRegistryName() {
        return formatRegistryName;
    }

    /**
     * Imposta il valore della proprietà formatRegistryName.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setFormatRegistryName(StringPlusAuthority value) {
        this.formatRegistryName = value;
    }

    /**
     * Recupera il valore della proprietà formatRegistryKey.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getFormatRegistryKey() {
        return formatRegistryKey;
    }

    /**
     * Imposta il valore della proprietà formatRegistryKey.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setFormatRegistryKey(StringPlusAuthority value) {
        this.formatRegistryKey = value;
    }

    /**
     * Recupera il valore della proprietà formatRegistryRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getFormatRegistryRole() {
        return formatRegistryRole;
    }

    /**
     * Imposta il valore della proprietà formatRegistryRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setFormatRegistryRole(StringPlusAuthority value) {
        this.formatRegistryRole = value;
    }

    /**
     * Recupera il valore della proprietà simpleLink.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimpleLink() {
        return simpleLink;
    }

    /**
     * Imposta il valore della proprietà simpleLink.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimpleLink(String value) {
        this.simpleLink = value;
    }

}
