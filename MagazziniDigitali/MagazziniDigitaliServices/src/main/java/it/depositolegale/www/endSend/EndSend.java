/**
 * EndSend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.endSend;

public class EndSend  implements java.io.Serializable {
    private it.depositolegale.www.readInfoOutput.ReadInfoOutput readInfoOutput;

    /* Esito dell'elaborazione */
    private boolean esito;

    private it.depositolegale.www.readInfoOutput.Errori[] errori;

    private it.depositolegale.www.readInfoOutput.Warning[] warning;

    private it.depositolegale.www.readInfoOutput.Info[] info;

    public EndSend() {
    }

    public EndSend(
           it.depositolegale.www.readInfoOutput.ReadInfoOutput readInfoOutput,
           boolean esito,
           it.depositolegale.www.readInfoOutput.Errori[] errori,
           it.depositolegale.www.readInfoOutput.Warning[] warning,
           it.depositolegale.www.readInfoOutput.Info[] info) {
           this.readInfoOutput = readInfoOutput;
           this.esito = esito;
           this.errori = errori;
           this.warning = warning;
           this.info = info;
    }


    /**
     * Gets the readInfoOutput value for this EndSend.
     * 
     * @return readInfoOutput
     */
    public it.depositolegale.www.readInfoOutput.ReadInfoOutput getReadInfoOutput() {
        return readInfoOutput;
    }


    /**
     * Sets the readInfoOutput value for this EndSend.
     * 
     * @param readInfoOutput
     */
    public void setReadInfoOutput(it.depositolegale.www.readInfoOutput.ReadInfoOutput readInfoOutput) {
        this.readInfoOutput = readInfoOutput;
    }


    /**
     * Gets the esito value for this EndSend.
     * 
     * @return esito   * Esito dell'elaborazione
     */
    public boolean isEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this EndSend.
     * 
     * @param esito   * Esito dell'elaborazione
     */
    public void setEsito(boolean esito) {
        this.esito = esito;
    }


    /**
     * Gets the errori value for this EndSend.
     * 
     * @return errori
     */
    public it.depositolegale.www.readInfoOutput.Errori[] getErrori() {
        return errori;
    }


    /**
     * Sets the errori value for this EndSend.
     * 
     * @param errori
     */
    public void setErrori(it.depositolegale.www.readInfoOutput.Errori[] errori) {
        this.errori = errori;
    }

    public it.depositolegale.www.readInfoOutput.Errori getErrori(int i) {
        return this.errori[i];
    }

    public void setErrori(int i, it.depositolegale.www.readInfoOutput.Errori _value) {
        this.errori[i] = _value;
    }


    /**
     * Gets the warning value for this EndSend.
     * 
     * @return warning
     */
    public it.depositolegale.www.readInfoOutput.Warning[] getWarning() {
        return warning;
    }


    /**
     * Sets the warning value for this EndSend.
     * 
     * @param warning
     */
    public void setWarning(it.depositolegale.www.readInfoOutput.Warning[] warning) {
        this.warning = warning;
    }

    public it.depositolegale.www.readInfoOutput.Warning getWarning(int i) {
        return this.warning[i];
    }

    public void setWarning(int i, it.depositolegale.www.readInfoOutput.Warning _value) {
        this.warning[i] = _value;
    }


    /**
     * Gets the info value for this EndSend.
     * 
     * @return info
     */
    public it.depositolegale.www.readInfoOutput.Info[] getInfo() {
        return info;
    }


    /**
     * Sets the info value for this EndSend.
     * 
     * @param info
     */
    public void setInfo(it.depositolegale.www.readInfoOutput.Info[] info) {
        this.info = info;
    }

    public it.depositolegale.www.readInfoOutput.Info getInfo(int i) {
        return this.info[i];
    }

    public void setInfo(int i, it.depositolegale.www.readInfoOutput.Info _value) {
        this.info[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndSend)) return false;
        EndSend other = (EndSend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.readInfoOutput==null && other.getReadInfoOutput()==null) || 
             (this.readInfoOutput!=null &&
              this.readInfoOutput.equals(other.getReadInfoOutput()))) &&
            this.esito == other.isEsito() &&
            ((this.errori==null && other.getErrori()==null) || 
             (this.errori!=null &&
              java.util.Arrays.equals(this.errori, other.getErrori()))) &&
            ((this.warning==null && other.getWarning()==null) || 
             (this.warning!=null &&
              java.util.Arrays.equals(this.warning, other.getWarning()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              java.util.Arrays.equals(this.info, other.getInfo())));
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
        if (getReadInfoOutput() != null) {
            _hashCode += getReadInfoOutput().hashCode();
        }
        _hashCode += (isEsito() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getErrori() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrori());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrori(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWarning() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getWarning());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getWarning(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInfo(), i);
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
        new org.apache.axis.description.TypeDesc(EndSend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/endSend", ">endSend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("readInfoOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "readInfoOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", ">readInfoOutput"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/endSend", "esito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errori");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "errori"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "errori"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("warning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "warning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "warning"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "info"));
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
