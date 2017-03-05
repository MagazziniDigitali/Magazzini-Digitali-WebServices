/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.utenti.MDUtentiBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDUtenti;
import mx.randalf.archive.check.exception.CheckArchiveException;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

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
	 * Variabile utilizzata per gestire la lista delle eccezioni degli Errori
	 */
	private Vector<Exception> exceptionErrors = null;

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
	 * Variabile utilizzata per indicare l'istituzione Depositante se presente nel tracciato consegnato
	 */
	private MDIstituzione idDepositante = null;

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
			addError(e);
		} catch (MDConfigurationException e) {
			log.error(e.getMessage(), e);
			addError(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			addError(e);
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
		if (archive.getIdDepositante() != null){
			setIdDepositante(archive.getIdDepositante());
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

	private void addError(Exception error) {
		if (exceptionErrors == null) {
			exceptionErrors = new Vector<Exception>();
		}
		exceptionErrors.add(error);
	}

	/**
	 * Metodo utilizzato per indicare la presenza degli errori
	 * 
	 * @return
	 */
	public boolean isErrors() {
		return ((errors != null && errors.size() > 0) ||
				(exceptionErrors != null && exceptionErrors.size() > 0));
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
	 * Metodo utilizzato per restituite la lista degli errori riscontrati
	 * 
	 * @return
	 */
	public Exception[] getExceptionErrors() {
		if (exceptionErrors != null && exceptionErrors.size() > 0) {
			return exceptionErrors.toArray(new Exception[exceptionErrors.size()]);
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

	private void setIdDepositante(String idDepositante) {
		MDUtentiBusiness mdUtentiBusiness = null;
		MDUtenti mdUtenti = null;
		
		try {
			if (idDepositante != null){
				mdUtentiBusiness = new MDUtentiBusiness();
				mdUtenti = mdUtentiBusiness.findById(idDepositante);
				if (mdUtenti!= null){
					if (mdUtenti.getIdIstituzione() != null){
						FactoryDAO.initialize(mdUtenti.getIdIstituzione());
					
						if (mdUtenti.getIdIstituzione().getBibliotecaDepositaria()!=0){
							addError("L'istituto indicato nel file bag-info.txt non è di tipo depositante");
						} else {
							this.idDepositante = mdUtenti.getIdIstituzione();
							
						}
					} else {
						addError("L'utente ["+mdUtenti.getCognome()+" "+mdUtenti.getNome()+"] indicato nel bag-info.txt non risulta collegato ad alcun istituzione");
					}
				} else {
					addError("L'utente indicato nel file bag-info.txt non risulta centisto nel sistema");
				}
			}
		} catch (HibernateException e) {
			addError(e);
		} catch (HibernateUtilException e) {
			addError(e);
		}
	}

	/**
	 * @return the idDepositante
	 */
	public MDIstituzione getIdDepositante() {
		return idDepositante;
	}
}
