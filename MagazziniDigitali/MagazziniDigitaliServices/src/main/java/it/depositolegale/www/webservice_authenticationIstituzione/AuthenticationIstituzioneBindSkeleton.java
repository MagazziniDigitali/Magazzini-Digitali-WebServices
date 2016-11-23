/**
 * AuthenticationIstituzioneBindSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.depositolegale.www.webservice_authenticationIstituzione;

public class AuthenticationIstituzioneBindSkeleton implements it.depositolegale.www.webservice_authenticationIstituzione.AuthenticationIstituzionePortType, org.apache.axis.wsdl.Skeleton {
    private it.depositolegale.www.webservice_authenticationIstituzione.AuthenticationIstituzionePortType impl;
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
        _oper = new org.apache.axis.description.OperationDesc("authenticationIstituzioneOperation", _params, new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", "mdIstituzione"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.depositolegale.it/istituzione", ">mdIstituzione"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "AuthenticationIstituzioneOperation"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("authenticationIstituzioneOperation") == null) {
            _myOperations.put("authenticationIstituzioneOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("authenticationIstituzioneOperation")).add(_oper);
    }

    public AuthenticationIstituzioneBindSkeleton() {
        this.impl = new it.depositolegale.www.webservice_authenticationIstituzione.AuthenticationIstituzioneBindImpl();
    }

    public AuthenticationIstituzioneBindSkeleton(it.depositolegale.www.webservice_authenticationIstituzione.AuthenticationIstituzionePortType impl) {
        this.impl = impl;
    }
    public it.depositolegale.www.istituzione.MdIstituzione authenticationIstituzioneOperation(it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationIstituzioneInput) throws java.rmi.RemoteException
    {
        it.depositolegale.www.istituzione.MdIstituzione ret = impl.authenticationIstituzioneOperation(authenticationIstituzioneInput);
        return ret;
    }

}
