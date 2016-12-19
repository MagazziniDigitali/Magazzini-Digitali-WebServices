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
 * <p>Classe Java per relatedObjectIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="relatedObjectIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relatedObjectIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relatedObjectIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relatedObjectSequence" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RelObjectXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="simpleLink" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relatedObjectIdentifierComplexType", propOrder = {
    "relatedObjectIdentifierType",
    "relatedObjectIdentifierValue",
    "relatedObjectSequence"
})
public class RelatedObjectIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority relatedObjectIdentifierType;
    @XmlElement(required = true)
    protected String relatedObjectIdentifierValue;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger relatedObjectSequence;
    @XmlAttribute(name = "RelObjectXmlID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object relObjectXmlID;
    @XmlAttribute(name = "simpleLink")
    @XmlSchemaType(name = "anyURI")
    protected String simpleLink;

    /**
     * Recupera il valore della proprietà relatedObjectIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getRelatedObjectIdentifierType() {
        return relatedObjectIdentifierType;
    }

    /**
     * Imposta il valore della proprietà relatedObjectIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setRelatedObjectIdentifierType(StringPlusAuthority value) {
        this.relatedObjectIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà relatedObjectIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedObjectIdentifierValue() {
        return relatedObjectIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà relatedObjectIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedObjectIdentifierValue(String value) {
        this.relatedObjectIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà relatedObjectSequence.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRelatedObjectSequence() {
        return relatedObjectSequence;
    }

    /**
     * Imposta il valore della proprietà relatedObjectSequence.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRelatedObjectSequence(BigInteger value) {
        this.relatedObjectSequence = value;
    }

    /**
     * Recupera il valore della proprietà relObjectXmlID.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRelObjectXmlID() {
        return relObjectXmlID;
    }

    /**
     * Imposta il valore della proprietà relObjectXmlID.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRelObjectXmlID(Object value) {
        this.relObjectXmlID = value;
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
