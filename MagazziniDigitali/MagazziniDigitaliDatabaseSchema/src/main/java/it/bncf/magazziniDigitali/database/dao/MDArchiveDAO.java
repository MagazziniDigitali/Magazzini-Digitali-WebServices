/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDArchive;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDArchiveDAO extends GenericHibernateDAO<MDArchive, String> {

	private Logger log = LogManager.getLogger(MDArchiveDAO.class);

	/**
	 * @param fileDb
	 */
	public MDArchiveDAO() {
		super();
	}

	public MDArchive find(MDFilesTmp mdFilesTmp,
			MDNodi mdNodi) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		MDArchive result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (mdFilesTmp != null) {
				criteria.add(Restrictions.eq("idMdFilesTmp", mdFilesTmp));
			}
			if (mdNodi != null) {
				criteria.add(Restrictions.eq("idNodo", mdNodi));
			}
			paging(criteria);
			result = (MDArchive) criteria.uniqueResult();
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw e;
		} catch (HibernateUtilException e) {
			rollbackTransaction();
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rollbackTransaction();
			throw new HibernateUtilException(e.getMessage(), e);
		}
		return result;
	}
}
