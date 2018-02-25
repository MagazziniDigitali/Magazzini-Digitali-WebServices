/**
 * NodoStorageS3.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.nodi;

public class NodoStorageS3  implements java.io.Serializable {
    private java.lang.String urlS3;

    private java.lang.String region;

    private java.lang.String accessKey;

    private java.lang.String secretKey;

    private java.lang.String bucketName;

    public NodoStorageS3() {
    }

    public NodoStorageS3(
           java.lang.String urlS3,
           java.lang.String region,
           java.lang.String accessKey,
           java.lang.String secretKey,
           java.lang.String bucketName) {
           this.urlS3 = urlS3;
           this.region = region;
           this.accessKey = accessKey;
           this.secretKey = secretKey;
           this.bucketName = bucketName;
    }


    /**
     * Gets the urlS3 value for this NodoStorageS3.
     * 
     * @return urlS3
     */
    public java.lang.String getUrlS3() {
        return urlS3;
    }


    /**
     * Sets the urlS3 value for this NodoStorageS3.
     * 
     * @param urlS3
     */
    public void setUrlS3(java.lang.String urlS3) {
        this.urlS3 = urlS3;
    }


    /**
     * Gets the region value for this NodoStorageS3.
     * 
     * @return region
     */
    public java.lang.String getRegion() {
        return region;
    }


    /**
     * Sets the region value for this NodoStorageS3.
     * 
     * @param region
     */
    public void setRegion(java.lang.String region) {
        this.region = region;
    }


    /**
     * Gets the accessKey value for this NodoStorageS3.
     * 
     * @return accessKey
     */
    public java.lang.String getAccessKey() {
        return accessKey;
    }


    /**
     * Sets the accessKey value for this NodoStorageS3.
     * 
     * @param accessKey
     */
    public void setAccessKey(java.lang.String accessKey) {
        this.accessKey = accessKey;
    }


    /**
     * Gets the secretKey value for this NodoStorageS3.
     * 
     * @return secretKey
     */
    public java.lang.String getSecretKey() {
        return secretKey;
    }


    /**
     * Sets the secretKey value for this NodoStorageS3.
     * 
     * @param secretKey
     */
    public void setSecretKey(java.lang.String secretKey) {
        this.secretKey = secretKey;
    }


    /**
     * Gets the bucketName value for this NodoStorageS3.
     * 
     * @return bucketName
     */
    public java.lang.String getBucketName() {
        return bucketName;
    }


    /**
     * Sets the bucketName value for this NodoStorageS3.
     * 
     * @param bucketName
     */
    public void setBucketName(java.lang.String bucketName) {
        this.bucketName = bucketName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NodoStorageS3)) return false;
        NodoStorageS3 other = (NodoStorageS3) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.urlS3==null && other.getUrlS3()==null) || 
             (this.urlS3!=null &&
              this.urlS3.equals(other.getUrlS3()))) &&
            ((this.region==null && other.getRegion()==null) || 
             (this.region!=null &&
              this.region.equals(other.getRegion()))) &&
            ((this.accessKey==null && other.getAccessKey()==null) || 
             (this.accessKey!=null &&
              this.accessKey.equals(other.getAccessKey()))) &&
            ((this.secretKey==null && other.getSecretKey()==null) || 
             (this.secretKey!=null &&
              this.secretKey.equals(other.getSecretKey()))) &&
            ((this.bucketName==null && other.getBucketName()==null) || 
             (this.bucketName!=null &&
              this.bucketName.equals(other.getBucketName())));
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
        if (getUrlS3() != null) {
            _hashCode += getUrlS3().hashCode();
        }
        if (getRegion() != null) {
            _hashCode += getRegion().hashCode();
        }
        if (getAccessKey() != null) {
            _hashCode += getAccessKey().hashCode();
        }
        if (getSecretKey() != null) {
            _hashCode += getSecretKey().hashCode();
        }
        if (getBucketName() != null) {
            _hashCode += getBucketName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NodoStorageS3.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", ">>>nodo>storage>s3"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlS3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "urlS3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("region");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "accessKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secretKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "secretKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bucketName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/nodi", "bucketName"));
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
