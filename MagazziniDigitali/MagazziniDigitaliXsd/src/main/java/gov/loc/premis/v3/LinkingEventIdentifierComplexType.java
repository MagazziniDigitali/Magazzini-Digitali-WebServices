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
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per linkingEventIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="linkingEventIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingEventIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingEventIdentifierValue"/>
 *       &lt;/sequence>
 *       &lt;attribute name="LinkEventXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="simpleLink" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkingEventIdentifierComplexType", propOrder = {
    "linkingEventIdentifierType",
    "linkingEventIdentifierValue"
})
public class LinkingEventIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority linkingEventIdentifierType;
    @XmlElement(required = true)
    protected String linkingEventIdentifierValue;
    @XmlAttribute(name = "LinkEventXmlID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object linkEventXmlID;
    @XmlAttribute(name = "simpleLink")
    @XmlSchemaType(name = "anyURI")
    protected String simpleLink;

    /**
     * Recupera il valore della proprietà linkingEventIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getLinkingEventIdentifierType() {
        return linkingEventIdentifierType;
    }

    /**
     * Imposta il valore della proprietà linkingEventIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setLinkingEventIdentifierType(StringPlusAuthority value) {
        this.linkingEventIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà linkingEventIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkingEventIdentifierValue() {
        return linkingEventIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà linkingEventIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkingEventIdentifierValue(String value) {
        this.linkingEventIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà linkEventXmlID.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLinkEventXmlID() {
        return linkEventXmlID;
    }

    /**
     * Imposta il valore della proprietà linkEventXmlID.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLinkEventXmlID(Object value) {
        this.linkEventXmlID = value;
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
