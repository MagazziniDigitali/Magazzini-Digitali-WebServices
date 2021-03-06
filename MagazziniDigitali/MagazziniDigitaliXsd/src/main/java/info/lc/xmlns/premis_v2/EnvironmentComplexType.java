//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.29 at 08:41:10 PM CEST 
//


package info.lc.xmlns.premis_v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for environmentComplexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="environmentComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentCharacteristic"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentPurpose" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentNote" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}dependency" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}software" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}hardware" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentPurpose" maxOccurs="unbounded"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentNote" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}dependency" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}software" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}hardware" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentNote" maxOccurs="unbounded"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}dependency" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}software" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}hardware" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}dependency" maxOccurs="unbounded"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}software" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}hardware" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}software" maxOccurs="unbounded"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}hardware" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}hardware" maxOccurs="unbounded"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element ref="{info:lc/xmlns/premis-v2}environmentExtension"/>
 *           &lt;element ref="{info:lc/xmlns/premis-v2}mdSec"/>
 *         &lt;/choice>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environmentComplexType", propOrder = {
    "content"
})
public class EnvironmentComplexType {

    @XmlElementRefs({
        @XmlElementRef(name = "software", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "dependency", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "hardware", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mdSec", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "environmentCharacteristic", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "environmentNote", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "environmentExtension", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "environmentPurpose", namespace = "info:lc/xmlns/premis-v2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "EnvironmentPurpose" is used by two different parts of a schema. See: 
     * line 382 of http://www.loc.gov/standards/premis/premis.xsd
     * line 372 of http://www.loc.gov/standards/premis/premis.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link SoftwareComplexType }{@code >}
     * {@link JAXBElement }{@code <}{@link DependencyComplexType }{@code >}
     * {@link JAXBElement }{@code <}{@link HardwareComplexType }{@code >}
     * {@link JAXBElement }{@code <}{@link MdSecDefinition }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionComplexType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
