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

import it.bncf.magazziniDigitali.database.entity.MDUtenti;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDUtentiDAO extends GenericHibernateDAO<MDUtenti, String> {

	private Logger log =Logger.getLogger(MDUtentiDAO.class);

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MDUtentiDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDUtenti> find(String login, String nome, String cognome,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDUtenti> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			if (login != null){
				criteria.add(Restrictions.eq("login", login));
			}
			if (nome != null) {
				criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
			}
			if (cognome != null) {
				criteria.add(Restrictions.ilike("cognome", "%"+cognome+"%"));
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
