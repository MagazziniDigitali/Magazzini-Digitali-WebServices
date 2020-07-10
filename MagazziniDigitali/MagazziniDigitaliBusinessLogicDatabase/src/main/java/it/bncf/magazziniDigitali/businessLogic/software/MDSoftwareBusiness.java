/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.software;

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
import it.bncf.magazziniDigitali.businessLogic.rigths.MDRigthsBusiness;
import it.bncf.magazziniDigitali.database.dao.MDConfigDefaultsDAO;
import it.bncf.magazziniDigitali.database.dao.MDConfigDefaultsRowDAO;
import it.bncf.magazziniDigitali.database.dao.MDSoftwareDAO;
import it.bncf.magazziniDigitali.database.entity.MDConfigDefaultsRow;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.SHA256Tools;

/**
 * @author massi
 *
 */
public class MDSoftwareBusiness extends BusinessLogic<MDSoftware, MDSoftwareDAO, String> {

	private Logger log = LogManager.getLogger(MDSoftwareBusiness.class);

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
		    (MDIstituzione) dati.get("idIstituzione"), orders);
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

			if (dati.get("bibliotecaDepositaria") != null){
				table.setBibliotecaDepositaria((Integer) dati.get("bibliotecaDepositaria"));
			} else {
				table.setBibliotecaDepositaria(0);
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

			if (dati.get("note") != null){
				table.setNote((String) dati.get("note"));
			} else {
				table.setNote(null);
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
		MDRigthsBusiness mdRigthsBusiness = null;
		MDSoftwareConfigBusiness mdSoftwareConfigBusiness = null;
		
		try {
			if (value instanceof MDIstituzione){
				mdIstituzioneBusiness = new MDIstituzioneBusiness();
				jsonArray = mdIstituzioneBusiness.toJson((MDIstituzione) value) + "\n";
			} else if (value instanceof MDRigths){
				mdRigthsBusiness = new MDRigthsBusiness();
				jsonArray = mdRigthsBusiness.toJson((MDRigths) value) + "\n";
      } else if (value instanceof MDSoftwareConfig){
        mdSoftwareConfigBusiness = new MDSoftwareConfigBusiness();
        jsonArray = mdSoftwareConfigBusiness.toJson((MDSoftwareConfig) value, MDSoftware.class) + "\n";
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

  /**
   * Metodo utilizzato per la creazione della configurazione di Default per il Software
   * 
   * @param idSoftware
   * @param idConfigDefaults
   * @throws BusinessLogicException 
   */
  public static void createSoftwareConfig(String idSoftware, String idConfigDefaults) throws BusinessLogicException {
    MDSoftwareDAO mdSoftwareDAO = null;
    MDSoftwareConfigBusiness mdSoftwareConfigBusiness = null;
    MDConfigDefaultsDAO mdConfigDefaultsDAO = null; 
    MDConfigDefaultsRowDAO mdConfigDefaultsRowDAO = null;
    MDSoftware mdSoftware = null;
    List<MDConfigDefaultsRow> mdConfigDefaultsRows = null;
    
    try {
      mdSoftwareDAO = new MDSoftwareDAO();
      mdSoftware = mdSoftwareDAO.findById(idSoftware);

      mdConfigDefaultsDAO = new MDConfigDefaultsDAO();
      mdConfigDefaultsRowDAO = new MDConfigDefaultsRowDAO();
      mdConfigDefaultsRows = mdConfigDefaultsRowDAO.find(mdConfigDefaultsDAO.findById(idConfigDefaults), 
          null, null);
      if (mdConfigDefaultsRows !=null) {
        mdSoftwareConfigBusiness = new MDSoftwareConfigBusiness();
        for (MDConfigDefaultsRow mdConfigDefaultsRow: mdConfigDefaultsRows) {
          mdSoftwareConfigBusiness.add(mdSoftware, mdConfigDefaultsRow);
        }
      }
    } catch (HibernateException e) {
      throw new BusinessLogicException(e.getMessage(), e);
    } catch (HibernateUtilException e) {
      throw new BusinessLogicException(e.getMessage(), e);
    } catch (BusinessLogicException e) {
      throw e;
    }
  }
}
