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
import org.hibernate.sql.JoinType;

import it.bncf.magazziniDigitali.database.entity.MDRigths;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDRigthsDAO extends GenericHibernateDAO<MDRigths, String> {

	private Logger log = LogManager.getLogger(MDRigthsDAO.class);

	/**
	 */
	public MDRigthsDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDRigths> find(String nome,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDRigths> result = null;

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

	/**
	 * @see mx.randalf.hibernate.GenericHibernateDAO#initTableJoin(org.hibernate.Criteria)
	 */
	@Override
	protected void initTableJoin(Criteria crit) {
		crit.createAlias("idModalitaAccesso", "idModalitaAccesso", JoinType.LEFT_OUTER_JOIN);
		super.initTableJoin(crit);
	}

}
