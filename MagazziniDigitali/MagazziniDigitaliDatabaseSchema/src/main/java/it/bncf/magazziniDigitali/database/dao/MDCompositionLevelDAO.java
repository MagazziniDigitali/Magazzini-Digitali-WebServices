/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDCompositionLevel;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDCompositionLevelDAO extends GenericHibernateDAO<MDCompositionLevel, String> {

	private Logger log = Logger.getLogger(MDCompositionLevelDAO.class);

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MDCompositionLevelDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDCompositionLevel> find(String key,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDCompositionLevel> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (key != null) {
				criteria.add(Restrictions.ilike("key", "%"+key+"%"));
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
