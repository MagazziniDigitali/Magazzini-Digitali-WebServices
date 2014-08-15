package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.depositolegale.www.endSend.EndSend;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class EndSendMDImpl {

	private static Logger log = Logger.getLogger(EndSendMDImpl.class);

	public EndSendMDImpl() {
	}

    public static void endSendMDOperation(EndSend input) throws java.rmi.RemoteException {
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;
    	String[] errors = null;

		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness(null);

			if (input.getErrori() != null && 
					 input.getErrori().length>0){
				errors = new String[input.getErrori().length];
				for(int x=0; x<input.getErrori().length; x++){
					errors[x]=input.getErrori()[x].getMessaggio();
				}
			}
			oggettoDigitaleBusiness.updatEndSend(input.getReadInfoOutput().getOggettoDigitale().getId(), 
					input.isEsito(), 
					errors);

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
