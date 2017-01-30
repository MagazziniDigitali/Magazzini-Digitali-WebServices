/**
 * AuthenticationUserInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.authenticationUserInput;

public class AuthenticationUserInput  implements java.io.Serializable {
    private it.depositolegale.www.authenticationUserInput.UserInput userInput;

    public AuthenticationUserInput() {
    }

    public AuthenticationUserInput(
           it.depositolegale.www.authenticationUserInput.UserInput userInput) {
           this.userInput = userInput;
    }


    /**
     * Gets the userInput value for this AuthenticationUserInput.
     * 
     * @return userInput
     */
    public it.depositolegale.www.authenticationUserInput.UserInput getUserInput() {
        return userInput;
    }


    /**
     * Sets the userInput value for this AuthenticationUserInput.
     * 
     * @param userInput
     */
    public void setUserInput(it.depositolegale.www.authenticationUserInput.UserInput userInput) {
        this.userInput = userInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthenticationUserInput)) return false;
        AuthenticationUserInput other = (AuthenticationUserInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userInput==null && other.getUserInput()==null) || 
             (this.userInput!=null &&
              this.userInput.equals(other.getUserInput())));
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
        if (getUserInput() != null) {
            _hashCode += getUserInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthenticationUserInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">authenticationUserInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userInput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "userInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "userInput"));
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
