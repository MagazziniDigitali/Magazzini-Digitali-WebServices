/**
 * Istituto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.istituto;

public class Istituto  implements java.io.Serializable {
    /* Identificativo dell'istituto */
    private java.lang.String id;

    /* Nome dell'istituto */
    private java.lang.String nome;

    /* Password di validazione dell'istituto */
    private java.lang.String password;

    private it.depositolegale.www.istituto.StatoIstituto_type statoIstituto;

    public Istituto() {
    }

    public Istituto(
           java.lang.String id,
           java.lang.String nome,
           java.lang.String password,
           it.depositolegale.www.istituto.StatoIstituto_type statoIstituto) {
           this.id = id;
           this.nome = nome;
           this.password = password;
           this.statoIstituto = statoIstituto;
    }


    /**
     * Gets the id value for this Istituto.
     * 
     * @return id   * Identificativo dell'istituto
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Istituto.
     * 
     * @param id   * Identificativo dell'istituto
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nome value for this Istituto.
     * 
     * @return nome   * Nome dell'istituto
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Istituto.
     * 
     * @param nome   * Nome dell'istituto
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the password value for this Istituto.
     * 
     * @return password   * Password di validazione dell'istituto
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Istituto.
     * 
     * @param password   * Password di validazione dell'istituto
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the statoIstituto value for this Istituto.
     * 
     * @return statoIstituto
     */
    public it.depositolegale.www.istituto.StatoIstituto_type getStatoIstituto() {
        return statoIstituto;
    }


    /**
     * Sets the statoIstituto value for this Istituto.
     * 
     * @param statoIstituto
     */
    public void setStatoIstituto(it.depositolegale.www.istituto.StatoIstituto_type statoIstituto) {
        this.statoIstituto = statoIstituto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Istituto)) return false;
        Istituto other = (Istituto) obj;
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
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.statoIstituto==null && other.getStatoIstituto()==null) || 
             (this.statoIstituto!=null &&
              this.statoIstituto.equals(other.getStatoIstituto())));
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
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getStatoIstituto() != null) {
            _hashCode += getStatoIstituto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Istituto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", ">istituto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statoIstituto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", "statoIstituto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituto", "statoIstituto_type"));
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
