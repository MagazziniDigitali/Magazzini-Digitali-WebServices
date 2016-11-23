/**
 * MdIstituzione.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.istituzione;

public class MdIstituzione  implements java.io.Serializable {
    private it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtenti;

    private it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg;

    private it.depositolegale.www.istituzione.Istituzione istituzione;

    public MdIstituzione() {
    }

    public MdIstituzione(
           it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtenti,
           it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg,
           it.depositolegale.www.istituzione.Istituzione istituzione) {
           this.authenticationUtenti = authenticationUtenti;
           this.errorMsg = errorMsg;
           this.istituzione = istituzione;
    }


    /**
     * Gets the authenticationUtenti value for this MdIstituzione.
     * 
     * @return authenticationUtenti
     */
    public it.depositolegale.www.loginUtenti.AuthenticationUtenti getAuthenticationUtenti() {
        return authenticationUtenti;
    }


    /**
     * Sets the authenticationUtenti value for this MdIstituzione.
     * 
     * @param authenticationUtenti
     */
    public void setAuthenticationUtenti(it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtenti) {
        this.authenticationUtenti = authenticationUtenti;
    }


    /**
     * Gets the errorMsg value for this MdIstituzione.
     * 
     * @return errorMsg
     */
    public it.depositolegale.www.errorMsg.ErrorMsg[] getErrorMsg() {
        return errorMsg;
    }


    /**
     * Sets the errorMsg value for this MdIstituzione.
     * 
     * @param errorMsg
     */
    public void setErrorMsg(it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg) {
        this.errorMsg = errorMsg;
    }

    public it.depositolegale.www.errorMsg.ErrorMsg getErrorMsg(int i) {
        return this.errorMsg[i];
    }

    public void setErrorMsg(int i, it.depositolegale.www.errorMsg.ErrorMsg _value) {
        this.errorMsg[i] = _value;
    }


    /**
     * Gets the istituzione value for this MdIstituzione.
     * 
     * @return istituzione
     */
    public it.depositolegale.www.istituzione.Istituzione getIstituzione() {
        return istituzione;
    }


    /**
     * Sets the istituzione value for this MdIstituzione.
     * 
     * @param istituzione
     */
    public void setIstituzione(it.depositolegale.www.istituzione.Istituzione istituzione) {
        this.istituzione = istituzione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MdIstituzione)) return false;
        MdIstituzione other = (MdIstituzione) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authenticationUtenti==null && other.getAuthenticationUtenti()==null) || 
             (this.authenticationUtenti!=null &&
              this.authenticationUtenti.equals(other.getAuthenticationUtenti()))) &&
            ((this.errorMsg==null && other.getErrorMsg()==null) || 
             (this.errorMsg!=null &&
              java.util.Arrays.equals(this.errorMsg, other.getErrorMsg()))) &&
            ((this.istituzione==null && other.getIstituzione()==null) || 
             (this.istituzione!=null &&
              this.istituzione.equals(other.getIstituzione())));
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
        if (getAuthenticationUtenti() != null) {
            _hashCode += getAuthenticationUtenti().hashCode();
        }
        if (getErrorMsg() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorMsg());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorMsg(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIstituzione() != null) {
            _hashCode += getIstituzione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MdIstituzione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", ">mdIstituzione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticationUtenti");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", "authenticationUtenti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", ">authenticationUtenti"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorMsg"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("istituzione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "istituzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", ">istituzione"));
        elemField.setMinOccurs(0);
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
