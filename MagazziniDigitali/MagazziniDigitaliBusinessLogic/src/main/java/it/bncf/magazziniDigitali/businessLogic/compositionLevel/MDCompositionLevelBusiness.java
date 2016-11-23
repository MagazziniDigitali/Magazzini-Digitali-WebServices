/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.compositionLevel;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDCompositionLevelDAO;
import it.bncf.magazziniDigitali.database.entity.MDCompositionLevel;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDCompositionLevelBusiness extends BusinessLogic<MDCompositionLevel, MDCompositionLevelDAO, String> {
	
	/**
	 * @param hibernateTemplate
	 */
	public MDCompositionLevelBusiness() {
		super();
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDCompositionLevel dati) throws HibernateException, HibernateUtilException {
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
	protected List<MDCompositionLevel> find(MDCompositionLevelDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException, HibernateUtilException {
		List<MDCompositionLevel> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("key"),
				orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDCompositionLevelDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (dati.get("key") != null) {
			criteria.add(Restrictions.ilike("key", "%"+dati.get("key")+"%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDCompositionLevel table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDCompositionLevel newInstance() {
		return new MDCompositionLevel();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDCompositionLevelDAO newInstanceDao() {
		return new MDCompositionLevelDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDCompositionLevel table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {
		if (dati.get("key") != null){
			table.setKey((String) dati.get("key"));
		}

		if (dati.get("value") != null){
			table.setValue(((Integer) dati.get("value")));
		}
	}
}
