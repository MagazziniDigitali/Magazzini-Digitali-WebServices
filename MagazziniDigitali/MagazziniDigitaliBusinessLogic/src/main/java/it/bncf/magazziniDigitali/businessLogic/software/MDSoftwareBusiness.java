/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.software;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.SHA256Tools;

/**
 * @author massi
 *
 */
public class MDSoftwareBusiness extends BusinessLogic<MDSoftware, MDSoftwareDAO, String> {

	private Logger log = Logger.getLogger(MDSoftwareBusiness.class);

	private String nome = null;
	
	/**
	 * @param hibernateTemplate
	 */
	public MDSoftwareBusiness() {
		super();
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDSoftware dati) throws HibernateException,
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
	protected List<MDSoftware> find(MDSoftwareDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException,
			HibernateUtilException {
		List<MDSoftware> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("nome"),(String)dati.get("login"),
				orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDSoftwareDAO tableDao, HashTable<String, Object> dati) {
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
	protected void postSave(HashTable<String, Object> dati, MDSoftware table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDSoftware newInstance() {
		return new MDSoftware();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDSoftwareDAO newInstanceDao() {
		return new MDSoftwareDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDSoftware table, HashTable<String, Object> dati)
			throws HibernateException,
			HibernateUtilException {
		SHA256Tools sha256Tools = null;
		String newPassword = null;
		
		try {
			if (dati.get("nome") != null){
				table.setNome((String) dati.get("nome"));
			}

			if (dati.get("login") != null){
				table.setLogin((String) dati.get("login"));
			}
			
			if (dati.get("password") != null){
				sha256Tools = new SHA256Tools();
				newPassword = new String(sha256Tools.checkSum(((String) dati.get("password")).getBytes()));
				if (table.getPassword() == null ||
						!table.getPassword().equals((String) dati.get("password"))){
					table.setPassword(newPassword);
				}
			}

			if (dati.get("ipAutorizzati") != null){
				table.setIpAutorizzati((String) dati.get("ipAutorizzati"));
			} else {
				table.setIpAutorizzati(null);
			}

			if (dati.get("idIstituzione") != null){
				table.setIdIstituzione((MDIstituzione) dati.get("idIstituzione"));
			} else {
				table.setIdIstituzione(null);
			}

			if (dati.get("idRigths") != null){
				table.setIdRigths((MDRigths) dati.get("idRigths"));
			} else {
				table.setIdRigths(null);
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			throw new HibernateUtilException(e.getMessage(),e);
		}
	}

	public String getNome() {
		return nome;
	}
}
