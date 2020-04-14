/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.userLibrary;

import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.depositolegale.www.authenticationUserInput.AuthenticationUserInput;
import it.depositolegale.www.authenticationUserOutput.AuthenticationUserOutput;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class AuthenticationUserLibrary extends AuthenticationUserLibraryFindObject {

	private static Logger log = LogManager.getLogger(AuthenticationUserLibrary.class);

	/**
	 * 
	 */
	public AuthenticationUserLibrary() {
	}

	public static AuthenticationUserOutput AuthenticationUserLibraryOperation(AuthenticationUserInput input) {
		AuthenticationUserOutput output = null;
		Vector<ErrorMsg> errorMsgs = null;

		output = new AuthenticationUserOutput();
		output.setUserInput(input.getUserInput());
		errorMsgs = new Vector<ErrorMsg>();
		try {
			if (SoftwareTools.checkSoftware(input.getUserInput().getSoftware(),
					input.getUserInput().getSoftware().getAuthentication().getLogin())) {
				if (input.getUserInput() != null && input.getUserInput().getObjectIdentifier() != null
						&& input.getUserInput().getObjectIdentifier().getObjectIdentifierType() != null
						&& input.getUserInput().getObjectIdentifier().getObjectIdentifierValue() != null
						&& input.getUserInput().getIpClient() != null) {
					if (input.getUserInput().getRights() != null && input.getUserInput().getAuthentication() != null) {
						// verifico le credenziali e costruisco il ticket per
						// soddisfare la richiesta
						output.setRights(input.getUserInput().getRights());
						output.setUrl(genTicket(input.getUserInput(), input.getUserInput().getRights(),
								input.getUserInput().getActualFileName(), input.getUserInput().getOriginalFileName()));
					} else {
						// verifico le infomrazioni relative all'oggetto
						// richiesto e sottoconto le credenziali disponibili
						findObject(input, output);
					}
				} else {
					errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR,
							"Dati inseriti non sufficenti controllare il tracciato"));
				}
			} else {
				errorMsgs.add(
						new ErrorMsg(ErrorType_type.SOFTWARE_ERROR, "Le credenziali del Software non sono valide"));
			}
		} catch (HibernateException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} catch (AuthenticationUserLibraryException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			// log.error(e.getMessage(), e);
		} catch (Exception e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} finally {
			if (errorMsgs.size() > 0) {
				output.setErrorMsg(errorMsgs.toArray(new ErrorMsg[errorMsgs.size()]));
			}
		}
		return output;
	}
}
