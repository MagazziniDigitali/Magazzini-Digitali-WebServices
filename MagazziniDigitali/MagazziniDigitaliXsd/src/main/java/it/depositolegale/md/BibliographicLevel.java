//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.19 at 07:22:52 PM CET 
//


package it.depositolegale.md;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bibliographic_level.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="bibliographic_level">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="a"/>
 *     &lt;enumeration value="m"/>
 *     &lt;enumeration value="s"/>
 *     &lt;enumeration value="c"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "bibliographic_level")
@XmlEnum
public enum BibliographicLevel {


    /**
     * analitico
     * 
     */
    @XmlEnumValue("a")
    A("a"),

    /**
     * monografia
     * 
     */
    @XmlEnumValue("m")
    M("m"),

    /**
     * pubblicazione in serie
     * 
     */
    @XmlEnumValue("s")
    S("s"),

    /**
     * raccolta
     * 
     */
    @XmlEnumValue("c")
    C("c");
    private final String value;

    BibliographicLevel(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BibliographicLevel fromValue(String v) {
        for (BibliographicLevel c: BibliographicLevel.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
