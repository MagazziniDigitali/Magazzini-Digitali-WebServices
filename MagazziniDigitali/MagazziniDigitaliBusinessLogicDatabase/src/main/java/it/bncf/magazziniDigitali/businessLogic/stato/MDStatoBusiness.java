/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.stato;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDStato;
import it.bncf.magazziniDigitali.utils.Record;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDStatoBusiness extends BusinessLogic<MDStato, MDStatoDAO, String> {

	private Logger log = LogManager.getLogger(MDStatoBusiness.class);

	/**
	 * @param hibernateTemplate
	 */
	public MDStatoBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDStato dati) throws HibernateException, HibernateUtilException {

		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}
			this.records.add(setRecord(dati));
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public static Record setRecord(MDStato dati) throws HibernateException, HibernateUtilException {
		Record record = null;

		record = new Record();
		record.set("idStato", dati.getId());
		record.set("descrizione", dati.getDescrizione());

		return record;
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
	protected List<MDStato> find(MDStatoDAO tableDao, HashTable<String, Object> dati, List<Order> orders, int page,
			int pageSize) throws HibernateException, HibernateUtilException {
		return null;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#setOrder()
	 */
	@Override
	protected List<Order> setOrder() {
		return null;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable,
	 *      java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDStato table) throws NamingException,
			HibernateUtilException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDStato newInstance() {
		return new MDStato();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDStatoDAO newInstanceDao() {
		return new MDStatoDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDStato table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {
	}

	@Override
	protected Criteria rowsCount(MDStatoDAO tableDao, HashTable<String, Object> dati) {
		return null;
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		throw new BusinessLogicException(this.getClass().getName()+" - Il formato Key: "+key+" class ["+value.getClass().getName()+"] non gestito");
	}

}
