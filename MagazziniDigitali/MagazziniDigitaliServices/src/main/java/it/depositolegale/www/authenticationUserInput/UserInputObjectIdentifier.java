/**
 * UserInputObjectIdentifier.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.authenticationUserInput;

public class UserInputObjectIdentifier  implements java.io.Serializable {
    private java.lang.String objectIdentifierType;

    private java.lang.String objectIdentifierValue;

    public UserInputObjectIdentifier() {
    }

    public UserInputObjectIdentifier(
           java.lang.String objectIdentifierType,
           java.lang.String objectIdentifierValue) {
           this.objectIdentifierType = objectIdentifierType;
           this.objectIdentifierValue = objectIdentifierValue;
    }


    /**
     * Gets the objectIdentifierType value for this UserInputObjectIdentifier.
     * 
     * @return objectIdentifierType
     */
    public java.lang.String getObjectIdentifierType() {
        return objectIdentifierType;
    }


    /**
     * Sets the objectIdentifierType value for this UserInputObjectIdentifier.
     * 
     * @param objectIdentifierType
     */
    public void setObjectIdentifierType(java.lang.String objectIdentifierType) {
        this.objectIdentifierType = objectIdentifierType;
    }


    /**
     * Gets the objectIdentifierValue value for this UserInputObjectIdentifier.
     * 
     * @return objectIdentifierValue
     */
    public java.lang.String getObjectIdentifierValue() {
        return objectIdentifierValue;
    }


    /**
     * Sets the objectIdentifierValue value for this UserInputObjectIdentifier.
     * 
     * @param objectIdentifierValue
     */
    public void setObjectIdentifierValue(java.lang.String objectIdentifierValue) {
        this.objectIdentifierValue = objectIdentifierValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserInputObjectIdentifier)) return false;
        UserInputObjectIdentifier other = (UserInputObjectIdentifier) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectIdentifierType==null && other.getObjectIdentifierType()==null) || 
             (this.objectIdentifierType!=null &&
              this.objectIdentifierType.equals(other.getObjectIdentifierType()))) &&
            ((this.objectIdentifierValue==null && other.getObjectIdentifierValue()==null) || 
             (this.objectIdentifierValue!=null &&
              this.objectIdentifierValue.equals(other.getObjectIdentifierValue())));
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
        if (getObjectIdentifierType() != null) {
            _hashCode += getObjectIdentifierType().hashCode();
        }
        if (getObjectIdentifierValue() != null) {
            _hashCode += getObjectIdentifierValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserInputObjectIdentifier.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">userInput>objectIdentifier"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectIdentifierType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "objectIdentifierType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectIdentifierValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "objectIdentifierValue"));
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
