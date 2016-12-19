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
 * <p>Classe Java per formatDesignationComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="formatDesignationComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}formatName"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}formatVersion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formatDesignationComplexType", propOrder = {
    "formatName",
    "formatVersion"
})
public class FormatDesignationComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority formatName;
    protected String formatVersion;

    /**
     * Recupera il valore della proprietà formatName.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getFormatName() {
        return formatName;
    }

    /**
     * Imposta il valore della proprietà formatName.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setFormatName(StringPlusAuthority value) {
        this.formatName = value;
    }

    /**
     * Recupera il valore della proprietà formatVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatVersion() {
        return formatVersion;
    }

    /**
     * Imposta il valore della proprietà formatVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatVersion(String value) {
        this.formatVersion = value;
    }

}
