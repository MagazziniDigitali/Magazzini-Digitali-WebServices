/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.filesTmp;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.stato.MDStatoBusiness;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpErrorDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmpError;
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
import mx.randalf.hibernate.FactoryDAO;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDFilesTmpErrorBusiness extends
		BusinessLogic<MDFilesTmpError, MDFilesTmpErrorDAO, String> {

	private Logger log = Logger.getLogger(getClass());

	/**
	 * @param hibernateTemplate
	 */
	public MDFilesTmpErrorBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDFilesTmpError dati) throws NamingException,
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

	public static Record setRecord(MDFilesTmpError dati) throws NamingException,
			ConfigurationException {
		Record record = null;

		record = new Record();
		record.set("idMDFilesTmpError", dati.getId());
		if (dati.getIdMdFilesTmp() != null){
			FactoryDAO.initialize(dati.getIdMdFilesTmp());
			record.set("MDregistroIngresso", MDFilesTmpBusiness.setRecord(dati.getIdMdFilesTmp()));
		}
		record.set("dataIns", DateBusiness.convert(dati.getDataIns()));
		if (dati.getType() != null){
			FactoryDAO.initialize(dati.getType());
			record.set("type", MDStatoBusiness.setRecord(dati.getType()));
		}
		record.set("msgError", dati.getMsgError());

		return record;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {
		MDFilesTmpErrorDAO operaDAO;
		MDFilesTmpError opere;

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
	protected List<MDFilesTmpError> find(MDFilesTmpErrorDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws NamingException, ConfigurationException {
//		List<MDRegistroIngresso> tables;
//
//		tables = tableDao.find((String)dati.get("cognome"),
//				(String) dati.get("nome"), 
//				(dati.get("idClub")==null?null:
//				club.findById(Integer.parseInt((String) dati.get("idClub")))), 
//				orders);
//		return tables;
		return null;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#setOrder()
	 */
	@Override
	protected List<Order> setOrder() {
		Vector<Order> orders;
		orders = new Vector<Order>();
		orders.add(Order.asc("dataIns"));
		return orders;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati,
			MDFilesTmpError table) throws NamingException,
			ConfigurationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDFilesTmpError newInstance() {
		return new MDFilesTmpError();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDFilesTmpErrorDAO newInstanceDao() {
		return new MDFilesTmpErrorDAO(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDFilesTmpError table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException {
		MDFilesTmpDAO mdRegistroIngressoDAO=null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO(hibernateTemplate);

		if (dati.containsKey("idMDFilesTmp")) {
			mdRegistroIngressoDAO = new MDFilesTmpDAO(hibernateTemplate);
			table.setIdMdFilesTmp(mdRegistroIngressoDAO.findById((String) dati
					.get("idMDFilesTmp")));
		}

		if (!dati.contains("id")){
			table.setDataIns(new Timestamp(new GregorianCalendar().getTimeInMillis()));
		}

		if (dati.containsKey("type")) {
			if (dati.get("type") instanceof String){
				table.setType(mdStatoDAO.findById((String) dati.get("type")));
			} else {
				table.setType((MDStato) dati.get("type"));
			}
		}
		if (dati.containsKey("msgError")) {
			table.setMsgError((String) dati.get("msgError"));
		}
	}

}
