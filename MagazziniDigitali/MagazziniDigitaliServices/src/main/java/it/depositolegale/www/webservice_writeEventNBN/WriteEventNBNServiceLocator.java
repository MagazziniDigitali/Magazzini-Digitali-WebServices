/**
 * WriteEventNBNServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_writeEventNBN;

public class WriteEventNBNServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNService {

    public WriteEventNBNServiceLocator() {
    }


    public WriteEventNBNServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WriteEventNBNServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WriteEventNBNPort
    private java.lang.String WriteEventNBNPort_address = "http://localhost:8080/MagazziniDigitaliServices/services/WriteEventNBNPort";

    public java.lang.String getWriteEventNBNPortAddress() {
        return WriteEventNBNPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WriteEventNBNPortWSDDServiceName = "WriteEventNBNPort";

    public java.lang.String getWriteEventNBNPortWSDDServiceName() {
        return WriteEventNBNPortWSDDServiceName;
    }

    public void setWriteEventNBNPortWSDDServiceName(java.lang.String name) {
        WriteEventNBNPortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNPortType getWriteEventNBNPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WriteEventNBNPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWriteEventNBNPort(endpoint);
    }

    public it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNPortType getWriteEventNBNPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNBindStub _stub = new it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNBindStub(portAddress, this);
            _stub.setPortName(getWriteEventNBNPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWriteEventNBNPortEndpointAddress(java.lang.String address) {
        WriteEventNBNPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNBindStub _stub = new it.depositolegale.www.webservice_writeEventNBN.WriteEventNBNBindStub(new java.net.URL(WriteEventNBNPort_address), this);
                _stub.setPortName(getWriteEventNBNPortWSDDServiceName());
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
        if ("WriteEventNBNPort".equals(inputPortName)) {
            return getWriteEventNBNPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.writeEventNBN", "WriteEventNBNService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.writeEventNBN", "WriteEventNBNPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WriteEventNBNPort".equals(portName)) {
            setWriteEventNBNPortEndpointAddress(address);
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
