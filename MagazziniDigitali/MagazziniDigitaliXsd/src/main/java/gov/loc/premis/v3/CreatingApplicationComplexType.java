//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.12.03 alle 08:44:01 AM CET 
//


package gov.loc.premis.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per creatingApplicationComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="creatingApplicationComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationName"/>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationVersion" minOccurs="0"/>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}dateCreatedByApplication" minOccurs="0"/>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationExtension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationVersion"/>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}dateCreatedByApplication" minOccurs="0"/>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationExtension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}dateCreatedByApplication"/>
 *           &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationExtension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}creatingApplicationExtension" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creatingApplicationComplexType", propOrder = {
    "content"
})
public class CreatingApplicationComplexType {

    @XmlElementRefs({
        @XmlElementRef(name = "dateCreatedByApplication", namespace = "http://www.loc.gov/premis/v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creatingApplicationVersion", namespace = "http://www.loc.gov/premis/v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creatingApplicationExtension", namespace = "http://www.loc.gov/premis/v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creatingApplicationName", namespace = "http://www.loc.gov/premis/v3", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Recupera il resto del modello di contenuto. 
     * 
     * <p>
     * Questa proprietà "catch-all" viene recuperata per il seguente motivo: 
     * Il nome di campo "CreatingApplicationVersion" è usato da due diverse parti di uno schema. Vedere: 
     * riga 373 di https://www.loc.gov/standards/premis/premis.xsd
     * riga 367 di https://www.loc.gov/standards/premis/premis.xsd
     * <p>
     * Per eliminare questa proprietà, applicare una personalizzazione della proprietà a una 
     * delle seguenti due dichiarazioni per modificarne il nome: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link ExtensionComplexType }{@code >}
     * {@link JAXBElement }{@code <}{@link StringPlusAuthority }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
