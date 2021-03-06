/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmpError;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDFilesTmpErrorDAO extends
		GenericHibernateDAO<MDFilesTmpError, String> {

	private Logger log = Logger.getLogger(MDFilesTmpErrorDAO.class);
	
	/**
	 * @param hibernateTemplate
	 */
	public MDFilesTmpErrorDAO() {
		super();
	}


	@SuppressWarnings("unchecked")
	public List<MDFilesTmpError> find(MDFilesTmp idMdFilesTmp,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDFilesTmpError> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (idMdFilesTmp != null) {
				criteria.add(Restrictions.eq("idMdFilesTmp", idMdFilesTmp));
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			paging(criteria);
			result = criteria.list();
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
