/**
 * Nodo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.nodi;

public class Nodo  implements java.io.Serializable {
    /* Identificativo */
    private java.lang.String id;

    /* Nome */
    private java.lang.String nome;

    /* Descrizione del Nodo */
    private java.lang.String descrizioni;

    private it.depositolegale.www.nodi.NodoStorage storage;

    public Nodo() {
    }

    public Nodo(
           java.lang.String id,
           java.lang.String nome,
           java.lang.String descrizioni,
           it.depositolegale.www.nodi.NodoStorage storage) {
           this.id = id;
           this.nome = nome;
           this.descrizioni = descrizioni;
           this.storage = storage;
    }


    /**
     * Gets the id value for this Nodo.
     * 
     * @return id   * Identificativo
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Nodo.
     * 
     * @param id   * Identificativo
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nome value for this Nodo.
     * 
     * @return nome   * Nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Nodo.
     * 
     * @param nome   * Nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the descrizioni value for this Nodo.
     * 
     * @return descrizioni   * Descrizione del Nodo
     */
    public java.lang.String getDescrizioni() {
        return descrizioni;
    }


    /**
     * Sets the descrizioni value for this Nodo.
     * 
     * @param descrizioni   * Descrizione del Nodo
     */
    public void setDescrizioni(java.lang.String descrizioni) {
        this.descrizioni = descrizioni;
    }


    /**
     * Gets the storage value for this Nodo.
     * 
     * @return storage
     */
    public it.depositolegale.www.nodi.NodoStorage getStorage() {
        return storage;
    }


    /**
     * Sets the storage value for this Nodo.
     * 
     * @param storage
     */
    public void setStorage(it.depositolegale.www.nodi.NodoStorage storage) {
        this.storage = storage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Nodo)) return false;
        Nodo other = (Nodo) obj;
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
            ((this.storage==null && other.getStorage()==null) || 
             (this.storage!=null &&
              this.storage.equals(other.getStorage())));
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
        if (getStorage() != null) {
            _hashCode += getStorage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Nodo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">nodo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioni");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "descrizioni"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "storage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>nodo>storage"));
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
