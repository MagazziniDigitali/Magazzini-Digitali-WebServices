//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per linkingObjectIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="linkingObjectIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingObjectIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingObjectIdentifierValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingObjectRole" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="LinkObjectXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="simpleLink" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkingObjectIdentifierComplexType", propOrder = {
    "linkingObjectIdentifierType",
    "linkingObjectIdentifierValue",
    "linkingObjectRole"
})
public class LinkingObjectIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority linkingObjectIdentifierType;
    @XmlElement(required = true)
    protected String linkingObjectIdentifierValue;
    protected List<StringPlusAuthority> linkingObjectRole;
    @XmlAttribute(name = "LinkObjectXmlID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object linkObjectXmlID;
    @XmlAttribute(name = "simpleLink")
    @XmlSchemaType(name = "anyURI")
    protected String simpleLink;

    /**
     * Recupera il valore della proprietà linkingObjectIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getLinkingObjectIdentifierType() {
        return linkingObjectIdentifierType;
    }

    /**
     * Imposta il valore della proprietà linkingObjectIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setLinkingObjectIdentifierType(StringPlusAuthority value) {
        this.linkingObjectIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà linkingObjectIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkingObjectIdentifierValue() {
        return linkingObjectIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà linkingObjectIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkingObjectIdentifierValue(String value) {
        this.linkingObjectIdentifierValue = value;
    }

    /**
     * Gets the value of the linkingObjectRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkingObjectRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkingObjectRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringPlusAuthority }
     * 
     * 
     */
    public List<StringPlusAuthority> getLinkingObjectRole() {
        if (linkingObjectRole == null) {
            linkingObjectRole = new ArrayList<StringPlusAuthority>();
        }
        return this.linkingObjectRole;
    }

    /**
     * Recupera il valore della proprietà linkObjectXmlID.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLinkObjectXmlID() {
        return linkObjectXmlID;
    }

    /**
     * Imposta il valore della proprietà linkObjectXmlID.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLinkObjectXmlID(Object value) {
        this.linkObjectXmlID = value;
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
