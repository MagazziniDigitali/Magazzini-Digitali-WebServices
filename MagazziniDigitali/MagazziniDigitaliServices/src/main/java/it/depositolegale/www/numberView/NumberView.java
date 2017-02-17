/**
 * NumberView.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.numberView;

public class NumberView  implements java.io.Serializable {
    private java.lang.String idObject;

    public NumberView() {
    }

    public NumberView(
           java.lang.String idObject) {
           this.idObject = idObject;
    }


    /**
     * Gets the idObject value for this NumberView.
     * 
     * @return idObject
     */
    public java.lang.String getIdObject() {
        return idObject;
    }


    /**
     * Sets the idObject value for this NumberView.
     * 
     * @param idObject
     */
    public void setIdObject(java.lang.String idObject) {
        this.idObject = idObject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NumberView)) return false;
        NumberView other = (NumberView) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idObject==null && other.getIdObject()==null) || 
             (this.idObject!=null &&
              this.idObject.equals(other.getIdObject())));
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
        if (getIdObject() != null) {
            _hashCode += getIdObject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NumberView.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/numberView", ">numberView"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/numberView", "idObject"));
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
