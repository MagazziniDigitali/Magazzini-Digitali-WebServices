/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.utenti;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.database.dao.MDUtentiDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDUtenti;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.SHA256Tools;

/**
 * @author massi
 *
 */
public class MDUtentiBusiness extends BusinessLogic<MDUtenti, MDUtentiDAO, String> {

	private Logger log = LogManager.getLogger(MDUtentiBusiness.class);

	private String nome = null;

	private String cognome = null;
	
	/**
	 * @param hibernateTemplate
	 */
	public MDUtentiBusiness() {
		super();
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDUtenti dati) throws HibernateException, HibernateUtilException {
		
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
	protected List<MDUtenti> find(MDUtentiDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException, HibernateUtilException {
		List<MDUtenti> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("login"), 
		    (String)dati.get("nome"), 
		    (String)dati.get("cognome"), 
		    null, 
		    (MDIstituzione)dati.get("idIstituzione"), 
		    null, 
				orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDUtentiDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (nome != null) {
			criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
		}
		if (cognome != null) {
			criteria.add(Restrictions.ilike("cognome", "%"+cognome+"%"));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDUtenti table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDUtenti newInstance() {
		return new MDUtenti();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDUtentiDAO newInstanceDao() {
		return new MDUtentiDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDUtenti table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {
		SHA256Tools sha256Tools = null;
		String newPassword = null;
		
		try {
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

			if (dati.get("cognome") != null){
				table.setCognome((String) dati.get("cognome"));
			}

			if (dati.get("nome") != null){
				table.setNome((String) dati.get("nome"));
			}

			if (dati.get("amministratore") != null){
				table.setAmministratore((Integer) dati.get("amministratore"));
			} else {
				table.setAmministratore(0);
			}

			if (dati.get("ipAutorizzati") != null){
				table.setIpAutorizzati((String) dati.get("ipAutorizzati"));
			} else {
				table.setIpAutorizzati(null);
			}

			if (dati.get("idIstituzione") != null){
//				if (dati.get("idIstituzione") != null &&
//						!((String)dati.get("idIstituzione")).trim().equals("")){
				table.setIdIstituzione((MDIstituzione) dati.get("idIstituzione"));
			} else {
				table.setIdIstituzione(null);
			}

			if (dati.get("note") != null && 
					!((String)dati.get("note")).trim().equals("")){
				table.setNote((String) dati.get("note"));
			} else {
				table.setNote(null);
			}

			if (dati.get("codiceFiscale") != null && 
					!((String)dati.get("codiceFiscale")).trim().equals("")){
				table.setCodiceFiscale((String) dati.get("codiceFiscale"));
			} else {
				table.setCodiceFiscale(null);
			}

			if (dati.get("email") != null && 
					!((String)dati.get("email")).trim().equals("")){
				table.setEmail(((String) dati.get("email")).trim().toLowerCase());
			} else {
				table.setEmail(null);
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			throw new HibernateUtilException(e.getMessage(),e);
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
		return super.genID()+"-AG";
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		
		try {
			if (value instanceof MDIstituzione){
				mdIstituzioneBusiness = new MDIstituzioneBusiness();
				jsonArray = mdIstituzioneBusiness.toJson((MDIstituzione) value) + "\n";
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
