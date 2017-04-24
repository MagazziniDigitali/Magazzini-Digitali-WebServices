/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.archive;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.nodi.MDNodiBusiness;
import it.bncf.magazziniDigitali.database.dao.MDArchiveDAO;
import it.bncf.magazziniDigitali.database.dao.MDFilesTmpDAO;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.entity.MDArchive;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDArchiveBusiness extends
		BusinessLogic<MDArchive, MDArchiveDAO, String> {

	/**
	 * @param hibernateTemplate
	 */
	public MDArchiveBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDArchive dati) throws HibernateException,
			HibernateUtilException {
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
	protected List<MDArchive> find(MDArchiveDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int firstRecord, int maxRecords)
			throws HibernateException,
			HibernateUtilException {
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
	protected void postSave(HashTable<String, Object> dati, MDArchive table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDArchive newInstance() {
		return new MDArchive();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDArchiveDAO newInstanceDao() {
		return new MDArchiveDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDArchive table, HashTable<String, Object> dati)
			throws HibernateException,
			HibernateUtilException {
		MDFilesTmpDAO mdFilesTmpDAO = null;
		MDNodiDAO mdNodiDAO = null;

		if (dati.containsKey("idMdFilesTmp")) {
			if (dati.get("idMdFilesTmp") instanceof String){
				mdFilesTmpDAO = new MDFilesTmpDAO();
				table.setIdMdFilesTmp(mdFilesTmpDAO.findById((String) dati.get("idMdFilesTmp")));
			} else {
				table.setIdMdFilesTmp((MDFilesTmp) dati.get("idMdFilesTmp"));
			}
		}
		if (dati.containsKey("idNodo")) {
			if (dati.get("idNodo") instanceof String){
				mdNodiDAO = new MDNodiDAO();
				table.setIdNodo(mdNodiDAO.findById((String) dati.get("idNodo")));
			} else {
				table.setIdNodo((MDNodi) dati.get("idNodo"));
			}
		}

		if (dati.containsKey("dataStart")) {
			table.setDataStart((Timestamp) dati.get("dataStart"));
		}
		if (dati.containsKey("dataEnd")) {
			table.setDataEnd((Timestamp) dati.get("dataEnd"));
		}
		if (dati.containsKey("esito")) {
			table.setEsito((Boolean) dati.get("esito"));
		}
	}

	public MDArchive find(MDFilesTmp mdFilesTmp,
			MDNodi mdNodi) throws HibernateException,
			HibernateUtilException {
		return this.newInstanceDao().find(mdFilesTmp, mdNodi);
	}

	public void insert(String id, MDFilesTmp idMdFilesTmp, MDNodi idNodo, GregorianCalendar dataStart, 
			GregorianCalendar dataEnd, Boolean esito) throws SQLException{
		String ris = null;
		HashTable<String, Object> dati=null;
		
		try {
			dati = new HashTable<String, Object>();
			if (id != null){
				dati.put("id", id);
			}
			dati.put("idMdFilesTmp", idMdFilesTmp);
			dati.put("idNodo", idNodo);
			dati.put("dataStart", new Timestamp(dataStart.getTimeInMillis()));
			dati.put("dataEnd", new Timestamp(dataEnd.getTimeInMillis()));
			dati.put("esito", esito);
			ris = save(dati);
			if (ris == null || ris.trim().equals("")){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			}
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(), e);
		}
	}

	@Override
	protected Criteria rowsCount(MDArchiveDAO tableDao, HashTable<String, Object> dati) {
		return null;
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDFilesTmpBusiness mdFilesTmpBusiness = null;
		MDNodiBusiness mdNodiBusiness = null;
		
		try {
			if (value instanceof MDFilesTmp){
				mdFilesTmpBusiness = new MDFilesTmpBusiness();
				jsonArray = mdFilesTmpBusiness.toJson((MDFilesTmp) value) + "\n";
			} else if (value instanceof MDNodi){
				mdNodiBusiness = new MDNodiBusiness();
				jsonArray = mdNodiBusiness.toJson((MDNodi) value) + "\n";
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
