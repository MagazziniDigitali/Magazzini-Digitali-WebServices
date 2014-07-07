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
import javax.xml.datatype.XMLGregorianCalendar;
import org.niso.pdfs.datadict.DocFormat;


/**
 * <p>Java class for ocr complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ocr">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sequence_number" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="nomenclature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usage" type="{http://www.iccu.sbn.it/metaAG1.pdf}usages" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="file" type="{http://www.iccu.sbn.it/metaAG1.pdf}link"/>
 *         &lt;element name="md5" type="{http://www.niso.org/pdfs/DataDict.pdf}checksum"/>
 *         &lt;element name="source" type="{http://www.iccu.sbn.it/metaAG1.pdf}link"/>
 *         &lt;element name="filesize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="format" type="{http://www.niso.org/pdfs/DataDict.pdf}docFormat"/>
 *         &lt;element name="software_ocr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datetimecreated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="holdingsID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ocr", propOrder = {
    "sequenceNumber",
    "nomenclature",
    "usage",
    "file",
    "md5",
    "source",
    "filesize",
    "format",
    "softwareOcr",
    "datetimecreated",
    "note"
})
public class Ocr {

    @XmlElement(name = "sequence_number", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequenceNumber;
    @XmlElement(required = true)
    protected String nomenclature;
    protected List<String> usage;
    @XmlElement(required = true)
    protected Link file;
    @XmlElement(required = true)
    protected String md5;
    @XmlElement(required = true)
    protected Link source;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger filesize;
    @XmlElement(required = true)
    protected DocFormat format;
    @XmlElement(name = "software_ocr")
    protected String softwareOcr;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datetimecreated;
    protected String note;
    @XmlAttribute(name = "holdingsID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object holdingsID;

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
     * Gets the value of the usage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUsage() {
        if (usage == null) {
            usage = new ArrayList<String>();
        }
        return this.usage;
    }

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
     * Gets the value of the md5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Sets the value of the md5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMd5(String value) {
        this.md5 = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Link }
     *     
     */
    public Link getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Link }
     *     
     */
    public void setSource(Link value) {
        this.source = value;
    }

    /**
     * Gets the value of the filesize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFilesize() {
        return filesize;
    }

    /**
     * Sets the value of the filesize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFilesize(BigInteger value) {
        this.filesize = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link DocFormat }
     *     
     */
    public DocFormat getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocFormat }
     *     
     */
    public void setFormat(DocFormat value) {
        this.format = value;
    }

    /**
     * Gets the value of the softwareOcr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareOcr() {
        return softwareOcr;
    }

    /**
     * Sets the value of the softwareOcr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareOcr(String value) {
        this.softwareOcr = value;
    }

    /**
     * Gets the value of the datetimecreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatetimecreated() {
        return datetimecreated;
    }

    /**
     * Sets the value of the datetimecreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatetimecreated(XMLGregorianCalendar value) {
        this.datetimecreated = value;
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

}
