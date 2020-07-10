/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.configDefaults;

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
import it.bncf.magazziniDigitali.database.dao.MDConfigDefaultsRowDAO;
import it.bncf.magazziniDigitali.database.entity.MDConfigDefaults;
import it.bncf.magazziniDigitali.database.entity.MDConfigDefaultsRow;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDConfigDefaultsRowBusiness extends BusinessLogic<MDConfigDefaultsRow, MDConfigDefaultsRowDAO, String> {

	/**
	 * @param hibernateTemplate
	 */
	public MDConfigDefaultsRowBusiness() {
		super();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDConfigDefaultsRow dati) throws HibernateException, HibernateUtilException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {

	}

  /**
   * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#genID()
   */
  @Override
  protected String genID() {
    return super.genID()+"-CDS";
  }

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#find(mx.randalf.hibernate.GenericHibernateDAO,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable, java.util.List)
	 */
	@Override
	protected List<MDConfigDefaultsRow> find(MDConfigDefaultsRowDAO tableDao, HashTable<String, Object> dati,
			List<Order> orders, int page, int pageSize) throws HibernateException, HibernateUtilException {
		List<MDConfigDefaultsRow> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((MDConfigDefaults) dati.get("idConfigDefaults"), 
		    (String) dati.get("name"), orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDConfigDefaultsRowDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;

		criteria = tableDao.createCriteria();
		criteria.add(Restrictions.eq("idConfigDefaults", (MDConfigDefaults) dati.get("idConfigDefaults")));
		if (dati != null && dati.get("name") != null) {
			criteria.add(Restrictions.ilike("name", "%" + dati.get("name") + "%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable,
	 *      java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDConfigDefaultsRow table) throws NamingException,
			HibernateUtilException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDConfigDefaultsRow newInstance() {
		return new MDConfigDefaultsRow();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDConfigDefaultsRowDAO newInstanceDao() {
		return new MDConfigDefaultsRowDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDConfigDefaultsRow table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {

		if (dati.get("idConfigDefaults") != null) {
			table.setIdConfigDefaults((MDConfigDefaults) dati.get("idConfigDefaults"));
		}

		if (dati.get("name") != null) {
			table.setName((String) dati.get("name"));
		}

    if (dati.get("descrizione") != null) {
      table.setDescrizione((String) dati.get("descrizione"));
    }
		
		if (dati.get("value") != null) {
			table.setValue((String) dati.get("value"));
		}
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDConfigDefaultsBusiness mdConfigDefaultsBusiness = null;
		
		try {
			if (value instanceof MDConfigDefaults){
			  mdConfigDefaultsBusiness = new MDConfigDefaultsBusiness();
				jsonArray = mdConfigDefaultsBusiness.toJson((MDConfigDefaults) value) + "\n";
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

//	/* (non-Javadoc)
//	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#findById(java.lang.String)
//	 */
//	@Override
//	public MDConfigDefaultsRow findById(String id) throws HibernateException, HibernateUtilException {
//	  MDConfigDefaultsRow mdSoftwareConfig = null;
//		
//		mdSoftwareConfig = super.findById(id);
//		return mdSoftwareConfig;
//	}
}
