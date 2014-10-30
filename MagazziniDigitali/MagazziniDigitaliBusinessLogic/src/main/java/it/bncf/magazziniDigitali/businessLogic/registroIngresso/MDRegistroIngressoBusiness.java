/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.registroIngresso;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDRegistroIngressoDAO;
import it.bncf.magazziniDigitali.database.entity.MDRegistroIngresso;
import it.bncf.magazziniDigitali.database.entity.MDStato;
import it.bncf.magazziniDigitali.utils.DateBusiness;
import it.bncf.magazziniDigitali.utils.Record;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
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
public class MDRegistroIngressoBusiness extends
		BusinessLogic<MDRegistroIngresso, MDRegistroIngressoDAO, String> {

	private Logger log = Logger.getLogger(getClass());

	/**
	 * @param hibernateTemplate
	 */
	public MDRegistroIngressoBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/* (non-Javadoc)
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDRegistroIngresso dati) throws NamingException,
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

	public static Record setRecord(MDRegistroIngresso dati) throws NamingException,
			ConfigurationException {
		Record record = null;
//		Vector<Record> clubs = null;
//		Iterator<SociClub> iClubs = null;

		record = new Record();
		record.set("idMDRegistroIngresso", dati.getId());
		record.set("timestampIngest", DateBusiness.convert(dati.getTimestampIngest()));
		record.set("agentDepositor", dati.getAgentDepositor());
		record.set("originalContainerName", dati.getOriginalContainerName());
		record.set("containerName", dati.getContainerName());
		record.set("containerFingerPrint", dati.getContainerFingerPrint());

		if (dati.getContainerFingerPrintChain() != null){
			record.set("containerFingerPrintChain", dati.getContainerFingerPrintChain());
		}

		record.set("containerType", dati.getContainerType());
		record.set("agentMachineIngest", dati.getAgentMachineIngest());
		record.set("agentSoftwareIngest", dati.getAgentSoftwareIngest());
		record.set("status", dati.getStatus());

		if (dati.getTimestampInit() != null){
			record.set("timestampInit", DateBusiness.convert(dati.getTimestampInit()));
		}

		if (dati.getTimestampElab() != null){
			record.set("timestampElab", DateBusiness.convert(dati.getTimestampElab()));
		}

		if (dati.getTimestampPub() != null){
			record.set("timestampPub", DateBusiness.convert(dati.getTimestampPub()));
		}

		if (dati.getTimestampErr() != null){
			record.set("timestampErr", DateBusiness.convert(dati.getTimestampErr()));
		}

		return record;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {
		MDRegistroIngressoDAO operaDAO;
		MDRegistroIngresso opere;

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
	protected List<MDRegistroIngresso> find(MDRegistroIngressoDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders)
			throws NamingException, ConfigurationException {
//		List<MDRegistroIngresso> tables;

//		tables = tableDao.find(, 
//				orders);
//		return tables;
		return null;
	}

	public List<MDRegistroIngresso> findCoda() throws HibernateException, NamingException, ConfigurationException{
		MDRegistroIngressoDAO operaDAO;
		Vector<Order> orders;
		List<MDRegistroIngresso> result = null;
		Integer[] stato = null;
		
		try {
			orders = new Vector<Order>();
			orders.add(Order.asc("timestampPub"));
			orders.add(Order.asc("timestampElab"));

			operaDAO = newInstanceDao();
			stato = new Integer[2];
			stato[0]=1;
			stato[1]=2;
			result = operaDAO.findCoda(stato, true, orders);
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
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#setOrder()
	 */
	@Override
	protected List<Order> setOrder() {
		Vector<Order> orders;
		orders = new Vector<Order>();
		orders.add(Order.asc("timestampIngest"));
		return orders;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati,
			MDRegistroIngresso table) throws NamingException,
			ConfigurationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] errors = null;
		HashTable<String, Object> myDati = null;
		MDRegistroIngressoErrorBusiness errorBusiness = null;

		if (dati.containsKey("errors")){
			errors = (String[]) dati.get("errors");
			errorBusiness = new MDRegistroIngressoErrorBusiness(hibernateTemplate);
			for (int x=0; x<errors.length; x++){
				if (errors[x] != null){
					myDati = new HashTable<String, Object>();
					myDati.put("idMDRegistroIngresso", table.getId());
					myDati.put("type", dati.get("type"));
					myDati.put("msgError", errors[x]);
					errorBusiness.save(myDati);
				}
			}
		}
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDRegistroIngresso newInstance() {
		return new MDRegistroIngresso();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDRegistroIngressoDAO newInstanceDao() {
		return new MDRegistroIngressoDAO(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDRegistroIngresso table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException {

		if (dati.containsKey("timestampIngest")) {
			table.setTimestampIngest((Timestamp) dati.get("timestampIngest"));
		}
		if (dati.containsKey("agentDepositor")) {
			table.setAgentDepositor((String) dati.get("agentDepositor"));
		}
		if (dati.containsKey("originalContainerName")) {
			table.setOriginalContainerName((String) dati.get("originalContainerName"));
		}
		if (dati.containsKey("containerName")) {
			table.setContainerName((String) dati.get("containerName"));
		}

		if (dati.containsKey("containerFingerPrint")) {
			table.setContainerFingerPrint((String) dati.get("containerFingerPrint"));
		}

		if (dati.containsKey("containerFingerPrintChain")) {
			table.setContainerFingerPrintChain((String) dati.get("containerFingerPrintChain"));
		}

		if (dati.containsKey("containerType")) {
			table.setContainerType((Integer) dati.get("containerType"));
		}

		if (dati.containsKey("agentMachineIngest")) {
			table.setAgentMachineIngest((String) dati.get("agentMachineIngest"));
		}

		if (dati.containsKey("agentSoftwareIngest")) {
			table.setAgentSoftwareIngest((String) dati.get("agentSoftwareIngest"));
		}

		if (dati.containsKey("status")) {
			table.setStatus((Integer) dati.get("status"));
		}

		if (dati.containsKey("timestampInit")) {
			table.setTimestampInit((Timestamp) dati.get("timestampInit"));
		}

		if (dati.containsKey("timestampElab")) {
			table.setTimestampElab((Timestamp) dati.get("timestampElab"));
		}

		if (dati.containsKey("timestampPub")) {
			table.setTimestampPub((Timestamp) dati.get("timestampPub"));
		}

		if (dati.containsKey("timestampErr")) {
			table.setTimestampErr((Timestamp) dati.get("timestampErr"));
		}

		if (dati.containsKey("timestampCoda")) {
			table.setTimestampCoda((Timestamp) dati.get("timestampCoda"));
		}
	}

	public void error(String id, MDStato type, String[] registroErrori) throws IllegalAccessException,
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

	public void coda(String id)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			NamingException, ConfigurationException{
		HashTable<String, Object> dati = null;
		GregorianCalendar timeStampElab = null;

		try {
			timeStampElab = new GregorianCalendar();
			dati = new HashTable<String,Object>();
			dati.put("id", id);
			dati.put("timestampCoda", new Timestamp(timeStampElab.getTimeInMillis()));
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

	/**
	 * 
	 * @param id
	 * @param originalContainerName
	 * @param containerName
	 * @param containerFingerPrint
	 * @param containerType
	 * @param agentMachineIngest
	 * @param agentSoftwareIngest
	 * @param timeStampInit
	 * @throws SQLException
	 */
	public void insert(String id, GregorianCalendar timeStampIngest, String agentDepositor, String originalContainerName, String containerName, 
			String containerFingerPrint,
			int containerType, String agentMachineIngest,
			String agentSoftwareIngest, Timestamp timeStampInit)
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


}
