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
					key = mdContatori.getKey();
				} else {
					throw new HibernateUtilException("La chiave della talebba contatore ["+mdContatori.getId()+"] non corrisponde a  ["+id+"]");
				}
			} else {
				throw new HibernateUtilException("Non risulta presente i contatore ["+id+"]");
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return key;
	}

	public void writeKey(String id, String key) throws HibernateException, HibernateUtilException {
		MDContatori mdContatori = null;

		try {
			mdContatori = this.findById(id);
			if (mdContatori != null) {
				if (mdContatori.getId().equals(id)) {
					mdContatori.setKey(key);
					this.update(mdContatori);
				} else {
					throw new HibernateUtilException("La chiave della talebba contatore ["+mdContatori.getId()+"] non corrisponde a  ["+id+"]");
				}
			} else {
				throw new HibernateUtilException("Non risulta presente i contatore ["+id+"]");
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}

	}
}
