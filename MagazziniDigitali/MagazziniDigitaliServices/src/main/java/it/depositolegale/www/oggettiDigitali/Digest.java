/**
 * Digest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.oggettiDigitali;

public class Digest  implements java.io.Serializable {
    private it.depositolegale.www.oggettiDigitali.Digest_type digestType;

    private java.lang.String digestValue;

    public Digest() {
    }

    public Digest(
           it.depositolegale.www.oggettiDigitali.Digest_type digestType,
           java.lang.String digestValue) {
           this.digestType = digestType;
           this.digestValue = digestValue;
    }


    /**
     * Gets the digestType value for this Digest.
     * 
     * @return digestType
     */
    public it.depositolegale.www.oggettiDigitali.Digest_type getDigestType() {
        return digestType;
    }


    /**
     * Sets the digestType value for this Digest.
     * 
     * @param digestType
     */
    public void setDigestType(it.depositolegale.www.oggettiDigitali.Digest_type digestType) {
        this.digestType = digestType;
    }


    /**
     * Gets the digestValue value for this Digest.
     * 
     * @return digestValue
     */
    public java.lang.String getDigestValue() {
        return digestValue;
    }


    /**
     * Sets the digestValue value for this Digest.
     * 
     * @param digestValue
     */
    public void setDigestValue(java.lang.String digestValue) {
        this.digestValue = digestValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Digest)) return false;
        Digest other = (Digest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.digestType==null && other.getDigestType()==null) || 
             (this.digestType!=null &&
              this.digestType.equals(other.getDigestType()))) &&
            ((this.digestValue==null && other.getDigestValue()==null) || 
             (this.digestValue!=null &&
              this.digestValue.equals(other.getDigestValue())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDigestType() != null) {
            _hashCode += getDigestType().hashCode();
        }
        if (getDigestValue() != null) {
            _hashCode += getDigestValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Digest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", ">digest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digestType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "digestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "digest_type"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digestValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "digestValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
