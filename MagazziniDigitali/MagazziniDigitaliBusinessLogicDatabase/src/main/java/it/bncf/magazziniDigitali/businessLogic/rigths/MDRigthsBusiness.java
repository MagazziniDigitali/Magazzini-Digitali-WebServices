/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.rigths;

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
import it.bncf.magazziniDigitali.businessLogic.modalitaAccesso.MDModalitaAccessoBusiness;
import it.bncf.magazziniDigitali.database.dao.MDRigthsDAO;
import it.bncf.magazziniDigitali.database.entity.MDModalitaAccesso;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDRigthsBusiness extends BusinessLogic<MDRigths, MDRigthsDAO, String> {

//
//	private String idIstituto = null;
//
//	private boolean configurata=false;

	private String nome = null;
//
//	private String pathTmp = null;
//
//	private String password = null;
	
	/**
	 * @param hibernateTemplate
	 */
	public MDRigthsBusiness() {
		super();
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDRigths dati) throws HibernateException,
			HibernateUtilException {
		
//		try {
//			if (this.records == null) {
//				this.records = new Vector<Record>();
//			}
//			this.records.add(setRecord(dati));
//		} catch (NamingException e) {
//			log.error(e);
//			throw e;
//		} catch (ConfigurationException e) {
//			log.error(e);
//			throw e;
//		}
	}

//	public static Record setRecord(MDUtenti dati) throws NamingException,
//			ConfigurationException {
//		Record record = null;
//
//		record = new Record();
//		record.set("idIstituto", dati.getId());
//		record.set("nome", dati.getNome());
////		record.set("pathTmp", dati.getPathTmp());
////		record.set("password", dati.getPassword());
////		record.set("url", dati.getUrl());
////		record.set("urlLogo", dati.getUrlLogo());
////		record.set("uuid", dati.getUuid());
////		record.set("machineUuid", dati.getMachineUuid());
////		record.set("softwareUuid", dati.getSoftwareUuid());
////		record.set("rightUuid", dati.getRightUuid());
////		record.set("ipAuthentication", dati.getIpAuthentication());
////		record.set("ipDownload", dati.getIpDownload());
//
//		return record;
//	}

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
	protected List<MDRigths> find(MDRigthsDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws HibernateException,
			HibernateUtilException {
		List<MDRigths> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("nome"),
				orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDRigthsDAO tableDao, HashTable<String, Object> dati) {
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
	protected void postSave(HashTable<String, Object> dati, MDRigths table)
			throws NamingException, HibernateUtilException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDRigths newInstance() {
		return new MDRigths();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDRigthsDAO newInstanceDao() {
		return new MDRigthsDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDRigths table, HashTable<String, Object> dati)
			throws HibernateException,
			HibernateUtilException {

		if (dati.get("nome") != null){
			table.setNome((String) dati.get("nome"));
		}

		if (dati.get("idModalitaAccesso") != null){
			table.setIdModalitaAccesso((MDModalitaAccesso) dati.get("idModalitaAccesso"));
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
		return super.genID()+"-RG";
	}

	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		String jsonArray = "";
		MDModalitaAccessoBusiness mdModalitaAccessoBusiness = null;
		
		try {
			if (value instanceof MDModalitaAccesso){
				mdModalitaAccessoBusiness = new MDModalitaAccessoBusiness();
				jsonArray = mdModalitaAccessoBusiness.toJson((MDModalitaAccesso) value) + "\n";
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
	public MDRigths findById(String id) throws HibernateException, HibernateUtilException {
		MDRigths mdRigths = null;
		
		mdRigths = super.findById(id);
		FactoryDAO.initialize(mdRigths.getIdModalitaAccesso());
		return mdRigths;
	}
}
