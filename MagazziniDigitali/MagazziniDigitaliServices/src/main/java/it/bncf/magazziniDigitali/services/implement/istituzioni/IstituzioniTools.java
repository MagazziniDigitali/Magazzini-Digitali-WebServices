package it.bncf.magazziniDigitali.services.implement.istituzioni;

import java.math.BigInteger;

import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;

import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.depositolegale.www.istituzione.Istituzione;
import it.depositolegale.www.istituzione.IstituzioneApiUtente;

public class IstituzioniTools {

	public IstituzioniTools() {
	}

	public static Istituzione genIstituzione(MDIstituzione mdIstituzione){
		Istituzione istituzione = null;
		
		istituzione = new Istituzione();
		istituzione.setId(mdIstituzione.getId());
		istituzione.setNome(mdIstituzione.getNome());
		istituzione.setIndirizzo(mdIstituzione.getIndirizzo());
		istituzione.setTelefono(mdIstituzione.getTelefono());
		istituzione.setNomeContatto(mdIstituzione.getNomeContatto());
		istituzione.setBibliotecaDepositaria(new BigInteger(mdIstituzione.getBibliotecaDepositaria()+""));
		istituzione.setIstitutoCentrale(new BigInteger(mdIstituzione.getIstitutoCentrale()+""));
		if (mdIstituzione.getIpAccreditati()!= null && !mdIstituzione.getIpAccreditati().trim().equals("")){
			istituzione.setIpAccreditati(mdIstituzione.getIpAccreditati().split(","));
		}
		if ((mdIstituzione.getInterfacciaApiUtente() != null && !mdIstituzione.getInterfacciaApiUtente().trim().equals("")) &&
				(mdIstituzione.getLibreriaApiUtente() != null && !mdIstituzione.getLibreriaApiUtente().trim().equals(""))){
			try {
				istituzione.setApiUtente(new IstituzioneApiUtente(new URI(mdIstituzione.getInterfacciaApiUtente().trim()), mdIstituzione.getLibreriaApiUtente().trim()));
			} catch (MalformedURIException e) {
				e.printStackTrace();
			}
		}
		istituzione.setEmailBagit(mdIstituzione.getEmailBagit());

		return istituzione;
	}

}
