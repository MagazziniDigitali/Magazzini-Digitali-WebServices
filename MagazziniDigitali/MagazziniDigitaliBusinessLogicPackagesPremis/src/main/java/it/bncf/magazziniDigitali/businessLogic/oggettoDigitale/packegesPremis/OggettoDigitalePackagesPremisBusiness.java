package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.packegesPremis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.exception.OggettoDigitaleException;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import mx.randalf.tools.MD5Tools;
import mx.randalf.tools.Utils;
import mx.randalf.tools.exception.UtilException;

public class OggettoDigitalePackagesPremisBusiness extends OggettoDigitaleBusiness {

	private Logger log = Logger.getLogger(OggettoDigitalePackagesPremisBusiness.class);

	public OggettoDigitalePackagesPremisBusiness() {
		super();
	}

	/**
	 * Questo metodo viene utilizzato per gestire la coda del materiale da
	 * inviare negli altri Storage
	 * 
	 * @param application
	 */
	public void packagePremis(Logger logCoda, IMDConfiguration<?> configuration)
			throws MDConfigurationException, SQLException {
		GregorianCalendar gc = null;
		File fPremis = null;
		File[] fAnni = null;

		gc = new GregorianCalendar();
		gc.add(Calendar.DAY_OF_MONTH, -1);
		gc.set(Calendar.HOUR_OF_DAY, 23);
		gc.set(Calendar.MINUTE, 59);
		gc.set(Calendar.SECOND, 59);
		gc.set(Calendar.MILLISECOND, 999);
		logCoda.debug("\n"+"Ricerca Oggetti Premis da elaborare");
		fPremis = new File(configuration.getSoftwareConfigString("path.premis"));
		fAnni = fPremis.listFiles(new CheckFolder());

		for (File fAnno : fAnni) {
			checkFolder(logCoda, configuration, fAnno, gc);
			if (fAnno.list().length==0){
				if (!fAnno.delete()){
					log.error("\n"+"Riscontrato un problema nella cancellazione della cartella ["+fAnno.getAbsolutePath()+"]");
				}
			}
		}
	}

	private void checkFolder(Logger logCoda, IMDConfiguration<?> configuration, File fAnno, GregorianCalendar gcMax) {
		File[] fMesi = null;

		fMesi = fAnno.listFiles(new CheckFolder());

		for (File fMese : fMesi) {
			checkFolder(logCoda, configuration, fAnno, fMese, gcMax);
			if (fMese.list().length==0){
				if (!fMese.delete()){
					log.error("\n"+"Riscontrato un problema nella cancellazione della cartella ["+fMese.getAbsolutePath()+"]");
				}
			}
		}
	}

	private void checkFolder(Logger logCoda, IMDConfiguration<?> configuration, File fAnno, File fMese,
			GregorianCalendar gcMax) {
		GregorianCalendar gc = null;
		File[] fGiorni = null;

		fGiorni = fMese.listFiles(new CheckFolder());

		for (File fGiorno : fGiorni) {
			gc = new GregorianCalendar(Integer.parseInt(fAnno.getName()), Integer.parseInt(fMese.getName())-1	,
					Integer.parseInt(fGiorno.getName()), 23, 50, 59);
			if (gc.getTimeInMillis() <= gcMax.getTimeInMillis()) {
				checkFolder(logCoda, configuration, fAnno, fMese, fGiorno);
				if (fGiorno.list().length==0){
					if (!fGiorno.delete()){
						log.error("\n"+"Riscontrato un problema nella cancellazione della cartella ["+fGiorno.getAbsolutePath()+"]");
					}
				}
			}
		}
	}

	private void checkFolder(Logger logCoda, IMDConfiguration<?> configuration, File fAnno, File fMese, File fGiorno) {
		File[] fTipi = null;

		fTipi = fGiorno.listFiles(new CheckFolder());

		for (File fTipo : fTipi) {
			if (!fTipo.getName().equalsIgnoreCase("Validate")) {
				checkFolder(logCoda, configuration, fAnno, fMese, fGiorno, fTipo);
				if (fTipo.list().length==0){
					if (!fTipo.delete()){
						log.error("\n"+"Riscontrato un problema nella cancellazione della cartella ["+fTipo.getAbsolutePath()+"]");
					}
				}
			}
		}
	}

	private void checkFolder(Logger logCoda, IMDConfiguration<?> configuration, File fAnno, File fMese, File fGiorno,
			File fTipo) {
		File[] fPremiss = null;
		File fDest = null;
		File fTgz = null;
		Boolean genTgz = true;

		try {
			fPremiss = fTipo.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					boolean result = false;
					if (!pathname.getName().startsWith(".") && pathname.getName().endsWith(".premis")) {
						result = true;
					}
					return result;
				}
			});

			fDest = new File(configuration.getSoftwareConfigString("path.scambioTgz"));
			fDest = new File(fDest.getAbsolutePath() + File.separator + fTipo.getName() + "-" + fAnno.getName()
					+ fMese.getName() + fGiorno.getName());
			for (File fPremis : fPremiss) {
				try {
					if (Utils.copyFileValidate(fPremis.getAbsolutePath(),
							fDest.getAbsolutePath() + File.separator + fPremis.getName())) {
						if (!fPremis.delete()) {
							genTgz = false;
						}
					} else {
						genTgz = false;
					}
				} catch (UtilException e) {
					throw e;
				}
			}

			if (genTgz) {
				fTgz = new File(fDest.getAbsolutePath()+".tgz");
				if (fDest.exists() &&
						!fTgz.exists()) {

					if (genCheckSum(fDest)){
						if (genTarGz(fDest)){
							genCodaFile(configuration, fDest.getName());
						}
					}
				}
			}
		} catch (MDConfigurationException e) {
			log.error(e.getMessage(), e);
		} catch (UtilException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (OggettoDigitaleException e) {
			log.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		}
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

	private void genCodaFile(IMDConfiguration<?> configuration, String name)
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
			fw.write(name);
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

class CheckFolder implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		boolean result = false;
		if (!pathname.getName().startsWith(".") && pathname.isDirectory()) {
			result = true;
		}
		return result;
	}

}