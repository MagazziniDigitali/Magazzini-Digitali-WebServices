/**
 * StatoOggettoDigitale_type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.oggettiDigitali;

public class StatoOggettoDigitale_type implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected StatoOggettoDigitale_type(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _NONPRESENTE = "NONPRESENTE";
    public static final java.lang.String _INITTRASF = "INITTRASF";
    public static final java.lang.String _FINETRASF = "FINETRASF";
    public static final java.lang.String _CHECKARCHIVIAZIONE = "CHECKARCHIVIAZIONE";
    public static final java.lang.String _ARCHIVIATO = "ARCHIVIATO";
    public static final java.lang.String _CHECKFALLITO = "CHECKFALLITO";
    public static final java.lang.String _ERROR = "ERROR";
    public static final StatoOggettoDigitale_type NONPRESENTE = new StatoOggettoDigitale_type(_NONPRESENTE);
    public static final StatoOggettoDigitale_type INITTRASF = new StatoOggettoDigitale_type(_INITTRASF);
    public static final StatoOggettoDigitale_type FINETRASF = new StatoOggettoDigitale_type(_FINETRASF);
    public static final StatoOggettoDigitale_type CHECKARCHIVIAZIONE = new StatoOggettoDigitale_type(_CHECKARCHIVIAZIONE);
    public static final StatoOggettoDigitale_type ARCHIVIATO = new StatoOggettoDigitale_type(_ARCHIVIATO);
    public static final StatoOggettoDigitale_type CHECKFALLITO = new StatoOggettoDigitale_type(_CHECKFALLITO);
    public static final StatoOggettoDigitale_type ERROR = new StatoOggettoDigitale_type(_ERROR);
    public java.lang.String getValue() { return _value_;}
    public static StatoOggettoDigitale_type fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        StatoOggettoDigitale_type enumeration = (StatoOggettoDigitale_type)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static StatoOggettoDigitale_type fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(StatoOggettoDigitale_type.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "statoOggettoDigitale_type"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
