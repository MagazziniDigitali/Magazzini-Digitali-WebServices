/**
 * CheckTicketServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_checkTicket;

public class CheckTicketServiceLocator extends org.apache.axis.client.Service implements it.depositolegale.www.webservice_checkTicket.CheckTicketService {

    public CheckTicketServiceLocator() {
    }


    public CheckTicketServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CheckTicketServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CheckTicketPort
    private java.lang.String CheckTicketPort_address = "http://localhost:8080/MagazziniDigitaliServices/services/CheckTicketPort";

    public java.lang.String getCheckTicketPortAddress() {
        return CheckTicketPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CheckTicketPortWSDDServiceName = "CheckTicketPort";

    public java.lang.String getCheckTicketPortWSDDServiceName() {
        return CheckTicketPortWSDDServiceName;
    }

    public void setCheckTicketPortWSDDServiceName(java.lang.String name) {
        CheckTicketPortWSDDServiceName = name;
    }

    public it.depositolegale.www.webservice_checkTicket.CheckTicketPortType getCheckTicketPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CheckTicketPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCheckTicketPort(endpoint);
    }

    public it.depositolegale.www.webservice_checkTicket.CheckTicketPortType getCheckTicketPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.depositolegale.www.webservice_checkTicket.CheckTicketBindStub _stub = new it.depositolegale.www.webservice_checkTicket.CheckTicketBindStub(portAddress, this);
            _stub.setPortName(getCheckTicketPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCheckTicketPortEndpointAddress(java.lang.String address) {
        CheckTicketPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.depositolegale.www.webservice_checkTicket.CheckTicketPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.depositolegale.www.webservice_checkTicket.CheckTicketBindStub _stub = new it.depositolegale.www.webservice_checkTicket.CheckTicketBindStub(new java.net.URL(CheckTicketPort_address), this);
                _stub.setPortName(getCheckTicketPortWSDDServiceName());
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
        if ("CheckTicketPort".equals(inputPortName)) {
            return getCheckTicketPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.checkTicket", "CheckTicketService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.depositolegale.it/webservice.checkTicket", "CheckTicketPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CheckTicketPort".equals(portName)) {
            setCheckTicketPortEndpointAddress(address);
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
