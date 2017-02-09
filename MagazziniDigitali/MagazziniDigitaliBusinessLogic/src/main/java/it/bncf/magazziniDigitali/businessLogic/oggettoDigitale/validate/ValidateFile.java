/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import mx.randalf.archive.check.exception.CheckArchiveException;
import mx.randalf.archive.info.Xmltype;

/**
 * @author massi
 * 
 */
public class ValidateFile {

	private Logger log = Logger.getLogger(ValidateFile.class);

	/**
	 * Variabile utilizzata per gestire la lista degli Errori
	 */
	private Vector<String> errors = null;

	/**
	 * Variabile utilizzata per indicare il tipo di file Xml presente nel
	 * contenitore
	 */
	private Xmltype xmlType = null;

	/**
	 * Variabile utilizzata per identificare le tipologie di Archiviazione
	 */
	private ArchiveMD archive = null;

	/**
	 * Variabile utilizzata per indicare la data in cui inizia la procedura di unzip dei File
	 */
	private GregorianCalendar gcUnzipStart = null;

	/**
	 * Variabile utilizzata per indicare la data in cui inizia la procedura di unzip dei File
	 */
	private String[] unzipError = null;

	/**
	 * Variabile utilizzata per indicare la data in cui fine la procedura di unzip dei File
	 */
	private GregorianCalendar gcUnzipStop = null;

	/**
	 * Variabile utilizzata per indicare se è richiesta la decompressione
	 */
	private boolean decompressRequired = true;

	/**
	 * Costruttore
	 * 
	 */
	public ValidateFile() {
	}

	/**
	 * Metodo utilizzato per testare l'architetture di un file
	 * 
	 * @param file
	 */
	public void check(File file, File fileTar, IMDConfiguration<?> configuration, Boolean deCompEsito, Boolean removeOrigin) {
		CheckArchiveMD checkArchive = null;

		try {
			archive = null;
			xmlType = null;
			errors = null;
			checkArchive = new CheckArchiveMD(
					configuration.getSoftwareConfigString("path.droid"), configuration
//					Configuration.getValue("path.droid")
					);
			checkArchive.setUnZip(true);
			checkArchive.setRemoveOrgin(removeOrigin);

			archive = checkArchive.check(file, fileTar, deCompEsito, decompressRequired);
			if (archive != null) {
				check((ArchiveMD)archive);
			} else {
				addError("Riscontrato problemi di validazione sul file ["
						+ file.getAbsolutePath() + "]");
			}
		} catch (CheckArchiveException e) {
			log.error(e.getMessage(), e);
			addError(e.getMessage());
		} catch (MDConfigurationException e) {
			log.error(e.getMessage(), e);
			addError(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			addError(e.getMessage());
		} finally {
			if (checkArchive != null){
				gcUnzipStart = checkArchive.getGcUnzipStart();
				gcUnzipStop = checkArchive.getGcUnzipStop();
				unzipError = checkArchive.getUnzipError();
			}
		}
	}

	/**
	 * Metodo utilizzato per testare l'archivio risultate dalla Validazione del
	 * file
	 * 
	 * @param archive
	 */
	private void check(ArchiveMD archive) {
		if (archive.getType() != null) {
			if (archive.getType().getMsgError() != null) {
				addError(archive.getNome() + " => "
						+ archive.getType().getMsgError());
			}
		}
		checkXmlType(archive);
		if (archive.getArchive() != null && archive.getArchive().size() > 0) {
			for (int x = 0; x < archive.getArchive().size(); x++) {
				check((ArchiveMD)archive.getArchive().get(x));
			}
		}
	}

	private void checkXmlType(ArchiveMD archive){
		if (archive.checkMimetype("application/xml") &&
				archive.getXmltype() != null) {
			xmlType = archive.getXmltype();
		} else if (archive.getXmltype() != null &&
				(archive.getXmltype().value().equals(Xmltype.BAGIT.value()) ||
						archive.getXmltype().value().equals(Xmltype.WARC.value()))){
			xmlType = archive.getXmltype();
		}
		
	}

	/**
	 * Metodo utilizzato per aggiungere un nuovo errore
	 * 
	 * @param error
	 */
	private void addError(String error) {
		if (errors == null) {
			errors = new Vector<String>();
		}
		errors.add(error);
	}

	/**
	 * Metodo utilizzato per indicare la presenza degli errori
	 * 
	 * @return
	 */
	public boolean isErrors() {
		return (errors != null && errors.size() > 0);
	}

	/**
	 * Metodo utilizzato per restituite la lista degli errori riscontrati
	 * 
	 * @return
	 */
	public String[] getErrors() {
		if (errors != null && errors.size() > 0) {
			return errors.toArray(new String[errors.size()]);
		} else {
			return null;
		}
	}

	/**
	 * Metodo utilizzato per ricavare la tipologia di file Xml presente
	 * nell'archivio
	 * 
	 * @return
	 */
	public Xmltype getXmlType() {
		return xmlType;
	}

	public ArchiveMD getArchive() {
		return archive;
	}

	public GregorianCalendar getGcUnzipStart() {
		return gcUnzipStart;
	}

	public String[] getUnzipError() {
		return unzipError;
	}

	public GregorianCalendar getGcUnzipStop() {
		return gcUnzipStop;
	}

	/**
	 * @return the decompressRequired
	 */
	public boolean isDecompressRequired() {
		return decompressRequired;
	}

	/**
	 * @param decompressRequired the decompressRequired to set
	 */
	public void setDecompressRequired(boolean decompressRequired) {
		this.decompressRequired = decompressRequired;
	}
}
