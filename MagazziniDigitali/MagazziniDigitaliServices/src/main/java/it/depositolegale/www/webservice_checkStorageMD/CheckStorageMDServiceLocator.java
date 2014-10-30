/**
 * CheckStorageMDServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkStorageMD;

public class CheckStorageMDServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDService {

    public CheckStorageMDServiceLocator() {
    }


    public CheckStorageMDServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CheckStorageMDServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CheckStorageMDPort
    private java.lang.String CheckStorageMDPort_address = "http://localhost:8080/MagazziniDigitaliServices/services/CheckStorageMDPort";

    public java.lang.String getCheckStorageMDPortAddress() {
        return CheckStorageMDPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CheckStorageMDPortWSDDServiceName = "CheckStorageMDPort";

    public java.lang.String getCheckStorageMDPortWSDDServiceName() {
        return CheckStorageMDPortWSDDServiceName;
    }

    public void setCheckStorageMDPortWSDDServiceName(java.lang.String name) {
        CheckStorageMDPortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType getCheckStorageMDPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CheckStorageMDPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCheckStorageMDPort(endpoint);
    }

    public it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType getCheckStorageMDPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDBindStub _stub = new it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDBindStub(portAddress, this);
            _stub.setPortName(getCheckStorageMDPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCheckStorageMDPortEndpointAddress(java.lang.String address) {
        CheckStorageMDPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDBindStub _stub = new it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDBindStub(new java.net.URL(CheckStorageMDPort_address), this);
                _stub.setPortName(getCheckStorageMDPortWSDDServiceName());
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
        if ("CheckStorageMDPort".equals(inputPortName)) {
            return getCheckStorageMDPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.checkStorageMD", "CheckStorageMDService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.checkStorageMD", "CheckStorageMDPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CheckStorageMDPort".equals(portName)) {
            setCheckStorageMDPortEndpointAddress(address);
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
