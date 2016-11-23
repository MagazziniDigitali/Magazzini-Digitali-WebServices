/**
 * DatiUtente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.utenti;

public class DatiUtente  implements java.io.Serializable {
    /* Identificativo dell'utente */
    private java.lang.String id;

    /* Cognome dell'utente */
    private java.lang.String cognome;

    /* Nome dell'utente */
    private java.lang.String nome;

    /* Indica se l'utente è un amministartore */
    private int amministratore;

    /* Indica la lista degli IP Accreditati */
    private java.lang.String[] ipAccreditati;

    private it.depositolegale.www.istituzione.Istituzione istituzione;

    public DatiUtente() {
    }

    public DatiUtente(
           java.lang.String id,
           java.lang.String cognome,
           java.lang.String nome,
           int amministratore,
           java.lang.String[] ipAccreditati,
           it.depositolegale.www.istituzione.Istituzione istituzione) {
           this.id = id;
           this.cognome = cognome;
           this.nome = nome;
           this.amministratore = amministratore;
           this.ipAccreditati = ipAccreditati;
           this.istituzione = istituzione;
    }


    /**
     * Gets the id value for this DatiUtente.
     * 
     * @return id   * Identificativo dell'utente
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this DatiUtente.
     * 
     * @param id   * Identificativo dell'utente
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the cognome value for this DatiUtente.
     * 
     * @return cognome   * Cognome dell'utente
     */
    public java.lang.String getCognome() {
        return cognome;
    }


    /**
     * Sets the cognome value for this DatiUtente.
     * 
     * @param cognome   * Cognome dell'utente
     */
    public void setCognome(java.lang.String cognome) {
        this.cognome = cognome;
    }


    /**
     * Gets the nome value for this DatiUtente.
     * 
     * @return nome   * Nome dell'utente
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this DatiUtente.
     * 
     * @param nome   * Nome dell'utente
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the amministratore value for this DatiUtente.
     * 
     * @return amministratore   * Indica se l'utente è un amministartore
     */
    public int getAmministratore() {
        return amministratore;
    }


    /**
     * Sets the amministratore value for this DatiUtente.
     * 
     * @param amministratore   * Indica se l'utente è un amministartore
     */
    public void setAmministratore(int amministratore) {
        this.amministratore = amministratore;
    }


    /**
     * Gets the ipAccreditati value for this DatiUtente.
     * 
     * @return ipAccreditati   * Indica la lista degli IP Accreditati
     */
    public java.lang.String[] getIpAccreditati() {
        return ipAccreditati;
    }


    /**
     * Sets the ipAccreditati value for this DatiUtente.
     * 
     * @param ipAccreditati   * Indica la lista degli IP Accreditati
     */
    public void setIpAccreditati(java.lang.String[] ipAccreditati) {
        this.ipAccreditati = ipAccreditati;
    }

    public java.lang.String getIpAccreditati(int i) {
        return this.ipAccreditati[i];
    }

    public void setIpAccreditati(int i, java.lang.String _value) {
        this.ipAccreditati[i] = _value;
    }


    /**
     * Gets the istituzione value for this DatiUtente.
     * 
     * @return istituzione
     */
    public it.depositolegale.www.istituzione.Istituzione getIstituzione() {
        return istituzione;
    }


    /**
     * Sets the istituzione value for this DatiUtente.
     * 
     * @param istituzione
     */
    public void setIstituzione(it.depositolegale.www.istituzione.Istituzione istituzione) {
        this.istituzione = istituzione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatiUtente)) return false;
        DatiUtente other = (DatiUtente) obj;
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
            ((this.cognome==null && other.getCognome()==null) || 
             (this.cognome!=null &&
              this.cognome.equals(other.getCognome()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            this.amministratore == other.getAmministratore() &&
            ((this.ipAccreditati==null && other.getIpAccreditati()==null) || 
             (this.ipAccreditati!=null &&
              java.util.Arrays.equals(this.ipAccreditati, other.getIpAccreditati()))) &&
            ((this.istituzione==null && other.getIstituzione()==null) || 
             (this.istituzione!=null &&
              this.istituzione.equals(other.getIstituzione())));
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
        if (getCognome() != null) {
            _hashCode += getCognome().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        _hashCode += getAmministratore();
        if (getIpAccreditati() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIpAccreditati());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIpAccreditati(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIstituzione() != null) {
            _hashCode += getIstituzione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatiUtente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", ">datiUtente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cognome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", "cognome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amministratore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", "amministratore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipAccreditati");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", "IpAccreditati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("istituzione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "istituzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", ">istituzione"));
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
