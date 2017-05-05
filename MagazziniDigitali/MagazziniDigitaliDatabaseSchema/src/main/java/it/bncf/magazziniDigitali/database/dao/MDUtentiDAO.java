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

	public List<MDUtenti> find(String login, String nome, String cognome,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		return find(login, nome, cognome, null, orders);
	}

	@SuppressWarnings("unchecked")
	public List<MDUtenti> find(String login, String nome, String cognome,
			String email,
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
			if (email != null) {
				criteria.add(Restrictions.eq("email", email.toLowerCase()));
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

	public MDUtenti findByCodiceFiscale(String codiceFiscale) throws HibernateException, HibernateUtilException {
		MDUtenti result = null;

		try {
			beginTransaction();
			Criteria crit = createCriteria();
			crit.add(Restrictions.eq("codiceFiscale", codiceFiscale.toUpperCase()));
			paging(crit);
			result = (MDUtenti) crit.uniqueResult();
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
