/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.software;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareConfigDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDSoftwareConfigBusiness extends BusinessLogic<MDSoftwareConfig, MDSoftwareConfigDAO, String> {

	/**
	 * @param hibernateTemplate
	 */
	public MDSoftwareConfigBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDSoftwareConfig dati) throws HibernateException, HibernateUtilException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#find(mx.randalf.hibernate.GenericHibernateDAO,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable, java.util.List)
	 */
	@Override
	protected List<MDSoftwareConfig> find(MDSoftwareConfigDAO tableDao, HashTable<String, Object> dati,
			List<Order> orders, int page, int pageSize) throws HibernateException, HibernateUtilException {
		List<MDSoftwareConfig> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((MDSoftware) dati.get("idSoftware"), (String) dati.get("nome"), orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDSoftwareConfigDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;

		criteria = tableDao.createCriteria();
		criteria.add(Restrictions.eq("idSoftware", (MDSoftware) dati.get("idSoftware")));
		if (dati != null && dati.get("nome") != null) {
			criteria.add(Restrictions.ilike("nome", "%" + dati.get("nome") + "%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable,
	 *      java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDSoftwareConfig table) throws NamingException,
			HibernateUtilException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDSoftwareConfig newInstance() {
		return new MDSoftwareConfig();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDSoftwareConfigDAO newInstanceDao() {
		return new MDSoftwareConfigDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDSoftwareConfig table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {

		if (dati.get("idSoftware") != null) {
			table.setIdSoftware((MDSoftware) dati.get("idSoftware"));
		}

		if (dati.get("nome") != null) {
			table.setNome((String) dati.get("nome"));
		}

		if (dati.get("descrizione") != null) {
			table.setDescrizione((String) dati.get("descrizione"));
		}

		if (dati.get("value") != null) {
			table.setValue((String) dati.get("value"));
		} else {
			table.setValue(null);
		}

		if (dati.get("idNodo") != null) {
			table.setIdNodo((MDNodi) dati.get("idNodo"));
		} else {
			table.setIdNodo(null);
		}
	}
}
