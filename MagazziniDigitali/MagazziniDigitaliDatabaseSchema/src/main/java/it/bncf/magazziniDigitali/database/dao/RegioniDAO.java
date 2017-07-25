/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.Regioni;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class RegioniDAO extends GenericHibernateDAO<Regioni, Integer> {

	private Logger log = Logger.getLogger(RegioniDAO.class);

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public RegioniDAO() {
		super();
	}

	public Regioni findByNomeRegione(String nomeRegione) throws HibernateException, HibernateUtilException {
		Regioni result = null;

		try {
			beginTransaction();
			Criteria crit = createCriteria();
			initTableJoin(crit);
			crit.add(Restrictions.eq("nomeRegione", nomeRegione.toUpperCase()));
			paging(crit);
			result = (Regioni) crit.uniqueResult();
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
