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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eventDetailInformationComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eventDetailInformationComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}eventDetail" minOccurs="0"/>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}eventDetailExtension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventDetailInformationComplexType", propOrder = {
    "eventDetail",
    "eventDetailExtension"
})
public class EventDetailInformationComplexType {

    protected String eventDetail;
    protected List<ExtensionComplexType> eventDetailExtension;

    /**
     * Recupera il valore della proprietà eventDetail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventDetail() {
        return eventDetail;
    }

    /**
     * Imposta il valore della proprietà eventDetail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDetail(String value) {
        this.eventDetail = value;
    }

    /**
     * Gets the value of the eventDetailExtension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventDetailExtension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventDetailExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensionComplexType }
     * 
     * 
     */
    public List<ExtensionComplexType> getEventDetailExtension() {
        if (eventDetailExtension == null) {
            eventDetailExtension = new ArrayList<ExtensionComplexType>();
        }
        return this.eventDetailExtension;
    }

}
