//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.28 at 09:53:06 AM CEST 
//


package it.sbn.iccu.metaag1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dis_item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dis_item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="file" type="{http://www.iccu.sbn.it/metaAG1.pdf}link"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="preview" type="{http://www.iccu.sbn.it/metaAG1.pdf}preview"/>
 *           &lt;element name="available" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dis_item", propOrder = {
    "file",
    "preview",
    "available"
})
public class DisItem {

    @XmlElement(required = true)
    protected Link file;
    protected Preview preview;
    @XmlSchemaType(name = "anyURI")
    protected String available;

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link Link }
     *     
     */
    public Link getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link Link }
     *     
     */
    public void setFile(Link value) {
        this.file = value;
    }

    /**
     * Gets the value of the preview property.
     * 
     * @return
     *     possible object is
     *     {@link Preview }
     *     
     */
    public Preview getPreview() {
        return preview;
    }

    /**
     * Sets the value of the preview property.
     * 
     * @param value
     *     allowed object is
     *     {@link Preview }
     *     
     */
    public void setPreview(Preview value) {
        this.preview = value;
    }

    /**
     * Gets the value of the available property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailable() {
        return available;
    }

    /**
     * Sets the value of the available property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailable(String value) {
        this.available = value;
    }

}
