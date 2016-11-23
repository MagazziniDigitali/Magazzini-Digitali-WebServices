package it.depositolegale.www.webservice_authenticationUtenti;

public class AuthenticationUtentiPortTypeProxy implements it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType {
  private String _endpoint = null;
  private it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType authenticationUtentiPortType = null;
  
  public AuthenticationUtentiPortTypeProxy() {
    _initAuthenticationUtentiPortTypeProxy();
  }
  
  public AuthenticationUtentiPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAuthenticationUtentiPortTypeProxy();
  }
  
  private void _initAuthenticationUtentiPortTypeProxy() {
    try {
      authenticationUtentiPortType = (new it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiServiceLocator()).getAuthenticationUtentiPort();
      if (authenticationUtentiPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)authenticationUtentiPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)authenticationUtentiPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (authenticationUtentiPortType != null)
      ((javax.xml.rpc.Stub)authenticationUtentiPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public it.depositolegale.www.webservice_authenticationUtenti.AuthenticationUtentiPortType getAuthenticationUtentiPortType() {
    if (authenticationUtentiPortType == null)
      _initAuthenticationUtentiPortTypeProxy();
    return authenticationUtentiPortType;
  }
  
  public it.depositolegale.www.utenti.Utenti authenticationUtentiOperation(it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtenti) throws java.rmi.RemoteException{
    if (authenticationUtentiPortType == null)
      _initAuthenticationUtentiPortTypeProxy();
    return authenticationUtentiPortType.authenticationUtentiOperation(authenticationUtenti);
  }
  
  
}