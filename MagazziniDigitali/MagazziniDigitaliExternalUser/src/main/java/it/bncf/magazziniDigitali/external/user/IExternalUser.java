package it.bncf.magazziniDigitali.external.user;

import it.bncf.magazziniDigitali.external.user.exception.ExternalUserException;

/**
 * 
 * @author massi
 *
 */
public interface IExternalUser {

	public boolean isValid(String url, String login, String password, String ipClient) throws ExternalUserException;
}
