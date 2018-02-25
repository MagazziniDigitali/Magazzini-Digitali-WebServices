/**
 * Digest_type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.oggettiDigitali;

public class Digest_type implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Digest_type(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _value1 = "SHA256";
    public static final java.lang.String _value2 = "SHA1";
    public static final java.lang.String _value3 = "MD5";
    public static final java.lang.String _value4 = "MD5-64Base";
    public static final java.lang.String _value5 = "SHA1-64Base";
    public static final java.lang.String _value6 = "SHA256-64Base";
    public static final Digest_type value1 = new Digest_type(_value1);
    public static final Digest_type value2 = new Digest_type(_value2);
    public static final Digest_type value3 = new Digest_type(_value3);
    public static final Digest_type value4 = new Digest_type(_value4);
    public static final Digest_type value5 = new Digest_type(_value5);
    public static final Digest_type value6 = new Digest_type(_value6);
    public java.lang.String getValue() { return _value_;}
    public static Digest_type fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Digest_type enumeration = (Digest_type)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Digest_type fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Digest_type.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "digest_type"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
