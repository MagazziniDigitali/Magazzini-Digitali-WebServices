/**
 * AuthenticationUtentiSoftwareIstituzioneApiUtente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.loginUtenti;

public class AuthenticationUtentiSoftwareIstituzioneApiUtente  implements java.io.Serializable {
    private org.apache.axis.types.URI interfaccia;

    private java.lang.String libreria;

    public AuthenticationUtentiSoftwareIstituzioneApiUtente() {
    }

    public AuthenticationUtentiSoftwareIstituzioneApiUtente(
           org.apache.axis.types.URI interfaccia,
           java.lang.String libreria) {
           this.interfaccia = interfaccia;
           this.libreria = libreria;
    }


    /**
     * Gets the interfaccia value for this AuthenticationUtentiSoftwareIstituzioneApiUtente.
     * 
     * @return interfaccia
     */
    public org.apache.axis.types.URI getInterfaccia() {
        return interfaccia;
    }


    /**
     * Sets the interfaccia value for this AuthenticationUtentiSoftwareIstituzioneApiUtente.
     * 
     * @param interfaccia
     */
    public void setInterfaccia(org.apache.axis.types.URI interfaccia) {
        this.interfaccia = interfaccia;
    }


    /**
     * Gets the libreria value for this AuthenticationUtentiSoftwareIstituzioneApiUtente.
     * 
     * @return libreria
     */
    public java.lang.String getLibreria() {
        return libreria;
    }


    /**
     * Sets the libreria value for this AuthenticationUtentiSoftwareIstituzioneApiUtente.
     * 
     * @param libreria
     */
    public void setLibreria(java.lang.String libreria) {
        this.libreria = libreria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthenticationUtentiSoftwareIstituzioneApiUtente)) return false;
        AuthenticationUtentiSoftwareIstituzioneApiUtente other = (AuthenticationUtentiSoftwareIstituzioneApiUtente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.interfaccia==null && other.getInterfaccia()==null) || 
             (this.interfaccia!=null &&
              this.interfaccia.equals(other.getInterfaccia()))) &&
            ((this.libreria==null && other.getLibreria()==null) || 
             (this.libreria!=null &&
              this.libreria.equals(other.getLibreria())));
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
        if (getInterfaccia() != null) {
            _hashCode += getInterfaccia().hashCode();
        }
        if (getLibreria() != null) {
            _hashCode += getLibreria().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthenticationUtentiSoftwareIstituzioneApiUtente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", ">>>>authenticationUtenti>software>istituzione>apiUtente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interfaccia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", "Interfaccia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("libreria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", "libreria"));
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
