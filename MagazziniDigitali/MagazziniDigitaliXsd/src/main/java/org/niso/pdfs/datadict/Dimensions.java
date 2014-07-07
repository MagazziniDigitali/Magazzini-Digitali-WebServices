//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.28 at 09:53:06 AM CEST 
//


package org.niso.pdfs.datadict;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dimensions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dimensions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="imagelength" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="imagewidth" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="source_xdimension" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="source_ydimension" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dimensions", propOrder = {
    "imagelength",
    "imagewidth",
    "sourceXdimension",
    "sourceYdimension"
})
public class Dimensions {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger imagelength;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger imagewidth;
    @XmlElement(name = "source_xdimension")
    protected Double sourceXdimension;
    @XmlElement(name = "source_ydimension")
    protected Double sourceYdimension;

    /**
     * Gets the value of the imagelength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getImagelength() {
        return imagelength;
    }

    /**
     * Sets the value of the imagelength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setImagelength(BigInteger value) {
        this.imagelength = value;
    }

    /**
     * Gets the value of the imagewidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getImagewidth() {
        return imagewidth;
    }

    /**
     * Sets the value of the imagewidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setImagewidth(BigInteger value) {
        this.imagewidth = value;
    }

    /**
     * Gets the value of the sourceXdimension property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSourceXdimension() {
        return sourceXdimension;
    }

    /**
     * Sets the value of the sourceXdimension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSourceXdimension(Double value) {
        this.sourceXdimension = value;
    }

    /**
     * Gets the value of the sourceYdimension property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSourceYdimension() {
        return sourceYdimension;
    }

    /**
     * Sets the value of the sourceYdimension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSourceYdimension(Double value) {
        this.sourceYdimension = value;
    }

}
