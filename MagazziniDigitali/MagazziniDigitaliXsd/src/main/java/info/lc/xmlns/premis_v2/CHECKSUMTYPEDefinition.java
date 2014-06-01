//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.27 at 04:47:07 PM CEST 
//


package info.lc.xmlns.premis_v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CHECKSUMTYPEDefinition.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CHECKSUMTYPEDefinition">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Adler-32"/>
 *     &lt;enumeration value="CRC32"/>
 *     &lt;enumeration value="HAVAL"/>
 *     &lt;enumeration value="MD5"/>
 *     &lt;enumeration value="MNP"/>
 *     &lt;enumeration value="SHA-1"/>
 *     &lt;enumeration value="SHA-256"/>
 *     &lt;enumeration value="SHA-384"/>
 *     &lt;enumeration value="SHA-512"/>
 *     &lt;enumeration value="TIGER"/>
 *     &lt;enumeration value="WHIRLPOOL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CHECKSUMTYPEDefinition")
@XmlEnum
public enum CHECKSUMTYPEDefinition {

    @XmlEnumValue("Adler-32")
    ADLER_32("Adler-32"),
    @XmlEnumValue("CRC32")
    CRC_32("CRC32"),
    HAVAL("HAVAL"),
    @XmlEnumValue("MD5")
    MD_5("MD5"),
    MNP("MNP"),
    @XmlEnumValue("SHA-1")
    SHA_1("SHA-1"),
    @XmlEnumValue("SHA-256")
    SHA_256("SHA-256"),
    @XmlEnumValue("SHA-384")
    SHA_384("SHA-384"),
    @XmlEnumValue("SHA-512")
    SHA_512("SHA-512"),
    TIGER("TIGER"),
    WHIRLPOOL("WHIRLPOOL");
    private final String value;

    CHECKSUMTYPEDefinition(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CHECKSUMTYPEDefinition fromValue(String v) {
        for (CHECKSUMTYPEDefinition c: CHECKSUMTYPEDefinition.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
