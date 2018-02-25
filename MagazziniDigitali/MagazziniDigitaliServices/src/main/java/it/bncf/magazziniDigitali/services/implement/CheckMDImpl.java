package it.bncf.magazziniDigitali.services.implement;

import java.rmi.RemoteException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.oggettoDigitale.OggettoDigitaleBusinessStatus;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.depositolegale.www.oggettiDigitali.Digest;
import it.depositolegale.www.oggettiDigitali.Digest_type;
import it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type;
import it.depositolegale.www.readInfoInput.ReadInfoInput;
import it.depositolegale.www.readInfoOutput.Errori;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.solr.exception.SolrException;

public class CheckMDImpl {

	private static Logger log = Logger.getLogger(CheckMDImpl.class);

	public CheckMDImpl() {
	}

    public static ReadInfoOutput checkMDOperation(ReadInfoInput readInfoMsg) throws java.rmi.RemoteException {
    	ReadInfoOutput output = null;
    	OggettoDigitaleBusinessStatus oggettoDigitaleBusiness = null;
    	Errori[] errori = null;
    	Hashtable<String , String> dati = null;
    	String md5 = null;
    	String md564base = null;
    	String sha1 = null;
    	String sha164base = null; 
	String sha256 = null;
	String sha25664base = null;

    	try {
			output = new ReadInfoOutput();

			output.setSoftware(readInfoMsg.getSoftware());
			output.setOggettoDigitale(readInfoMsg.getOggettoDigitale());
			if (SoftwareTools.checkSoftware(readInfoMsg.getSoftware(), readInfoMsg.getSoftware().getNome())){

				oggettoDigitaleBusiness = new OggettoDigitaleBusinessStatus();
				for (int x=0; x<output.getOggettoDigitale().getDigest().length; x++){
					try {
						for (Digest digest:output.getOggettoDigitale().getDigest()) {
							switch(digest.getDigestType().getValue()) {
								case Digest_type._value1:  // SHA256
									sha256 = digest.getDigestValue();
									break;
								case Digest_type._value2:  // SHA1
									sha1 = digest.getDigestValue();
									break;
								case Digest_type._value3:  // MD5
									md5 = digest.getDigestValue();
									break;
								case Digest_type._value4:  // MD5-64Base
									md564base = digest.getDigestValue();
									break;
								case Digest_type._value5:  // SHA1-64Base
									sha164base = digest.getDigestValue();
									break;
								case Digest_type._value6:  // SHA256-64Base
									sha25664base = digest.getDigestValue();
									break;
								default:
									output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
									errori = new Errori[1];
									errori[0] = new Errori("-4","Digest del file usato non gestito");
									output.setErrori(errori);
									throw new RemoteException("Digest del file usato non gestito");
							}
						}
						dati = oggettoDigitaleBusiness.checkStatus(md5, md564base, sha1, sha164base, sha256, sha25664base,
								DepositoLegaleAxisServlet.mdConfiguration);
						if (dati!= null){
							output.getOggettoDigitale().setId(dati.get("id"));
							
							if(dati.get("stato").equalsIgnoreCase(MDStatoDAO.FINEARCHIVE) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.INITINDEX) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.CHECKINDEX) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.FINEINDEX)){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ARCHIVIATO);
							} else if (dati.get("stato").equalsIgnoreCase(MDStatoDAO.INITARCHIVE) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.FINEPUBLISH) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.INITPUBLISH) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.FINEVALID) ||
									dati.get("stato").equalsIgnoreCase(MDStatoDAO.INITVALID)){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.CHECKARCHIVIAZIONE);
							} else if (dati.get("stato").equalsIgnoreCase(MDStatoDAO.FINETRASF)){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.FINETRASF);
							} else if (dati.get("stato").equalsIgnoreCase(MDStatoDAO.INITTRASF)){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.INITTRASF);
							} else {
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ERROR);
							}
						}
					} catch (SolrException e) {
						log.error(e.getMessage(), e);
						throw new RemoteException(e.getMessage(), e);
					} catch (SolrServerException e) {
						log.error(e.getMessage(), e);
						throw new RemoteException(e.getMessage(), e);
					} catch (HibernateException e) {
						throw e;
					} catch (HibernateUtilException e) {
						log.error(e.getMessage(), e);
						throw new RemoteException(e.getMessage(), e);
					} catch (MDConfigurationException e) {
						log.error(e.getMessage(), e);
						throw new RemoteException(e.getMessage(), e);
					}
				}
				if (output.getOggettoDigitale().getStatoOggettoDigitale()==null){
					output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.NONPRESENTE);
				}
			} else {
				errori = new Errori[1];
				errori[0] = new Errori();
				errori[0].setId("-1");
				errori[0].setMessaggio("Il software chiamante non risulta accreditato");
				output.setErrori(errori);
			}
//		} catch (ConfigurationException e) {
//			log.error(e.getMessage(), e);
//			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
        return output;
	}
}
