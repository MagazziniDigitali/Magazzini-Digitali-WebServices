/**
 * ReadInfoInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.readInfoInput;

public class ReadInfoInput  implements java.io.Serializable {
    private it.depositolegale.www.istituto.Istituto istituto;

    private it.depositolegale.www.oggettiDigitali.OggettoDigitale oggettoDigitale;

    public ReadInfoInput() {
    }

    public ReadInfoInput(
           it.depositolegale.www.istituto.Istituto istituto,
           it.depositolegale.www.oggettiDigitali.OggettoDigitale oggettoDigitale) {
           this.istituto = istituto;
           this.oggettoDigitale = oggettoDigitale;
    }


    /**
     * Gets the istituto value for this ReadInfoInput.
     * 
     * @return istituto
     */
    public it.depositolegale.www.istituto.Istituto getIstituto() {
        return istituto;
    }


    /**
     * Sets the istituto value for this ReadInfoInput.
     * 
     * @param istituto
     */
    public void setIstituto(it.depositolegale.www.istituto.Istituto istituto) {
        this.istituto = istituto;
    }


    /**
     * Gets the oggettoDigitale value for this ReadInfoInput.
     * 
     * @return oggettoDigitale
     */
    public it.depositolegale.www.oggettiDigitali.OggettoDigitale getOggettoDigitale() {
        return oggettoDigitale;
    }


    /**
     * Sets the oggettoDigitale value for this ReadInfoInput.
     * 
     * @param oggettoDigitale
     */
    public void setOggettoDigitale(it.depositolegale.www.oggettiDigitali.OggettoDigitale oggettoDigitale) {
        this.oggettoDigitale = oggettoDigitale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReadInfoInput)) return false;
        ReadInfoInput other = (ReadInfoInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.istituto==null && other.getIstituto()==null) || 
             (this.istituto!=null &&
              this.istituto.equals(other.getIstituto()))) &&
            ((this.oggettoDigitale==null && other.getOggettoDigitale()==null) || 
             (this.oggettoDigitale!=null &&
              this.oggettoDigitale.equals(other.getOggettoDigitale())));
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
        if (getIstituto() != null) {
            _hashCode += getIstituto().hashCode();
        }
        if (getOggettoDigitale() != null) {
            _hashCode += getOggettoDigitale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReadInfoInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoInput", ">readInfoInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("istituto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", "istituto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", ">istituto"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggettoDigitale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "oggettoDigitale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", ">oggettoDigitale"));
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
