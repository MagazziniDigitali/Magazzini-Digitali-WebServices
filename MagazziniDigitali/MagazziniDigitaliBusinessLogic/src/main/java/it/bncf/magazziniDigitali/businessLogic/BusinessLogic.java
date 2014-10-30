/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic;


import it.bncf.magazziniDigitali.utils.Record;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class BusinessLogic<T extends Serializable, D extends GenericHibernateDAO, ID extends Serializable> {

	protected HibernateTemplate hibernateTemplate;

	protected Vector<Record> records = null;

	private Logger log = Logger.getLogger(BusinessLogic.class);

	/**
	 * 
	 */
	public BusinessLogic(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	protected abstract void addRecord(T dati) throws NamingException,
			ConfigurationException;

	public abstract void delete(ID id) throws Exception;

	@SuppressWarnings("unchecked")
	public List<T> find(HashTable<String, Object> dati)
			throws NamingException, HibernateException, ConfigurationException {
		D tableDao = null;
		T table = null;
		List<T> tables = null;
		List<Order> orders = null;

		try {
			tableDao = newInstanceDao();
			orders = setOrder();

			if (dati == null || dati.size() == 0) {
				tables = tableDao.findAll(orders);
			} else if (dati.containsKey("id")) {
				table = (T) tableDao.findById((ID) dati.get("id"));
				if (table != null) {
					tables = new Vector<T>();
					tables.add(table);
				}
			} else {
				tables = find(tableDao, dati, orders);
			}
		} catch (NamingException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

		return tables;
	}

	protected Vector<Record> findToRecord(HashTable<String, Object> dati, int maxRec)
			throws NamingException, HibernateException, ConfigurationException {
		List<T> tables = null;
		int x = 0;

		try {
			tables = find(dati);
			if (tables != null) {
				for (T ai : tables) {
					x++;
					addRecord(ai);
					if (x==maxRec){
						break;
					}
				}
			}
		} catch (NamingException e) {
			throw e;
		} catch (HibernateException e) {
			throw e;
		} catch (ConfigurationException e) {
			throw e;
		}

		return records;
	}

	protected abstract List<T> find(D tableDao, HashTable<String, Object> dati,
			List<Order> orders) throws NamingException, ConfigurationException;

	protected abstract List<Order> setOrder();

	public ID save(HashTable<String, Object> dati) throws NamingException,
		ConfigurationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		return save(dati, false);
	}

	@SuppressWarnings("unchecked")
	public ID save(HashTable<String, Object> dati, boolean forceSave) throws NamingException,
			ConfigurationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		D tableDao = null;
		T table = null;
		ID id = null;

		try {
			FactoryDAO.beginTransaction();
			tableDao = newInstanceDao();
			if (!dati.containsKey("id")) {
				id = genID();
				table = newInstance();
				if (id instanceof String){
					table.getClass().getMethod("setId", String.class)
					.invoke(table, id);
				} else if (id instanceof Integer){
					table.getClass().getMethod("setId", Integer.class)
					.invoke(table, id);
				} 
			} else {
				table = (T) tableDao.findById((ID) dati.get("id"));
				if (table==null){
					forceSave=true;
					id = (ID) dati.get("id");
					table = newInstance();
					if (id instanceof String){
						table.getClass().getMethod("setId", String.class)
						.invoke(table, id);
					} else if (id instanceof Integer){
						table.getClass().getMethod("setId", Integer.class)
						.invoke(table, id);
					} 
				}
			}

			save(table, dati);

			if (dati.containsKey("id") && !forceSave) {
				tableDao.update(table);
			} else {
				tableDao.save(table);
			}

			FactoryDAO.commitTransaction(true);
			postSave(dati, table);
		} catch (HibernateException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (ConfigurationException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (SecurityException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		}
		return (table == null ? null : (ID) table.getClass()
				.getMethod("getId").invoke(table));
	}

	@SuppressWarnings("unchecked")
	protected ID genID(){
		ID id = null;
		id = (ID) UUID.randomUUID().toString();
		return id;
	}
	protected abstract void postSave(HashTable<String, Object> dati, T table)
			throws NamingException, ConfigurationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	protected abstract T newInstance();

	protected abstract D newInstanceDao();

	protected abstract void save(T table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException;
}
