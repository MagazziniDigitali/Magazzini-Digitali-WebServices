/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.istituzione;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.businessLogic.regioni.RegioniBusiness;
import it.bncf.magazziniDigitali.database.dao.MDIstituzioneDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.Regioni;
import it.bncf.magazziniDigitali.utils.Record;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.tools.SHA256Tools;

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
	public MDIstituzioneBusiness() {
		super();
	}

	public MDIstituzioneBusiness(
			String idIstituto) throws HibernateException, HibernateUtilException
	{
		super();
		MDIstituzioneDAO mdIstituzioneDAO = null;
		MDIstituzione mdIstituzione = null;

		try {
			mdIstituzioneDAO = new MDIstituzioneDAO();

			mdIstituzione = mdIstituzioneDAO.findById(idIstituto);

			this.idIstituto = idIstituto;
			if (mdIstituzione != null && mdIstituzione.getId().equals(idIstituto)) {
				configurata = true;
				nome = mdIstituzione.getNome();
// TODO				pathTmp = mdIstituzione.getPathTmp();
// TODO 				password = mdIstituzione.getPassword();
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDIstituzione dati) throws HibernateException, HibernateUtilException {
		
		try {
			if (this.records == null) {
				this.records = new Vector<Record>();
			}
			this.records.add(setRecord(dati));
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} catch (HibernateUtilException e) {
			log.error(e);
			throw e;
		}
	}

	public static Record setRecord(MDIstituzione dati) throws HibernateException,
			HibernateUtilException {
		Record record = null;

		record = new Record();
		record.set("idIstituto", dati.getId());
		record.set("nome", dati.getNome());
//		record.set("pathTmp", dati.getPathTmp());
//		record.set("password", dati.getPassword());
//		record.set("url", dati.getUrl());
//		record.set("urlLogo", dati.getUrlLogo());
//		record.set("uuid", dati.getUuid());
//		record.set("machineUuid", dati.getMachineUuid());
//		record.set("softwareUuid", dati.getSoftwareUuid());
//		record.set("rightUuid", dati.getRightUuid());
//		record.set("ipAuthentication", dati.getIpAuthentication());
//		record.set("ipDownload", dati.getIpDownload());

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
			throws HibernateException, HibernateUtilException {
		List<MDIstituzione> tables;

		tableDao.setPage(page);
		tableDao.setPageSize(pageSize);
		tables = tableDao.find((String)dati.get("nome"),
				(String)dati.get("login"), 
				(Integer)dati.get("bibliotecaDepositaria"),
				(Integer)dati.get("istitutoCentrale"),
				(Regioni)dati.get("idRegione"),
				orders);
		return tables;
	}

	@Override
	protected Criteria rowsCount(MDIstituzioneDAO tableDao, HashTable<String, Object> dati) {
		Criteria criteria = null;
		
		criteria = tableDao.createCriteria();
		if (nome != null) {
			criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
		}
		if (dati.get("bibliotecaDepositaria") != null) {
			criteria.add(Restrictions.eq("bibliotecaDepositaria", dati.get("bibliotecaDepositaria")));
		}
		return criteria;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable, java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDIstituzione table)
			throws NamingException, HibernateUtilException,
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
		return new MDIstituzioneDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable, it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDIstituzione table, HashTable<String, Object> dati)
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

			if (dati.get("ipAutorizzati") != null){
				table.setIpAutorizzati((String) dati.get("ipAutorizzati"));
			} else {
				table.setIpAutorizzati(null);
			}

			if (dati.get("nome") != null){
				table.setNome((String) dati.get("nome"));
			}

			if (dati.get("indirizzo") != null && 
					!((String)dati.get("indirizzo")).trim().equals("")){
				table.setIndirizzo((String) dati.get("indirizzo"));
			} else {
				table.setIndirizzo(null);
			}

			if (dati.get("telefono") != null && 
					!((String)dati.get("telefono")).trim().equals("")){
				table.setTelefono((String) dati.get("telefono"));
			} else {
				table.setTelefono(null);
			}

			if (dati.get("nomeContatto") != null && 
					!((String)dati.get("nomeContatto")).trim().equals("")){
				table.setNomeContatto((String) dati.get("nomeContatto"));
			} else {
				table.setNomeContatto(null);
			}

			if (dati.get("bibliotecaDepositaria") != null){
				table.setBibliotecaDepositaria((Integer) dati.get("bibliotecaDepositaria"));
			} else {
				table.setBibliotecaDepositaria(0);
			}

			if (dati.get("istitutoCentrale") != null){
				table.setIstitutoCentrale((Integer) dati.get("istitutoCentrale"));
			} else {
				table.setIstitutoCentrale(0);
			}

			if (dati.get("ipAccreditati") != null && 
					!((String)dati.get("ipAccreditati")).trim().equals("")){
				table.setIpAccreditati((String) dati.get("ipAccreditati"));
			} else {
				table.setIpAccreditati(null);
			}

			if (dati.get("interfacciaApiUtente") != null && 
					!((String)dati.get("interfacciaApiUtente")).trim().equals("")){
				table.setInterfacciaApiUtente((String) dati.get("interfacciaApiUtente"));
			} else {
				table.setInterfacciaApiUtente(null);
			}

			if (dati.get("libreriaApiUtente") != null && 
					!((String)dati.get("libreriaApiUtente")).trim().equals("")){
				table.setLibreriaApiUtente((String) dati.get("libreriaApiUtente"));
			} else {
				table.setLibreriaApiUtente(null);
			}
//
//			if (dati.get("emailBagit") != null && 
//					!((String)dati.get("emailBagit")).trim().equals("")){
//				table.setEmailBagit((String) dati.get("emailBagit"));
//			} else {
//				table.setEmailBagit(null);
//			}

			if (dati.get("pathTmp") != null && 
					!((String)dati.get("pathTmp")).trim().equals("")){
				table.setPathTmp((String) dati.get("pathTmp"));
//			} else {
//				table.setPathTmp(null);
			}

			if (dati.get("note") != null && 
					!((String)dati.get("note")).trim().equals("")){
				table.setNote((String) dati.get("note"));
			} else {
				table.setNote(null);
			}

			if (dati.get("url") != null && 
					!((String)dati.get("url")).trim().equals("")){
				table.setUrl((String) dati.get("url"));
			} else {
				table.setUrl(null);
			}

			if (dati.get("idRegione") != null){
				table.setIdRegione((Regioni) dati.get("idRegione"));
			} else {
				table.setIdRegione(null);
			}
			
			if (dati.get("pIva") != null && 
					!((String)dati.get("pIva")).trim().equals("")){
				table.setpIva((String) dati.get("pIva"));
			} else {
				table.setpIva(null);
			}
			
			if (dati.get("altaRisoluzione") != null && 
					!((String)dati.get("altaRisoluzione")).trim().equals("")){
				table.setAltaRisoluzione(new Integer((String) dati.get("altaRisoluzione")));
			} else {
				table.setAltaRisoluzione(0);
			}
			
			if (dati.get("bagit") != null && 
					!((String)dati.get("bagit")).trim().equals("")){
				table.setBagit(new Integer((String) dati.get("bagit")));
			} else {
				table.setBagit(0);
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			throw new HibernateUtilException(e.getMessage(),e);
		}
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
		RegioniBusiness regioniBusiness = null;
		
		try {
			if (value instanceof Regioni){
				regioniBusiness = new RegioniBusiness();
				FactoryDAO.initialize(value);
				jsonArray = regioniBusiness.toJson((Regioni) value) + "\n";
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
		} catch (HibernateException e) {
			throw new BusinessLogicException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw new BusinessLogicException(e.getMessage(), e);
		}
		return jsonArray;
	}
}
