/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.database.entity.MDContatori;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDContatoriDAO extends GenericHibernateDAO<MDContatori, String> {

	/**
	 */
	public MDContatoriDAO() {
		super();
	}

	public String readKey(String id) throws HibernateException, HibernateUtilException {
		String key = null;
		MDContatori mdContatori = null;
		
		try {
			mdContatori = this.findById(id);
			if (mdContatori != null) {
				if (mdContatori.getId().equals(id)) {
					key = mdContatori.getValue();
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return key;
	}

	public void writeKey(String id, String value) throws HibernateException, HibernateUtilException {
		MDContatori mdContatori = null;

		try {
			mdContatori = this.findById(id);
			if (mdContatori != null) {
				if (mdContatori.getId().equals(id)) {
					mdContatori.setValue(value);
					this.update(mdContatori);
				} else {
					throw new HibernateUtilException("La chiave della talebba contatore ["+mdContatori.getId()+"] non corrisponde a  ["+id+"]");
				}
			} else {
				// La Chiave non è presente ne registro una nuova
				mdContatori = new MDContatori();
				mdContatori.setId(id);
				mdContatori.setValue(value);
				this.save(mdContatori);
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}

	}
}
