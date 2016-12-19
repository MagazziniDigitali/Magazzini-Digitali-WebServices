/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.modalitaAccesso;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.database.dao.MDModalitaAccessoDAO;
import it.bncf.magazziniDigitali.database.entity.MDModalitaAccesso;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDModalitaAccessoBusiness extends BusinessLogic<MDModalitaAccesso, MDModalitaAccessoDAO, String> {

	private String nome = null;

	/**
	 * @param hibernateTemplate
	 */
	public MDModalitaAccessoBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDModalitaAccesso dati) throws HibernateException, HibernateUtilException {
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
	protected List<MDModalitaAccesso> find(MDModalitaAccessoDAO tableDao, HashTable<String, Object> dati,
			List<Order> orders, int page, int pageSize) throws HibernateException, HibernateUtilException {
		List<MDModalitaAccesso> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String) dati.get("descrizione"), orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDModalitaAccessoDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;

		criteria = tableDao.createCriteria();
		if (nome != null) {
			criteria.add(Restrictions.ilike("descrizione", "%" + nome + "%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable,
	 *      java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDModalitaAccesso table) throws NamingException,
			HibernateUtilException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDModalitaAccesso newInstance() {
		return new MDModalitaAccesso();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDModalitaAccessoDAO newInstanceDao() {
		return new MDModalitaAccessoDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDModalitaAccesso table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {

		if (dati.get("descrizione") != null) {
			table.setDescrizione((String) dati.get("descrizione"));
		}
	}

	public String getNome() {
		return nome;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#genID()
	 */
	@Override
	protected String genID() {
		return super.genID() + "-AG";
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		throw new BusinessLogicException(this.getClass().getName()+" - Il formato Key: "+key+" class ["+value.getClass().getName()+"] non gestito");
	}
}
