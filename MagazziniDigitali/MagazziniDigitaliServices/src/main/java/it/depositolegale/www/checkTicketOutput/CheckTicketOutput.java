/**
 * CheckTicketOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.checkTicketOutput;

public class CheckTicketOutput  implements java.io.Serializable {
    private it.depositolegale.www.checkTicket.CheckTicket checkTicket;

    private it.depositolegale.www.checkTicketOutput.CheckTicketOutputTipo tipo;

    private java.lang.String url;

    private it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg;

    public CheckTicketOutput() {
    }

    public CheckTicketOutput(
           it.depositolegale.www.checkTicket.CheckTicket checkTicket,
           it.depositolegale.www.checkTicketOutput.CheckTicketOutputTipo tipo,
           java.lang.String url,
           it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg) {
           this.checkTicket = checkTicket;
           this.tipo = tipo;
           this.url = url;
           this.errorMsg = errorMsg;
    }


    /**
     * Gets the checkTicket value for this CheckTicketOutput.
     * 
     * @return checkTicket
     */
    public it.depositolegale.www.checkTicket.CheckTicket getCheckTicket() {
        return checkTicket;
    }


    /**
     * Sets the checkTicket value for this CheckTicketOutput.
     * 
     * @param checkTicket
     */
    public void setCheckTicket(it.depositolegale.www.checkTicket.CheckTicket checkTicket) {
        this.checkTicket = checkTicket;
    }


    /**
     * Gets the tipo value for this CheckTicketOutput.
     * 
     * @return tipo
     */
    public it.depositolegale.www.checkTicketOutput.CheckTicketOutputTipo getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this CheckTicketOutput.
     * 
     * @param tipo
     */
    public void setTipo(it.depositolegale.www.checkTicketOutput.CheckTicketOutputTipo tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the url value for this CheckTicketOutput.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this CheckTicketOutput.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the errorMsg value for this CheckTicketOutput.
     * 
     * @return errorMsg
     */
    public it.depositolegale.www.errorMsg.ErrorMsg[] getErrorMsg() {
        return errorMsg;
    }


    /**
     * Sets the errorMsg value for this CheckTicketOutput.
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

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckTicketOutput)) return false;
        CheckTicketOutput other = (CheckTicketOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.checkTicket==null && other.getCheckTicket()==null) || 
             (this.checkTicket!=null &&
              this.checkTicket.equals(other.getCheckTicket()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.errorMsg==null && other.getErrorMsg()==null) || 
             (this.errorMsg!=null &&
              java.util.Arrays.equals(this.errorMsg, other.getErrorMsg())));
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
        if (getCheckTicket() != null) {
            _hashCode += getCheckTicket().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckTicketOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicketOutput", ">checkTicketOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkTicket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", "checkTicket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", ">checkTicket"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicketOutput", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicketOutput", ">>checkTicketOutput>tipo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicketOutput", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
