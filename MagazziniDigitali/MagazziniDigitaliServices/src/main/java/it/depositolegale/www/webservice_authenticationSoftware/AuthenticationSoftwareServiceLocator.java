/**
 * AuthenticationSoftwareServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationSoftware;

public class AuthenticationSoftwareServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwareService {

    public AuthenticationSoftwareServiceLocator() {
    }


    public AuthenticationSoftwareServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AuthenticationSoftwareServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AuthenticationSoftwarePort
    private java.lang.String AuthenticationSoftwarePort_address = "http://localhost:8080/MagazziniDigitaliServices/services/AuthenticationSoftwarePort";

    public java.lang.String getAuthenticationSoftwarePortAddress() {
        return AuthenticationSoftwarePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AuthenticationSoftwarePortWSDDServiceName = "AuthenticationSoftwarePort";

    public java.lang.String getAuthenticationSoftwarePortWSDDServiceName() {
        return AuthenticationSoftwarePortWSDDServiceName;
    }

    public void setAuthenticationSoftwarePortWSDDServiceName(java.lang.String name) {
        AuthenticationSoftwarePortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType getAuthenticationSoftwarePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuthenticationSoftwarePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuthenticationSoftwarePort(endpoint);
    }

    public it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType getAuthenticationSoftwarePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwareBindStub _stub = new it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwareBindStub(portAddress, this);
            _stub.setPortName(getAuthenticationSoftwarePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAuthenticationSoftwarePortEndpointAddress(java.lang.String address) {
        AuthenticationSoftwarePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwareBindStub _stub = new it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwareBindStub(new java.net.URL(AuthenticationSoftwarePort_address), this);
                _stub.setPortName(getAuthenticationSoftwarePortWSDDServiceName());
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
        if ("AuthenticationSoftwarePort".equals(inputPortName)) {
            return getAuthenticationSoftwarePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.authenticationSoftware", "AuthenticationSoftwareService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.authenticationSoftware", "AuthenticationSoftwarePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AuthenticationSoftwarePort".equals(portName)) {
            setAuthenticationSoftwarePortEndpointAddress(address);
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
