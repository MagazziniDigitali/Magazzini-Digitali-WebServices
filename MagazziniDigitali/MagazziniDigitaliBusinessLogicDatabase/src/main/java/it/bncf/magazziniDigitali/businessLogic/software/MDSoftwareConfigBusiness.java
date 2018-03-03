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
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.businessLogic.nodi.MDNodiBusiness;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareConfigDAO;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import mx.randalf.hibernate.FactoryDAO;
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
		}

		if (dati.get("idNodo") != null) {
			table.setIdNodo((MDNodi) dati.get("idNodo"));
		}
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDSoftwareBusiness mdSoftwareBusiness = null;
		MDNodiBusiness mdNodiBusiness = null;
		
		try {
			if (value instanceof MDSoftware){
				mdSoftwareBusiness = new MDSoftwareBusiness();
				jsonArray = mdSoftwareBusiness.toJson((MDSoftware) value) + "\n";
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

	/* (non-Javadoc)
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#findById(java.lang.String)
	 */
	@Override
	public MDSoftwareConfig findById(String id) throws HibernateException, HibernateUtilException {
		MDSoftwareConfig mdSoftwareConfig = null;
		
		mdSoftwareConfig = super.findById(id);
		if (mdSoftwareConfig.getIdNodo() != null) {
			FactoryDAO.initialize(mdSoftwareConfig.getIdNodo());
		}
		if (mdSoftwareConfig.getIdSoftware() != null) {
			FactoryDAO.initialize(mdSoftwareConfig.getIdSoftware());
			if (mdSoftwareConfig.getIdSoftware().getIdIstituzione() != null) {
				FactoryDAO.initialize(mdSoftwareConfig.getIdSoftware().getIdIstituzione());
				if (mdSoftwareConfig.getIdSoftware().getIdIstituzione().getIdRegione() != null) {
					FactoryDAO.initialize(mdSoftwareConfig.getIdSoftware().getIdIstituzione().getIdRegione());
				}
			}
			if (mdSoftwareConfig.getIdSoftware().getIdRigths() != null) {
				FactoryDAO.initialize(mdSoftwareConfig.getIdSoftware().getIdRigths());
				if (mdSoftwareConfig.getIdSoftware().getIdRigths().getIdModalitaAccesso() != null) {
					FactoryDAO.initialize(mdSoftwareConfig.getIdSoftware().getIdRigths().getIdModalitaAccesso());
				}
			}
		}
		// TODO Auto-generated method stub
		return mdSoftwareConfig;
	}
}
