//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per relatedEventIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="relatedEventIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relatedEventIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relatedEventIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relatedEventSequence" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RelEventXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="simpleLink" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relatedEventIdentifierComplexType", propOrder = {
    "relatedEventIdentifierType",
    "relatedEventIdentifierValue",
    "relatedEventSequence"
})
public class RelatedEventIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority relatedEventIdentifierType;
    @XmlElement(required = true)
    protected String relatedEventIdentifierValue;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger relatedEventSequence;
    @XmlAttribute(name = "RelEventXmlID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object relEventXmlID;
    @XmlAttribute(name = "simpleLink")
    @XmlSchemaType(name = "anyURI")
    protected String simpleLink;

    /**
     * Recupera il valore della proprietà relatedEventIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getRelatedEventIdentifierType() {
        return relatedEventIdentifierType;
    }

    /**
     * Imposta il valore della proprietà relatedEventIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setRelatedEventIdentifierType(StringPlusAuthority value) {
        this.relatedEventIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà relatedEventIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedEventIdentifierValue() {
        return relatedEventIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà relatedEventIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedEventIdentifierValue(String value) {
        this.relatedEventIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà relatedEventSequence.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRelatedEventSequence() {
        return relatedEventSequence;
    }

    /**
     * Imposta il valore della proprietà relatedEventSequence.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRelatedEventSequence(BigInteger value) {
        this.relatedEventSequence = value;
    }

    /**
     * Recupera il valore della proprietà relEventXmlID.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRelEventXmlID() {
        return relEventXmlID;
    }

    /**
     * Imposta il valore della proprietà relEventXmlID.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRelEventXmlID(Object value) {
        this.relEventXmlID = value;
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
