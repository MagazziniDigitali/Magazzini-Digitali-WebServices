package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.ticket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import gov.loc.premis.v3.LinkingRightsStatementIdentifierComplexType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.exception.OggettoDigitaleException;
import it.bncf.magazziniDigitali.businessLogic.ticket.MDTicketBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDTicket;
import it.depositolegale.ticket.Ticket;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import it.magazziniDigitali.xsd.premis.v3_0.PremisV3_0Xsd;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.MD5Tools;
import mx.randalf.xsd.exception.XsdException;

public class OggettoDigitaleTicketBusiness extends OggettoDigitaleBusiness {

	private Logger log = Logger.getLogger(OggettoDigitaleTicketBusiness.class);

	public OggettoDigitaleTicketBusiness() {
		super();
	}

	/**
	 * Questo metodo viene utilizzato per gestire la coda del materiale da
	 * inviare negli altri Storage
	 * 
	 * @param application
	 */
	public void ticket(Logger logCoda, IMDConfiguration<?> configuration)
			throws MDConfigurationException, SQLException {
		MDTicketBusiness mdTicketBusiness = null;
		List<MDTicket> mdTickets = null;
		
		try {
			mdTicketBusiness = new MDTicketBusiness();
			mdTickets = mdTicketBusiness.findExport();

			for (MDTicket mdTicket : mdTickets) {
				createTicket(configuration, mdTicket);
				mdTicketBusiness.export(mdTicket.getId(), new GregorianCalendar());
			}
			compatta(configuration);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (XsdException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (OggettoDigitaleException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		} catch (PremisXsdException e) {
			log.error(e.getMessage(), e);
			throw new MDConfigurationException(e.getMessage(),e);
		}

	}

	private void compatta(IMDConfiguration<?> configuration) throws NoSuchAlgorithmException, MDConfigurationException, IOException, InterruptedException, OggettoDigitaleException {
		File pDest = null;
		File[] fl = null;
		File fTgz = null;
		Vector<String> listaCoda = new Vector<String>();
		
		try {
			pDest = new File(configuration.getSoftwareConfigString("path.scambioTgz"));

			fl = pDest.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					boolean result = false;
					File fTgz = null;
					if (pathname.getName().startsWith("UserAuthentication-") && 
							pathname.isDirectory()){
						fTgz = new File(pathname.getAbsolutePath()+".tgz");
						if (!fTgz.exists()){
							result = true;
						}
					}
					return result;
				}
			});
			
			for (int x=0; x<fl.length; x++){
				fTgz = new File(fl[x].getAbsolutePath()+".tgz");
				if (fl[x].exists() &&
						!fTgz.exists()) {

					if (genCheckSum(fl[x])){
						if (genTarGz(fl[x])){
							listaCoda.add(fl[x].getName());
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (InterruptedException e) {
			throw e;
		} catch (OggettoDigitaleException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}  finally {
			genCodaFile(configuration, listaCoda);
		}
	}

	private void createTicket(IMDConfiguration<?> configuration, 
			MDTicket mdTicket) throws MDConfigurationException, 
			XsdException, PremisXsdException, IOException {
		GregorianCalendar gc = null;
		File pDest = null;
		File fDest = null;
		DecimalFormat df6 = new DecimalFormat("000000");
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		EventXsd<?, ?, ?, ?, ?> eventXsd = null;
		String eventDateTime = null;
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar stop = new GregorianCalendar();
		Ticket ticket = null;
		LinkingRightsStatementIdentifierComplexType lrsict = null;
		
		try {
			gc = new GregorianCalendar();
			gc.setTimeInMillis(mdTicket.getDataStart().getTime());
			
			pDest = new File(configuration.getSoftwareConfigString("path.scambioTgz"));
			pDest = new File(pDest.getAbsolutePath() + 
					File.separator + "UserAuthentication-" + 
					df4.format(gc.get(Calendar.YEAR)) + 
					df2.format(gc.get(Calendar.MONTH)+1) + 
					df2.format(gc.get(Calendar.DAY_OF_MONTH)));
			pDest = checkFolder(pDest, 0);

			fDest = new File(pDest.getAbsolutePath()+File.separator+mdTicket.getId()+".xml");
			eventXsd = EventXsd.initialize();

			eventXsd.setEventIdentifier(PremisXsd.UUID_MD_EV, mdTicket.getId()+"-EV");
			eventXsd.setEventType(EventXsd.USERAUTHENTICATION);

			if (mdTicket.getDataStart() != null){
				start.setTimeInMillis(mdTicket.getDataStart().getTime());
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
			if (mdTicket.getDataEnd() != null){
				stop.setTimeInMillis(mdTicket.getDataEnd().getTime());
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

			if (mdTicket.getIdIstituzione() != null){
				eventXsd.addLinkingAgentIdentifier(PremisXsd.UUID_MD_AG, 
						mdTicket.getIdIstituzione().getId(), 
						PremisXsd.BIBLIOTECHE);
			}
			
			eventXsd.addLinkingObjectIdentifier(PremisXsd.UUID_MD, 
					mdTicket.getObjectIdentifier());

			ticket = new Ticket();
			
			lrsict = new LinkingRightsStatementIdentifierComplexType();
			lrsict.setLinkingRightsStatementIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(PremisXsd.UUID_MD_RG));
			lrsict.setLinkingRightsStatementIdentifierValue(mdTicket.getIdRights());
			ticket.setLinkingRightsStatementIdentifier(lrsict);

			ticket.setIpClient(mdTicket.getIpClient());
			if (mdTicket.getLoginUtente()!=null){
				ticket.setLoginUtente(mdTicket.getLoginUtente());
			}

			eventXsd.addEventDetailInformationExtension(
					new JAXBElement<Ticket>(
							new QName("http://www.depositolegale.it/Ticket", "ticket")
							, Ticket.class, null, ticket)
					);

			eventXsd.write(fDest, false);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
	
	private File checkFolder(File pDest, int seq) throws FileNotFoundException {
		File output = null;
		File fTgz = null;
		DecimalFormat df3 = new DecimalFormat("000");
		
		try {
			if (seq ==0){
				output = pDest;
			} else {
				output = new File(pDest.getAbsolutePath()+"_"+df3.format(seq));
			}
			if (!output.exists()){
				if (!output.mkdirs()){
					throw new FileNotFoundException("Problemi nella creazione della cartella ["+output.getAbsolutePath()+"]");
				}
			} else {
				fTgz = new File(output.getAbsolutePath()+".tgz");
				if (fTgz.exists()){
					output = checkFolder(pDest, seq+1);
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
		return output;
	}

	private boolean genCheckSum(File fDest) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
		File[] ls = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		boolean result = false;

		try {
			ls = fDest.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					boolean result = false;
					if (!pathname.getName().startsWith(".") && !pathname.getName().equals("checksum.md5")) {
						result = true;
					}
					return result;
				}
			});
			fw = new FileWriter(new File(fDest.getAbsolutePath() + File.separator + "checksum.md5"));
			bw = new BufferedWriter(fw);
			for (File file : ls) {
				result = true;
				bw.write(MD5Tools.readMD5File(file.getAbsolutePath()) + "  " + file.getName() + "\n");
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return result;
	}


	private void genCodaFile(IMDConfiguration<?> configuration, Vector<String> names)
			throws MDConfigurationException, IOException {
		File fCoda = null;
		GregorianCalendar gc = null;
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df3 = new DecimalFormat("000");
		DecimalFormat df2 = new DecimalFormat("00");
		FileWriter fw = null;

		try {
			fCoda = new File(configuration.getSoftwareConfigString("path.scambioCoda"));

			gc = new GregorianCalendar();

			fCoda = new File(fCoda.getAbsolutePath() + File.separator + df4.format(gc.get(Calendar.YEAR))
					+ df2.format(gc.get(Calendar.MONTH) + 1) + df2.format(gc.get(Calendar.DAY_OF_MONTH))
					+ df2.format(gc.get(Calendar.HOUR_OF_DAY)) + df2.format(gc.get(Calendar.MINUTE))
					+ df2.format(gc.get(Calendar.SECOND)) + df3.format(gc.get(Calendar.MILLISECOND)) + ".txt");

			if (!fCoda.getParentFile().exists()){
				if (!fCoda.getParentFile().mkdirs()){
					throw new IOException("Riscontrato un problema nella creazione della cartella ["+fCoda.getParentFile().getAbsolutePath()+"]");
				}
			}
			fw = new FileWriter(fCoda);
			for (String name: names){
				fw.write(name+"\n");
			}
		} catch (MDConfigurationException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fw != null) {
					fw.flush();
					fw.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}

	}


	private boolean genTarGz(File fDest) throws IOException, InterruptedException, OggettoDigitaleException {
		Process process = null;
		Runtime runtime = null;
		String[] cmdarray = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		int exitValue = 0;
		String msgError = "";
		String line = null;

		try {
			cmdarray = new String[4];
			cmdarray[0] = "tar";
			cmdarray[1] = "cvfz";
			cmdarray[2] = fDest.getName() + ".tgz";
			cmdarray[3] = fDest.getName();

			runtime = Runtime.getRuntime();
			process = runtime.exec(cmdarray, null, fDest.getParentFile());

			isr = new InputStreamReader(process.getErrorStream());
			br = new BufferedReader(isr);

			exitValue = process.waitFor();

			while ((line = br.readLine()) != null) {
				msgError += (msgError.equals("") ? "" : "\n");
				msgError += line;
			}

			if (exitValue != 0){
				if (!msgError.equals("")) {
					throw new OggettoDigitaleException(msgError);
				}
			}
			process.destroy();
		} catch (IOException e) {
			throw e;
		} catch (InterruptedException e) {
			throw e;
		} catch (OggettoDigitaleException e) {
			throw e;
		}
		return exitValue == 0;
	}
}
