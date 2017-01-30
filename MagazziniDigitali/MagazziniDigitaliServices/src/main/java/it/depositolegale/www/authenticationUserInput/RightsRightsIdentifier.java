/**
 * RightsRightsIdentifier.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.authenticationUserInput;

public class RightsRightsIdentifier  implements java.io.Serializable {
    private java.lang.String rightsIdentifierType;

    private java.lang.String rightsIdentifierValue;

    public RightsRightsIdentifier() {
    }

    public RightsRightsIdentifier(
           java.lang.String rightsIdentifierType,
           java.lang.String rightsIdentifierValue) {
           this.rightsIdentifierType = rightsIdentifierType;
           this.rightsIdentifierValue = rightsIdentifierValue;
    }


    /**
     * Gets the rightsIdentifierType value for this RightsRightsIdentifier.
     * 
     * @return rightsIdentifierType
     */
    public java.lang.String getRightsIdentifierType() {
        return rightsIdentifierType;
    }


    /**
     * Sets the rightsIdentifierType value for this RightsRightsIdentifier.
     * 
     * @param rightsIdentifierType
     */
    public void setRightsIdentifierType(java.lang.String rightsIdentifierType) {
        this.rightsIdentifierType = rightsIdentifierType;
    }


    /**
     * Gets the rightsIdentifierValue value for this RightsRightsIdentifier.
     * 
     * @return rightsIdentifierValue
     */
    public java.lang.String getRightsIdentifierValue() {
        return rightsIdentifierValue;
    }


    /**
     * Sets the rightsIdentifierValue value for this RightsRightsIdentifier.
     * 
     * @param rightsIdentifierValue
     */
    public void setRightsIdentifierValue(java.lang.String rightsIdentifierValue) {
        this.rightsIdentifierValue = rightsIdentifierValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RightsRightsIdentifier)) return false;
        RightsRightsIdentifier other = (RightsRightsIdentifier) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rightsIdentifierType==null && other.getRightsIdentifierType()==null) || 
             (this.rightsIdentifierType!=null &&
              this.rightsIdentifierType.equals(other.getRightsIdentifierType()))) &&
            ((this.rightsIdentifierValue==null && other.getRightsIdentifierValue()==null) || 
             (this.rightsIdentifierValue!=null &&
              this.rightsIdentifierValue.equals(other.getRightsIdentifierValue())));
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
        if (getRightsIdentifierType() != null) {
            _hashCode += getRightsIdentifierType().hashCode();
        }
        if (getRightsIdentifierValue() != null) {
            _hashCode += getRightsIdentifierValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RightsRightsIdentifier.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">>rights>rightsIdentifier"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rightsIdentifierType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "rightsIdentifierType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rightsIdentifierValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "rightsIdentifierValue"));
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
