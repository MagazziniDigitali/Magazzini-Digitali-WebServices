/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDPreRegistrazione;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDPreRegistrazioneDAO extends GenericHibernateDAO<MDPreRegistrazione, String> {

	private Logger log =Logger.getLogger(MDPreRegistrazioneDAO.class);

	/**
	 */
	public MDPreRegistrazioneDAO() {
		super();
	}

	public MDPreRegistrazione findByProgressivo(Integer progressivo, Timestamp dataPreIscrizione) throws  HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		MDPreRegistrazione result = null;

		try {
			beginTransaction();
			criteria = createCriteria();
			initTableJoin(criteria);
			criteria.add(Restrictions.eq("progressivo", progressivo));
			criteria.add(Restrictions.eq("dataPreIscrizione", dataPreIscrizione));
			paging(criteria);
			result = (MDPreRegistrazione) criteria.uniqueResult();
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


	@SuppressWarnings("unchecked")
	public List<MDPreRegistrazione> find(String utenteNome, String utenteCognome, String emailValidata,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDPreRegistrazione> result = null;
		String[] st = null;
		Integer[] emailValid = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);

			if (utenteNome != null) {
				criteria.add(Restrictions.ilike("utenteNome", "%"+utenteNome+"%"));
			}
			if (utenteCognome != null) {
				criteria.add(Restrictions.ilike("utenteCognome", "%"+utenteCognome+"%"));
			}
			if (emailValidata != null) {
				st = emailValidata.split(",");
				emailValid = new Integer[st.length];
				for(int x=0;x<st.length; x++){
					emailValid[x] = new Integer(st[x]); 
				}
				criteria.add(Restrictions.in("emailValidata", emailValid));
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
