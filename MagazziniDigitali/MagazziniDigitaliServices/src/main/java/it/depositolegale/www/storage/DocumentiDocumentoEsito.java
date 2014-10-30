/**
 * DocumentiDocumentoEsito.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.storage;

public class DocumentiDocumentoEsito implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DocumentiDocumentoEsito(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _NOTFOUND = "NOTFOUND";
    public static final java.lang.String _EXIST = "EXIST";
    public static final java.lang.String _SHAERROR = "SHAERROR";
    public static final DocumentiDocumentoEsito NOTFOUND = new DocumentiDocumentoEsito(_NOTFOUND);
    public static final DocumentiDocumentoEsito EXIST = new DocumentiDocumentoEsito(_EXIST);
    public static final DocumentiDocumentoEsito SHAERROR = new DocumentiDocumentoEsito(_SHAERROR);
    public java.lang.String getValue() { return _value_;}
    public static DocumentiDocumentoEsito fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DocumentiDocumentoEsito enumeration = (DocumentiDocumentoEsito)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DocumentiDocumentoEsito fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DocumentiDocumentoEsito.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/storage", ">>>documenti>documento>esito"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
