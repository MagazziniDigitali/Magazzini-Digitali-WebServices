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
 * <p>Classe Java per environmentFunctionComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="environmentFunctionComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentFunctionType"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}environmentFunctionLevel"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environmentFunctionComplexType", propOrder = {
    "environmentFunctionType",
    "environmentFunctionLevel"
})
public class EnvironmentFunctionComplexType {

    @XmlElement(required = true)
    protected StringPlusAuthority environmentFunctionType;
    @XmlElement(required = true)
    protected String environmentFunctionLevel;

    /**
     * Recupera il valore della proprietà environmentFunctionType.
     * 
     * @return
     *     possible object is
     *     {@link StringPlusAuthority }
     *     
     */
    public StringPlusAuthority getEnvironmentFunctionType() {
        return environmentFunctionType;
    }

    /**
     * Imposta il valore della proprietà environmentFunctionType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringPlusAuthority }
     *     
     */
    public void setEnvironmentFunctionType(StringPlusAuthority value) {
        this.environmentFunctionType = value;
    }

    /**
     * Recupera il valore della proprietà environmentFunctionLevel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentFunctionLevel() {
        return environmentFunctionLevel;
    }

    /**
     * Imposta il valore della proprietà environmentFunctionLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentFunctionLevel(String value) {
        this.environmentFunctionLevel = value;
    }

}
