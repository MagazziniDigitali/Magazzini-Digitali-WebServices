package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDStato;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.utils.Record;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.Future;

import javax.naming.NamingException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.solr.exception.SolrException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class OggettoDigitaleBusiness {


	protected HibernateTemplate hibernateTemplate;
	
	public Logger log = Logger.getLogger(OggettoDigitaleBusiness.class);

	public OggettoDigitaleBusiness(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<MDFilesTmp> findStatus(String idMDFilesTmp, MDStato[] status, int numRec, List<MDFilesTmp> result)
				throws HibernateException, NamingException, ConfigurationException {
		MDFilesTmpBusiness mdFileTmp = null;
		List<MDFilesTmp> rs = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			rs = mdFileTmp.find(idMDFilesTmp, null, null, status, null,1,numRec);
			if (rs!=null && rs.size()>0){
				if (result== null){
					result = new Vector<MDFilesTmp>();
				}
				for (int x=0; x<(rs.size()<numRec?rs.size():numRec); x++){
					result.add(rs.get(x));
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
		return result;
	}
	/**
	 * Metodo utilizzato per individuare lo stato di caricamento del materiale
	 * relativo ad un istituto specifico
	 * 
	 * @param idIstituto
	 *            Identificativo dell'Istituto
	 * @return Status dell'Istituto
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException
	 */
	public TreeMap<String, Integer> findStatus(MDIstituzione idIstituto)
			throws HibernateException, NamingException, ConfigurationException {
		MDFilesTmpBusiness mdFileTmp = null;
		TreeMap<String, Integer> ris = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			ris = new TreeMap<String, Integer>(
					mdFileTmp.findCountByIstituto(idIstituto));
		} catch (HibernateException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
		return ris;
	}

	public Vector<Record> findByNomeFile(MDIstituzione idIstituto, String nomeFile)
			throws SQLException {
		MDFilesTmpBusiness mdFileTmp = null;
		Vector<Record> records = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			records = mdFileTmp.findToRecord(null, idIstituto, nomeFile, null, null, 1, 1000);
//			if (records!= null){
//				for (MDFilesTmp ai : records) {
//					addRecord(ai);
//				}
//			}
		} catch (ConfigurationException | HibernateException | NamingException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		}
		return records;
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
	 * @throws SolrException
	 * @throws SolrServerException
	 */
	public Hashtable<String, String> checkStatus(String sha1)
			throws FileNotFoundException, ClassNotFoundException, SQLException,
			ConfigurationException, SolrException, SolrServerException,
			HibernateException, NamingException {
		MDFilesTmpBusiness mdFileTmp = null;
		List<MDFilesTmp> records = null;
		Hashtable<String, String> result = null;
		AddDocumentMD admd = null;
		QueryResponse qr = null;

		try {
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			records = mdFileTmp.find(null, null, null, null, sha1, 0, 0);

			if (records != null) {
				for (int x = 0; x < records.size(); x++) {
					if (records.get(x).getStato() != null) {
						if (result == null) {
							result = new Hashtable<String, String>();
						}
						result.put("id", records.get(x).getId());
						result.put("stato", records.get(x).getStato().getId());

						break;
					}
				}
			} else {
				admd = new AddDocumentMD(
						Configuration.getValue("demoni.Publish.solr.URL"),
						Boolean.parseBoolean(Configuration
								.getValue("demoni.Publish.solr.Cloud")),
						Configuration
								.getValue("demoni.Publish.solr.collection"),
						Integer.parseInt(Configuration
								.getValue("demoni.Publish.solr.connectionTimeOut")),
						Integer.parseInt(Configuration
								.getValue("demoni.Publish.solr.clientTimeOut")));
				qr = admd.find("sha1:" + sha1);
				if (qr != null && qr.getResults() != null
						&& qr.getResults().getNumFound() > 0) {
					if (result == null) {
						result = new Hashtable<String, String>();
					}
					result.put("id", ((String) qr.getResults().get(0)
							.getFieldValue("id")).substring(0, 36));
					result.put("stato", MDStatoDAO.FINEPUBLISH);
				}
			}
		} catch (ConfigurationException e) {
			throw e;
		} catch (NumberFormatException e) {
			throw e;
		} catch (SolrException e) {
			throw e;
		} catch (SolrServerException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		}
		return result;
	}

	public static Date convertDate(String date){
		Date ris = null;
		GregorianCalendar gc = null;
		gc = convertGregorianCalendar(date);
		ris = new Date(gc.getTimeInMillis());
		return ris;
	}
	

	public static Timestamp convertTimestamp(String date){
		Timestamp ris = null;
		GregorianCalendar gc = null;
		gc = convertGregorianCalendar(date);
		ris = new Timestamp(gc.getTimeInMillis());
		return ris;
	}

	public static GregorianCalendar convertGregorianCalendar(String date) {
		GregorianCalendar gc = null;
		int year;
		int month;
		int dayOfMonth;
		int hourOfDay;
		int minute;
		int second;
		int millisecond = 0;
		String[] st = null;
		String[] st2 = null;
		int pos = 0;

		st = date.split(" ");
		st2 = st[0].split("/");
		year = Integer.parseInt(st2[0]);
		month = Integer.parseInt(st2[1]);
		dayOfMonth = Integer.parseInt(st2[2]);

		st2 = st[1].split(":");
		hourOfDay = Integer.parseInt(st2[0]);
		minute = Integer.parseInt(st2[1]);
		pos = st2[2].indexOf(".");
		if (pos == -1) {
			second = Integer.parseInt(st2[2]);
		} else {
			second = Integer.parseInt(st2[2].substring(0, pos));
			millisecond = Integer.parseInt(st2[2].substring(pos + 1));
		}

		gc = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute,
				second);
		if (millisecond > 0) {
			gc.add(Calendar.MILLISECOND, millisecond);
		}
		return gc;
	}

	/**
	 * Metodo utilizzaato per verificare i Thread in esecuzione
	 * 
	 * @param futuresList
	 * @param numberThread
	 */
	protected void checkThread(List<Future<Boolean>> futuresList, int numberThread) {
		while (true) {
			try {
				for (Future<Boolean> future : futuresList) {
					if (future.isDone()) {
						future.get();
						if (!futuresList.remove(future)) {
							log.error("Riscontrato nella eliminazione di un Thread dalla Lista");
						}
						future = null;
						break;
					}
				}
				if (futuresList.size() == 0) {
					break;
				} else {
					if (numberThread > -1) {
						if (futuresList.size() < numberThread) {
							break;
						}
					}
				}
			} catch (Exception e) {
			}

		}
	}
}
