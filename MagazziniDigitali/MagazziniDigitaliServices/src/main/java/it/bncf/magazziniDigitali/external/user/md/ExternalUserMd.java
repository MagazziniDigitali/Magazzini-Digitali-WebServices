/**
 * 
 */
package it.bncf.magazziniDigitali.external.user.md;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.external.user.IExternalUser;
import it.bncf.magazziniDigitali.external.user.exception.ExternalUserException;
import it.bncf.magazziniDigitali.services.implement.utenti.AuthenticationUtenti;
import it.depositolegale.www.login.Authentication;
import it.depositolegale.www.software.Software;
import it.depositolegale.www.utenti.Utenti;
import mx.randalf.tools.SHA256Tools;

/**
 * @author massi
 *
 */
public class ExternalUserMd implements IExternalUser {

	private IMDConfiguration<Software> configuration = null;

	private String idIstituzione = null;

	/**
	 * 
	 */
	public ExternalUserMd(IMDConfiguration<Software> configuration, String idIstituzione) {
		this.configuration = configuration;
		this.idIstituzione = idIstituzione;
	}

	public boolean isValid(String url, String login, String password, String ipClient) throws ExternalUserException {
		Utenti utenti = null;
		it.depositolegale.www.loginUtenti.AuthenticationUtenti authenticationUtnti = null;
		Authentication authentication = null;
		SHA256Tools sha256Tools = null;
		boolean esito = false;
		
		try {
			authenticationUtnti = new it.depositolegale.www.loginUtenti.AuthenticationUtenti();

			authenticationUtnti.setSoftware(configuration.getSoftware());

			sha256Tools = new SHA256Tools();
			authentication = new Authentication(login, sha256Tools.checkSum(password.getBytes()));
			authenticationUtnti.setAuthentication(authentication);

			authenticationUtnti.setIpClient(ipClient);
			
			utenti = AuthenticationUtenti.AuthenticationUtentiOperation(authenticationUtnti);
			
			if (utenti.getErrorMsg() != null &&
					utenti.getErrorMsg().length>0){
				throw new ExternalUserException(utenti.getErrorMsg()[0].getMsgError());
			} else {
				if (utenti.getDatiUtente().getIstituzione() ==null){
					esito = true;
				} else if (utenti.getDatiUtente().getIstituzione().getId().equals(this.idIstituzione)){
					esito = true;
				} else {
					throw new ExternalUserException("L'editore non risulta essere proprietario dell'oggetto richisto");
				}
			}
		} catch (RemoteException e) {
			throw new ExternalUserException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new ExternalUserException(e.getMessage(), e);
		}
		return esito;
	}

}
