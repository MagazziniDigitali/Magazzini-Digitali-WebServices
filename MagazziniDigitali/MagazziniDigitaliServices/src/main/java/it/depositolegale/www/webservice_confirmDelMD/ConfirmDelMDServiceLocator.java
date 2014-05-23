/**
 * ConfirmDelMDServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_confirmDelMD;

public class ConfirmDelMDServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDService {

    public ConfirmDelMDServiceLocator() {
    }


    public ConfirmDelMDServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConfirmDelMDServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConfirmDelMDPort
    private java.lang.String ConfirmDelMDPort_address = "http://localhost:8080/TestWB/services/clientService";

    public java.lang.String getConfirmDelMDPortAddress() {
        return ConfirmDelMDPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConfirmDelMDPortWSDDServiceName = "ConfirmDelMDPort";

    public java.lang.String getConfirmDelMDPortWSDDServiceName() {
        return ConfirmDelMDPortWSDDServiceName;
    }

    public void setConfirmDelMDPortWSDDServiceName(java.lang.String name) {
        ConfirmDelMDPortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDPortType getConfirmDelMDPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConfirmDelMDPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConfirmDelMDPort(endpoint);
    }

    public it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDPortType getConfirmDelMDPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDBindStub _stub = new it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDBindStub(portAddress, this);
            _stub.setPortName(getConfirmDelMDPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConfirmDelMDPortEndpointAddress(java.lang.String address) {
        ConfirmDelMDPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDBindStub _stub = new it.depositolegale.www.webservice_confirmDelMD.ConfirmDelMDBindStub(new java.net.URL(ConfirmDelMDPort_address), this);
                _stub.setPortName(getConfirmDelMDPortWSDDServiceName());
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
        if ("ConfirmDelMDPort".equals(inputPortName)) {
            return getConfirmDelMDPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.confirmDelMD", "ConfirmDelMDService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.confirmDelMD", "ConfirmDelMDPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConfirmDelMDPort".equals(portName)) {
            setConfirmDelMDPortEndpointAddress(address);
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
