//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per environmentRegistryComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="environmentRegistryComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentRegistryName"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentRegistryKey"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentRegistryRole" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environmentRegistryComplexType", propOrder = {
    "environmentRegistryName",
    "environmentRegistryKey",
    "environmentRegistryRole"
})
public class EnvironmentRegistryComplexType {

    @XmlElement(required = true)
    protected String environmentRegistryName;
    @XmlElement(required = true)
    protected String environmentRegistryKey;
    protected StringPlusAuthority environmentRegistryRole;

    /**
     * Recupera il valore della proprietà environmentRegistryName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentRegistryName() {
        return environmentRegistryName;
    }

    /**
     * Imposta il valore della proprietà environmentRegistryName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentRegistryName(String value) {
        this.environmentRegistryName = value;
    }

    /**
     * Recupera il valore della proprietà environmentRegistryKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentRegistryKey() {
        return environmentRegistryKey;
    }

    /**
     * Imposta il valore della proprietà environmentRegistryKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentRegistryKey(String value) {
        this.environmentRegistryKey = value;
    }

    /**
     * Recupera il valore della proprietà environmentRegistryRole.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getEnvironmentRegistryRole() {
        return environmentRegistryRole;
    }

    /**
     * Imposta il valore della proprietà environmentRegistryRole.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setEnvironmentRegistryRole(StringPlusAuthority value) {
        this.environmentRegistryRole = value;
    }

}
