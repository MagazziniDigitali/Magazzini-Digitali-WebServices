//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.29 at 08:41:10 PM CEST 
//


package info.lc.xmlns.premis_v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for rightsComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rightsComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{info:lc/xmlns/premis-v2}rightsStatement"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}rightsExtension"/>
 *         &lt;element ref="{info:lc/xmlns/premis-v2}mdSec"/>
 *       &lt;/choice>
 *       &lt;attribute name="xmlID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="version" type="{info:lc/xmlns/premis-v2}versionSimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rightsComplexType", propOrder = {
    "rightsStatementOrRightsExtensionOrMdSec"
})
@XmlRootElement(name = "rights")
public class RightsComplexType {

    @XmlElements({
        @XmlElement(name = "rightsStatement", type = RightsStatementComplexType.class),
        @XmlElement(name = "rightsExtension", type = ExtensionComplexType.class),
        @XmlElement(name = "mdSec", type = MdSecDefinition.class)
    })
    protected List<Object> rightsStatementOrRightsExtensionOrMdSec;
    @XmlAttribute(name = "xmlID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String xmlID;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the rightsStatementOrRightsExtensionOrMdSec property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rightsStatementOrRightsExtensionOrMdSec property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRightsStatementOrRightsExtensionOrMdSec().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RightsStatementComplexType }
     * {@link ExtensionComplexType }
     * {@link MdSecDefinition }
     * 
     * 
     */
    public List<Object> getRightsStatementOrRightsExtensionOrMdSec() {
        if (rightsStatementOrRightsExtensionOrMdSec == null) {
            rightsStatementOrRightsExtensionOrMdSec = new ArrayList<Object>();
        }
        return this.rightsStatementOrRightsExtensionOrMdSec;
    }

    /**
     * Gets the value of the xmlID property.
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
     * Sets the value of the xmlID property.
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
     * Gets the value of the version property.
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
     * Sets the value of the version property.
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
