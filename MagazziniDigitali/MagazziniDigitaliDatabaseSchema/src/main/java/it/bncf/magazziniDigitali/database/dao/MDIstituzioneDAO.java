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

import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.Regioni;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDIstituzioneDAO extends GenericHibernateDAO<MDIstituzione, String> {

	private Logger log =LogManager.getLogger(MDIstituzioneDAO.class);

	/**
	 */
	public MDIstituzioneDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDIstituzione> find(String nome, String login, Integer bibliotecaDepositaria, Integer istitutoCentrale, 
			Regioni idRegione, 
			List<Order> orders) throws  HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDIstituzione> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (nome != null) {
				criteria.add(Restrictions.ilike("nome", "%"+nome+"%"));
			}
			if (login != null) {
				criteria.add(Restrictions.ilike("login", login));
			}
			if (bibliotecaDepositaria != null) {
				criteria.add(Restrictions.eq("bibliotecaDepositaria", bibliotecaDepositaria));
			}
			if (istitutoCentrale != null) {
				criteria.add(Restrictions.eq("istitutoCentrale", istitutoCentrale));
			}
			if (idRegione != null) {
				criteria.add(Restrictions.eq("idRegione", idRegione));
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

	public MDIstituzione findByPIva(String pIva) throws HibernateException, HibernateUtilException {
		MDIstituzione result = null;

		try {
			beginTransaction();
			Criteria crit = createCriteria();
			initTableJoin(crit);
			crit.add(Restrictions.eq("pIva", pIva.toUpperCase()));
			paging(crit);
			result = (MDIstituzione) crit.uniqueResult();
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

	/**
	 * @see mx.randalf.hibernate.GenericHibernateDAO#initTableJoin(org.hibernate.Criteria)
	 */
	@Override
	protected void initTableJoin(Criteria crit) {
		crit.createAlias("idRegione", "idRegione", JoinType.LEFT_OUTER_JOIN);
		super.initTableJoin(crit);
	}

}
