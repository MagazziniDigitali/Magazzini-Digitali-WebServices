/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import it.bncf.magazziniDigitali.utils.Record;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class BusinessLogic<T extends Serializable, D extends GenericHibernateDAO, ID extends Serializable> {


	protected Vector<Record> records = null;

	private Logger log = Logger.getLogger(BusinessLogic.class);

	private List<Order> order = null;
	
	/**
	 * 
	 */
	public BusinessLogic() {
	}

	/**
	 * Metodo per la ricerca
	 * 
	 * @param dati Lista dei dati da ricercare
	 * @param page Pagina da ricercare
	 * @param pageSize Record per pagine 
	 * @return Lista dei record trovati
	 * @throws HibernateException Eccezioni di Hibernate
	 * @throws NamingException Eccezioni di Naming
	 * @throws ConfigurationException Eccezioni di Configurazione
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(HashTable<String, Object> dati, int page, int pageSize)
			throws HibernateException, HibernateUtilException {
		D tableDao = null;
		T table = null;
		List<T> tables = null;
		List<Order> orders = null;

		try {
			tableDao = newInstanceDao();
			orders = setOrder();

			if (dati == null || dati.size() == 0) {
				tables = tableDao.findAll(orders, page, pageSize);
			} else if (dati.containsKey("id")) {
				table = (T) tableDao.findById((ID) dati.get("id"));
				if (table != null) {
					tables = new Vector<T>();
					tables.add(table);
				}
			} else {
				tables = find(tableDao, dati, orders, page, pageSize);
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}

		return tables;
	}

	public Long rowsCount(HashTable<String, Object> dati) throws HibernateException, HibernateUtilException{
		D tableDao = null;
		Long result = new Long("0");
		Criteria crit = null;
		try {

			tableDao = newInstanceDao();
			tableDao.beginTransaction();
			
			crit = rowsCount(tableDao, dati);
			
			crit.setProjection(Projections.rowCount());
			result = (Long) crit.uniqueResult();
			tableDao.commitTransaction();
		} catch (HibernateException e) {
			tableDao.rollbackTransaction();
			log.error(e.getMessage(), e);
			throw e;
		} catch (HibernateUtilException e) {
			tableDao.rollbackTransaction();
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			tableDao.rollbackTransaction();
			log.error(e.getMessage(), e);
			throw new HibernateUtilException(e.getMessage(), e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public T findById(String id) throws HibernateException, HibernateUtilException{
		D tableDao = null;
		T result = null;

		try {
			tableDao = newInstanceDao();
			result = (T) tableDao.getById(id);
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}

		return result;
	}

	protected Vector<Record> findToRecord(HashTable<String, Object> dati, int page, int pageSize)
			throws HibernateException, HibernateUtilException {
		List<T> tables = null;
		int x = 0;

		try {
			tables = find(dati, page, pageSize);
			if (tables != null) {
				for (T ai : tables) {
					x++;
					addRecord(ai);
					if (x==pageSize){
						break;
					}
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}

		return records;
	}

	public void addOrder(String key, String verso){
		if (order ==null){
			order = new Vector<Order>();
		}
		if (verso.trim().equalsIgnoreCase("ASC")){
			order.add(Order.asc(key.trim()));
		} else {
			order.add(Order.desc(key.trim()));
		}
	}

	protected List<Order> setOrder(){
		return order;
	}

	public ID save(HashTable<String, Object> dati) 
			throws HibernateException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, HibernateUtilException,
			NamingException {
		return save(dati, false);
	}

	@SuppressWarnings("unchecked")
	public ID save(HashTable<String, Object> dati, boolean forceSave) 
			throws HibernateException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, HibernateUtilException,
			NamingException {
		D tableDao = null;
		T table = null;
		ID id = null;
		ID result = null;
		

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
				table = (T) tableDao.getById((ID) dati.get("id"));
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
			
			result = (table == null ? null : (ID) table.getClass()
					.getMethod("getId").invoke(table));
		} catch (HibernateException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (IllegalArgumentException e) {
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
		} catch (SecurityException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (HibernateUtilException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (NamingException e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			FactoryDAO.rollbackTransaction(true);
			log.error(e.getMessage(), e);
			throw new HibernateUtilException(e.getMessage(), e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected ID genID(){
		ID id = null;
		id = (ID) UUID.randomUUID().toString();
		return id;
	}

	protected abstract void postSave(HashTable<String, Object> dati, T table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	protected abstract T newInstance();

	protected abstract D newInstanceDao();

	protected abstract void save(T table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException;

	/**
	 * Metodo per la Ricerca
	 * 
	 * @param tableDao Oggetto DAO per la ricerca
	 * @param dati Metodi per la ricerca
	 * @param orders Metodi di Ordinamento
	 * @param page Pagina da ricercare
	 * @param pageSize Record per pagine 
	 * @return Lista dei record trovati
	 * @throws NamingException Eccezioni di Naming
	 * @throws ConfigurationException Eccezioni di Configurazione
	 */
	protected abstract List<T> find(D tableDao, HashTable<String, Object> dati,
			List<Order> orders, int page, int pageSize) throws HibernateException, HibernateUtilException;

	protected abstract Criteria rowsCount(D tableDao, HashTable<String, Object> dati);

	public abstract void delete(ID id) throws Exception;

	protected abstract void addRecord(T dati) throws HibernateException, HibernateUtilException;

}
