/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.filesTmp;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.businessLogic.stato.MDStatoBusiness;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpErrorDAO;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmpError;
import it.bncf.magazziniDigitali.database.entity.MDStato;
import it.bncf.magazziniDigitali.utils.DateBusiness;
import it.bncf.magazziniDigitali.utils.Record;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDFilesTmpErrorBusiness extends
		BusinessLogic<MDFilesTmpError, MDFilesTmpErrorDAO, String> {

	private Logger log = LogManager.getLogger(getClass());

	/**
	 * @param hibernateTemplate
	 */
	public MDFilesTmpErrorBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDFilesTmpError dati) throws HibernateException,
			HibernateUtilException {
		
		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}
			this.records.add(setRecord(dati));
		} catch (HibernateException e) {
			log.error(e.getMessage(),e);
			throw e;
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(),e);
			throw e;
		}
	}

	public static Record setRecord(MDFilesTmpError dati) throws HibernateException,
			HibernateUtilException {
		Record record = null;

		record = new Record();
		record.set("idMDFilesTmpError", dati.getId());
		if (dati.getIdMdFilesTmp() != null){
			FactoryDAO.initialize(dati.getIdMdFilesTmp());
			record.set("MDregistroIngresso", MDFilesTmpBusiness.setRecord(dati.getIdMdFilesTmp()));
		}
		record.set("dataIns", DateBusiness.convert(new Timestamp(dati.getDataIns().getTime())));
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
			throws HibernateException, HibernateUtilException {
		List<MDFilesTmpError> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((MDFilesTmp)dati.get("idMdFilesTmp"), 
				orders);
		return tables;
	}
//
//	/**
//	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#setOrder()
//	 */
//	@Override
//	protected List<Order> setOrder() {
//		Vector<Order> orders;
//		orders = new Vector<Order>();
//		orders.add(Order.asc("dataIns"));
//		return orders;
//	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati,
			MDFilesTmpError table) throws NamingException,
			HibernateUtilException, IllegalAccessException,
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
		return new MDFilesTmpErrorDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDFilesTmpError table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {
		MDFilesTmpDAO mdRegistroIngressoDAO=null;
		MDStatoDAO mdStatoDAO = new MDStatoDAO();

		if (dati.containsKey("idMDFilesTmp")) {
			mdRegistroIngressoDAO = new MDFilesTmpDAO();
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
		if (dati.containsKey("traceError")) {
			table.setTraceError((String) dati.get("traceError"));
		}
	}

	@Override
	protected Criteria rowsCount(MDFilesTmpErrorDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (dati != null){
			if (dati.get("idMdFilesTmp") != null) {
				criteria.add(Restrictions.eq("idMdFilesTmp", dati.get("idMdFilesTmp")));
			}
		}
		return criteria;
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDFilesTmpBusiness mdFilesTmpBusiness = null;
		MDStatoBusiness mdStatoBusiness = null;
		
		try {
			if (value instanceof MDFilesTmp){
				mdFilesTmpBusiness = new MDFilesTmpBusiness();
				jsonArray = mdFilesTmpBusiness.toJson((MDFilesTmp) value) + "\n";
			} else if (value instanceof MDStato){
				mdStatoBusiness = new MDStatoBusiness();
				jsonArray = mdStatoBusiness.toJson((MDStato) value) + "\n";
			} else {
				throw new BusinessLogicException(this.getClass().getName()+" - Il formato Key: "+key+" class ["+value.getClass().getName()+"] non gestito");
			}
		} catch (SecurityException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		} catch (BusinessLogicException e) {
			throw e;
		}
		return jsonArray;
	}

}
