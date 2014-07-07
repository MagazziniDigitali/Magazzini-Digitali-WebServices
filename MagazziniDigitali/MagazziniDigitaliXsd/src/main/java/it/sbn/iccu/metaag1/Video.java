//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.28 at 09:53:06 AM CEST 
//


package it.sbn.iccu.metaag1;

import java.math.BigInteger;
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
 * <p>Java class for video complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="video">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sequence_number" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="nomenclature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="proxies" type="{http://www.iccu.sbn.it/metaAG1.pdf}videoproxy" maxOccurs="unbounded"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="holdingsID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="videogroupID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "video", propOrder = {
    "sequenceNumber",
    "nomenclature",
    "proxies",
    "note"
})
public class Video {

    @XmlElement(name = "sequence_number", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequenceNumber;
    @XmlElement(required = true)
    protected String nomenclature;
    @XmlElement(required = true)
    protected List<Videoproxy> proxies;
    protected String note;
    @XmlAttribute(name = "holdingsID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object holdingsID;
    @XmlAttribute(name = "videogroupID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object videogroupID;

    /**
     * Gets the value of the sequenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the sequenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequenceNumber(BigInteger value) {
        this.sequenceNumber = value;
    }

    /**
     * Gets the value of the nomenclature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomenclature() {
        return nomenclature;
    }

    /**
     * Sets the value of the nomenclature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomenclature(String value) {
        this.nomenclature = value;
    }

    /**
     * Gets the value of the proxies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proxies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProxies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Videoproxy }
     * 
     * 
     */
    public List<Videoproxy> getProxies() {
        if (proxies == null) {
            proxies = new ArrayList<Videoproxy>();
        }
        return this.proxies;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the holdingsID property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHoldingsID() {
        return holdingsID;
    }

    /**
     * Sets the value of the holdingsID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHoldingsID(Object value) {
        this.holdingsID = value;
    }

    /**
     * Gets the value of the videogroupID property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVideogroupID() {
        return videogroupID;
    }

    /**
     * Sets the value of the videogroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVideogroupID(Object value) {
        this.videogroupID = value;
    }

}
