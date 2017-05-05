/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.preRegistrazione;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import it.bncf.magazziniDigitali.businessLogic.BusinessLogic;
import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.exception.BusinessLogicException;
import it.bncf.magazziniDigitali.database.dao.MDPreRegistrazioneDAO;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDPreRegistrazione;
import it.bncf.magazziniDigitali.database.entity.MDUtenti;
import it.bncf.magazziniDigitali.database.entity.Regioni;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDPreRegistrazioneBusiness extends BusinessLogic<MDPreRegistrazione, MDPreRegistrazioneDAO, String> {

	/**
	 * 
	 */
	public MDPreRegistrazioneBusiness() {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#postSave(it.bncf.magazziniDigitali.businessLogic.HashTable,
	 *      java.io.Serializable)
	 */
	@Override
	protected void postSave(HashTable<String, Object> dati, MDPreRegistrazione table) throws NamingException,
			HibernateUtilException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstance()
	 */
	@Override
	protected MDPreRegistrazione newInstance() {
		return new MDPreRegistrazione();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#newInstanceDao()
	 */
	@Override
	protected MDPreRegistrazioneDAO newInstanceDao() {
		return new MDPreRegistrazioneDAO();
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#save(java.io.Serializable,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected void save(MDPreRegistrazione table, HashTable<String, Object> dati)
			throws HibernateException, HibernateUtilException {

		if (dati.get("progressivo") != null) {
			table.setProgressivo((Integer) dati.get("progressivo"));
		}

		if (dati.get("dataPreIscrizione") != null) {
			table.setDataPreIscrizione((Timestamp) dati.get("dataPreIscrizione"));
		}

		if (dati.get("utenteEmail") != null && !((String) dati.get("utenteEmail")).trim().equals("")) {
			table.setUtenteEmail((String) dati.get("utenteEmail"));
		}

		if (dati.get("utenteNome") != null && !((String) dati.get("utenteNome")).trim().equals("")) {
			table.setUtenteNome((String) dati.get("utenteNome"));
		}

		if (dati.get("utenteCognome") != null && !((String) dati.get("utenteCognome")).trim().equals("")) {
			table.setUtenteCognome((String) dati.get("utenteCognome"));
		}

		if (dati.get("utenteCodiceFiscale") != null && !((String) dati.get("utenteCodiceFiscale")).trim().equals("")) {
			table.setUtenteCodiceFiscale(((String) dati.get("utenteCodiceFiscale")).toUpperCase());
		}

		if (dati.get("utenteNote") != null && !((String) dati.get("utenteNote")).trim().equals("")) {
			table.setUtenteNote((String) dati.get("utenteNote"));
		}

		if (dati.get("istituzionePIva") != null && !((String) dati.get("istituzionePIva")).trim().equals("")) {
			table.setIstituzionePIva(((String) dati.get("istituzionePIva")).toUpperCase());
		}

		if (dati.get("istituzioneNome") != null && !((String) dati.get("istituzioneNome")).trim().equals("")) {
			table.setIstituzioneNome((String) dati.get("istituzioneNome"));
		}

		if (dati.get("istituzioneIndirizzo") != null
				&& !((String) dati.get("istituzioneIndirizzo")).trim().equals("")) {
			table.setIstituzioneIndirizzo((String) dati.get("istituzioneIndirizzo"));
		}

		if (dati.get("idRegione") != null) {
			table.setIdRegione((Regioni) dati.get("idRegione"));
		}

		if (dati.get("istituzioneUrl") != null && !((String) dati.get("istituzioneUrl")).trim().equals("")) {
			table.setIstituzioneUrl((String) dati.get("istituzioneUrl"));
		}

		if (dati.get("istituzioneTelefono") != null && !((String) dati.get("istituzioneTelefono")).trim().equals("")) {
			table.setIstituzioneTelefono((String) dati.get("istituzioneTelefono"));
		}

		if (dati.get("istituzioneNomeContatto") != null
				&& !((String) dati.get("istituzioneNomeContatto")).trim().equals("")) {
			table.setIstituzioneNomeContatto((String) dati.get("istituzioneNomeContatto"));
		}

		if (dati.get("istituzioneNote") != null && !((String) dati.get("istituzioneNote")).trim().equals("")) {
			table.setIstituzioneNote((String) dati.get("istituzioneNote"));
		}

		if (dati.get("altaRisoluzione") != null) {
			table.setAltaRisoluzione((Integer) dati.get("altaRisoluzione"));
		}

		if (dati.get("tesiDottorato") != null) {
			table.setTesiDottorato((Integer) dati.get("tesiDottorato"));
		}

		if (dati.get("rivisteAperte") != null && !((String) dati.get("rivisteAperte")).trim().equals("")) {
			table.setRivisteAperte((String) dati.get("rivisteAperte"));
		}

		if (dati.get("rivisteRistrette") != null && !((String) dati.get("rivisteRistrette")).trim().equals("")) {
			table.setRivisteRistrette((String) dati.get("rivisteRistrette"));
		}

		if (dati.get("ebookAperte") != null && !((String) dati.get("ebookAperte")).trim().equals("")) {
			table.setEbookAperte((String) dati.get("ebookAperte"));
		}

		if (dati.get("ebookRistrette") != null && !((String) dati.get("ebookRistrette")).trim().equals("")) {
			table.setEbookRistrette((String) dati.get("ebookRistrette"));
		}

		if (dati.get("altro") != null && !((String) dati.get("altro")).trim().equals("")) {
			table.setAltro((String) dati.get("altro"));
		}

		if (dati.get("emailValidata") != null) {
			table.setEmailValidata((Integer) dati.get("emailValidata"));
		}

		if (dati.get("idIstituzione") != null) {
			table.setIdIstituzione((MDIstituzione) dati.get("idIstituzione"));
		}

		if (dati.get("idUtente") != null) {
			table.setIdUtente((MDUtenti) dati.get("idUtente"));
		}
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#find(mx.randalf.hibernate.GenericHibernateDAO,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable, java.util.List,
	 *      int, int)
	 */
	@Override
	protected List<MDPreRegistrazione> find(MDPreRegistrazioneDAO tableDao, HashTable<String, Object> dati,
			List<Order> orders, int page, int pageSize) throws HibernateException, HibernateUtilException {
		return null;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#rowsCount(mx.randalf.hibernate.GenericHibernateDAO,
	 *      it.bncf.magazziniDigitali.businessLogic.HashTable)
	 */
	@Override
	protected Criteria rowsCount(MDPreRegistrazioneDAO tableDao, HashTable<String, Object> dati) {
		return null;
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#delete(java.io.Serializable)
	 */
	@Override
	public void delete(String id) throws Exception {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#addRecord(java.io.Serializable)
	 */
	@Override
	protected void addRecord(MDPreRegistrazione dati) throws HibernateException, HibernateUtilException {
	}

	/**
	 * @see it.bncf.magazziniDigitali.businessLogic.BusinessLogic#toJson(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	protected String toJson(String key, Object value) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, BusinessLogicException {
		return null;
	}

}
