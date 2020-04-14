package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.depositolegale.www.endSend.EndSend;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndSendMDImpl {

	private static Logger log = LogManager.getLogger(EndSendMDImpl.class);

	public EndSendMDImpl() {
	}

    public static void endSendMDOperation(EndSend input) throws java.rmi.RemoteException {
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;
    	String[] errors = null;

		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness();

			if (input.getErrori() != null && 
					 input.getErrori().length>0){
				errors = new String[input.getErrori().length];
				for(int x=0; x<input.getErrori().length; x++){
					errors[x]=input.getErrori()[x].getMessaggio();
				}
			}
			oggettoDigitaleBusiness.updatEndSend(input.getReadInfoOutput().getOggettoDigitale().getId(), 
					input.isEsito(), 
					null, errors);

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
    	
	}
}
