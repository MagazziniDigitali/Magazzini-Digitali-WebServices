package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type;
import it.depositolegale.www.readInfoInput.ReadInfoInput;
import it.depositolegale.www.readInfoOutput.Errori;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class InitSendMDImpl {

	private static Logger log = Logger.getLogger(InitSendMDImpl.class);

	public InitSendMDImpl() {
	}

    public static ReadInfoOutput initSendMDOperation(ReadInfoInput input) throws java.rmi.RemoteException {
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;
    	ReadInfoOutput output = null;
    	String id = null;
    	Errori[] errori = null;

		output = new ReadInfoOutput();

		output.setIstituto(input.getIstituto());
		output.setOggettoDigitale(input.getOggettoDigitale());
		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness(null);


			id = oggettoDigitaleBusiness.insertNewRec(input.getIstituto().getId(), 
					input.getOggettoDigitale().getNomeFile(), 
					input.getOggettoDigitale().getDigest()[0].getDigestValue(), 
					input.getOggettoDigitale().getUltimaModifica());
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.INITTRASF);
			output.getOggettoDigitale().setId(id);
//		} catch (FileNotFoundException e) {
//			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
//			errori = new Errori[1];
//			errori[0] = new Errori("-1",e.getMessage());
//			output.setErrori(errori);
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
//		} catch (ClassNotFoundException e) {
//			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
//			errori = new Errori[1];
//			errori[0] = new Errori("-1",e.getMessage());
//			output.setErrori(errori);
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
		} catch (SQLException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1",e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
//		} catch (ConfigurationException e) {
//			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
//			errori = new Errori[1];
//			errori[0] = new Errori("-1",e.getMessage());
//			output.setErrori(errori);
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
		}
    	
        return output;
	}
}
