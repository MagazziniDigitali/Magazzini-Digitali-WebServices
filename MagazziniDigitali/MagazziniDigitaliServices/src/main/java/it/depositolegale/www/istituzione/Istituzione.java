/**
 * Istituzione.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.istituzione;

public class Istituzione  implements java.io.Serializable {
    /* Identificativo dell'istituzione */
    private java.lang.String id;

    /* Nome dell'istituzione */
    private java.lang.String nome;

    /* Indirizzo */
    private java.lang.String indirizzo;

    /* Numero di telefono del Contatto o Istituzione */
    private java.lang.String telefono;

    /* Nome e Cognome del contatto dell'istituzione */
    private java.lang.String nomeContatto;

    /* Indica se l'istituto è una biblioteca depositaria */
    private java.math.BigInteger bibliotecaDepositaria;

    /* Indica sel si tratta di un Istituto Centrale */
    private java.math.BigInteger istitutoCentrale;

    /* Indica la lista degli IP Accreditati */
    private java.lang.String[] ipAccreditati;

    /* Dati relativa all'Api per l'autenticazione dell'utente */
    private it.depositolegale.www.istituzione.IstituzioneApiUtente apiUtente;

    /* Indirizzo Email accreditato per il Bagit */
    private java.lang.String emailBagit;

    public Istituzione() {
    }

    public Istituzione(
           java.lang.String id,
           java.lang.String nome,
           java.lang.String indirizzo,
           java.lang.String telefono,
           java.lang.String nomeContatto,
           java.math.BigInteger bibliotecaDepositaria,
           java.math.BigInteger istitutoCentrale,
           java.lang.String[] ipAccreditati,
           it.depositolegale.www.istituzione.IstituzioneApiUtente apiUtente,
           java.lang.String emailBagit) {
           this.id = id;
           this.nome = nome;
           this.indirizzo = indirizzo;
           this.telefono = telefono;
           this.nomeContatto = nomeContatto;
           this.bibliotecaDepositaria = bibliotecaDepositaria;
           this.istitutoCentrale = istitutoCentrale;
           this.ipAccreditati = ipAccreditati;
           this.apiUtente = apiUtente;
           this.emailBagit = emailBagit;
    }


    /**
     * Gets the id value for this Istituzione.
     * 
     * @return id   * Identificativo dell'istituzione
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Istituzione.
     * 
     * @param id   * Identificativo dell'istituzione
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nome value for this Istituzione.
     * 
     * @return nome   * Nome dell'istituzione
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Istituzione.
     * 
     * @param nome   * Nome dell'istituzione
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the indirizzo value for this Istituzione.
     * 
     * @return indirizzo   * Indirizzo
     */
    public java.lang.String getIndirizzo() {
        return indirizzo;
    }


    /**
     * Sets the indirizzo value for this Istituzione.
     * 
     * @param indirizzo   * Indirizzo
     */
    public void setIndirizzo(java.lang.String indirizzo) {
        this.indirizzo = indirizzo;
    }


    /**
     * Gets the telefono value for this Istituzione.
     * 
     * @return telefono   * Numero di telefono del Contatto o Istituzione
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this Istituzione.
     * 
     * @param telefono   * Numero di telefono del Contatto o Istituzione
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the nomeContatto value for this Istituzione.
     * 
     * @return nomeContatto   * Nome e Cognome del contatto dell'istituzione
     */
    public java.lang.String getNomeContatto() {
        return nomeContatto;
    }


    /**
     * Sets the nomeContatto value for this Istituzione.
     * 
     * @param nomeContatto   * Nome e Cognome del contatto dell'istituzione
     */
    public void setNomeContatto(java.lang.String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }


    /**
     * Gets the bibliotecaDepositaria value for this Istituzione.
     * 
     * @return bibliotecaDepositaria   * Indica se l'istituto è una biblioteca depositaria
     */
    public java.math.BigInteger getBibliotecaDepositaria() {
        return bibliotecaDepositaria;
    }


    /**
     * Sets the bibliotecaDepositaria value for this Istituzione.
     * 
     * @param bibliotecaDepositaria   * Indica se l'istituto è una biblioteca depositaria
     */
    public void setBibliotecaDepositaria(java.math.BigInteger bibliotecaDepositaria) {
        this.bibliotecaDepositaria = bibliotecaDepositaria;
    }


    /**
     * Gets the istitutoCentrale value for this Istituzione.
     * 
     * @return istitutoCentrale   * Indica sel si tratta di un Istituto Centrale
     */
    public java.math.BigInteger getIstitutoCentrale() {
        return istitutoCentrale;
    }


    /**
     * Sets the istitutoCentrale value for this Istituzione.
     * 
     * @param istitutoCentrale   * Indica sel si tratta di un Istituto Centrale
     */
    public void setIstitutoCentrale(java.math.BigInteger istitutoCentrale) {
        this.istitutoCentrale = istitutoCentrale;
    }


    /**
     * Gets the ipAccreditati value for this Istituzione.
     * 
     * @return ipAccreditati   * Indica la lista degli IP Accreditati
     */
    public java.lang.String[] getIpAccreditati() {
        return ipAccreditati;
    }


    /**
     * Sets the ipAccreditati value for this Istituzione.
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
     * Gets the apiUtente value for this Istituzione.
     * 
     * @return apiUtente   * Dati relativa all'Api per l'autenticazione dell'utente
     */
    public it.depositolegale.www.istituzione.IstituzioneApiUtente getApiUtente() {
        return apiUtente;
    }


    /**
     * Sets the apiUtente value for this Istituzione.
     * 
     * @param apiUtente   * Dati relativa all'Api per l'autenticazione dell'utente
     */
    public void setApiUtente(it.depositolegale.www.istituzione.IstituzioneApiUtente apiUtente) {
        this.apiUtente = apiUtente;
    }


    /**
     * Gets the emailBagit value for this Istituzione.
     * 
     * @return emailBagit   * Indirizzo Email accreditato per il Bagit
     */
    public java.lang.String getEmailBagit() {
        return emailBagit;
    }


    /**
     * Sets the emailBagit value for this Istituzione.
     * 
     * @param emailBagit   * Indirizzo Email accreditato per il Bagit
     */
    public void setEmailBagit(java.lang.String emailBagit) {
        this.emailBagit = emailBagit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Istituzione)) return false;
        Istituzione other = (Istituzione) obj;
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
            ((this.indirizzo==null && other.getIndirizzo()==null) || 
             (this.indirizzo!=null &&
              this.indirizzo.equals(other.getIndirizzo()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.nomeContatto==null && other.getNomeContatto()==null) || 
             (this.nomeContatto!=null &&
              this.nomeContatto.equals(other.getNomeContatto()))) &&
            ((this.bibliotecaDepositaria==null && other.getBibliotecaDepositaria()==null) || 
             (this.bibliotecaDepositaria!=null &&
              this.bibliotecaDepositaria.equals(other.getBibliotecaDepositaria()))) &&
            ((this.istitutoCentrale==null && other.getIstitutoCentrale()==null) || 
             (this.istitutoCentrale!=null &&
              this.istitutoCentrale.equals(other.getIstitutoCentrale()))) &&
            ((this.ipAccreditati==null && other.getIpAccreditati()==null) || 
             (this.ipAccreditati!=null &&
              java.util.Arrays.equals(this.ipAccreditati, other.getIpAccreditati()))) &&
            ((this.apiUtente==null && other.getApiUtente()==null) || 
             (this.apiUtente!=null &&
              this.apiUtente.equals(other.getApiUtente()))) &&
            ((this.emailBagit==null && other.getEmailBagit()==null) || 
             (this.emailBagit!=null &&
              this.emailBagit.equals(other.getEmailBagit())));
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
        if (getIndirizzo() != null) {
            _hashCode += getIndirizzo().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getNomeContatto() != null) {
            _hashCode += getNomeContatto().hashCode();
        }
        if (getBibliotecaDepositaria() != null) {
            _hashCode += getBibliotecaDepositaria().hashCode();
        }
        if (getIstitutoCentrale() != null) {
            _hashCode += getIstitutoCentrale().hashCode();
        }
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
        if (getApiUtente() != null) {
            _hashCode += getApiUtente().hashCode();
        }
        if (getEmailBagit() != null) {
            _hashCode += getEmailBagit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Istituzione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", ">istituzione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "indirizzo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeContatto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "nomeContatto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bibliotecaDepositaria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "bibliotecaDepositaria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("istitutoCentrale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "istitutoCentrale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipAccreditati");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "ipAccreditati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apiUtente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "apiUtente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", ">>istituzione>apiUtente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailBagit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "emailBagit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
