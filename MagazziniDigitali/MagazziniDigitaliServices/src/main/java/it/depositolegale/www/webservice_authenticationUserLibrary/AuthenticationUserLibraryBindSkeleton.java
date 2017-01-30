/**
 * AuthenticationUserLibraryBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationUserLibrary;

public class AuthenticationUserLibraryBindSkeleton implements it.depositolegale.www.webservice_authenticationUserLibrary.AuthenticationUserLibraryPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_authenticationUserLibrary.AuthenticationUserLibraryPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", "authenticationUserInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserInput", ">authenticationUserInput"), it.depositolegale.www.authenticationUserInput.AuthenticationUserInput.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("authenticationUserLibraryOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserOutput", "authenticationUserOutput"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/authenticationUserOutput", ">authenticationUserOutput"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "AuthenticationUserLibraryOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("authenticationUserLibraryOperation") == null) {
            _myOperations.put("authenticationUserLibraryOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("authenticationUserLibraryOperation")).add(_oper);
    }

    public AuthenticationUserLibraryBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_authenticationUserLibrary.AuthenticationUserLibraryBindImpl();
    }

    public AuthenticationUserLibraryBindSkeleton(it.depositolegale.www.webservice_authenticationUserLibrary.AuthenticationUserLibraryPortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.authenticationUserOutput.AuthenticationUserOutput authenticationUserLibraryOperation(it.depositolegale.www.authenticationUserInput.AuthenticationUserInput authenticationUserLibraryInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.authenticationUserOutput.AuthenticationUserOutput ret = impl.authenticationUserLibraryOperation(authenticationUserLibraryInput);
        return ret;
    }

}
