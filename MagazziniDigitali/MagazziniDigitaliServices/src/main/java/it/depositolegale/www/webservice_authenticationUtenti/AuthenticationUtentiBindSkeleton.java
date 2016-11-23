/**
 * AuthenticationUtentiBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationUtenti;

public class AuthenticationUtentiBindSkeleton implements it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", "authenticationUtenti"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.depositolegale.it/loginUtenti", ">authenticationUtenti"), it.depositolegale.www.loginUtenti.AuthenticationUtenti.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("authenticationUtentiOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", "utenti"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/utenti", ">utenti"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "AuthenticationUtentiOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("authenticationUtentiOperation") == null) {
            _myOperations.put("authenticationUtentiOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("authenticationUtentiOperation")).add(_oper);
    }

    public AuthenticationUtentiBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiBindImpl();
    }

    public AuthenticationUtentiBindSkeleton(it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.utenti.Utenti authenticationUtentiOperation(it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtentiInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.utenti.Utenti ret = impl.authenticationUtentiOperation(authenticationUtentiInput);
        return ret;
    }

}
