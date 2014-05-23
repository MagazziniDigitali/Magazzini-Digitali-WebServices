/**
 * OggettoDigitale.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.oggettiDigitali;

public class OggettoDigitale  implements java.io.Serializable {
    /* Identificativo relativo all'Oggetti Digitale */
    private java.lang.String id;

    /* Nome originale dell'oggetto digitale */
    private java.lang.String nomeFile;

    private it.depositolegale.www.oggettiDigitali.Digest[] digest;

    /* Data e ora dell'ultima modifica dell'oggetto digitale */
    private java.util.Calendar ultimaModifica;

    private it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type statoOggettoDigitale;

    public OggettoDigitale() {
    }

    public OggettoDigitale(
           java.lang.String id,
           java.lang.String nomeFile,
           it.depositolegale.www.oggettiDigitali.Digest[] digest,
           java.util.Calendar ultimaModifica,
           it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type statoOggettoDigitale) {
           this.id = id;
           this.nomeFile = nomeFile;
           this.digest = digest;
           this.ultimaModifica = ultimaModifica;
           this.statoOggettoDigitale = statoOggettoDigitale;
    }


    /**
     * Gets the id value for this OggettoDigitale.
     * 
     * @return id   * Identificativo relativo all'Oggetti Digitale
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this OggettoDigitale.
     * 
     * @param id   * Identificativo relativo all'Oggetti Digitale
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nomeFile value for this OggettoDigitale.
     * 
     * @return nomeFile   * Nome originale dell'oggetto digitale
     */
    public java.lang.String getNomeFile() {
        return nomeFile;
    }


    /**
     * Sets the nomeFile value for this OggettoDigitale.
     * 
     * @param nomeFile   * Nome originale dell'oggetto digitale
     */
    public void setNomeFile(java.lang.String nomeFile) {
        this.nomeFile = nomeFile;
    }


    /**
     * Gets the digest value for this OggettoDigitale.
     * 
     * @return digest
     */
    public it.depositolegale.www.oggettiDigitali.Digest[] getDigest() {
        return digest;
    }


    /**
     * Sets the digest value for this OggettoDigitale.
     * 
     * @param digest
     */
    public void setDigest(it.depositolegale.www.oggettiDigitali.Digest[] digest) {
        this.digest = digest;
    }

    public it.depositolegale.www.oggettiDigitali.Digest getDigest(int i) {
        return this.digest[i];
    }

    public void setDigest(int i, it.depositolegale.www.oggettiDigitali.Digest _value) {
        this.digest[i] = _value;
    }


    /**
     * Gets the ultimaModifica value for this OggettoDigitale.
     * 
     * @return ultimaModifica   * Data e ora dell'ultima modifica dell'oggetto digitale
     */
    public java.util.Calendar getUltimaModifica() {
        return ultimaModifica;
    }


    /**
     * Sets the ultimaModifica value for this OggettoDigitale.
     * 
     * @param ultimaModifica   * Data e ora dell'ultima modifica dell'oggetto digitale
     */
    public void setUltimaModifica(java.util.Calendar ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }


    /**
     * Gets the statoOggettoDigitale value for this OggettoDigitale.
     * 
     * @return statoOggettoDigitale
     */
    public it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type getStatoOggettoDigitale() {
        return statoOggettoDigitale;
    }


    /**
     * Sets the statoOggettoDigitale value for this OggettoDigitale.
     * 
     * @param statoOggettoDigitale
     */
    public void setStatoOggettoDigitale(it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type statoOggettoDigitale) {
        this.statoOggettoDigitale = statoOggettoDigitale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OggettoDigitale)) return false;
        OggettoDigitale other = (OggettoDigitale) obj;
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
            ((this.nomeFile==null && other.getNomeFile()==null) || 
             (this.nomeFile!=null &&
              this.nomeFile.equals(other.getNomeFile()))) &&
            ((this.digest==null && other.getDigest()==null) || 
             (this.digest!=null &&
              java.util.Arrays.equals(this.digest, other.getDigest()))) &&
            ((this.ultimaModifica==null && other.getUltimaModifica()==null) || 
             (this.ultimaModifica!=null &&
              this.ultimaModifica.equals(other.getUltimaModifica()))) &&
            ((this.statoOggettoDigitale==null && other.getStatoOggettoDigitale()==null) || 
             (this.statoOggettoDigitale!=null &&
              this.statoOggettoDigitale.equals(other.getStatoOggettoDigitale())));
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
        if (getNomeFile() != null) {
            _hashCode += getNomeFile().hashCode();
        }
        if (getDigest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDigest());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDigest(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUltimaModifica() != null) {
            _hashCode += getUltimaModifica().hashCode();
        }
        if (getStatoOggettoDigitale() != null) {
            _hashCode += getStatoOggettoDigitale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OggettoDigitale.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", ">oggettoDigitale"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "nomeFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "digest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "digest"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ultimaModifica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "ultimaModifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statoOggettoDigitale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "statoOggettoDigitale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/oggettiDigitali", "statoOggettoDigitale_type"));
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
