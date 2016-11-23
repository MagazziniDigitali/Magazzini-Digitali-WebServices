/**
 * AuthenticationUtentiServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationUtenti;

public class AuthenticationUtentiServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiService {

    public AuthenticationUtentiServiceLocator() {
    }


    public AuthenticationUtentiServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AuthenticationUtentiServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AuthenticationUtentiPort
    private java.lang.String AuthenticationUtentiPort_address = "http://localhost:8080/MagazziniDigitaliServices/services/AuthenticationUtentiPort";

    public java.lang.String getAuthenticationUtentiPortAddress() {
        return AuthenticationUtentiPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AuthenticationUtentiPortWSDDServiceName = "AuthenticationUtentiPort";

    public java.lang.String getAuthenticationUtentiPortWSDDServiceName() {
        return AuthenticationUtentiPortWSDDServiceName;
    }

    public void setAuthenticationUtentiPortWSDDServiceName(java.lang.String name) {
        AuthenticationUtentiPortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType getAuthenticationUtentiPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuthenticationUtentiPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuthenticationUtentiPort(endpoint);
    }

    public it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType getAuthenticationUtentiPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiBindStub _stub = new it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiBindStub(portAddress, this);
            _stub.setPortName(getAuthenticationUtentiPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAuthenticationUtentiPortEndpointAddress(java.lang.String address) {
        AuthenticationUtentiPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiBindStub _stub = new it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiBindStub(new java.net.URL(AuthenticationUtentiPort_address), this);
                _stub.setPortName(getAuthenticationUtentiPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AuthenticationUtentiPort".equals(inputPortName)) {
            return getAuthenticationUtentiPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.authenticationUtenti", "AuthenticationUtentiService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.authenticationUtenti", "AuthenticationUtentiPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AuthenticationUtentiPort".equals(portName)) {
            setAuthenticationUtentiPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
