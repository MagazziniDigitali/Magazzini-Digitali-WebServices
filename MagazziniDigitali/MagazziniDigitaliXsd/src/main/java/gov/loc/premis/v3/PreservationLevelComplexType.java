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
 * <p>Classe Java per preservationLevelComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="preservationLevelComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}preservationLevelType" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}preservationLevelValue"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}preservationLevelRole" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}preservationLevelRationale" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}preservationLevelDateAssigned" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preservationLevelComplexType", propOrder = {
    "preservationLevelType",
    "preservationLevelValue",
    "preservationLevelRole",
    "preservationLevelRationale",
    "preservationLevelDateAssigned"
})
public class PreservationLevelComplexType {

    protected StringPlusAuthority preservationLevelType;
    @XmlElement(required = true)
    protected StringPlusAuthority preservationLevelValue;
    protected StringPlusAuthority preservationLevelRole;
    protected List<String> preservationLevelRationale;
    protected String preservationLevelDateAssigned;

    /**
     * Recupera il valore della proprietà preservationLevelType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getPreservationLevelType() {
        return preservationLevelType;
    }

    /**
     * Imposta il valore della proprietà preservationLevelType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setPreservationLevelType(StringPlusAuthority value) {
        this.preservationLevelType = value;
    }

    /**
     * Recupera il valore della proprietà preservationLevelValue.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getPreservationLevelValue() {
        return preservationLevelValue;
    }

    /**
     * Imposta il valore della proprietà preservationLevelValue.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setPreservationLevelValue(StringPlusAuthority value) {
        this.preservationLevelValue = value;
    }

    /**
     * Recupera il valore della proprietà preservationLevelRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getPreservationLevelRole() {
        return preservationLevelRole;
    }

    /**
     * Imposta il valore della proprietà preservationLevelRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setPreservationLevelRole(StringPlusAuthority value) {
        this.preservationLevelRole = value;
    }

    /**
     * Gets the value of the preservationLevelRationale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preservationLevelRationale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreservationLevelRationale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPreservationLevelRationale() {
        if (preservationLevelRationale == null) {
            preservationLevelRationale = new ArrayList<String>();
        }
        return this.preservationLevelRationale;
    }

    /**
     * Recupera il valore della proprietà preservationLevelDateAssigned.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreservationLevelDateAssigned() {
        return preservationLevelDateAssigned;
    }

    /**
     * Imposta il valore della proprietà preservationLevelDateAssigned.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreservationLevelDateAssigned(String value) {
        this.preservationLevelDateAssigned = value;
    }

}
