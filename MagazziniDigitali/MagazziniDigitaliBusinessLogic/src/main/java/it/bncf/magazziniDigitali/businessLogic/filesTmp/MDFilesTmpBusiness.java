/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.filesTmp;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.registroIngresso.MDRegistroIngressoBusiness;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.utils.DateBusiness;
import it.bncf.magazziniDigitali.utils.Record;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

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
	public MDFilesTmpBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/* (non-Javadoc)
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDFilesTmp dati) throws NamingException,
			ConfigurationException {
		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}
			this.records.add(setRecord(dati));
		} catch (NamingException e) {
			log.error(e);
			throw e;
		} catch (ConfigurationException e) {
			log.error(e);
			throw e;
		}
	}

	public static Record setRecord(MDFilesTmp dati) throws NamingException,
			ConfigurationException {
		Record record = null;
//		Vector<Record> clubs = null;
//		Iterator<SociClub> iClubs = null;

		record = new Record();
		record.set("idMDRegistroIngresso", dati.getId());
		record.set("idIstituto", dati.getIdIstituto());
		record.set("nomeFile", dati.getNomeFile());
		record.set("sha1", dati.getSha1());
		record.set("nomeFileMod", DateBusiness.convert(dati.getNomeFileMod()));
		record.set("stato", dati.getStato());

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
			HashTable<String, Object> dati, List<Order> orders)
			throws NamingException, ConfigurationException {
		List<MDFilesTmp> tables;

		tables = tableDao.find((String)dati.get("idIstituto"),
				(String)dati.get("nomeFile"), (String[])dati.get("stato"), (String)dati.get("sha1"), 
				orders);
		return tables;
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
			MDFilesTmp table) throws NamingException,
			ConfigurationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] errors = null;
		HashTable<String, Object> myDati = null;
		MDFilesTmpErrorBusiness errorBusiness = null;

		if (dati.containsKey("errors")){
			errors = (String[]) dati.get("errors");
			errorBusiness = new MDFilesTmpErrorBusiness(hibernateTemplate);
			for (int x=0; x<errors.length; x++){
				myDati = new HashTable<String, Object>();
				myDati.put("idMDFilesTmp", table.getId());
				myDati.put("type", dati.get("type"));
				myDati.put("msgError", errors[x]);
				errorBusiness.save(myDati);
			}
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
		return new MDFilesTmpDAO(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDFilesTmp table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException {

		if (dati.containsKey("idIstituto")) {
			table.setIdIstituto((String) dati.get("idIstituto"));
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
			table.setStato((String) dati.get("stato"));
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
	}

	/*
	public void error(String id, String type, String[] registroErrori) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NamingException, ConfigurationException{
		HashTable<String, Object> dati = null;
		
		try {
			dati = new HashTable<String,Object>();
			dati.put("id", id);
			dati.put("status", -1);
			dati.put("type", type);
			dati.put("errors", registroErrori);
			save(dati);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
	}


	public void pubblicato(String id, GregorianCalendar timeStampPub)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			NamingException, ConfigurationException{
		HashTable<String, Object> dati = null;

		try {
			dati = new HashTable<String,Object>();
			dati.put("id", id);
			dati.put("status", 2);
			dati.put("timestampElab", new Timestamp(timeStampPub.getTimeInMillis()));
			save(dati);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
	}

	public void archiviato(String id, GregorianCalendar timeStampElab)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			NamingException, ConfigurationException{
		HashTable<String, Object> dati = null;

		try {
			dati = new HashTable<String,Object>();
			dati.put("id", id);
			dati.put("status", 1);
			dati.put("timestampElab", new Timestamp(timeStampElab.getTimeInMillis()));
			save(dati);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
	}

	public void insert(String id, GregorianCalendar timeStampIngest, String agentDepositor, String originalContainerName, String containerName, 
			String containerFingerPrint,
			int containerType, String agentMachineIngest,
			String agentSoftwareIngest, String timeStampInit)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NamingException, ConfigurationException {
		
		MDRegistroIngressoDAO registroDAO = null;
		HashTable<String, Object> dati = null;
		String containerFingerPrintChain = null;


		try {
			registroDAO = new MDRegistroIngressoDAO(hibernateTemplate);
			containerFingerPrintChain = registroDAO.findLastKey();
			if (containerFingerPrintChain != null){
				containerFingerPrintChain = containerFingerPrint+containerFingerPrintChain;
			}

			dati = new HashTable<String,Object>();
			dati.put("id", id);
			dati.put("status", 1);
			dati.put("timestampIngest", new Timestamp(timeStampIngest.getTimeInMillis()));
			dati.put("agentDepositor", agentDepositor);
			dati.put("originalContainerName", originalContainerName);
			dati.put("containerName", containerName);
			dati.put("containerFingerPrint", containerFingerPrint);
			if (containerFingerPrintChain!=null){
				dati.put("containerFingerPrintChain", containerFingerPrintChain);
			}
			dati.put("containerType", containerType);
			dati.put("agentMachineIngest", agentMachineIngest);
			dati.put("agentSoftwareIngest", agentSoftwareIngest);
			dati.put("status", 0);
			dati.put("timestampInit", timeStampInit);
			save(dati, true);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}
		
	}
*/
	public List<MDFilesTmp> find(String idMDFilesTmp, String idIstituto,
			String nomeFile, String[] stato, String sha1) throws HibernateException, NamingException, ConfigurationException{
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
		
		return find(dati);
	}

	public Vector<Record> findToRecord(String idMDFilesTmp, String idIstituto,
			String nomeFile, String[] stato, String sha1) throws HibernateException, NamingException, ConfigurationException{
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
		
		return new Vector<Record>(findToRecord(dati));
	}

	public Hashtable<String, Integer> findCountByIstituto(String idIstituto) throws HibernateException, NamingException, ConfigurationException{
		MDFilesTmpDAO operaDAO;
		Hashtable<String, Integer> ris = null;
		
		operaDAO = newInstanceDao();
		ris = operaDAO.findCountByIstituto(idIstituto);
		return ris;
		
	}
	
	public String insertNewRec(String idIstituto, String nomeFile, String sha1, 
			Calendar nomeFileMod) throws SQLException 
					{
		String ris = null;
		HashTable<String, Object> dati=null;
		
		try {
			dati = new HashTable<String, Object>();
			dati.put("idIstituto", idIstituto);
			dati.put("nomeFile", nomeFile);
			dati.put("sha1", sha1);
			dati.put("nomeFileMod", new Timestamp(nomeFileMod.getTimeInMillis()));
			dati.put("stato", MDFilesTmpDAO.INITTRASF);
			dati.put("trasfDataStart", new Timestamp(new GregorianCalendar().getTimeInMillis()));
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
		} catch (ConfigurationException e) {
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
	public void updatEndSend(String id, boolean esito, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		
		try {
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("trasfDataEnd", new Timestamp(new GregorianCalendar().getTimeInMillis()));
			if (esito){
				dati.put("stato", MDFilesTmpDAO.FINETRASF);
				dati.put("trasfEsito", Boolean.TRUE);
			} else {
				dati.put("stato", MDFilesTmpDAO.ERRORTRASF);
				dati.put("trasfEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORTRASF);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
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
		
		try {
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("deleteLocalData", new Timestamp(new GregorianCalendar().getTimeInMillis()));
			if (esito){
				dati.put("deleteLocalEsito", Boolean.TRUE);
			} else {
				dati.put("deleteLocalEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORDELETE);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new SQLException(e.getMessage(), e);
		}
	}

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStartValidate(String id) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", MDFilesTmpDAO.INITVALID);
			dati.put("validDataStart", new Timestamp(gc.getTimeInMillis()));
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
		} catch (ConfigurationException e) {
			throw new SQLException(e.getMessage(), e);
		}
		return gc;
	}

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public GregorianCalendar updateStopValidate(String id, String xmlMimeType, boolean esito, String[] msgError, String premisFile) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("validDataEnd", new Timestamp(gc.getTimeInMillis()));
			dati.put("premisFile", premisFile);
			dati.put("xmlMimeType", xmlMimeType);
			if (esito){
				dati.put("stato", MDFilesTmpDAO.FINEVALID);
				dati.put("validEsito", Boolean.TRUE);
			} else {
				dati.put("stato", MDFilesTmpDAO.ERRORVAL);
				dati.put("validEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORVAL);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new SQLException(e.getMessage(), e);
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
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("stato", MDFilesTmpDAO.INITPUBLISH);
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
		} catch (ConfigurationException e) {
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
	public void updateDecompress(String id, GregorianCalendar compressStart, GregorianCalendar compressStop, boolean esito, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		
		try {
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("decompDataStart", new Timestamp(compressStart.getTimeInMillis()));
			dati.put("decompDataEnd", new Timestamp(compressStop.getTimeInMillis()));
			if (esito){
				dati.put("decompEsito", Boolean.TRUE);
			} else {
				dati.put("stato", MDFilesTmpDAO.ERRORDECOMP);
				dati.put("validEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORDECOMP);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
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
			GregorianCalendar copyStop, boolean esito, String[] msgError, 
			String agentDepositor, String agentMachineIngest, String agentSoftwareIngest) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDFilesTmpDAO filesTmpDAO = null;
		MDFilesTmp filesTmp =  null;
		MDRegistroIngressoBusiness registroBusiness = null;
		int containerType = -1;
		String containerName=null;
		int pos = 0;
		
		try {
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("copyPremisDataStart", new Timestamp(copyStart.getTimeInMillis()));
			dati.put("copyPremisDataEnd", new Timestamp(copyStop.getTimeInMillis()));
			if (esito){
				dati.put("copyPremisEsito", Boolean.TRUE);
			} else {
				dati.put("stato", MDFilesTmpDAO.ERRORCOPY);
				dati.put("copyPremisEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORCOPY);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				filesTmpDAO = new MDFilesTmpDAO(hibernateTemplate);
				filesTmp = filesTmpDAO.findById(id);
				if (filesTmp !=null){
					registroBusiness = new MDRegistroIngressoBusiness(hibernateTemplate);
					if (filesTmp.getXmlMimeType().equals("mets")){
						containerType=5;
					}
					containerName = filesTmp.getPremisFile();
					pos = containerName.indexOf(".");
					containerName = containerName.substring(0, pos);
					registroBusiness.insert(id, copyStart, agentDepositor, 
							filesTmp.getNomeFile(), containerName, 
							filesTmp.getSha1(), containerType, 
							agentMachineIngest, agentSoftwareIngest, 
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
			} catch (ConfigurationException e) {
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
	public void updateMoveFile(String id, GregorianCalendar moveFileStart, GregorianCalendar moveFileStop, boolean esito, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		MDRegistroIngressoBusiness registroBusiness = null;
		
		try {
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("moveFileDataStart", new Timestamp(moveFileStart.getTimeInMillis()));
			dati.put("moveFileDataEnd", new Timestamp(moveFileStop.getTimeInMillis()));
			if (esito){
				dati.put("moveFileEsito", Boolean.TRUE);
			} else {
				dati.put("stato", MDFilesTmpDAO.ERRORMOVE);
				dati.put("moveFileEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORMOVE);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				registroBusiness = new MDRegistroIngressoBusiness(hibernateTemplate);
				if (esito){
					registroBusiness.archiviato(id, moveFileStop);
				} else {
					registroBusiness.error(id, MDFilesTmpDAO.ERRORMOVE, msgError);
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
			} catch (ConfigurationException e) {
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
	public GregorianCalendar updateStopPublish(String id, boolean esito, String[] msgError) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		GregorianCalendar gc = null;
		MDRegistroIngressoBusiness registroBusiness = null;
		
		try {
			gc = new GregorianCalendar();
			
			dati = new HashTable<String, Object>();
			dati.put("id", id);
			dati.put("publishDataEnd", new Timestamp(gc.getTimeInMillis()));
			if (esito){
				dati.put("stato", MDFilesTmpDAO.FINEPUBLISH);
				dati.put("copyPremisEsito", Boolean.TRUE);
			} else {
				dati.put("stato", MDFilesTmpDAO.ERRORPUB);
				dati.put("copyPremisEsito", Boolean.FALSE);
				dati.put("type", MDFilesTmpDAO.ERRORPUB);
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
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				registroBusiness = new MDRegistroIngressoBusiness(hibernateTemplate);
				if (esito){
					registroBusiness.pubblicato(id, gc);
				} else {
					registroBusiness.error(id, MDFilesTmpDAO.ERRORPUB, msgError);
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
			} catch (ConfigurationException e) {
				throw new SQLException(e.getMessage(), e);
			}
		}
		return gc;
	}

}
