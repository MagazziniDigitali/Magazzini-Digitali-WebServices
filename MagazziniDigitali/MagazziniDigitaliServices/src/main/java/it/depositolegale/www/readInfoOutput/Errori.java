/**
 * Errori.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.readInfoOutput;

public class Errori  implements java.io.Serializable {
    /* Identificativo relativo al codice di Errore */
    private java.lang.String id;

    /* Messaggio di errore */
    private java.lang.String messaggio;

    public Errori() {
    }

    public Errori(
           java.lang.String id,
           java.lang.String messaggio) {
           this.id = id;
           this.messaggio = messaggio;
    }


    /**
     * Gets the id value for this Errori.
     * 
     * @return id   * Identificativo relativo al codice di Errore
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Errori.
     * 
     * @param id   * Identificativo relativo al codice di Errore
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the messaggio value for this Errori.
     * 
     * @return messaggio   * Messaggio di errore
     */
    public java.lang.String getMessaggio() {
        return messaggio;
    }


    /**
     * Sets the messaggio value for this Errori.
     * 
     * @param messaggio   * Messaggio di errore
     */
    public void setMessaggio(java.lang.String messaggio) {
        this.messaggio = messaggio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Errori)) return false;
        Errori other = (Errori) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.messaggio==null && other.getMessaggio()==null) || 
             (this.messaggio!=null &&
              this.messaggio.equals(other.getMessaggio())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMessaggio() != null) {
            _hashCode += getMessaggio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Errori.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", ">errori"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messaggio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/readInfoOutput", "messaggio"));
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
