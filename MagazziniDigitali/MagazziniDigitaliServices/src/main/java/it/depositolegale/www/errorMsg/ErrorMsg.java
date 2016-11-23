/**
 * ErrorMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.errorMsg;

public class ErrorMsg  implements java.io.Serializable {
    /* Indica il Tipo di Errore */
    private it.depositolegale.www.errorMsg.ErrorType_type errorType;

    /* Messaggio di Errore */
    private java.lang.String msgError;

    public ErrorMsg() {
    }

    public ErrorMsg(
           it.depositolegale.www.errorMsg.ErrorType_type errorType,
           java.lang.String msgError) {
           this.errorType = errorType;
           this.msgError = msgError;
    }


    /**
     * Gets the errorType value for this ErrorMsg.
     * 
     * @return errorType   * Indica il Tipo di Errore
     */
    public it.depositolegale.www.errorMsg.ErrorType_type getErrorType() {
        return errorType;
    }


    /**
     * Sets the errorType value for this ErrorMsg.
     * 
     * @param errorType   * Indica il Tipo di Errore
     */
    public void setErrorType(it.depositolegale.www.errorMsg.ErrorType_type errorType) {
        this.errorType = errorType;
    }


    /**
     * Gets the msgError value for this ErrorMsg.
     * 
     * @return msgError   * Messaggio di Errore
     */
    public java.lang.String getMsgError() {
        return msgError;
    }


    /**
     * Sets the msgError value for this ErrorMsg.
     * 
     * @param msgError   * Messaggio di Errore
     */
    public void setMsgError(java.lang.String msgError) {
        this.msgError = msgError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorMsg)) return false;
        ErrorMsg other = (ErrorMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorType==null && other.getErrorType()==null) || 
             (this.errorType!=null &&
              this.errorType.equals(other.getErrorType()))) &&
            ((this.msgError==null && other.getMsgError()==null) || 
             (this.msgError!=null &&
              this.msgError.equals(other.getMsgError())));
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
        if (getErrorType() != null) {
            _hashCode += getErrorType().hashCode();
        }
        if (getMsgError() != null) {
            _hashCode += getMsgError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", ">errorMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorType_type"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "msgError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
