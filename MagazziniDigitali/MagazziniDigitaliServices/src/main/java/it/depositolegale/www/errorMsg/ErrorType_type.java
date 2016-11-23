/**
 * ErrorType_type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.errorMsg;

public class ErrorType_type implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ErrorType_type(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _LOGINERROR = "LOGINERROR";
    public static final java.lang.String _PASSWORDERROR = "PASSWORDERROR";
    public static final java.lang.String _IPERROR = "IPERROR";
    public static final java.lang.String _ERROR = "ERROR";
    public static final java.lang.String _SOFTWARE_ERROR = "SOFTWARE_ERROR";
    public static final ErrorType_type LOGINERROR = new ErrorType_type(_LOGINERROR);
    public static final ErrorType_type PASSWORDERROR = new ErrorType_type(_PASSWORDERROR);
    public static final ErrorType_type IPERROR = new ErrorType_type(_IPERROR);
    public static final ErrorType_type ERROR = new ErrorType_type(_ERROR);
    public static final ErrorType_type SOFTWARE_ERROR = new ErrorType_type(_SOFTWARE_ERROR);
    public java.lang.String getValue() { return _value_;}
    public static ErrorType_type fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ErrorType_type enumeration = (ErrorType_type)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ErrorType_type fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ErrorType_type.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorType_type"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
