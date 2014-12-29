/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.istituzione;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.database.dao.MDIstituzioneDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.utils.Record;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDIstituzioneBusiness extends BusinessLogic<MDIstituzione, MDIstituzioneDAO, String> {

	private Logger log = Logger.getLogger(MDIstituzioneBusiness.class);

	private String idIstituto = null;

	private boolean configurata=false;

	private String nome = null;

	private String pathTmp = null;

	private String password = null;
	
	/**
	 * @param hibernateTemplate
	 */
	public MDIstituzioneBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	public MDIstituzioneBusiness(HibernateTemplate hibernateTemplate,
			String idIstituto) throws HibernateException, NamingException,
			ConfigurationException {
		super(hibernateTemplate);
		MDIstituzioneDAO mdIstituzioneDAO = null;
		MDIstituzione mdIstituzione = null;

		try {
			mdIstituzioneDAO = new MDIstituzioneDAO(hibernateTemplate);

			mdIstituzione = mdIstituzioneDAO.findById(idIstituto);

			this.idIstituto = idIstituto;
			if (mdIstituzione != null && mdIstituzione.getId().equals(idIstituto)) {
				configurata = true;
				nome = mdIstituzione.getNome();
				pathTmp = mdIstituzione.getPathTmp();
				password = mdIstituzione.getPassword();
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDIstituzione dati) throws NamingException,
			ConfigurationException {
		
		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}
			this.records.add(setRecord(dati));
		} catch (NamingException e) {
			log.error(e);
			throw e;
		} catch (ConfigurationException e) {
			log.error(e);
			throw e;
		}
	}

	public static Record setRecord(MDIstituzione dati) throws NamingException,
			ConfigurationException {
		Record record = null;

		record = new Record();
		record.set("idIstituto", dati.getId());
		record.set("nome", dati.getNome());
		record.set("pathTmp", dati.getPathTmp());
		record.set("password", dati.getPassword());
		record.set("url", dati.getUrl());
		record.set("urlLogo", dati.getUrlLogo());
		record.set("uuid", dati.getUuid());
		record.set("machineUuid", dati.getMachineUuid());
		record.set("softwareUuid", dati.getSoftwareUuid());
		record.set("rightUuid", dati.getRightUuid());
		record.set("ipAuthentication", dati.getIpAuthentication());
		record.set("ipDownload", dati.getIpDownload());

		return record;
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
	protected List<MDIstituzione> find(MDIstituzioneDAO tableDao,
			HashTable<String, Object> dati, List<Order> orders, int page, int pageSize)
			throws NamingException, ConfigurationException {
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
	protected void postSave(HashTable<String, Object> dati, MDIstituzione table)
			throws NamingException, ConfigurationException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDIstituzione newInstance() {
		return new MDIstituzione();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDIstituzioneDAO newInstanceDao() {
		return new MDIstituzioneDAO(hibernateTemplate);
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDIstituzione table, HashTable<String, Object> dati)
			throws NamingException, ConfigurationException {
	}


	public boolean isConfigurata() {
		return configurata;
	}

	public String getIdIstituto() {
		return idIstituto;
	}

	public String getNome() {
		return nome;
	}

	public String getPathTmp() {
		return pathTmp;
	}

	public String getPassword() {
		return password;
	}
}
