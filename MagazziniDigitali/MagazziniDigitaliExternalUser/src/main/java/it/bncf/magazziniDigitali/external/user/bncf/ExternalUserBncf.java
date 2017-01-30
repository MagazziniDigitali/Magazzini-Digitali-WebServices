/**
 * 
 */
package it.bncf.magazziniDigitali.external.user.bncf;

import java.text.DecimalFormat;

import it.bncf.magazziniDigitali.external.user.IExternalUser;
import it.bncf.magazziniDigitali.external.user.exception.ExternalUserException;
import net.bncf.uol2010.utility.validate.user.UserValidator;
import net.bncf.uol2010.utility.validate.user.exception.UserValidatorException;

/**
 * @author massi
 *
 */
public class ExternalUserBncf implements IExternalUser {

	/**
	 * 
	 */
	public ExternalUserBncf() {
	}

	public boolean isValid(String url, String login, String password, String ipClient) throws ExternalUserException {
		UserValidator userValidator = null;
		boolean esito = false;
		DecimalFormat df7 = new DecimalFormat("0000000");
		
		try {
			userValidator = new UserValidator(url);
			login = login.toUpperCase();
			if (login.startsWith("CFU")){
				login = " CF"+df7.format(new Integer(login.replace("CFU", "")));
			} else if (login.startsWith(" CF")){
				login = " CF"+df7.format(new Integer(login.replace(" CF", "")));
			} else if (login.startsWith("CF")){
				login = " CF"+df7.format(new Integer(login.replace("CF", "")));
			}
			esito = userValidator.validate(login, password, ipClient, "public2010");
			if (!esito){
				throw new ExternalUserException(userValidator.getUtente().getMsgError().get(0).getValue());
			}
		} catch (UserValidatorException e) {
			throw new ExternalUserException(e.getMessage(), e);
		}
		return esito;
	}

}
