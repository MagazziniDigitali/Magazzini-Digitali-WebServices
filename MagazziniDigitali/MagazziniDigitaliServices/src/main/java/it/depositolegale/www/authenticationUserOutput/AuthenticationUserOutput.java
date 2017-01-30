/**
 * AuthenticationUserOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.authenticationUserOutput;

public class AuthenticationUserOutput  implements java.io.Serializable {
    private it.depositolegale.www.authenticationUserInput.UserInput userInput;

    private it.depositolegale.www.authenticationUserInput.Agent[] agent;

    private it.depositolegale.www.authenticationUserInput.Rights rights;

    private it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg;

    private java.lang.String url;

    public AuthenticationUserOutput() {
    }

    public AuthenticationUserOutput(
           it.depositolegale.www.authenticationUserInput.UserInput userInput,
           it.depositolegale.www.authenticationUserInput.Agent[] agent,
           it.depositolegale.www.authenticationUserInput.Rights rights,
           it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg,
           java.lang.String url) {
           this.userInput = userInput;
           this.agent = agent;
           this.rights = rights;
           this.errorMsg = errorMsg;
           this.url = url;
    }


    /**
     * Gets the userInput value for this AuthenticationUserOutput.
     * 
     * @return userInput
     */
    public it.depositolegale.www.authenticationUserInput.UserInput getUserInput() {
        return userInput;
    }


    /**
     * Sets the userInput value for this AuthenticationUserOutput.
     * 
     * @param userInput
     */
    public void setUserInput(it.depositolegale.www.authenticationUserInput.UserInput userInput) {
        this.userInput = userInput;
    }


    /**
     * Gets the agent value for this AuthenticationUserOutput.
     * 
     * @return agent
     */
    public it.depositolegale.www.authenticationUserInput.Agent[] getAgent() {
        return agent;
    }


    /**
     * Sets the agent value for this AuthenticationUserOutput.
     * 
     * @param agent
     */
    public void setAgent(it.depositolegale.www.authenticationUserInput.Agent[] agent) {
        this.agent = agent;
    }

    public it.depositolegale.www.authenticationUserInput.Agent getAgent(int i) {
        return this.agent[i];
    }

    public void setAgent(int i, it.depositolegale.www.authenticationUserInput.Agent _value) {
        this.agent[i] = _value;
    }


    /**
     * Gets the rights value for this AuthenticationUserOutput.
     * 
     * @return rights
     */
    public it.depositolegale.www.authenticationUserInput.Rights getRights() {
        return rights;
    }


    /**
     * Sets the rights value for this AuthenticationUserOutput.
     * 
     * @param rights
     */
    public void setRights(it.depositolegale.www.authenticationUserInput.Rights rights) {
        this.rights = rights;
    }


    /**
     * Gets the errorMsg value for this AuthenticationUserOutput.
     * 
     * @return errorMsg
     */
    public it.depositolegale.www.errorMsg.ErrorMsg[] getErrorMsg() {
        return errorMsg;
    }


    /**
     * Sets the errorMsg value for this AuthenticationUserOutput.
     * 
     * @param errorMsg
     */
    public void setErrorMsg(it.depositolegale.www.errorMsg.ErrorMsg[] errorMsg) {
        this.errorMsg = errorMsg;
    }

    public it.depositolegale.www.errorMsg.ErrorMsg getErrorMsg(int i) {
        return this.errorMsg[i];
    }

    public void setErrorMsg(int i, it.depositolegale.www.errorMsg.ErrorMsg _value) {
        this.errorMsg[i] = _value;
    }


    /**
     * Gets the url value for this AuthenticationUserOutput.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this AuthenticationUserOutput.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthenticationUserOutput)) return false;
        AuthenticationUserOutput other = (AuthenticationUserOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userInput==null && other.getUserInput()==null) || 
             (this.userInput!=null &&
              this.userInput.equals(other.getUserInput()))) &&
            ((this.agent==null && other.getAgent()==null) || 
             (this.agent!=null &&
              java.util.Arrays.equals(this.agent, other.getAgent()))) &&
            ((this.rights==null && other.getRights()==null) || 
             (this.rights!=null &&
              this.rights.equals(other.getRights()))) &&
            ((this.errorMsg==null && other.getErrorMsg()==null) || 
             (this.errorMsg!=null &&
              java.util.Arrays.equals(this.errorMsg, other.getErrorMsg()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl())));
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
        if (getUserInput() != null) {
            _hashCode += getUserInput().hashCode();
        }
        if (getAgent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAgent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAgent(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRights() != null) {
            _hashCode += getRights().hashCode();
        }
        if (getErrorMsg() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorMsg());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorMsg(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthenticationUserOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserOutput", ">authenticationUserOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userInput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserOutput", "userInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "userInput"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "agent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "agent"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rights");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "rights"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">rights"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.depositolegale.it/errorMsg", "errorMsg"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserOutput", "url"));
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
