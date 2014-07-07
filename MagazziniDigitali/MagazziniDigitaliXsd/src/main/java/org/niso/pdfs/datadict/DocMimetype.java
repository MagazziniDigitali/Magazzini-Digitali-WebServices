//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.28 at 09:53:06 AM CEST 
//


package org.niso.pdfs.datadict;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doc_mimetype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="doc_mimetype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="text/plain"/>
 *     &lt;enumeration value="text/xml"/>
 *     &lt;enumeration value="text/html"/>
 *     &lt;enumeration value="text/rtf"/>
 *     &lt;enumeration value="application/msword"/>
 *     &lt;enumeration value="application/pdf"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "doc_mimetype")
@XmlEnum
public enum DocMimetype {

    @XmlEnumValue("text/plain")
    TEXT_PLAIN("text/plain"),
    @XmlEnumValue("text/xml")
    TEXT_XML("text/xml"),
    @XmlEnumValue("text/html")
    TEXT_HTML("text/html"),
    @XmlEnumValue("text/rtf")
    TEXT_RTF("text/rtf"),
    @XmlEnumValue("application/msword")
    APPLICATION_MSWORD("application/msword"),
    @XmlEnumValue("application/pdf")
    APPLICATION_PDF("application/pdf");
    private final String value;

    DocMimetype(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DocMimetype fromValue(String v) {
        for (DocMimetype c: DocMimetype.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
