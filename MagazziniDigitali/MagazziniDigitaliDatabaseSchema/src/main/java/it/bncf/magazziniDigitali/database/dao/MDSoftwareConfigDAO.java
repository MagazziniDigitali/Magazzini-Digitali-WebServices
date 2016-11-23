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

import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDSoftwareConfigDAO extends GenericHibernateDAO<MDSoftwareConfig, String> {

	private Logger log = Logger.getLogger(MDSoftwareConfigDAO.class);

	/**
	 */
	public MDSoftwareConfigDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDSoftwareConfig> find(MDSoftware idSoftware, String nome,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDSoftwareConfig> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			criteria.add(Restrictions.eq("idSoftware", idSoftware));
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
