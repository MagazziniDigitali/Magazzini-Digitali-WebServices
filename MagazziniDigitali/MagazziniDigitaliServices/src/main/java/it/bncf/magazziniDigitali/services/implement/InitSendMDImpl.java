package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.database.dao.MDIstituzioneDAO;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type;
import it.depositolegale.www.readInfoInput.ReadInfoInput;
import it.depositolegale.www.readInfoOutput.Errori;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.naming.NamingException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

public class InitSendMDImpl {

	private static Logger log = Logger.getLogger(InitSendMDImpl.class);

	public InitSendMDImpl() {
	}

    public static ReadInfoOutput initSendMDOperation(ReadInfoInput input) throws java.rmi.RemoteException {
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;
    	ReadInfoOutput output = null;
    	String id = null;
    	Errori[] errori = null;
    	MDIstituzioneDAO mdIstituzioneDAO = null;
    	MDIstituzione mdIstituzione = null;
    	MDNodiDAO mdNodiDAO = null;
    	MDNodi mdNodi = null;

		output = new ReadInfoOutput();

		output.setIstituto(input.getIstituto());
		output.setOggettoDigitale(input.getOggettoDigitale());
		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness(null);

			mdIstituzioneDAO = new MDIstituzioneDAO(null);

			mdIstituzione = mdIstituzioneDAO.findById(input.getIstituto().getId());
			if (mdIstituzione != null && mdIstituzione.getId().equals(input.getIstituto().getId())){
				mdNodiDAO = new MDNodiDAO(null);
				mdNodi = mdNodiDAO.findById(Configuration.getValue("nodo"));
				if (mdNodi != null){
					id = oggettoDigitaleBusiness.insertNewRec(mdIstituzione, 
							input.getOggettoDigitale().getNomeFile(), 
							input.getOggettoDigitale().getDigest()[0].getDigestValue(), 
							input.getOggettoDigitale().getUltimaModifica(),
							mdNodi);
					output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.INITTRASF);
					output.getOggettoDigitale().setId(id);
				} else {
					output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
					errori = new Errori[1];
					errori[0] = new Errori("-3","Il nodo non risulta censito");
					output.setErrori(errori);
					throw new RemoteException("Il nodo non risulta censito");
				}
			} else {
				output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
				errori = new Errori[1];
				errori[0] = new Errori("-2","L'istituzione indicare non risulta essere censita");
				output.setErrori(errori);
				throw new RemoteException("L'istituzione indicare non risulta essere censita");
			}
		} catch (SQLException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1",e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1",e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1",e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (NamingException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1",e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
    	
        return output;
	}
}
