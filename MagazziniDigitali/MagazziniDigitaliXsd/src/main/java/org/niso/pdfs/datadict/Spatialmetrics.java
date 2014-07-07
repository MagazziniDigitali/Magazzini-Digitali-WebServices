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
 * <p>Java class for spatialmetrics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spatialmetrics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="samplingfrequencyunit" type="{http://www.niso.org/pdfs/DataDict.pdf}samplingfrequencyunittype"/>
 *         &lt;element name="samplingfrequencyplane" type="{http://www.niso.org/pdfs/DataDict.pdf}samplingfrequencyplanetype"/>
 *         &lt;element name="xsamplingfrequency" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="ysamplingfrequency" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="photometricinterpretation" type="{http://www.niso.org/pdfs/DataDict.pdf}photometricinterpretationtype"/>
 *         &lt;element name="bitpersample" type="{http://www.niso.org/pdfs/DataDict.pdf}bitpersampletype"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spatialmetrics", propOrder = {
    "samplingfrequencyunit",
    "samplingfrequencyplane",
    "xsamplingfrequency",
    "ysamplingfrequency",
    "photometricinterpretation",
    "bitpersample"
})
public class Spatialmetrics {

    @XmlElement(required = true)
    protected BigInteger samplingfrequencyunit;
    @XmlElement(required = true)
    protected BigInteger samplingfrequencyplane;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger xsamplingfrequency;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger ysamplingfrequency;
    @XmlElement(required = true)
    protected Photometricinterpretationtype photometricinterpretation;
    @XmlElement(required = true)
    protected String bitpersample;

    /**
     * Gets the value of the samplingfrequencyunit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSamplingfrequencyunit() {
        return samplingfrequencyunit;
    }

    /**
     * Sets the value of the samplingfrequencyunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSamplingfrequencyunit(BigInteger value) {
        this.samplingfrequencyunit = value;
    }

    /**
     * Gets the value of the samplingfrequencyplane property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSamplingfrequencyplane() {
        return samplingfrequencyplane;
    }

    /**
     * Sets the value of the samplingfrequencyplane property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSamplingfrequencyplane(BigInteger value) {
        this.samplingfrequencyplane = value;
    }

    /**
     * Gets the value of the xsamplingfrequency property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getXsamplingfrequency() {
        return xsamplingfrequency;
    }

    /**
     * Sets the value of the xsamplingfrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setXsamplingfrequency(BigInteger value) {
        this.xsamplingfrequency = value;
    }

    /**
     * Gets the value of the ysamplingfrequency property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYsamplingfrequency() {
        return ysamplingfrequency;
    }

    /**
     * Sets the value of the ysamplingfrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYsamplingfrequency(BigInteger value) {
        this.ysamplingfrequency = value;
    }

    /**
     * Gets the value of the photometricinterpretation property.
     * 
     * @return
     *     possible object is
     *     {@link Photometricinterpretationtype }
     *     
     */
    public Photometricinterpretationtype getPhotometricinterpretation() {
        return photometricinterpretation;
    }

    /**
     * Sets the value of the photometricinterpretation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Photometricinterpretationtype }
     *     
     */
    public void setPhotometricinterpretation(Photometricinterpretationtype value) {
        this.photometricinterpretation = value;
    }

    /**
     * Gets the value of the bitpersample property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBitpersample() {
        return bitpersample;
    }

    /**
     * Sets the value of the bitpersample property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBitpersample(String value) {
        this.bitpersample = value;
    }

}
