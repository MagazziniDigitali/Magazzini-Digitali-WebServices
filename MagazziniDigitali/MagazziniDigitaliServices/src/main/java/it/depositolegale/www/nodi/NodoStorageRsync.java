/**
 * NodoStorageRsync.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.nodi;

public class NodoStorageRsync  implements java.io.Serializable {
    /* Url per il collegamento Rsync */
    private java.lang.String urlRsync;

    /* PAssword per il collegamento Rsync */
    private java.lang.String password;

    /* Url per eseguire il Check dei dati sullo Storage */
    private org.apache.axis.types.URI urlCheckStorage;

    public NodoStorageRsync() {
    }

    public NodoStorageRsync(
           java.lang.String urlRsync,
           java.lang.String password,
           org.apache.axis.types.URI urlCheckStorage) {
           this.urlRsync = urlRsync;
           this.password = password;
           this.urlCheckStorage = urlCheckStorage;
    }


    /**
     * Gets the urlRsync value for this NodoStorageRsync.
     * 
     * @return urlRsync   * Url per il collegamento Rsync
     */
    public java.lang.String getUrlRsync() {
        return urlRsync;
    }


    /**
     * Sets the urlRsync value for this NodoStorageRsync.
     * 
     * @param urlRsync   * Url per il collegamento Rsync
     */
    public void setUrlRsync(java.lang.String urlRsync) {
        this.urlRsync = urlRsync;
    }


    /**
     * Gets the password value for this NodoStorageRsync.
     * 
     * @return password   * PAssword per il collegamento Rsync
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this NodoStorageRsync.
     * 
     * @param password   * PAssword per il collegamento Rsync
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the urlCheckStorage value for this NodoStorageRsync.
     * 
     * @return urlCheckStorage   * Url per eseguire il Check dei dati sullo Storage
     */
    public org.apache.axis.types.URI getUrlCheckStorage() {
        return urlCheckStorage;
    }


    /**
     * Sets the urlCheckStorage value for this NodoStorageRsync.
     * 
     * @param urlCheckStorage   * Url per eseguire il Check dei dati sullo Storage
     */
    public void setUrlCheckStorage(org.apache.axis.types.URI urlCheckStorage) {
        this.urlCheckStorage = urlCheckStorage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NodoStorageRsync)) return false;
        NodoStorageRsync other = (NodoStorageRsync) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.urlRsync==null && other.getUrlRsync()==null) || 
             (this.urlRsync!=null &&
              this.urlRsync.equals(other.getUrlRsync()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.urlCheckStorage==null && other.getUrlCheckStorage()==null) || 
             (this.urlCheckStorage!=null &&
              this.urlCheckStorage.equals(other.getUrlCheckStorage())));
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
        if (getUrlRsync() != null) {
            _hashCode += getUrlRsync().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUrlCheckStorage() != null) {
            _hashCode += getUrlCheckStorage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NodoStorageRsync.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>>nodo>storage>rsync"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlRsync");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "urlRsync"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlCheckStorage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "urlCheckStorage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
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
