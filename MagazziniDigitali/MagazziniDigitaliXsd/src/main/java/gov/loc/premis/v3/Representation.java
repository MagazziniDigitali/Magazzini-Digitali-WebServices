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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per representation complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="representation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.loc.gov/premis/v3}objectComplexType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}objectIdentifier" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}preservationLevel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}significantProperties" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}originalName" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}storage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}relationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingEventIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingRightsStatementIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="xmlID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="version" type="{http://www.loc.gov/premis/v3}version3" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "representation", propOrder = {
    "objectIdentifier",
    "preservationLevel",
    "significantProperties",
    "originalName",
    "storage",
    "relationship",
    "linkingEventIdentifier",
    "linkingRightsStatementIdentifier"
})
public class Representation
    extends ObjectComplexType
{

    @XmlElement(required = true)
    protected List<ObjectIdentifierComplexType> objectIdentifier;
    protected List<PreservationLevelComplexType> preservationLevel;
    protected List<SignificantPropertiesComplexType> significantProperties;
    protected OriginalNameComplexType originalName;
    protected List<StorageComplexType> storage;
    protected List<RelationshipComplexType> relationship;
    protected List<LinkingEventIdentifierComplexType> linkingEventIdentifier;
    protected List<LinkingRightsStatementIdentifierComplexType> linkingRightsStatementIdentifier;
    @XmlAttribute(name = "xmlID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String xmlID;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the objectIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdentifierComplexType }
     * 
     * 
     */
    public List<ObjectIdentifierComplexType> getObjectIdentifier() {
        if (objectIdentifier == null) {
            objectIdentifier = new ArrayList<ObjectIdentifierComplexType>();
        }
        return this.objectIdentifier;
    }

    /**
     * Gets the value of the preservationLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preservationLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreservationLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreservationLevelComplexType }
     * 
     * 
     */
    public List<PreservationLevelComplexType> getPreservationLevel() {
        if (preservationLevel == null) {
            preservationLevel = new ArrayList<PreservationLevelComplexType>();
        }
        return this.preservationLevel;
    }

    /**
     * Gets the value of the significantProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the significantProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignificantProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignificantPropertiesComplexType }
     * 
     * 
     */
    public List<SignificantPropertiesComplexType> getSignificantProperties() {
        if (significantProperties == null) {
            significantProperties = new ArrayList<SignificantPropertiesComplexType>();
        }
        return this.significantProperties;
    }

    /**
     * Recupera il valore della proprietà originalName.
     * 
     * @return
     *     possible object is
     *     {@link OriginalNameComplexType }
     *     
     */
    public OriginalNameComplexType getOriginalName() {
        return originalName;
    }

    /**
     * Imposta il valore della proprietà originalName.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginalNameComplexType }
     *     
     */
    public void setOriginalName(OriginalNameComplexType value) {
        this.originalName = value;
    }

    /**
     * Gets the value of the storage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStorage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StorageComplexType }
     * 
     * 
     */
    public List<StorageComplexType> getStorage() {
        if (storage == null) {
            storage = new ArrayList<StorageComplexType>();
        }
        return this.storage;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationship property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationship().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelationshipComplexType }
     * 
     * 
     */
    public List<RelationshipComplexType> getRelationship() {
        if (relationship == null) {
            relationship = new ArrayList<RelationshipComplexType>();
        }
        return this.relationship;
    }

    /**
     * Gets the value of the linkingEventIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkingEventIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkingEventIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkingEventIdentifierComplexType }
     * 
     * 
     */
    public List<LinkingEventIdentifierComplexType> getLinkingEventIdentifier() {
        if (linkingEventIdentifier == null) {
            linkingEventIdentifier = new ArrayList<LinkingEventIdentifierComplexType>();
        }
        return this.linkingEventIdentifier;
    }

    /**
     * Gets the value of the linkingRightsStatementIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkingRightsStatementIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkingRightsStatementIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkingRightsStatementIdentifierComplexType }
     * 
     * 
     */
    public List<LinkingRightsStatementIdentifierComplexType> getLinkingRightsStatementIdentifier() {
        if (linkingRightsStatementIdentifier == null) {
            linkingRightsStatementIdentifier = new ArrayList<LinkingRightsStatementIdentifierComplexType>();
        }
        return this.linkingRightsStatementIdentifier;
    }

    /**
     * Recupera il valore della proprietà xmlID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlID() {
        return xmlID;
    }

    /**
     * Imposta il valore della proprietà xmlID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlID(String value) {
        this.xmlID = value;
    }

    /**
     * Recupera il valore della proprietà version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
