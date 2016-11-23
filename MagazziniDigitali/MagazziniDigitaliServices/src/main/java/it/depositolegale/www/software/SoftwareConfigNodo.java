/**
 * SoftwareConfigNodo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.software;

public class SoftwareConfigNodo  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String nome;

    private java.lang.String descrizioni;

    private it.depositolegale.www.software.SoftwareConfigNodoRsync rsync;

    private org.apache.axis.types.URI urlCheckStorage;

    public SoftwareConfigNodo() {
    }

    public SoftwareConfigNodo(
           java.lang.String id,
           java.lang.String nome,
           java.lang.String descrizioni,
           it.depositolegale.www.software.SoftwareConfigNodoRsync rsync,
           org.apache.axis.types.URI urlCheckStorage) {
           this.id = id;
           this.nome = nome;
           this.descrizioni = descrizioni;
           this.rsync = rsync;
           this.urlCheckStorage = urlCheckStorage;
    }


    /**
     * Gets the id value for this SoftwareConfigNodo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this SoftwareConfigNodo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nome value for this SoftwareConfigNodo.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this SoftwareConfigNodo.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the descrizioni value for this SoftwareConfigNodo.
     * 
     * @return descrizioni
     */
    public java.lang.String getDescrizioni() {
        return descrizioni;
    }


    /**
     * Sets the descrizioni value for this SoftwareConfigNodo.
     * 
     * @param descrizioni
     */
    public void setDescrizioni(java.lang.String descrizioni) {
        this.descrizioni = descrizioni;
    }


    /**
     * Gets the rsync value for this SoftwareConfigNodo.
     * 
     * @return rsync
     */
    public it.depositolegale.www.software.SoftwareConfigNodoRsync getRsync() {
        return rsync;
    }


    /**
     * Sets the rsync value for this SoftwareConfigNodo.
     * 
     * @param rsync
     */
    public void setRsync(it.depositolegale.www.software.SoftwareConfigNodoRsync rsync) {
        this.rsync = rsync;
    }


    /**
     * Gets the urlCheckStorage value for this SoftwareConfigNodo.
     * 
     * @return urlCheckStorage
     */
    public org.apache.axis.types.URI getUrlCheckStorage() {
        return urlCheckStorage;
    }


    /**
     * Sets the urlCheckStorage value for this SoftwareConfigNodo.
     * 
     * @param urlCheckStorage
     */
    public void setUrlCheckStorage(org.apache.axis.types.URI urlCheckStorage) {
        this.urlCheckStorage = urlCheckStorage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SoftwareConfigNodo)) return false;
        SoftwareConfigNodo other = (SoftwareConfigNodo) obj;
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
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.descrizioni==null && other.getDescrizioni()==null) || 
             (this.descrizioni!=null &&
              this.descrizioni.equals(other.getDescrizioni()))) &&
            ((this.rsync==null && other.getRsync()==null) || 
             (this.rsync!=null &&
              this.rsync.equals(other.getRsync()))) &&
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getDescrizioni() != null) {
            _hashCode += getDescrizioni().hashCode();
        }
        if (getRsync() != null) {
            _hashCode += getRsync().hashCode();
        }
        if (getUrlCheckStorage() != null) {
            _hashCode += getUrlCheckStorage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SoftwareConfigNodo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/software", ">softwareConfig>nodo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioni");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "descrizioni"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rsync");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "rsync"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/software", ">>softwareConfig>nodo>rsync"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlCheckStorage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "urlCheckStorage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
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
