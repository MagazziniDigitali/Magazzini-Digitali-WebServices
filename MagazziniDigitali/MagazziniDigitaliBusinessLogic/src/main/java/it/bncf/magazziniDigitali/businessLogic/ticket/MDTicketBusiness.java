/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.ticket;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.businessLogic.rigths.MDRigthsBusiness;
import it.bncf.magazziniDigitali.database.dao.MDTicketDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDTicket;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDTicketBusiness extends BusinessLogic<MDTicket, MDTicketDAO, String> {

	private String nome = null;

	/**
	 * @param hibernateTemplate
	 */
	public MDTicketBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDTicket dati) throws HibernateException, HibernateUtilException {
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
	protected List<MDTicket> find(MDTicketDAO tableDao, HashTable<String, Object> dati, List<Order> orders, int page,
			int pageSize) throws HibernateException, HibernateUtilException {
		List<MDTicket> tables = null;
		//
		// tableDao.setPage(page);
		// tableDao.setPageSize(pageSize);
		// tables =
		// tableDao.find((String)dati.get("nome"),(String)dati.get("login"),
		// orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDTicketDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;

		criteria = tableDao.createCriteria();
		if (nome != null) {
			criteria.add(Restrictions.ilike("nome", "%" + nome + "%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable,
	 *      java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDTicket table) throws NamingException,
			HibernateUtilException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDTicket newInstance() {
		return new MDTicket();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDTicketDAO newInstanceDao() {
		return new MDTicketDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDTicket table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {

		if (dati.get("objectIdentifier") != null) {
			table.setObjectIdentifier((String) dati.get("objectIdentifier"));
		}

		if (dati.get("dataStart") != null) {
			table.setDataStart((Timestamp) dati.get("dataStart"));
		}

		if (dati.get("dataEnd") != null) {
			table.setDataEnd((Timestamp) dati.get("dataEnd"));
		}

		if (dati.get("modalitaAccesso") != null) {
			table.setModalitaAccesso((String) dati.get("modalitaAccesso"));
		}

		if (dati.get("idRights") != null) {
			table.setIdRights((String) dati.get("idRights"));
		}

		if (dati.get("idIstituzione") != null) {
			table.setIdIstituzione((MDIstituzione) dati.get("idIstituzione"));
		} else {
			table.setIdIstituzione(null);
		}

		if (dati.get("loginUtente") != null) {
			table.setLoginUtente((String) dati.get("loginUtente"));
		}

		if (dati.get("ipClient") != null) {
			table.setIpClient((String) dati.get("ipClient"));
		}

		if (dati.get("actualFileName") != null) {
			table.setActualFileName((String) dati.get("actualFileName"));
		}

		if (dati.get("originalFileName") != null) {
			table.setOriginalFileName((String) dati.get("originalFileName"));
		}
	}

	public String getNome() {
		return nome;
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		MDRigthsBusiness mdRigthsBusiness = null;

		try {
			if (value instanceof MDIstituzione) {
				mdIstituzioneBusiness = new MDIstituzioneBusiness();
				jsonArray = mdIstituzioneBusiness.toJson((MDIstituzione) value) + "\n";
			} else if (value instanceof MDRigths) {
				mdRigthsBusiness = new MDRigthsBusiness();
				jsonArray = mdRigthsBusiness.toJson((MDRigths) value) + "\n";
			} else {
				throw new BusinessLogicException(this.getClass().getName() + " - Il formato Key: " + key + " class ["
						+ value.getClass().getName() + "] non gestito");
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
