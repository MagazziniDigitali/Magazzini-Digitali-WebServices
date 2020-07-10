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
import it.bncf.magazziniDigitali.database.dao.MDConfigDefaultsDAO;
import it.bncf.magazziniDigitali.database.entity.MDConfigDefaults;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDConfigDefaultsBusiness extends BusinessLogic<MDConfigDefaults, MDConfigDefaultsDAO, String> {

	private String nome = null;
	
	/**
	 * @param hibernateTemplate
	 */
	public MDConfigDefaultsBusiness() {
		super();
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDConfigDefaults dati) throws HibernateException,
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
	protected List<MDConfigDefaults> find(MDConfigDefaultsDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException,
			HibernateUtilException {
		List<MDConfigDefaults> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("name"),
		    (String[])dati.get("tipoIstituto"),
				orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDConfigDefaultsDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (nome != null) {
			criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDConfigDefaults table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDConfigDefaults newInstance() {
		return new MDConfigDefaults();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDConfigDefaultsDAO newInstanceDao() {
		return new MDConfigDefaultsDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDConfigDefaults table, HashTable<String, Object> dati)
			throws HibernateException,
			HibernateUtilException {
		
			if (dati.get("name") != null){
				table.setName((String) dati.get("name"));
			}

			if (dati.get("tipoIstituto") != null){
				table.setTipoIstituto((String) dati.get("tipoIstituto"));
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
		return super.genID()+"-CD";
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
//		MDIstituzioneBusiness mdIstituzioneBusiness = null;
//		MDRigthsBusiness mdRigthsBusiness = null;
//		MDSoftwareConfigBusiness mdSoftwareConfigBusiness = null;
		
		try {
			if (value instanceof MDIstituzione){
//				mdIstituzioneBusiness = new MDIstituzioneBusiness();
//				jsonArray = mdIstituzioneBusiness.toJson((MDIstituzione) value) + "\n";
//			} else if (value instanceof MDRigths){
//				mdRigthsBusiness = new MDRigthsBusiness();
//				jsonArray = mdRigthsBusiness.toJson((MDRigths) value) + "\n";
//      } else if (value instanceof MDSoftwareConfig){
//        mdSoftwareConfigBusiness = new MDSoftwareConfigBusiness();
//        jsonArray = mdSoftwareConfigBusiness.toJson((MDSoftwareConfig) value, MDSoftware.class) + "\n";
			} else {
				throw new BusinessLogicException(this.getClass().getName()+" - Il formato Key: "+key+" class ["+value.getClass().getName()+"] non gestito");
			}
		} catch (SecurityException e) {
			throw e;
//		} catch (IllegalAccessException e) {
//			throw e;
		} catch (IllegalArgumentException e) {
			throw e;
//		} catch (InvocationTargetException e) {
//			throw e;
		} catch (BusinessLogicException e) {
			throw e;
		}
		return jsonArray;
	}
}
