/**
 * AuthenticationSoftwareBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationSoftware;

public class AuthenticationSoftwareBindSkeleton implements it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/login", "authentication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/login", ">authentication"), it.depositolegale.www.login.Authentication.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("authenticationSoftwareOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/software", "software"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/software", ">software"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "AuthenticationSoftwareOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("authenticationSoftwareOperation") == null) {
            _myOperations.put("authenticationSoftwareOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("authenticationSoftwareOperation")).add(_oper);
    }

    public AuthenticationSoftwareBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwareBindImpl();
    }

    public AuthenticationSoftwareBindSkeleton(it.depositolegale.www.webservice_authenticationSoftware.AuthenticationSoftwarePortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.software.Software authenticationSoftwareOperation(it.depositolegale.www.login.Authentication authenticationSoftwareInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.software.Software ret = impl.authenticationSoftwareOperation(authenticationSoftwareInput);
        return ret;
    }

}
