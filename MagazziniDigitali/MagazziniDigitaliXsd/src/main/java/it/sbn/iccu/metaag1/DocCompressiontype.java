//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.28 at 09:53:06 AM CEST 
//


package it.sbn.iccu.metaag1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doc_compressiontype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="doc_compressiontype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Uncompressed"/>
 *     &lt;enumeration value="ZIP"/>
 *     &lt;enumeration value="RAR"/>
 *     &lt;enumeration value="GZ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "doc_compressiontype")
@XmlEnum
public enum DocCompressiontype {

    @XmlEnumValue("Uncompressed")
    UNCOMPRESSED("Uncompressed"),
    ZIP("ZIP"),
    RAR("RAR"),
    GZ("GZ");
    private final String value;

    DocCompressiontype(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DocCompressiontype fromValue(String v) {
        for (DocCompressiontype c: DocCompressiontype.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
