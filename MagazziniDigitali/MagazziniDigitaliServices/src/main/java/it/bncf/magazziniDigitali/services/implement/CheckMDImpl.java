package it.bncf.magazziniDigitali.services.implement;

import it.bncf.magazziniDigitali.businessLogic.istituto.IstitutoBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.depositolegale.www.istituto.StatoIstituto_type;
import it.depositolegale.www.oggettiDigitali.StatoOggettoDigitale_type;
import it.depositolegale.www.readInfoInput.ReadInfoInput;
import it.depositolegale.www.readInfoOutput.Errori;
import it.depositolegale.www.readInfoOutput.ReadInfoOutput;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;

import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;

public class CheckMDImpl {

	private static Logger log = Logger.getLogger(CheckMDImpl.class);

	public CheckMDImpl() {
	}

    public static ReadInfoOutput checkMDOperation(ReadInfoInput readInfoMsg) throws java.rmi.RemoteException {
    	ReadInfoOutput output = null;
    	IstitutoBusiness istitutoBusiness = null;
    	OggettoDigitaleBusiness oggettoDigitaleBusiness = null;
    	Errori[] errori = null;
    	Hashtable<String , String> dati = null;

    	try {
			output = new ReadInfoOutput();

			output.setIstituto(readInfoMsg.getIstituto());
			istitutoBusiness = new IstitutoBusiness(readInfoMsg.getIstituto().getId());
			if (istitutoBusiness.isConfigurata()){
				output.getIstituto().setNome(istitutoBusiness.getNome());
				if (istitutoBusiness.getPassword().equals(readInfoMsg.getIstituto().getPassword())){
					output.getIstituto().setStatoIstituto(StatoIstituto_type.VALIDO);
				} else {
					output.getIstituto().setStatoIstituto(StatoIstituto_type.NONVALIDO);
					errori = new Errori[1];
					errori[0] = new Errori();
					errori[0].setId("-2");
					errori[0].setMessaggio("L'istituto non risulta validato");
					output.setErrori(errori);
				}
			} else {
				output.getIstituto().setStatoIstituto(StatoIstituto_type.NONPRESENTE);
				errori = new Errori[1];
				errori[0] = new Errori();
				errori[0].setId("-1");
				errori[0].setMessaggio("L'istituto non risulta essere censito");
				output.setErrori(errori);
			}

			output.setOggettoDigitale(readInfoMsg.getOggettoDigitale());
			if (output.getIstituto().getStatoIstituto().equals(StatoIstituto_type.VALIDO)){
				oggettoDigitaleBusiness = new OggettoDigitaleBusiness();
				for (int x=0; x<output.getOggettoDigitale().getDigest().length; x++){
					try {
						dati = oggettoDigitaleBusiness.checkStatus(output.getOggettoDigitale().getDigest()[x].getDigestValue());
						if (dati!= null){
							output.getOggettoDigitale().setId(dati.get("id"));
							
							if(dati.get("stato").equalsIgnoreCase("ARCHIVIATO")){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.ARCHIVIATO);
							} else if (dati.get("stato").equalsIgnoreCase("CHECKARCHIVIAZIONE")){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.CHECKARCHIVIAZIONE);
							} else if (dati.get("stato").equalsIgnoreCase("FINETRASF")){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.FINETRASF);
							} else if (dati.get("stato").equalsIgnoreCase("INITTRASF")){
								output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.INITTRASF);
							}
						}
					} catch (FileNotFoundException e) {
						log.error(e.getMessage(), e);
					} catch (ClassNotFoundException e) {
						log.error(e.getMessage(), e);
					} catch (SQLException e) {
						log.error(e.getMessage(), e);
					}
				}
				if (output.getOggettoDigitale().getStatoOggettoDigitale()==null){
					output.getOggettoDigitale().setStatoOggettoDigitale(StatoOggettoDigitale_type.NONPRESENTE);
				}
			}
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage(), e);
		}
        return output;
	}
}
