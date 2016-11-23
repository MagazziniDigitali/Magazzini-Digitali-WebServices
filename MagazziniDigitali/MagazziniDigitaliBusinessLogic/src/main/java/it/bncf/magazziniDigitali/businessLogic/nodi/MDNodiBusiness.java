/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.nodi;

import java.lang.reflect.InvocationTargetException;
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
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.utils.Record;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDNodiBusiness extends BusinessLogic<MDNodi, MDNodiDAO, String> {

	private Logger log = Logger.getLogger(MDNodiBusiness.class);

	/**
	 * @param hibernateTemplate
	 */
	public MDNodiBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDNodi dati) throws HibernateException, HibernateUtilException {
		
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

	public static Record setRecord(MDNodi dati) throws HibernateException,
			HibernateUtilException {
		Record record = null;

		record = new Record();
		record.set("idNodo", dati.getId());
		record.set("nome", dati.getNome());
		record.set("descrizione", dati.getDescrizione());
		record.set("rsync", dati.getRsync());
		record.set("rsyncPassword", dati.getRsyncPassword());

		return record;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#find(mx.randalf.hibernate.GenericHibernateDAO, it.bncf.magazziniDigitali.businessLogic.HashTable, java.util.List)
	 */
	@Override
	protected List<MDNodi> find(MDNodiDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException, HibernateUtilException {
		List<MDNodi> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("nome"),
				orders);
		return tables;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#setOrder()
	 */
	@Override
	protected List<Order> setOrder() {
		return null;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDNodi table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDNodi newInstance() {
		return new MDNodi();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDNodiDAO newInstanceDao() {
		return new MDNodiDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDNodi table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {

		if (dati.get("nome") != null){
			table.setNome((String) dati.get("nome"));
		}

		if (dati.get("descrizione") != null){
			table.setDescrizione((String) dati.get("descrizione"));
		}

		if (dati.get("rsync") != null){
			table.setRsync((String) dati.get("rsync"));
		} else {
			table.setRsync(null);
		}

		if (dati.get("rsyncPassword") != null){
			table.setRsyncPassword((String) dati.get("rsyncPassword"));
		} else {
			table.setRsyncPassword(null);
		}

		if (dati.get("urlCheckStorage") != null){
			table.setUrlCheckStorage((String) dati.get("urlCheckStorage"));
		} else {
			table.setUrlCheckStorage(null);
		}

		if (dati.get("pathStorage") != null){
			table.setPathStorage((String) dati.get("pathStorage"));
		} else {
			table.setPathStorage(null);
		}
		
		if (dati.get("active") != null){
			table.setActive((Integer) dati.get("active"));
		} else {
			table.setPathStorage(null);
		}
	}

	@Override
	protected Criteria rowsCount(MDNodiDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (dati != null && dati.get("nome") != null) {
			criteria.add(Restrictions.ilike("nome", "%"+dati.get("nome")+"%"));
		}
		return criteria;
	}

}
