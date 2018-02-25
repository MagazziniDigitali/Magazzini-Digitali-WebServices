/**
 * NodoStorage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.nodi;

public class NodoStorage  implements java.io.Serializable {
    /* Informazioni relativi agli storage di tipo FileSystem */
    private java.lang.String fileSystem;

    /* Informazioni per il collegamento Rsync */
    private it.depositolegale.www.nodi.NodoStorageRsync rsync;

    /* Informazioni relativi agli storafe di tipo S3 */
    private it.depositolegale.www.nodi.NodoStorageS3 s3;

    private it.depositolegale.www.nodi.NodoStorageTipo tipo;  // attribute

    public NodoStorage() {
    }

    public NodoStorage(
           java.lang.String fileSystem,
           it.depositolegale.www.nodi.NodoStorageRsync rsync,
           it.depositolegale.www.nodi.NodoStorageS3 s3,
           it.depositolegale.www.nodi.NodoStorageTipo tipo) {
           this.fileSystem = fileSystem;
           this.rsync = rsync;
           this.s3 = s3;
           this.tipo = tipo;
    }


    /**
     * Gets the fileSystem value for this NodoStorage.
     * 
     * @return fileSystem   * Informazioni relativi agli storage di tipo FileSystem
     */
    public java.lang.String getFileSystem() {
        return fileSystem;
    }


    /**
     * Sets the fileSystem value for this NodoStorage.
     * 
     * @param fileSystem   * Informazioni relativi agli storage di tipo FileSystem
     */
    public void setFileSystem(java.lang.String fileSystem) {
        this.fileSystem = fileSystem;
    }


    /**
     * Gets the rsync value for this NodoStorage.
     * 
     * @return rsync   * Informazioni per il collegamento Rsync
     */
    public it.depositolegale.www.nodi.NodoStorageRsync getRsync() {
        return rsync;
    }


    /**
     * Sets the rsync value for this NodoStorage.
     * 
     * @param rsync   * Informazioni per il collegamento Rsync
     */
    public void setRsync(it.depositolegale.www.nodi.NodoStorageRsync rsync) {
        this.rsync = rsync;
    }


    /**
     * Gets the s3 value for this NodoStorage.
     * 
     * @return s3   * Informazioni relativi agli storafe di tipo S3
     */
    public it.depositolegale.www.nodi.NodoStorageS3 getS3() {
        return s3;
    }


    /**
     * Sets the s3 value for this NodoStorage.
     * 
     * @param s3   * Informazioni relativi agli storafe di tipo S3
     */
    public void setS3(it.depositolegale.www.nodi.NodoStorageS3 s3) {
        this.s3 = s3;
    }


    /**
     * Gets the tipo value for this NodoStorage.
     * 
     * @return tipo
     */
    public it.depositolegale.www.nodi.NodoStorageTipo getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this NodoStorage.
     * 
     * @param tipo
     */
    public void setTipo(it.depositolegale.www.nodi.NodoStorageTipo tipo) {
        this.tipo = tipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NodoStorage)) return false;
        NodoStorage other = (NodoStorage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileSystem==null && other.getFileSystem()==null) || 
             (this.fileSystem!=null &&
              this.fileSystem.equals(other.getFileSystem()))) &&
            ((this.rsync==null && other.getRsync()==null) || 
             (this.rsync!=null &&
              this.rsync.equals(other.getRsync()))) &&
            ((this.s3==null && other.getS3()==null) || 
             (this.s3!=null &&
              this.s3.equals(other.getS3()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo())));
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
        if (getFileSystem() != null) {
            _hashCode += getFileSystem().hashCode();
        }
        if (getRsync() != null) {
            _hashCode += getRsync().hashCode();
        }
        if (getS3() != null) {
            _hashCode += getS3().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NodoStorage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>nodo>storage"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("tipo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>>nodo>storage>tipo"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "fileSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rsync");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "rsync"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>>nodo>storage>rsync"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("s3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "s3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>>nodo>storage>s3"));
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
