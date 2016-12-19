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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per environmentDesignationComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="environmentDesignationComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentName"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentVersion" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentOrigin" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentDesignationNote" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentDesignationExtension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environmentDesignationComplexType", propOrder = {
    "environmentName",
    "environmentVersion",
    "environmentOrigin",
    "environmentDesignationNote",
    "environmentDesignationExtension"
})
public class EnvironmentDesignationComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority environmentName;
    protected String environmentVersion;
    protected String environmentOrigin;
    protected List<String> environmentDesignationNote;
    protected List<String> environmentDesignationExtension;

    /**
     * Recupera il valore della proprietà environmentName.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getEnvironmentName() {
        return environmentName;
    }

    /**
     * Imposta il valore della proprietà environmentName.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setEnvironmentName(StringPlusAuthority value) {
        this.environmentName = value;
    }

    /**
     * Recupera il valore della proprietà environmentVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentVersion() {
        return environmentVersion;
    }

    /**
     * Imposta il valore della proprietà environmentVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentVersion(String value) {
        this.environmentVersion = value;
    }

    /**
     * Recupera il valore della proprietà environmentOrigin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentOrigin() {
        return environmentOrigin;
    }

    /**
     * Imposta il valore della proprietà environmentOrigin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentOrigin(String value) {
        this.environmentOrigin = value;
    }

    /**
     * Gets the value of the environmentDesignationNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the environmentDesignationNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnvironmentDesignationNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEnvironmentDesignationNote() {
        if (environmentDesignationNote == null) {
            environmentDesignationNote = new ArrayList<String>();
        }
        return this.environmentDesignationNote;
    }

    /**
     * Gets the value of the environmentDesignationExtension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the environmentDesignationExtension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnvironmentDesignationExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEnvironmentDesignationExtension() {
        if (environmentDesignationExtension == null) {
            environmentDesignationExtension = new ArrayList<String>();
        }
        return this.environmentDesignationExtension;
    }

}
