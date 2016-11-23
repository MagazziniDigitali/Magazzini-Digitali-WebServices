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

import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDIstituzioneDAO extends GenericHibernateDAO<MDIstituzione, String> {

	private Logger log =Logger.getLogger(MDIstituzioneDAO.class);

	/**
	 */
	public MDIstituzioneDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDIstituzione> find(String nome, String login, 
			List<Order> orders) throws  HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDIstituzione> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			if (nome != null) {
				criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
			}
			if (login != null) {
				criteria.add(Restrictions.ilike("login", login));
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
