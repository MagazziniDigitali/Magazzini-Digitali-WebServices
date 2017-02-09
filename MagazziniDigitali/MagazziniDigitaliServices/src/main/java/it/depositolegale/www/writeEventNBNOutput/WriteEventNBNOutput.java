/**
 * WriteEventNBNOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.writeEventNBNOutput;

public class WriteEventNBNOutput  implements java.io.Serializable {
    private it.depositolegale.www.writeEventNBN.WriteEventNBN writeEventNBN;

    private it.depositolegale.www.writeEventNBNOutput.WriteEventNBNOutputEsito esito;

    private it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg;

    public WriteEventNBNOutput() {
    }

    public WriteEventNBNOutput(
           it.depositolegale.www.writeEventNBN.WriteEventNBN writeEventNBN,
           it.depositolegale.www.writeEventNBNOutput.WriteEventNBNOutputEsito esito,
           it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg) {
           this.writeEventNBN = writeEventNBN;
           this.esito = esito;
           this.errorMsg = errorMsg;
    }


    /**
     * Gets the writeEventNBN value for this WriteEventNBNOutput.
     * 
     * @return writeEventNBN
     */
    public it.depositolegale.www.writeEventNBN.WriteEventNBN getWriteEventNBN() {
        return writeEventNBN;
    }


    /**
     * Sets the writeEventNBN value for this WriteEventNBNOutput.
     * 
     * @param writeEventNBN
     */
    public void setWriteEventNBN(it.depositolegale.www.writeEventNBN.WriteEventNBN writeEventNBN) {
        this.writeEventNBN = writeEventNBN;
    }


    /**
     * Gets the esito value for this WriteEventNBNOutput.
     * 
     * @return esito
     */
    public it.depositolegale.www.writeEventNBNOutput.WriteEventNBNOutputEsito getEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this WriteEventNBNOutput.
     * 
     * @param esito
     */
    public void setEsito(it.depositolegale.www.writeEventNBNOutput.WriteEventNBNOutputEsito esito) {
        this.esito = esito;
    }


    /**
     * Gets the errorMsg value for this WriteEventNBNOutput.
     * 
     * @return errorMsg
     */
    public it.depositolegale.www.errorMsg.ErrorMsg[] getErrorMsg() {
        return errorMsg;
    }


    /**
     * Sets the errorMsg value for this WriteEventNBNOutput.
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
        if (!(obj instanceof WriteEventNBNOutput)) return false;
        WriteEventNBNOutput other = (WriteEventNBNOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.writeEventNBN==null && other.getWriteEventNBN()==null) || 
             (this.writeEventNBN!=null &&
              this.writeEventNBN.equals(other.getWriteEventNBN()))) &&
            ((this.esito==null && other.getEsito()==null) || 
             (this.esito!=null &&
              this.esito.equals(other.getEsito()))) &&
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
        if (getWriteEventNBN() != null) {
            _hashCode += getWriteEventNBN().hashCode();
        }
        if (getEsito() != null) {
            _hashCode += getEsito().hashCode();
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
        new org.apache.axis.description.TypeDesc(WriteEventNBNOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBNOutput", ">writeEventNBNOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("writeEventNBN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBN", "writeEventNBN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBN", ">writeEventNBN"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBNOutput", "esito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/writeEventNBNOutput", ">>writeEventNBNOutput>esito"));
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
