/**
 * CheckMDServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkMD;

public class CheckMDServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_checkMD.CheckMDService {

    public CheckMDServiceLocator() {
    }


    public CheckMDServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CheckMDServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CheckMDPort
    private java.lang.String CheckMDPort_address = "http://localhost:8080/MagazziniDigitaliServices/services/CheckMDPort";

    public java.lang.String getCheckMDPortAddress() {
        return CheckMDPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CheckMDPortWSDDServiceName = "CheckMDPort";

    public java.lang.String getCheckMDPortWSDDServiceName() {
        return CheckMDPortWSDDServiceName;
    }

    public void setCheckMDPortWSDDServiceName(java.lang.String name) {
        CheckMDPortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_checkMD.CheckMDPortType getCheckMDPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CheckMDPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCheckMDPort(endpoint);
    }

    public it.depositolegale.www.webservice_checkMD.CheckMDPortType getCheckMDPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_checkMD.CheckMDBindStub _stub = new it.depositolegale.www.webservice_checkMD.CheckMDBindStub(portAddress, this);
            _stub.setPortName(getCheckMDPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCheckMDPortEndpointAddress(java.lang.String address) {
        CheckMDPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_checkMD.CheckMDPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_checkMD.CheckMDBindStub _stub = new it.depositolegale.www.webservice_checkMD.CheckMDBindStub(new java.net.URL(CheckMDPort_address), this);
                _stub.setPortName(getCheckMDPortWSDDServiceName());
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
        if ("CheckMDPort".equals(inputPortName)) {
            return getCheckMDPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.checkMD", "CheckMDService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.checkMD", "CheckMDPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CheckMDPort".equals(portName)) {
            setCheckMDPortEndpointAddress(address);
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
