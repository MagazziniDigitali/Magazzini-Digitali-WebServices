/**
 * ReadInfoOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.readInfoOutput;

public class ReadInfoOutput  implements java.io.Serializable {
    private it.depositolegale.www.software.Software software;

    private it.depositolegale.www.oggettiDigitali.OggettoDigitale oggettoDigitale;

    private it.depositolegale.www.readInfoOutput.Errori[] errori;

    private it.depositolegale.www.readInfoOutput.Warning[] warning;

    private it.depositolegale.www.readInfoOutput.Info[] info;

    public ReadInfoOutput() {
    }

    public ReadInfoOutput(
           it.depositolegale.www.software.Software software,
           it.depositolegale.www.oggettiDigitali.OggettoDigitale oggettoDigitale,
           it.depositolegale.www.readInfoOutput.Errori[] errori,
           it.depositolegale.www.readInfoOutput.Warning[] warning,
           it.depositolegale.www.readInfoOutput.Info[] info) {
           this.software = software;
           this.oggettoDigitale = oggettoDigitale;
           this.errori = errori;
           this.warning = warning;
           this.info = info;
    }


    /**
     * Gets the software value for this ReadInfoOutput.
     * 
     * @return software
     */
    public it.depositolegale.www.software.Software getSoftware() {
        return software;
    }


    /**
     * Sets the software value for this ReadInfoOutput.
     * 
     * @param software
     */
    public void setSoftware(it.depositolegale.www.software.Software software) {
        this.software = software;
    }


    /**
     * Gets the oggettoDigitale value for this ReadInfoOutput.
     * 
     * @return oggettoDigitale
     */
    public it.depositolegale.www.oggettiDigitali.OggettoDigitale getOggettoDigitale() {
        return oggettoDigitale;
    }


    /**
     * Sets the oggettoDigitale value for this ReadInfoOutput.
     * 
     * @param oggettoDigitale
     */
    public void setOggettoDigitale(it.depositolegale.www.oggettiDigitali.OggettoDigitale oggettoDigitale) {
        this.oggettoDigitale = oggettoDigitale;
    }


    /**
     * Gets the errori value for this ReadInfoOutput.
     * 
     * @return errori
     */
    public it.depositolegale.www.readInfoOutput.Errori[] getErrori() {
        return errori;
    }


    /**
     * Sets the errori value for this ReadInfoOutput.
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
     * Gets the warning value for this ReadInfoOutput.
     * 
     * @return warning
     */
    public it.depositolegale.www.readInfoOutput.Warning[] getWarning() {
        return warning;
    }


    /**
     * Sets the warning value for this ReadInfoOutput.
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
     * Gets the info value for this ReadInfoOutput.
     * 
     * @return info
     */
    public it.depositolegale.www.readInfoOutput.Info[] getInfo() {
        return info;
    }


    /**
     * Sets the info value for this ReadInfoOutput.
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
        if (!(obj instanceof ReadInfoOutput)) return false;
        ReadInfoOutput other = (ReadInfoOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.software==null && other.getSoftware()==null) || 
             (this.software!=null &&
              this.software.equals(other.getSoftware()))) &&
            ((this.oggettoDigitale==null && other.getOggettoDigitale()==null) || 
             (this.oggettoDigitale!=null &&
              this.oggettoDigitale.equals(other.getOggettoDigitale()))) &&
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
        if (getSoftware() != null) {
            _hashCode += getSoftware().hashCode();
        }
        if (getOggettoDigitale() != null) {
            _hashCode += getOggettoDigitale().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(ReadInfoOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", ">readInfoOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("software");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "software"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/software", ">software"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggettoDigitale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "oggettoDigitale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", ">oggettoDigitale"));
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
