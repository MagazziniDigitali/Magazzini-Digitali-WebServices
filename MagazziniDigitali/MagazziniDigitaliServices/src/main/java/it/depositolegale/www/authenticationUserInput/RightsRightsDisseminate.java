/**
 * RightsRightsDisseminate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.authenticationUserInput;

public class RightsRightsDisseminate  implements java.io.Serializable {
    private it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType rightsDisseminateType;

    public RightsRightsDisseminate() {
    }

    public RightsRightsDisseminate(
           it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType rightsDisseminateType) {
           this.rightsDisseminateType = rightsDisseminateType;
    }


    /**
     * Gets the rightsDisseminateType value for this RightsRightsDisseminate.
     * 
     * @return rightsDisseminateType
     */
    public it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType getRightsDisseminateType() {
        return rightsDisseminateType;
    }


    /**
     * Sets the rightsDisseminateType value for this RightsRightsDisseminate.
     * 
     * @param rightsDisseminateType
     */
    public void setRightsDisseminateType(it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType rightsDisseminateType) {
        this.rightsDisseminateType = rightsDisseminateType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RightsRightsDisseminate)) return false;
        RightsRightsDisseminate other = (RightsRightsDisseminate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rightsDisseminateType==null && other.getRightsDisseminateType()==null) || 
             (this.rightsDisseminateType!=null &&
              this.rightsDisseminateType.equals(other.getRightsDisseminateType())));
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
        if (getRightsDisseminateType() != null) {
            _hashCode += getRightsDisseminateType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RightsRightsDisseminate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">>rights>rightsDisseminate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rightsDisseminateType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "rightsDisseminateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">>>rights>rightsDisseminate>rightsDisseminateType"));
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
