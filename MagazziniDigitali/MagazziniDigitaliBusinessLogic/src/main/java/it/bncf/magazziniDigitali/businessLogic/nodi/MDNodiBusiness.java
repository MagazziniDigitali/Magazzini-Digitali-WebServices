/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.nodi;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.utils.Record;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDNodiBusiness extends BusinessLogic<MDNodi, MDNodiDAO, String> {

	private Logger log = Logger.getLogger(MDNodiBusiness.class);

	/**
	 * @param hibernateTemplate
	 */
	public MDNodiBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDNodi dati) throws NamingException,
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

	public static Record setRecord(MDNodi dati) throws NamingException,
			ConfigurationException {
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
			throws NamingException, ConfigurationException {
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
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDNodi table)
			throws NamingException, ConfigurationException,
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
		return new MDNodiDAO(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDNodi table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException {
	}

}
