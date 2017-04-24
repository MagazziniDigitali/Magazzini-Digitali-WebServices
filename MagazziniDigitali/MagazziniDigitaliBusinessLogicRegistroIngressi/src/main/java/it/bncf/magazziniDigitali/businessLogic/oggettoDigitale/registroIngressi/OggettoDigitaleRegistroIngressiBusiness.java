package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.registroIngressi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import gov.loc.premis.v3.LinkingAgentIdentifierComplexType;
import gov.loc.premis.v3.ObjectIdentifierComplexType;
import gov.loc.premis.v3.OriginalNameComplexType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.exception.OggettoDigitaleException;
import it.bncf.magazziniDigitali.businessLogic.registroIngresso.MDRegistroIngressoBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDRegistroIngresso;
import it.depositolegale.registroIngressi.MdRegistroIngressi;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.v3_0.PremisV3_0Xsd;
import it.magazziniDigitali.xsd.registroIngressi.RegistroIngressiNPM;
import it.magazziniDigitali.xsd.registroIngressi.RegistroIngressiXsd;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.MD5Tools;
import mx.randalf.xsd.exception.XsdException;

public class OggettoDigitaleRegistroIngressiBusiness extends OggettoDigitaleBusiness {

	private Logger log = Logger.getLogger(OggettoDigitaleRegistroIngressiBusiness.class);

	public OggettoDigitaleRegistroIngressiBusiness() {
		super();
	}

	/**
	 * Questo metodo viene utilizzato per gestire la coda del materiale da
	 * inviare negli altri Storage
	 * 
	 * @param application
	 */
	public void registroIngressi(Logger logCoda, IMDConfiguration<?> configuration)
			throws MDConfigurationException, SQLException {
		MDRegistroIngressoBusiness mdRegistroIngressoBusiness = null;
		List<MDRegistroIngresso> mdRegistroIngressos = null;
		
		try {
			mdRegistroIngressoBusiness = new MDRegistroIngressoBusiness();
			mdRegistroIngressos = mdRegistroIngressoBusiness.findExport();

			for (MDRegistroIngresso mdRegistroIngresso : mdRegistroIngressos) {
				crateRegistroIngresso(configuration, mdRegistroIngresso);
				mdRegistroIngressoBusiness.export(mdRegistroIngresso.getId(), new GregorianCalendar());
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
		} catch (DatatypeConfigurationException e) {
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
					if (pathname.getName().startsWith("RegistroIngresso-") && 
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

	private void crateRegistroIngresso(IMDConfiguration<?> configuration, MDRegistroIngresso mdRegistroIngresso) throws FileNotFoundException, MDConfigurationException, 
			XsdException, DatatypeConfigurationException {
		GregorianCalendar gc = null;
		File pDest = null;
		File fDest = null;
		String schemaLocation = null;
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		RegistroIngressiXsd registroIngressiXsd = null;
		
		try {
			gc = new GregorianCalendar();
			gc.setTimeInMillis(mdRegistroIngresso.getTimestampPub().getTime());
			
			pDest = new File(configuration.getSoftwareConfigString("path.scambioTgz"));
			pDest = new File(pDest.getAbsolutePath() + 
					File.separator + "RegistroIngresso-" + 
					df4.format(gc.get(Calendar.YEAR)) + 
					df2.format(gc.get(Calendar.MONTH)+1) + 
					df2.format(gc.get(Calendar.DAY_OF_MONTH)));
			pDest = checkFolder(pDest, 0);

			fDest = new File(pDest.getAbsolutePath()+File.separator+mdRegistroIngresso.getId()+".xml");
			registroIngressiXsd = new RegistroIngressiXsd();

			schemaLocation = "http://www.depositolegale.it/registroIngressi http://md-www.test.bncf.lan/xsd/mdRegistroIngressi.xsd";
			registroIngressiXsd.write(genMdRegistroIngressi(mdRegistroIngresso), fDest, new RegistroIngressiNPM(), null, null, schemaLocation);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (DatatypeConfigurationException e) {
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

	private MdRegistroIngressi genMdRegistroIngressi(MDRegistroIngresso mdRegistroIngresso) throws DatatypeConfigurationException {
		MdRegistroIngressi mdRegistroIngressi = null;
		ObjectIdentifierComplexType id = null;
		DatatypeFactory df = null;
		GregorianCalendar gc = null;
		LinkingAgentIdentifierComplexType agentDepositor = null;
		OriginalNameComplexType originalContainerName = null;
		OriginalNameComplexType containerName = null;
		LinkingAgentIdentifierComplexType agentMachineIngest = null;
		LinkingAgentIdentifierComplexType agentSoftwareIngest = null;

		try {
			mdRegistroIngressi = new MdRegistroIngressi();

			id = new ObjectIdentifierComplexType();
			id.setObjectIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(RegistroIngressiXsd.UUID_MD_RI));
			id.setObjectIdentifierValue(mdRegistroIngresso.getId());
			mdRegistroIngressi.setId(id);

			df = DatatypeFactory.newInstance();
			gc = new GregorianCalendar();
			gc .setTimeInMillis(mdRegistroIngresso.getTimestampIngest().getTime());
			mdRegistroIngressi.setTimeStampIngest(df.newXMLGregorianCalendar(gc));

			agentDepositor = new LinkingAgentIdentifierComplexType();
			agentDepositor.setLinkingAgentIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(PremisXsd.UUID_MD_AG));
			agentDepositor.setLinkingAgentIdentifierValue(mdRegistroIngresso.getAgentDepositor());
			mdRegistroIngressi.setAgentDepositor(agentDepositor);

			originalContainerName = new OriginalNameComplexType();
			originalContainerName.setValue(mdRegistroIngresso.getOriginalContainerName());
			mdRegistroIngressi.setOriginalContainerName(originalContainerName);
			
			containerName = new OriginalNameComplexType();
			containerName.setValue(mdRegistroIngresso.getContainerName());
			mdRegistroIngressi.setContainerName(containerName);

			mdRegistroIngressi.setContainerFingerPrint(mdRegistroIngresso.getContainerFingerPrint());

			if (mdRegistroIngresso.getContainerFingerPrintChain() != null &&
					!mdRegistroIngresso.getContainerFingerPrintChain().trim().equals("")){
				mdRegistroIngressi.setContainerFingerPrintChain(mdRegistroIngresso.getContainerFingerPrintChain());
			}
			
			mdRegistroIngressi.setContainerType(new BigInteger(mdRegistroIngresso.getContainerType().toString()));

			agentMachineIngest = new LinkingAgentIdentifierComplexType();
			agentMachineIngest.setLinkingAgentIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(PremisXsd.UUID_MD_AG));
			agentMachineIngest.setLinkingAgentIdentifierValue(mdRegistroIngresso.getAgentMachineIngest());
			mdRegistroIngressi.setAgentMachineIngest(agentMachineIngest);

			agentSoftwareIngest = new LinkingAgentIdentifierComplexType();
			agentSoftwareIngest.setLinkingAgentIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(PremisXsd.UUID_MD_AG));
			agentSoftwareIngest.setLinkingAgentIdentifierValue(mdRegistroIngresso.getAgentSoftwareIngest());
			mdRegistroIngressi.setAgentSoftwareIngest(agentSoftwareIngest);

			mdRegistroIngressi.setStatus(new BigInteger(mdRegistroIngresso.getStatus().toString()));

			gc = new GregorianCalendar();
			gc .setTimeInMillis(mdRegistroIngresso.getTimestampInit().getTime());
			mdRegistroIngressi.setTimestampInit(df.newXMLGregorianCalendar(gc));

			gc = new GregorianCalendar();
			gc .setTimeInMillis(mdRegistroIngresso.getTimestampElab().getTime());
			mdRegistroIngressi.setTimestampElab(df.newXMLGregorianCalendar(gc));

			gc = new GregorianCalendar();
			gc .setTimeInMillis(mdRegistroIngresso.getTimestampPub().getTime());
			mdRegistroIngressi.setTimestampPub(df.newXMLGregorianCalendar(gc));

			if (mdRegistroIngresso.getTimestampErr() != null){
				gc = new GregorianCalendar();
				gc .setTimeInMillis(mdRegistroIngresso.getTimestampErr().getTime());
				mdRegistroIngressi.setTimestampPub(df.newXMLGregorianCalendar(gc));
			}

			gc = new GregorianCalendar();
			gc .setTimeInMillis(mdRegistroIngresso.getTimestampCoda().getTime());
			mdRegistroIngressi.setTimestampCoda(df.newXMLGregorianCalendar(gc));
		} catch (DatatypeConfigurationException e) {
			throw e;
		}

		return mdRegistroIngressi;
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
