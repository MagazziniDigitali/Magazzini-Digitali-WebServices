/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import java.io.File;
import java.util.Vector;

import mx.randalf.archive.check.exception.CheckArchiveException;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

/**
 * @author massi
 * 
 */
public class ValidateFile {

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
	public void check(File file) {
		CheckArchiveMD checkArchive = null;

		try {
			archive = null;
			xmlType = null;
			errors = null;
			checkArchive = new CheckArchiveMD(Configuration.getValue("path.droid"));
			checkArchive.setUnZip(true);
			checkArchive.setRemoveOrgin(true);

			archive = checkArchive.check(file);
			if (archive != null) {
				check((ArchiveMD)archive);
			} else {
				addError("Riscontrato problemi di validazione sul file ["
						+ file.getAbsolutePath() + "]");
			}
		} catch (CheckArchiveException e) {
			addError(e.getMessage());
		} catch (ConfigurationException e) {
			addError(e.getMessage());
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
			if (archive.checkMimetype("application/xml")) {
				xmlType = archive.getType().getXmltype();
			}
		}
		if (archive.getArchive() != null && archive.getArchive().size() > 0) {
			for (int x = 0; x < archive.getArchive().size(); x++) {
				check((ArchiveMD)archive.getArchive().get(x));
			}
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
}
