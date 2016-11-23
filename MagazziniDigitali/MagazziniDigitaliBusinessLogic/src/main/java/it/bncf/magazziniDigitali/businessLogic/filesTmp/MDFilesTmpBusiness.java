/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.filesTmp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.businessLogic.nodi.MDNodiBusiness;
import it.bncf.magazziniDigitali.businessLogic.registroIngresso.MDRegistroIngressoBusiness;
import it.bncf.magazziniDigitali.businessLogic.stato.MDStatoBusiness;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDIstituzioneDAO;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDStato;
import it.bncf.magazziniDigitali.utils.DateBusiness;
import it.bncf.magazziniDigitali.utils.Record;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDFilesTmpBusiness extends
		BusinessLogic<MDFilesTmp, MDFilesTmpDAO, String> {

	private Logger log = Logger.getLogger(getClass());

	/**
	 * @param hibernateTemplate
	 */
	public MDFilesTmpBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDFilesTmp dati) throws HibernateException,
			HibernateUtilException {
		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}
			this.records.add(setRecord(dati));
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} catch (HibernateUtilException e) {
			log.error(e);
			throw e;
		}
	}

	public static Record setRecord(MDFilesTmp dati) throws HibernateException,
			HibernateUtilException {
		Record record = null;
//		Vector<Record> clubs = null;
//		Iterator<SociClub> iClubs = null;

		try {
			record = new Record();
			record.set("idMDFilesTmp", dati.getId());

			FactoryDAO.initialize(dati.getIdIstituto());
			record.set("istituto", MDIstituzioneBusiness.setRecord(dati.getIdIstituto()));
			record.set("nomeFile", dati.getNomeFile());
			record.set("sha1", dati.getSha1());
			record.set("nomeFileMod", DateBusiness.convert(dati.getNomeFileMod()));
			FactoryDAO.initialize(dati.getStato());
			record.set("stato", MDStatoBusiness.setRecord(dati.getStato()));

			if (dati.getTrasfDataStart() != null){
				record.set("trasfDataStart", DateBusiness.convert(dati.getTrasfDataStart()));
			}
			if (dati.getTrasfDataEnd() != null){
				record.set("trasfDataEnd", DateBusiness.convert(dati.getTrasfDataEnd()));
			}
			if (dati.getTrasfEsito() != null){
				record.set("trasfEsito", dati.getTrasfEsito());
			}

			if (dati.getValidDataStart() != null){
				record.set("validDataStart", DateBusiness.convert(dati.getValidDataStart()));
			}
			if (dati.getValidDataEnd() != null){
				record.set("validDataEnd", DateBusiness.convert(dati.getValidDataEnd()));
			}
			if (dati.getValidEsito() != null){
				record.set("validEsito", dati.getValidEsito());
			}

			if (dati.getXmlMimeType() != null){
				record.set("xmlMimeType", dati.getXmlMimeType());
			}

			if (dati.getDecompDataStart() != null){
				record.set("decompDataStart", DateBusiness.convert(dati.getDecompDataStart()));
			}
			if (dati.getDecompDataEnd() != null){
				record.set("decompDataEnd", DateBusiness.convert(dati.getDecompDataEnd()));
			}
			if (dati.getDecompEsito() != null){
				record.set("decompEsito", dati.getDecompEsito());
			}

			if (dati.getPublishDataStart() != null){
				record.set("publishDataStart", DateBusiness.convert(dati.getPublishDataStart()));
			}
			if (dati.getPublishDataEnd() != null){
				record.set("publishDataEnd", DateBusiness.convert(dati.getPublishDataEnd()));
			}
			if (dati.getPublishEsito() != null){
				record.set("publishEsito", dati.getPublishEsito());
			}

			if (dati.getCopyPremisDataStart() != null){
				record.set("copyPremisDataStart", DateBusiness.convert(dati.getCopyPremisDataStart()));
			}
			if (dati.getCopyPremisDataEnd() != null){
				record.set("copyPremisDataEnd", DateBusiness.convert(dati.getCopyPremisDataEnd()));
			}
			if (dati.getCopyPremisEsito() != null){
				record.set("copyPremisEsito", dati.getCopyPremisEsito());
			}

			if (dati.getMoveFileDataStart() != null){
				record.set("moveFileDataStart", DateBusiness.convert(dati.getMoveFileDataStart()));
			}
			if (dati.getMoveFileDataEnd() != null){
				record.set("moveFileDataEnd", DateBusiness.convert(dati.getMoveFileDataEnd()));
			}
			if (dati.getMoveFileEsito() != null){
				record.set("moveFileEsito", dati.getMoveFileEsito());
			}

			if (dati.getDeleteLocalData() != null){
				record.set("deleteLocalData", DateBusiness.convert(dati.getDeleteLocalData()));
			}
			if (dati.getDeleteLocalEsito() != null){
				record.set("deleteLocalEsito", dati.getDeleteLocalEsito());
			}

			if (dati.getPremisFile() != null){
				record.set("premisFile", dati.getPremisFile());
			}

			if (dati.getArchiveDataStart() != null){
				record.set("archiveDataStart", DateBusiness.convert(dati.getArchiveDataStart()));
			}
			if (dati.getArchiveDataEnd() != null){
				record.set("archiveDataEnd", DateBusiness.convert(dati.getArchiveDataEnd()));
			}
			if (dati.getArchiveEsito() != null){
				record.set("archiveEsito", dati.getArchiveEsito());
			}

			if (dati.getIdNodo() != null){
				FactoryDAO.initialize(dati.getIdNodo());
				record.set("nodo", MDNodiBusiness.setRecord(dati.getIdNodo()));
			}

			if (dati.getIndexDataStart() != null){
				record.set("indexDataStart", DateBusiness.convert(dati.getIndexDataStart()));
			}
			if (dati.getIndexDataEnd() != null){
				record.set("indexDataEnd", DateBusiness.convert(dati.getIndexDataEnd()));
			}
			if (dati.getIndexEsito() != null){
				record.set("indexEsito", dati.getIndexEsito());
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}

		return record;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {
		MDFilesTmpDAO operaDAO;
		MDFilesTmp opere;

		try {
			if (id != null && id.trim().equals("")) {
				opere = newInstance();
				opere.setId(id);
				operaDAO = newInstanceDao();
				operaDAO.delete(opere);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#find(mx.randalf.hibernate.GenericHibernateDAO, it.bncf.magazziniDigitali.businessLogic.HashTable, java.util.List)
	 */
	@Override
	protected List<MDFilesTmp> find(MDFilesTmpDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException, HibernateUtilException {
		List<MDFilesTmp> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((MDIstituzione)dati.get("idIstituto"),
				(String)dati.get("nomeFile"), (MDStato[])dati.get("stato"), (String)dati.get("sha1"), 
				orders);
		return tables;
	}

	public MDFilesTmp findPremis(String premisFile)
		throws HibernateException, HibernateUtilException{

		MDFilesTmp ris = null;
		MDFilesTmpDAO operaDAO;
		
		operaDAO = newInstanceDao();
		ris = operaDAO.findPremis(premisFile);
		return ris;

	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#setOrder()
	 */
	@Override
	protected List<Order> setOrder() {
		Vector<Order> orders;
		orders = new Vector<Order>();
		orders.add(Order.asc("nomeFile"));
		return orders;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati,
			MDFilesTmp table) throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String[] errors = null;
		Exception[] exceptionErrors = null;
		MDFilesTmpErrorBusiness errorBusiness = null;

		try {
			if (dati.containsKey("errors")){
				errors = (String[]) dati.get("errors");
				errorBusiness = new MDFilesTmpErrorBusiness();
				for (int x=0; x<errors.length; x++){
					printError(errorBusiness, table.getId(), (MDStato) dati.get("type"), errors[x], null);
				}
			}
			if (dati.containsKey("exceptionErrors")){
				exceptionErrors = (Exception[]) dati.get("exceptionErrors");
				errorBusiness = new MDFilesTmpErrorBusiness();
				for (int x=0; x<exceptionErrors.length; x++){
					printError(errorBusiness, table.getId(), (MDStato) dati.get("type"), exceptionErrors[x]);
				}
			}
		} catch (HibernateException e) {
			throw new HibernateUtilException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new HibernateUtilException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new HibernateUtilException(e.getMessage(), e);
		} catch (IOException e) {
			throw new HibernateUtilException(e.getMessage(), e);
		}
	}
	private void printError(MDFilesTmpErrorBusiness errorBusiness, String idMDFilesTmp, MDStato mdStato, Exception exception)
			throws HibernateException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException, HibernateUtilException, NamingException{
		ByteArrayOutputStream baos = null;
		PrintStream ps = null;
		
		try {
			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			exception.printStackTrace(ps);
			
			baos.flush();
			
			printError(errorBusiness, idMDFilesTmp, mdStato, exception.getMessage(), baos.toString());

			if (exception.getCause() != null){
				printError(errorBusiness, idMDFilesTmp, mdStato, exception.getCause());
			}

			if (exception.getSuppressed() != null){
				for (int x=0; x<exception.getSuppressed().length; x++){
					printError(errorBusiness, idMDFilesTmp, mdStato, exception.getSuppressed()[x]);
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (SecurityException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		}
	}

	private void printError(MDFilesTmpErrorBusiness errorBusiness, String idMDFilesTmp, MDStato type, Throwable cause) 
			throws HibernateException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
				NoSuchMethodException, SecurityException, IOException, HibernateUtilException, NamingException{
		ByteArrayOutputStream baos = null;
		PrintStream ps = null;
		
		try {
			baos = new ByteArrayOutputStream();
			ps = new PrintStream(baos);
			cause.printStackTrace(ps);
			
			baos.flush();
			
			printError(errorBusiness, idMDFilesTmp, type, cause.getMessage(), baos.toString());

			if (cause.getCause() != null){
				printError(errorBusiness, idMDFilesTmp, type, cause.getCause());
			}

			if (cause.getSuppressed() != null){
				for (int x=0; x<cause.getSuppressed().length; x++){
					printError(errorBusiness, idMDFilesTmp, type, cause.getSuppressed()[x]);
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (SecurityException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		}
	}

	private void printError(MDFilesTmpErrorBusiness errorBusiness, String idMDFilesTmp, MDStato mdStato, String msgError,
			String traceError) throws HibernateException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, HibernateUtilException,
			NamingException {
		try {
			HashTable<String, Object> myDati = null;
			myDati = new HashTable<String, Object>();
			myDati.put("idMDFilesTmp", idMDFilesTmp);
			myDati.put("type", mdStato);
			myDati.put("msgError", msgError);
			if (traceError != null){
				myDati.put("traceError", traceError);
			}
			errorBusiness.save(myDati);
		} catch (HibernateException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (SecurityException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		}

	}
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDFilesTmp newInstance() {
		return new MDFilesTmp();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDFilesTmpDAO newInstanceDao() {
		return new MDFilesTmpDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDFilesTmp table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {
		MDIstituzioneDAO mdIstituzioneDAO = null;
		MDSoftwareDAO mdSoftwareDAO = null;
		MDStatoDAO mdStatoDAO = null;
		MDNodiDAO mdNodiDAO = null;

		if (dati.containsKey("idIstituto")) {
			if (dati.get("idIstituto") instanceof String){
				mdIstituzioneDAO = new MDIstituzioneDAO();
				table.setIdIstituto(mdIstituzioneDAO.findById((String) dati.get("idIstituto")));
			} else {
				table.setIdIstituto((MDIstituzione) dati.get("idIstituto"));
			}
		}

		if (dati.containsKey("idSoftware")) {
			if (dati.get("idSoftware") instanceof String){
				mdSoftwareDAO = new MDSoftwareDAO();
				table.setIdSoftware(mdSoftwareDAO.findById((String) dati.get("idSoftware")));
			} else {
				table.setIdSoftware((MDSoftware) dati.get("idSoftware"));
			}
		}
		if (dati.containsKey("nomeFile")) {
			table.setNomeFile((String) dati.get("nomeFile"));
		}
		if (dati.containsKey("sha1")) {
			table.setSha1((String) dati.get("sha1"));
		}
		if (dati.containsKey("nomeFileMod")) {
			table.setNomeFileMod((Timestamp) dati.get("nomeFileMod"));
		}
		if (dati.containsKey("stato")) {
			if (dati.get("stato") instanceof String){
				mdStatoDAO = new MDStatoDAO();
				table.setStato(mdStatoDAO.findById((String) dati.get("stato")));
			} else {
				table.setStato((MDStato) dati.get("stato"));
			}
		}

		if (dati.containsKey("trasfDataStart")) {
			table.setTrasfDataStart((Timestamp) dati.get("trasfDataStart"));
		}
		if (dati.containsKey("trasfDataEnd")) {
			table.setTrasfDataEnd((Timestamp) dati.get("trasfDataEnd"));
		}
		if (dati.containsKey("trasfEsito")) {
			table.setTrasfEsito((Boolean) dati.get("trasfEsito"));
		}

		if (dati.containsKey("validDataStart")) {
			table.setValidDataStart((Timestamp) dati.get("validDataStart"));
		}
		if (dati.containsKey("validDataEnd")) {
			table.setValidDataEnd((Timestamp) dati.get("validDataEnd"));
		}
		if (dati.containsKey("validEsito")) {
			table.setValidEsito((Boolean) dati.get("validEsito"));
		}

		if (dati.containsKey("xmlMimeType")) {
			table.setXmlMimeType((String) dati.get("xmlMimeType"));
		}

		if (dati.containsKey("decompDataStart")) {
			table.setDecompDataStart((Timestamp) dati.get("decompDataStart"));
		}
		if (dati.containsKey("decompDataEnd")) {
			table.setDecompDataEnd((Timestamp) dati.get("decompDataEnd"));
		}
		if (dati.containsKey("decompEsito")) {
			table.setDecompEsito((Boolean) dati.get("decompEsito"));
		}

		if (dati.containsKey("publishDataStart")) {
			table.setPublishDataStart((Timestamp) dati.get("publishDataStart"));
		}
		if (dati.containsKey("publishDataEnd")) {
			table.setPublishDataEnd((Timestamp) dati.get("publishDataEnd"));
		}
		if (dati.containsKey("publishEsito")) {
			table.setPublishEsito((Boolean) dati.get("publishEsito"));
		}

		if (dati.containsKey("copyPremisDataStart")) {
			table.setCopyPremisDataStart((Timestamp) dati.get("copyPremisDataStart"));
		}
		if (dati.containsKey("copyPremisDataEnd")) {
			table.setCopyPremisDataEnd((Timestamp) dati.get("copyPremisDataEnd"));
		}
		if (dati.containsKey("copyPremisEsito")) {
			table.setCopyPremisEsito((Boolean) dati.get("copyPremisEsito"));
		}

		if (dati.containsKey("moveFileDataStart")) {
			table.setMoveFileDataStart((Timestamp) dati.get("moveFileDataStart"));
		}
		if (dati.containsKey("moveFileDataEnd")) {
			table.setMoveFileDataEnd((Timestamp) dati.get("moveFileDataEnd"));
		}
		if (dati.containsKey("moveFileEsito")) {
			table.setMoveFileEsito((Boolean) dati.get("moveFileEsito"));
		}

		if (dati.containsKey("deleteLocalData")) {
			table.setDeleteLocalData((Timestamp) dati.get("deleteLocalData"));
		}
		if (dati.containsKey("deleteLocalEsito")) {
			table.setDeleteLocalEsito((Boolean) dati.get("deleteLocalEsito"));
		}

		if (dati.containsKey("premisFile")) {
			table.setPremisFile((String) dati.get("premisFile"));
		}

		if (dati.containsKey("archiveDataStart")) {
			table.setArchiveDataStart((Timestamp) dati.get("archiveDataStart"));
		}
		if (dati.containsKey("archiveDataEnd")) {
			table.setArchiveDataEnd((Timestamp) dati.get("archiveDataEnd"));
		}
		if (dati.containsKey("archiveEsito")) {
			table.setArchiveEsito((Boolean) dati.get("archiveEsito"));
		}

		if (dati.containsKey("idNodo")) {
			if (dati.get("idNodo") instanceof String){
				mdNodiDAO = new MDNodiDAO();
				table.setIdNodo(mdNodiDAO.findById((String) dati.get("idNodo")));
			} else {
				table.setIdNodo((MDNodi) dati.get("idNodo"));
			}
		}

		if (dati.containsKey("indexDataStart")) {
			table.setIndexDataStart((Timestamp) dati.get("indexDataStart"));
		}
		if (dati.containsKey("indexDataEnd")) {
			table.setIndexDataEnd((Timestamp) dati.get("indexDataEnd"));
		}
		if (dati.containsKey("indexEsito")) {
			table.setIndexEsito((Boolean) dati.get("indexEsito"));
		}
		
		if (dati.containsKey("indexPremis")) {
			table.setIndexPremis((String) dati.get("indexPremis"));
		}
		
		if (dati.containsKey("tarTmpFile")) {
			table.setTarTmpFile((String) dati.get("tarTmpFile"));
		}
		
		if (dati.containsKey("publishPremis")) {
			table.setPublishPremis((String) dati.get("publishPremis"));
		}
		
		if (dati.containsKey("geoReplicaPremis")) {
			table.setGeoReplicaPremis((String) dati.get("geoReplicaPremis"));
		}
	}

	/**
	 * Metodo utilizzato per la ricerca del materiale
	 * 
	 * @param idMDFilesTmp Identificativo del MDFileTmp
	 * @param idIstituto Identificativo dell'istituto
	 * @param nomeFile Nome del File
	 * @param stato Lista degli stati
	 * @param sha1 Chiave Sha1
	 * @param page Pagina da ricercare
	 * @param pageSize Record per pagine 
	 * @return Lista dei record trovati
	 * @throws HibernateException Eccezioni di Hibernate
	 * @throws HibernateUtilException
	 */
	public List<MDFilesTmp> find(String idMDFilesTmp, MDIstituzione idIstituto,
			String nomeFile, MDStato[] stato, String sha1, int page, int pageSize) 
					throws HibernateException, HibernateUtilException{
		HashTable<String, Object> dati = null;
		
		dati = new HashTable<String, Object>();
		
		if (idMDFilesTmp != null){
			dati.put("id", idMDFilesTmp);
		}
		
		if (idIstituto != null){
			dati.put("idIstituto", idIstituto);
		}
		
		if (nomeFile != null){
			dati.put("nomeFile", nomeFile);
		}
		
		if (stato != null){
			dati.put("stato", stato);
		}
		
		if (sha1 != null){
			dati.put("sha1", sha1);
		}
		
		return find(dati, page, pageSize);
	}

	public Vector<Record> findToRecord(String idMDFilesTmp, MDIstituzione idIstituto,
			String nomeFile, MDStato[] stato, String sha1, int page, int pageSize) 
					throws HibernateException, HibernateUtilException{
		HashTable<String, Object> dati = null;
		
		dati = new HashTable<String, Object>();
		
		if (idMDFilesTmp != null){
			dati.put("id", idMDFilesTmp);
		}
		
		if (idIstituto != null){
			dati.put("idIstituto", idIstituto);
		}
		
		if (nomeFile != null){
			dati.put("nomeFile", nomeFile);
		}
		
		if (stato != null){
			dati.put("stato", stato);
		}
		
		if (sha1 != null){
			dati.put("sha1", sha1);
		}
		
		return new Vector<Record>(findToRecord(dati, page, pageSize));
	}

	public Hashtable<String, Long> findCountByIstituto(MDIstituzione idIstituto) throws HibernateException, HibernateUtilException{
		MDFilesTmpDAO operaDAO;
		Hashtable<String, Long> ris = null;
		
		operaDAO = newInstanceDao();
		ris = operaDAO.findCountByIstituto(idIstituto);
		return ris;
		
	}
	
	public String insertNewRec(MDSoftware idSoftware, String nomeFile, String sha1, 
			Calendar nomeFileMod, MDNodi idNodo) throws SQLException 
					{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDStatoDAO mdStattoDAO =  new MDStatoDAO();
		
		try {
			dati = new HashTable<String, Object>();
			dati.put("idIstituto", idSoftware.getIdIstituzione());
			dati.put("idSoftware", idSoftware);
			dati.put("nomeFile", nomeFile);
			dati.put("sha1", sha1);
			dati.put("nomeFileMod", new Timestamp(nomeFileMod.getTimeInMillis()));
			dati.put("stato", mdStattoDAO.INITTRASF());
			dati.put("trasfDataStart", new Timestamp(new GregorianCalendar().getTimeInMillis()));
			dati.put("idNodo", idNodo);
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return ris;
	}

	/**
	 * Metodo utilizzato per indicare la Fine dell'invio dell'oggetto
	 * @param id
	 * @param esito
	 * @param msgError
	 * @throws SQLException
	 */
	public void updatEndSend(String id, boolean esito, Exception[] exceptionErrors, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("trasfDataEnd", new Timestamp(new GregorianCalendar().getTimeInMillis()));
			if (esito){
				dati.put("stato", mdStatoDAO.FINETRASF());
				dati.put("trasfEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORTRASF());
				dati.put("trasfEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORTRASF());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
	}

	/**
	 * Metodo utilizzato per indicare la Fine dell'invio dell'oggetto
	 * @param id
	 * @param esito
	 * @param msgError
	 * @throws SQLException
	 */
	public void confirmDel(String id, boolean esito, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDStatoDAO mdStatoDAO= new MDStatoDAO();
		
		try {
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("deleteLocalData", new Timestamp(new GregorianCalendar().getTimeInMillis()));
			if (esito){
				dati.put("deleteLocalEsito", Boolean.TRUE);
			} else {
				dati.put("deleteLocalEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORDELETE());
				dati.put("errors", msgError);
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		}
	}

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStartValidate(String id, File fTar) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", mdStatoDAO.INITVALID());
			dati.put("validDataStart", new Timestamp(gc.getTimeInMillis()));
			dati.put("tarTmpFile", fTar.getAbsolutePath());
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}

	/**
	 * Metodo utilizzato per indicare la fine della validazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStopValidate(String id, String xmlMimeType, boolean esito, 
			Exception[] exceptionErrors, String[] msgError, String premisFile) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("validDataEnd", new Timestamp(gc.getTimeInMillis()));
			dati.put("premisFile", premisFile);
			dati.put("xmlMimeType", xmlMimeType);
			if (esito){
				dati.put("stato", mdStatoDAO.FINEVALID());
				dati.put("validEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORVAL());
				dati.put("validEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORVAL());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}
	
	/**
	 * Metodo utilizzato per indicare l'inizio dell'archiviazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStartArchive(String id) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", mdStatoDAO.INITARCHIVE());
			dati.put("archiveDataStart", new Timestamp(gc.getTimeInMillis()));
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}

	/**
	 * Metodo utilizzato per indicare la fine dell'archiviazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStopArchive(String id, boolean esito, 
			Exception[] exceptionErrors, String[] msgError, String geoReplicaPremis) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("archiveDataEnd", new Timestamp(gc.getTimeInMillis()));
			if (geoReplicaPremis != null){
				dati.put("geoReplicaPremis", geoReplicaPremis);
			}
			if (esito){
				dati.put("stato", mdStatoDAO.FINEARCHIVE());
				dati.put("archiveEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORARCHIVE());
				dati.put("archiveEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORARCHIVE());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}
	
	/**
	 * Metodo utilizzato per indicare l'inizio dell'archiviazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStartIndex(String id) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", mdStatoDAO.INITINDEX());
			dati.put("indexDataStart", new Timestamp(gc.getTimeInMillis()));
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}
	
	/**
	 * Metodo utilizzato per indicare l'inizio dell'archiviazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateCheckIndex(String id, String indexPremis) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", mdStatoDAO.CHECKINDEX());
			dati.put("indexPremis", indexPremis);
//			dati.put("indexDataStart", new Timestamp(gc.getTimeInMillis()));
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}

	/**
	 * Metodo utilizzato per indicare la fine dell'archiviazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStopIndex(String id, String indexPremis, boolean esito, 
			Exception[] exceptionErrors, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		MDRegistroIngressoBusiness registroBusiness = null;
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("indexDataEnd", new Timestamp(gc.getTimeInMillis()));
			dati.put("indexPremis", indexPremis);
			if (esito){
				dati.put("stato", mdStatoDAO.FINEINDEX());
				dati.put("indexEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORINDEX());
				dati.put("indexEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORINDEX());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				registroBusiness = new MDRegistroIngressoBusiness();
				if (esito){
					registroBusiness.pubblicato(id, gc);
				} else {
					registroBusiness.error(id, mdStatoDAO.ERRORPUB(), exceptionErrors, msgError);
				}
			} catch (IllegalAccessException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NamingException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateUtilException e) {
				throw new SQLException(e.getMessage(), e);
			}
		}
		return gc;
	}

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStartPublish(String id) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", mdStatoDAO.INITPUBLISH());
			dati.put("publishDataStart", new Timestamp(gc.getTimeInMillis()));
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}

	/**
	 * Metodo utilizzato per indicare il periodo e l'esito dell'operazione di decompressione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void updateDecompress(String id, GregorianCalendar compressStart, GregorianCalendar compressStop, 
			boolean esito, Exception[] exceptionErrors, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("decompDataStart", new Timestamp(compressStart.getTimeInMillis()));
			dati.put("decompDataEnd", new Timestamp(compressStop.getTimeInMillis()));
			if (esito){
				dati.put("decompEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORDECOMP());
				dati.put("validEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORDECOMP());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
	}

	/**
	 * Metodo utilizzato per indicare il periodo e l'esito della procedura di copia del file premis nello Storage
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void updateCopyPremis(String id, GregorianCalendar copyStart, 
			GregorianCalendar copyStop, boolean esito, Exception[] exceptionErrors, String[] msgError, 
			String agentDepositor, MDNodi agentMachineIngest, String agentSoftwareIngest) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDFilesTmpDAO filesTmpDAO = null;
		MDFilesTmp filesTmp =  null;
		MDRegistroIngressoBusiness registroBusiness = null;
		int containerType = -1;
		String containerName=null;
		int pos = 0;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("copyPremisDataStart", new Timestamp(copyStart.getTimeInMillis()));
			dati.put("copyPremisDataEnd", new Timestamp(copyStop.getTimeInMillis()));
			if (esito){
				dati.put("copyPremisEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORCOPY());
				dati.put("copyPremisEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORCOPY());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				filesTmpDAO = new MDFilesTmpDAO();
				filesTmp = filesTmpDAO.findById(id);
				if (filesTmp !=null){
					registroBusiness = new MDRegistroIngressoBusiness();
					if (filesTmp.getXmlMimeType().equals("mets")){
						containerType=5;
					} else 	if (filesTmp.getXmlMimeType().equals("mag")){
						containerType=4;
					} else 	if (filesTmp.getXmlMimeType().equals("metatape")){
						containerType=3;
					} else 	if (filesTmp.getXmlMimeType().equals("bagit")){
						containerType=2;
					} else 	if (filesTmp.getXmlMimeType().equals("warc")){
						containerType=1;
					} else 	if (filesTmp.getXmlMimeType().equals("admtape")){
						containerType=0;
					} else {
						containerType=-1;
					}
					containerName = filesTmp.getPremisFile();
					
					pos = containerName.lastIndexOf("/");
					containerName = containerName.substring(pos).replace("/", "");
					pos = containerName.indexOf(".");
					containerName = containerName.substring(0, pos);
					registroBusiness.insert(id, copyStart, agentDepositor, 
							filesTmp.getNomeFile(), containerName, 
							filesTmp.getSha1(), containerType, 
							agentMachineIngest.getId(), agentSoftwareIngest, 
							filesTmp.getTrasfDataStart());
				}
			} catch (HibernateException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NamingException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateUtilException e) {
				throw new SQLException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Metodo utilizzato per indicare il periodo e l'esito dell'operazione di decompressione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void updateMoveFile(String id, GregorianCalendar moveFileStart, GregorianCalendar moveFileStop, 
			boolean esito, Exception[] exceptionErrors, String[] msgError, String publishPremis) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDRegistroIngressoBusiness registroBusiness = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			if (moveFileStart != null){
				dati.put("moveFileDataStart", new Timestamp(moveFileStart.getTimeInMillis()));
			}
			if (moveFileStop != null){
				dati.put("moveFileDataEnd", new Timestamp(moveFileStop.getTimeInMillis()));
				dati.put("publishDataEnd", new Timestamp(moveFileStop.getTimeInMillis()));
			}
			if (publishPremis != null){
				dati.put("publishPremis", publishPremis);
			}
			if (esito){
				dati.put("stato", mdStatoDAO.FINEPUBLISH());
				dati.put("moveFileEsito", Boolean.TRUE);
				dati.put("publishEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORMOVE());
				dati.put("moveFileEsito", Boolean.FALSE);
				dati.put("publishEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORMOVE());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				registroBusiness = new MDRegistroIngressoBusiness();
				if (esito){
					registroBusiness.archiviato(id, moveFileStop);
				} else {
					registroBusiness.error(id, mdStatoDAO.ERRORMOVE(), exceptionErrors, msgError);
				}
			} catch (IllegalAccessException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NamingException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateUtilException e) {
				throw new SQLException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStopPublish(String id, boolean esito, 
			Exception[] exceptionErrors, String[] msgError, String publishPremis) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDRegistroIngressoBusiness registroBusiness = null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("publishDataEnd", new Timestamp(gc.getTimeInMillis()));
			if (publishPremis != null){
				dati.put("publishPremis", publishPremis);
			}
			if (esito){
				dati.put("stato", mdStatoDAO.FINEPUBLISH());
				dati.put("publishEsito", Boolean.TRUE);
			} else {
				dati.put("stato", mdStatoDAO.ERRORPUB());
				dati.put("publishEsito", Boolean.FALSE);
				dati.put("type", mdStatoDAO.ERRORPUB());
				if (exceptionErrors != null){
					dati.put("exceptionErrors", exceptionErrors);
				}
				if (msgError != null){
					dati.put("errors", msgError);
				}
			}
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				registroBusiness = new MDRegistroIngressoBusiness();
				if (esito){
					registroBusiness.pubblicato(id, gc);
				} else {
					registroBusiness.error(id, mdStatoDAO.ERRORPUB(), exceptionErrors, msgError);
				}
			} catch (IllegalAccessException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (NamingException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateException e) {
				throw new SQLException(e.getMessage(), e);
			} catch (HibernateUtilException e) {
				throw new SQLException(e.getMessage(), e);
			}
		}
		return gc;
	}

	@Override
	protected Criteria rowsCount(MDFilesTmpDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (dati != null){
			if (dati.get("idIstituto") != null) {
				criteria.add(Restrictions.eq("idIstituto", dati.get("idIstituto")));
			}
			if (dati.get("nomeFile") != null) {
				criteria.add(Restrictions.ilike("nomeFile", "%"+dati.get("nomeFile")+"%"));
			}
		}
		return criteria;
	}

}
