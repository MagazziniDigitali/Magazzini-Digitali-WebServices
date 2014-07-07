package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.depositolegale.www.endSend.EndSend;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;

public class ConfirmDelMDImpl {

	private static Logger log = Logger.getLogger(ConfirmDelMDImpl.class);

	public ConfirmDelMDImpl() {
	}

    public static void confirmDelMDOperation(EndSend input) throws java.rmi.RemoteException {
    	OggettoDigitaleBusiness oggettoDigitaleBusiness = null;

		try {
			oggettoDigitaleBusiness = new OggettoDigitaleBusiness();

			oggettoDigitaleBusiness.confirmDel(input.getReadInfoOutput().getOggettoDigitale().getId(), 
					input.isEsito(), 
					(input.getErrori() != null && 
					 input.getErrori().length>0?
							 input.getErrori()[0].getMessaggio():null));

		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
    	
	}
}
