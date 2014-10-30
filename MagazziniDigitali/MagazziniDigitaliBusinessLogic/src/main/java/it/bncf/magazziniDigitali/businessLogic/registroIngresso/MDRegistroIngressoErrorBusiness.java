/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.registroIngresso;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDRegistroIngressoDAO;
import it.bncf.magazziniDigitali.database.dao.MDRegistroIngressoErrorDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDRegistroIngresso;
import it.bncf.magazziniDigitali.database.entity.MDRegistroIngressoError;
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
public class MDRegistroIngressoErrorBusiness extends
		BusinessLogic<MDRegistroIngressoError, MDRegistroIngressoErrorDAO, String> {

	private Logger log = Logger.getLogger(getClass());

	/**
	 * @param hibernateTemplate
	 */
	public MDRegistroIngressoErrorBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDRegistroIngressoError dati) throws NamingException,
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

	public static Record setRecord(MDRegistroIngressoError dati) throws NamingException,
			ConfigurationException {
		Record record = null;

		record = new Record();
		record.set("idMDRegistroIngressoError", dati.getId());
		if (dati.getIdMDRegistroIngresso() != null){
			FactoryDAO.initialize(dati.getIdMDRegistroIngresso());
			record.set("MDregistroIngresso", MDRegistroIngressoBusiness.setRecord(dati.getIdMDRegistroIngresso()));
		}
		record.set("dataIns", DateBusiness.convert(dati.getDataIns()));
		if (dati.getType() != null){
			FactoryDAO.initialize(dati.getType());
			record.set("type", dati.getType().getId());
		}
		record.set("msgError", dati.getMsgError());

		return record;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {
		MDRegistroIngressoErrorDAO operaDAO;
		MDRegistroIngressoError opere;

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
	protected List<MDRegistroIngressoError> find(MDRegistroIngressoErrorDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders)
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
			MDRegistroIngressoError table) throws NamingException,
			ConfigurationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDRegistroIngressoError newInstance() {
		return new MDRegistroIngressoError();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDRegistroIngressoErrorDAO newInstanceDao() {
		return new MDRegistroIngressoErrorDAO(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDRegistroIngressoError table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException {
		MDRegistroIngressoDAO mdRegistroIngressoDAO=null;
		MDRegistroIngresso mdRegistroIngresso = null;
		MDStatoDAO mdStatoDAO = null;

		if (dati.containsKey("idMDRegistroIngresso")) {
			mdRegistroIngressoDAO = new MDRegistroIngressoDAO(hibernateTemplate);
			mdRegistroIngresso = mdRegistroIngressoDAO.findById((String) dati
					.get("idMDRegistroIngresso"));
			if (mdRegistroIngresso==null){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mdRegistroIngresso = mdRegistroIngressoDAO.findById((String) dati
						.get("idMDRegistroIngresso"));
			}
			table.setIdMDRegistroIngresso(mdRegistroIngresso);
		}

		if (!dati.contains("id")){
			table.setDataIns(new Timestamp(new GregorianCalendar().getTimeInMillis()));
		}

		if (dati.containsKey("type")) {
			if (dati.get("type") instanceof String){
				mdStatoDAO = new MDStatoDAO(hibernateTemplate);
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
