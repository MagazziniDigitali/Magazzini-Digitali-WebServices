/**
 * CheckTicket.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.checkTicket;

public class CheckTicket  implements java.io.Serializable {
    private it.depositolegale.www.software.Software software;

    private java.lang.String ticket;

    private java.lang.String ipClient;

    public CheckTicket() {
    }

    public CheckTicket(
           it.depositolegale.www.software.Software software,
           java.lang.String ticket,
           java.lang.String ipClient) {
           this.software = software;
           this.ticket = ticket;
           this.ipClient = ipClient;
    }


    /**
     * Gets the software value for this CheckTicket.
     * 
     * @return software
     */
    public it.depositolegale.www.software.Software getSoftware() {
        return software;
    }


    /**
     * Sets the software value for this CheckTicket.
     * 
     * @param software
     */
    public void setSoftware(it.depositolegale.www.software.Software software) {
        this.software = software;
    }


    /**
     * Gets the ticket value for this CheckTicket.
     * 
     * @return ticket
     */
    public java.lang.String getTicket() {
        return ticket;
    }


    /**
     * Sets the ticket value for this CheckTicket.
     * 
     * @param ticket
     */
    public void setTicket(java.lang.String ticket) {
        this.ticket = ticket;
    }


    /**
     * Gets the ipClient value for this CheckTicket.
     * 
     * @return ipClient
     */
    public java.lang.String getIpClient() {
        return ipClient;
    }


    /**
     * Sets the ipClient value for this CheckTicket.
     * 
     * @param ipClient
     */
    public void setIpClient(java.lang.String ipClient) {
        this.ipClient = ipClient;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckTicket)) return false;
        CheckTicket other = (CheckTicket) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.software==null && other.getSoftware()==null) || 
             (this.software!=null &&
              this.software.equals(other.getSoftware()))) &&
            ((this.ticket==null && other.getTicket()==null) || 
             (this.ticket!=null &&
              this.ticket.equals(other.getTicket()))) &&
            ((this.ipClient==null && other.getIpClient()==null) || 
             (this.ipClient!=null &&
              this.ipClient.equals(other.getIpClient())));
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
        if (getSoftware() != null) {
            _hashCode += getSoftware().hashCode();
        }
        if (getTicket() != null) {
            _hashCode += getTicket().hashCode();
        }
        if (getIpClient() != null) {
            _hashCode += getIpClient().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckTicket.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", ">checkTicket"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("software");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/software", "software"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/software", ">software"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ticket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", "ticket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipClient");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/checkTicket", "ipClient"));
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
