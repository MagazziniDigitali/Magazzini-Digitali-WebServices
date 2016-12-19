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
 * <p>Classe Java per fixityComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="fixityComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}messageDigestAlgorithm"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}messageDigest"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}messageDigestOriginator" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fixityComplexType", propOrder = {
    "messageDigestAlgorithm",
    "messageDigest",
    "messageDigestOriginator"
})
public class FixityComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority messageDigestAlgorithm;
    @XmlElement(required = true)
    protected String messageDigest;
    protected StringPlusAuthority messageDigestOriginator;

    /**
     * Recupera il valore della proprietà messageDigestAlgorithm.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getMessageDigestAlgorithm() {
        return messageDigestAlgorithm;
    }

    /**
     * Imposta il valore della proprietà messageDigestAlgorithm.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setMessageDigestAlgorithm(StringPlusAuthority value) {
        this.messageDigestAlgorithm = value;
    }

    /**
     * Recupera il valore della proprietà messageDigest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageDigest() {
        return messageDigest;
    }

    /**
     * Imposta il valore della proprietà messageDigest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageDigest(String value) {
        this.messageDigest = value;
    }

    /**
     * Recupera il valore della proprietà messageDigestOriginator.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getMessageDigestOriginator() {
        return messageDigestOriginator;
    }

    /**
     * Imposta il valore della proprietà messageDigestOriginator.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setMessageDigestOriginator(StringPlusAuthority value) {
        this.messageDigestOriginator = value;
    }

}
