package it.bncf.magazziniDigitali.services.implement;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.depositolegale.www.oggettiDigitali.Digest;
import it.depositolegale.www.oggettiDigitali.Digest_type;
import it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type;
import it.depositolegale.www.readInfoInput.ReadInfoInput;
import it.depositolegale.www.readInfoOutput.Errori;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;
import mx.randalf.hibernate.exception.HibernateUtilException;

public class InitSendMDImpl {

	private static Logger log = LogManager.getLogger(InitSendMDImpl.class);

	public InitSendMDImpl() {
	}

	public static ReadInfoOutput initSendMDOperation(ReadInfoInput input) throws java.rmi.RemoteException {
		ReadInfoOutput output = null;
		MDFilesTmpBusiness oggettoDigitaleBusiness = null;
		String id = null;
		Errori[] errori = null;
		MDSoftwareDAO mdSoftwareDAO = null;
		MDSoftware mdSoftware = null;
		MDNodi mdNodi = null;
		String md5 = null;
		String md564base = null;
		String sha1 = null;
		String sha164base = null;
		String sha256 = null;
		String sha25664base = null;

		output = new ReadInfoOutput();

		output.setSoftware(input.getSoftware());
		output.setOggettoDigitale(input.getOggettoDigitale());
		try {
			oggettoDigitaleBusiness = new MDFilesTmpBusiness();

			if (SoftwareTools.checkSoftware(input.getSoftware(), input.getSoftware().getNome())) {
				mdNodi = DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigMDNodi("nodo");
				if (mdNodi != null) {
					mdSoftwareDAO = new MDSoftwareDAO();
					mdSoftware = mdSoftwareDAO.findById(input.getSoftware().getId());
					for (Digest digest : input.getOggettoDigitale().getDigest()) {
						switch (digest.getDigestType().getValue()) {
						case Digest_type._value1: // SHA256
							sha256 = digest.getDigestValue();
							break;
						case Digest_type._value2: // SHA1
							sha1 = digest.getDigestValue();
							break;
						case Digest_type._value3: // MD5
							md5 = digest.getDigestValue();
							break;
						case Digest_type._value4: // MD5-64Base
							md564base = digest.getDigestValue();
							break;
						case Digest_type._value5: // SHA1-64Base
							sha164base = digest.getDigestValue();
							break;
						case Digest_type._value6: // SHA256-64Base
							sha25664base = digest.getDigestValue();
							break;
						default:
							output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
							errori = new Errori[1];
							errori[0] = new Errori("-4", "Digest del file usato non gestito");
							output.setErrori(errori);
							throw new RemoteException("Digest del file usato non gestito");
						}
					}
					if (sha256 != null || sha1 != null || md5 != null || md564base != null || sha164base != null
							|| sha25664base != null) {
						
						id = oggettoDigitaleBusiness.insertNewRec(mdSoftware, input.getOggettoDigitale().getNomeFile(),
								md5, md564base, sha1, sha164base, sha256, sha25664base,
								input.getOggettoDigitale().getUltimaModifica(), 
								mdNodi,mdSoftware.getSoftwareConfigs("removeSource"));
						output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.INITTRASF);
						output.getOggettoDigitale().setId(id);
					} else {
						output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
						errori = new Errori[1];
						errori[0] = new Errori("-5", "Indicare almeno in Digest valido");
						output.setErrori(errori);
						throw new RemoteException("Indicare almeno in Digest valido");
					}
				} else {
					output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
					errori = new Errori[1];
					errori[0] = new Errori("-3", "Il nodo non risulta censito");
					output.setErrori(errori);
					throw new RemoteException("Il nodo non risulta censito");
				}
			} else {
				output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
				errori = new Errori[1];
				errori[0] = new Errori("-2", "L'istituzione indicare non risulta essere censita");
				output.setErrori(errori);
				throw new RemoteException("L'istituzione indicare non risulta essere censita");
			}
		} catch (SQLException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1", e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1", e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1", e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
			errori = new Errori[1];
			errori[0] = new Errori("-1", e.getMessage());
			output.setErrori(errori);
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}

		return output;
	}
}
