//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.29 at 08:41:10 PM CEST 
//


package it.depositolegale.md;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice minOccurs="0">
 *         &lt;sequence>
 *           &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="issue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="stpiece_per" type="{http://www.depositolegale.it/MD}SICI" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="part_number" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *           &lt;element name="part_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="stpiece_vol" type="{http://www.depositolegale.it/MD}BICI" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "year",
    "issue",
    "stpiecePer",
    "partNumber",
    "partName",
    "stpieceVol"
})
@XmlRootElement(name = "piece")
public class Piece {

    protected String year;
    protected String issue;
    @XmlElement(name = "stpiece_per")
    protected String stpiecePer;
    @XmlElement(name = "part_number")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger partNumber;
    @XmlElement(name = "part_name")
    protected String partName;
    @XmlElement(name = "stpiece_vol")
    protected String stpieceVol;

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssue(String value) {
        this.issue = value;
    }

    /**
     * Gets the value of the stpiecePer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStpiecePer() {
        return stpiecePer;
    }

    /**
     * Sets the value of the stpiecePer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStpiecePer(String value) {
        this.stpiecePer = value;
    }

    /**
     * Gets the value of the partNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the value of the partNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPartNumber(BigInteger value) {
        this.partNumber = value;
    }

    /**
     * Gets the value of the partName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets the value of the partName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartName(String value) {
        this.partName = value;
    }

    /**
     * Gets the value of the stpieceVol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStpieceVol() {
        return stpieceVol;
    }

    /**
     * Sets the value of the stpieceVol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStpieceVol(String value) {
        this.stpieceVol = value;
    }

}
