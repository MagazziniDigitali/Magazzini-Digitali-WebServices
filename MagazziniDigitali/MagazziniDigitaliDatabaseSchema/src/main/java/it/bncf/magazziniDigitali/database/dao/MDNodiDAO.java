/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDNodi;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDNodiDAO extends GenericHibernateDAO<MDNodi, String> {

	private Logger log = LogManager.getLogger(MDNodiDAO.class);

	/**
	 */
	public MDNodiDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDNodi> findActive() throws HibernateException, HibernateUtilException{
		Criteria criteria = null;
		List<MDNodi> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			criteria.add(Restrictions.eq("active", 1));

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

	@SuppressWarnings("unchecked")
	public List<MDNodi> find(String nome,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDNodi> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (nome != null) {
				criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
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
