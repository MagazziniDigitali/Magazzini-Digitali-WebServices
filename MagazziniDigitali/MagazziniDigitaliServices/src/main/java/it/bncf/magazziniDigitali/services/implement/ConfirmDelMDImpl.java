package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.depositolegale.www.readInfoInput.ReadInfoInput;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConfirmDelMDImpl {

	private static Logger log = Logger.getLogger(ConfirmDelMDImpl.class);

	public ConfirmDelMDImpl() {
	}

    public static void confirmDelMDOperation(ReadInfoInput confirmDelMDInput) throws java.rmi.RemoteException {
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;

		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness(null);

			oggettoDigitaleBusiness.confirmDel(confirmDelMDInput.getOggettoDigitale().getId(), 
					true, 
					null);

//		} catch (FileNotFoundException e) {
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
//		} catch (ClassNotFoundException e) {
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
//		} catch (ConfigurationException e) {
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
		}
    	
	}
}
