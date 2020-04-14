/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.writeEventNBN;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.OggettoDigitale;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import it.depositolegale.www.writeEventNBNOutput.WriteEventNBNOutput;
import it.depositolegale.www.writeEventNBNOutput.WriteEventNBNOutputEsito;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class WriteEventNBN {

	private static Logger log = LogManager.getLogger(WriteEventNBN.class);

	/**
	 * 
	 */
	public WriteEventNBN() {
	}

	public static WriteEventNBNOutput WriteEventNBNOperation(it.depositolegale.www.writeEventNBN.WriteEventNBN input){
		EventXsd<?, ?, ?, ?, ?> eventXsd = null;
		File filePremis = null;
		String eventDateTime = null;
		DecimalFormat df6 = new DecimalFormat("000000");
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar stop = new GregorianCalendar();
		String agId = null;
		Vector<ErrorMsg> errorMsgs = null;
		WriteEventNBNOutput output = null;

		errorMsgs = new Vector<ErrorMsg>();
		try {
			if (SoftwareTools.checkSoftware(input.getSoftware(), 
											input.getSoftware().getAuthentication().getLogin())){
				if (input.getCodiceNBN() != null &&
						input.getUrlOriginale() != null ){
					output = new WriteEventNBNOutput();
					output.setWriteEventNBN(input);

					agId = UUID.randomUUID().toString()+"-EV";
					eventXsd = EventXsd.initialize();
					
					eventXsd.setEventIdentifier(PremisXsd.UUID_MD_EV, agId);
					eventXsd.setEventType(EventXsd.NBN);
					if (input.getDataInizioElab() != null){
						start.setTimeInMillis(input.getDataInizioElab().getTimeInMillis());
					}
					eventDateTime = df4.format(start.get(Calendar.YEAR));
					eventDateTime += df2.format(start.get(Calendar.MONTH) + 1);
					eventDateTime += df2.format(start.get(Calendar.DAY_OF_MONTH));
					eventDateTime += "T";
					eventDateTime += df2.format(start.get(Calendar.HOUR_OF_DAY));
					eventDateTime += df2.format(start.get(Calendar.MINUTE));
					eventDateTime += df2.format(start.get(Calendar.SECOND));
					eventDateTime += "-";
					eventDateTime += df6.format(start.get(Calendar.MILLISECOND));
					if (input.getDataInizioElab() != null){
						eventDateTime += "/";
						eventDateTime += df4.format(stop.get(Calendar.YEAR));
						eventDateTime += df2.format(stop.get(Calendar.MONTH) + 1);
						eventDateTime += df2.format(stop.get(Calendar.DAY_OF_MONTH));
						eventDateTime += "T";
						eventDateTime += df2.format(stop.get(Calendar.HOUR_OF_DAY));
						eventDateTime += df2.format(stop.get(Calendar.MINUTE));
						eventDateTime += df2.format(stop.get(Calendar.SECOND));
						eventDateTime += "-";
						eventDateTime += df6.format(stop.get(Calendar.MILLISECOND));
					}

					eventXsd.setEventDateTime(eventDateTime);

					eventXsd.addLinkingObjectIdentifier(EventXsd.CODICENBN, 
							input.getCodiceNBN());

					eventXsd.addLinkingObjectIdentifier(EventXsd.URLORIGINAL, 
							input.getUrlOriginale());
					
					filePremis = new File(
							OggettoDigitale.genFilePremis(
									DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("path.premis"), 
									"Event_NBN",
									agId,".premis"));

					eventXsd.write(filePremis, false);
					
				} else {
					errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Dati inseriti non sufficenti controllare il tracciato"));
				}
			} else {
				errorMsgs.add(new ErrorMsg(ErrorType_type.SOFTWARE_ERROR, "Le credenziali del Software non sono valide"));
			}
		} catch (HibernateException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		}finally {
			if (errorMsgs.size()>0){
				output.setEsito(WriteEventNBNOutputEsito.KO);
				output.setErrorMsg(errorMsgs.toArray(new ErrorMsg[errorMsgs.size()]));
			} else {
				output.setEsito(WriteEventNBNOutputEsito.OK);
			}
		}
		return output;
	}
}
