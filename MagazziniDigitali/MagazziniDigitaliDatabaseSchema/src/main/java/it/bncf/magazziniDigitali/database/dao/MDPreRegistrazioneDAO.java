/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDPreRegistrazione;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDPreRegistrazioneDAO extends GenericHibernateDAO<MDPreRegistrazione, String> {

	private Logger log =Logger.getLogger(MDPreRegistrazioneDAO.class);

	/**
	 */
	public MDPreRegistrazioneDAO() {
		super();
	}

	public MDPreRegistrazione findByProgressivo(Integer progressivo, Timestamp dataPreIscrizione) throws  HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		MDPreRegistrazione result = null;

		try {
			beginTransaction();
			criteria = createCriteria();
			criteria.add(Restrictions.eq("progressivo", progressivo));
			criteria.add(Restrictions.eq("dataPreIscrizione", dataPreIscrizione));
			paging(criteria);
			result = (MDPreRegistrazione) criteria.uniqueResult();
			commitTransaction();
		} catch (HibernateException ex) {
			rollbackTransaction();
			log.error(ex.getMessage(), ex);
			throw ex;
		} catch (HibernateUtilException e) {
			rollbackTransaction();
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception ex) {
			rollbackTransaction();
			log.error(ex.getMessage(), ex);
			throw new HibernateException(ex.getMessage(), ex);
		}
		return result;
	}

}
