package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.depositolegale.www.readInfoInput.ReadInfoInput;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfirmDelMDImpl {

	private static Logger log = LogManager.getLogger(ConfirmDelMDImpl.class);

	public ConfirmDelMDImpl() {
	}

    public static void confirmDelMDOperation(ReadInfoInput confirmDelMDInput) throws java.rmi.RemoteException {
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;

		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness();

			oggettoDigitaleBusiness.confirmDel(confirmDelMDInput.getOggettoDigitale().getId(), 
					true, 
					null);

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
    	
	}
}
