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

import it.bncf.magazziniDigitali.database.entity.MDModalitaAccesso;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDModalitaAccessoDAO extends GenericHibernateDAO<MDModalitaAccesso, String> {

	private Logger log = Logger.getLogger(MDModalitaAccessoDAO.class);

	/**
	 */
	public MDModalitaAccessoDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDModalitaAccesso> find(String descrizione,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDModalitaAccesso> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			if (descrizione != null) {
				criteria.add(Restrictions.ilike("descrizione", "%"+descrizione+"%"));
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
