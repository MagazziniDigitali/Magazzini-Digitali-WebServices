//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.27 at 09:59:57 AM CET 
//


package it.depositolegale.ticket;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.loc.premis.v3.LinkingRightsStatementIdentifierComplexType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.loc.gov/premis/v3}linkingRightsStatementIdentifier"/>
 *         &lt;element name="ipClient" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "linkingRightsStatementIdentifier",
    "ipClient",
    "loginUtente"
})
@XmlRootElement(name = "ticket")
public class Ticket {

    @XmlElement(namespace = "http://www.loc.gov/premis/v3", required = true)
    protected LinkingRightsStatementIdentifierComplexType linkingRightsStatementIdentifier;
    @XmlElement(required = true)
    protected String ipClient;
    protected String loginUtente;

    /**
     * Gets the value of the linkingRightsStatementIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link LinkingRightsStatementIdentifierComplexType }
     *     
     */
    public LinkingRightsStatementIdentifierComplexType getLinkingRightsStatementIdentifier() {
        return linkingRightsStatementIdentifier;
    }

    /**
     * Sets the value of the linkingRightsStatementIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkingRightsStatementIdentifierComplexType }
     *     
     */
    public void setLinkingRightsStatementIdentifier(LinkingRightsStatementIdentifierComplexType value) {
        this.linkingRightsStatementIdentifier = value;
    }

    /**
     * Gets the value of the ipClient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpClient() {
        return ipClient;
    }

    /**
     * Sets the value of the ipClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpClient(String value) {
        this.ipClient = value;
    }

    /**
     * Gets the value of the loginUtente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUtente() {
        return loginUtente;
    }

    /**
     * Sets the value of the loginUtente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUtente(String value) {
        this.loginUtente = value;
    }

}