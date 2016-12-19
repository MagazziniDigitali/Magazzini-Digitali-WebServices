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
 * <p>Classe Java per linkingRightsStatementIdentifierComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="linkingRightsStatementIdentifierComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingRightsStatementIdentifierType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingRightsStatementIdentifierValue"/>
 *       &lt;/sequence>
 *       &lt;attribute name="LinkPermissionStatementXmlID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="simpleLink" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkingRightsStatementIdentifierComplexType", propOrder = {
    "linkingRightsStatementIdentifierType",
    "linkingRightsStatementIdentifierValue"
})
public class LinkingRightsStatementIdentifierComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority linkingRightsStatementIdentifierType;
    @XmlElement(required = true)
    protected String linkingRightsStatementIdentifierValue;
    @XmlAttribute(name = "LinkPermissionStatementXmlID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object linkPermissionStatementXmlID;
    @XmlAttribute(name = "simpleLink")
    @XmlSchemaType(name = "anyURI")
    protected String simpleLink;

    /**
     * Recupera il valore della proprietà linkingRightsStatementIdentifierType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getLinkingRightsStatementIdentifierType() {
        return linkingRightsStatementIdentifierType;
    }

    /**
     * Imposta il valore della proprietà linkingRightsStatementIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setLinkingRightsStatementIdentifierType(StringPlusAuthority value) {
        this.linkingRightsStatementIdentifierType = value;
    }

    /**
     * Recupera il valore della proprietà linkingRightsStatementIdentifierValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkingRightsStatementIdentifierValue() {
        return linkingRightsStatementIdentifierValue;
    }

    /**
     * Imposta il valore della proprietà linkingRightsStatementIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkingRightsStatementIdentifierValue(String value) {
        this.linkingRightsStatementIdentifierValue = value;
    }

    /**
     * Recupera il valore della proprietà linkPermissionStatementXmlID.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLinkPermissionStatementXmlID() {
        return linkPermissionStatementXmlID;
    }

    /**
     * Imposta il valore della proprietà linkPermissionStatementXmlID.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLinkPermissionStatementXmlID(Object value) {
        this.linkPermissionStatementXmlID = value;
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
