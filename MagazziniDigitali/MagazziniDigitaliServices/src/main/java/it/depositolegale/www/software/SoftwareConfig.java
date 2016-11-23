/**
 * SoftwareConfig.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.software;

public class SoftwareConfig  implements java.io.Serializable {
    /* Identificativo */
    private java.lang.String id;

    /* Nome della chiave del Parametro */
    private java.lang.String nome;

    /* Eventuale Descrizione */
    private java.lang.String descrizione;

    /* Valore assegnato al Parametro */
    private java.lang.String value;

    private it.depositolegale.www.nodi.Nodo nodo;

    public SoftwareConfig() {
    }

    public SoftwareConfig(
           java.lang.String id,
           java.lang.String nome,
           java.lang.String descrizione,
           java.lang.String value,
           it.depositolegale.www.nodi.Nodo nodo) {
           this.id = id;
           this.nome = nome;
           this.descrizione = descrizione;
           this.value = value;
           this.nodo = nodo;
    }


    /**
     * Gets the id value for this SoftwareConfig.
     * 
     * @return id   * Identificativo
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this SoftwareConfig.
     * 
     * @param id   * Identificativo
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nome value for this SoftwareConfig.
     * 
     * @return nome   * Nome della chiave del Parametro
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this SoftwareConfig.
     * 
     * @param nome   * Nome della chiave del Parametro
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the descrizione value for this SoftwareConfig.
     * 
     * @return descrizione   * Eventuale Descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this SoftwareConfig.
     * 
     * @param descrizione   * Eventuale Descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the value value for this SoftwareConfig.
     * 
     * @return value   * Valore assegnato al Parametro
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this SoftwareConfig.
     * 
     * @param value   * Valore assegnato al Parametro
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the nodo value for this SoftwareConfig.
     * 
     * @return nodo
     */
    public it.depositolegale.www.nodi.Nodo getNodo() {
        return nodo;
    }


    /**
     * Sets the nodo value for this SoftwareConfig.
     * 
     * @param nodo
     */
    public void setNodo(it.depositolegale.www.nodi.Nodo nodo) {
        this.nodo = nodo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SoftwareConfig)) return false;
        SoftwareConfig other = (SoftwareConfig) obj;
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
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.nodo==null && other.getNodo()==null) || 
             (this.nodo!=null &&
              this.nodo.equals(other.getNodo())));
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
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getNodo() != null) {
            _hashCode += getNodo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SoftwareConfig.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/software", ">softwareConfig"));
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
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "nodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">nodo"));
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
