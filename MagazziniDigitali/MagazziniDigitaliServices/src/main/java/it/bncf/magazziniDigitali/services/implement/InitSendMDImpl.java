package it.bncf.magazziniDigitali.services.implement;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type;
import it.depositolegale.www.readInfoInput.ReadInfoInput;
import it.depositolegale.www.readInfoOutput.Errori;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;
import mx.randalf.hibernate.exception.HibernateUtilException;

public class InitSendMDImpl {

	private static Logger log = Logger.getLogger(InitSendMDImpl.class);

	public InitSendMDImpl() {
	}

    public static ReadInfoOutput initSendMDOperation(ReadInfoInput input) throws java.rmi.RemoteException {
    	ReadInfoOutput output = null;
    	MDFilesTmpBusiness oggettoDigitaleBusiness = null;
    	String id = null;
    	Errori[] errori = null;
//    	MDIstituzioneDAO mdIstituzioneDAO = null;
//    	MDIstituzione mdIstituzione = null;
//    	MDNodiDAO mdNodiDAO = null;
    	MDSoftwareDAO mdSoftwareDAO = null;
    	MDSoftware mdSoftware = null;
    	MDNodi mdNodi = null;

		output = new ReadInfoOutput();

		output.setSoftware(input.getSoftware());
		output.setOggettoDigitale(input.getOggettoDigitale());
		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness();

			if (SoftwareTools.checkSoftware(input.getSoftware(), input.getSoftware().getNome())){
//			mdIstituzioneDAO = new MDIstituzioneDAO();
//
//			mdIstituzione = mdIstituzioneDAO.findById(input.getIstituto().getId());
//			if (mdIstituzione != null && mdIstituzione.getId().equals(input.getIstituto().getId())){
//				mdNodiDAO = new MDNodiDAO();
				mdNodi = DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigMDNodi("nodo");
//				mdNodi = mdNodiDAO.findById(
//						Configuration.getValue("nodo")
//						);
				if (mdNodi != null){
					mdSoftwareDAO = new MDSoftwareDAO();
					mdSoftware = mdSoftwareDAO.findById(input.getSoftware().getId());
					id = oggettoDigitaleBusiness.insertNewRec(mdSoftware, 
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
		} catch (MDConfigurationException e) {
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
		} catch (HibernateUtilException e) {
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
