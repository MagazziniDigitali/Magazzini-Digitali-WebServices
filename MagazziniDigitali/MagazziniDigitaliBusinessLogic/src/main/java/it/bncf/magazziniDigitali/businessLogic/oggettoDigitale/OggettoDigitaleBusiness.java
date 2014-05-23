package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import it.bncf.magazzimiDigitali.database.entity.MDFilesTmp;
import it.bncf.magazzimiDigitali.databaseSchema.sqlite.MDFilesTmpSqlite;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import mx.randalf.configuration.exception.ConfigurationException;

public class OggettoDigitaleBusiness {

	public OggettoDigitaleBusiness() {
	}

	/**
	 * Metodo utilizzato per verificare lo stato dell'OggettoDigitale
	 * 
	 * @param sha1
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException
	 */
	public Hashtable<String, String> checkStatus(String sha1)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException {
		MDFilesTmpSqlite mdFileTmp = null;
		List<MDFilesTmp> records = null;
		Hashtable<String, String> result = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();
			records = mdFileTmp.findBySha1(sha1);

			if (records != null) {
				for (int x = 0; x < records.size(); x++) {
					if (records.get(x).getStato() != null
							&& !records.get(x).getStato()
									.equalsIgnoreCase("ERROR")) {
						if (result == null) {
							result = new Hashtable<String, String>();
						}
						result.put("id", records.get(x).getId());
						if (records.get(x).getDataArchiviazione() != null) {
							result.put("stato", "ARCHIVIATO");
						} else if (records.get(x).getValidDataEnd() != null) {
							result.put("stato", "CHECKARCHIVIAZIONE");
						} else if (records.get(x).getValidDataStart() != null) {
							result.put("stato", "CHECKARCHIVIAZIONE");
						} else if (records.get(x).getTrasfDataEnd() != null) {
							result.put("stato", "FINETRASF");
						} else if (records.get(x).getTrasfDataStart() != null) {
							result.put("stato", "INITTRASF");
						}
						break;
					}
				}
			}

			// TODO: E' da implementare il colloquio con l'archivio solr di
			// Magazini Digitali
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} finally{
			if (mdFileTmp != null){
				mdFileTmp.disconnect();
			}
		}
		return result;
	}

	/**
	 * Metodo utilizzato per la gestione dell'inserimento dell'informazioni dell'oggetto digitale nella tabella Tempornea
	 * 
	 * @param idIstituto Identicativo dell'Istituto
	 * @param nomeFile Nome del file originale
	 * @param sha1 Chiave Sha1 del file originale
	 * @param nomeFileMod Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException Eccezione SQL
	 * @throws ConfigurationException
	 */
	public String initSend(String idIstituto, String nomeFile, String sha1, Calendar nomeFileMod) throws FileNotFoundException, ClassNotFoundException, SQLException, ConfigurationException{
		MDFilesTmpSqlite mdFileTmp = null;
		String id = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();
			
			id  = mdFileTmp.insertNewRec(idIstituto, nomeFile, sha1, nomeFileMod);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} finally{
			if (mdFileTmp != null){
				mdFileTmp.disconnect();
			}
		}
		return id;
	}

	/**
	 * Metodo utilizzato per la gestione dell'inserimento dell'informazioni dell'oggetto digitale nella tabella Tempornea
	 * 
	 * @param idIstituto Identicativo dell'Istituto
	 * @param nomeFile Nome del file originale
	 * @param sha1 Chiave Sha1 del file originale
	 * @param nomeFileMod Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException Eccezione SQL
	 * @throws ConfigurationException
	 */
	public void endSend(String id, boolean esito, String msgError) throws FileNotFoundException, ClassNotFoundException, SQLException, ConfigurationException{
		MDFilesTmpSqlite mdFileTmp = null;

		try {
			mdFileTmp = new MDFilesTmpSqlite();
			
			mdFileTmp.updatEndSend(id, esito, (msgError==null?null:new String[] {msgError}));
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} finally{
			if (mdFileTmp != null){
				mdFileTmp.disconnect();
			}
		}
	}
}
