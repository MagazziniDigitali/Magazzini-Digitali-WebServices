/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDRegistroIngresso;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDRegistroIngressoDAO extends GenericHibernateDAO<MDRegistroIngresso, String> {

	private Logger log = Logger.getLogger(MDRegistroIngressoDAO.class);

	/**
	 * @param fileDb
	 */
	public MDRegistroIngressoDAO() {
		super();
	}


	/**
	 * Metodo utilizzato per la ricerca tramite lo Sha1
	 * 
	 * @param sha1
	 * @return
	 * @throws SQLException
	 */
//	@SuppressWarnings("unchecked")
//	public String findLastKey() throws HibernateException, HibernateUtilException{
//		List<MDRegistroIngresso> result = null;
//		Criteria criteria = null;
//		String res = null;
//		
//	    try {
//			beginTransaction();
//			criteria = this.createCriteria();
//			initTableJoin(criteria);
//
//			criteria.addOrder(Order.desc("timestampIngest"));
//			paging(criteria);
//			result = criteria.list();
//			if (result != null &&
//					result.size()>0){
//				res = result.get(0).getContainerFingerPrint();
//			}
//			commitTransaction();
//		} catch (HibernateException e) {
//			rollbackTransaction();
//			throw e;
//		} catch (HibernateUtilException e) {
//			rollbackTransaction();
//			throw e;
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			rollbackTransaction();
//			throw new HibernateUtilException(e.getMessage(), e);
//		}
//	    return res;
//	}

	@SuppressWarnings("unchecked")
	public List<MDRegistroIngresso> findCoda(Integer[] stato, boolean timeStamCodaNull,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDRegistroIngresso> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (stato != null) {
				criteria.add(Restrictions.in("status", stato));
			}
			if (timeStamCodaNull) {
				criteria.add(Restrictions.isNull("timestampCoda"));
			} else {
				criteria.add(Restrictions.isNotNull("timestampCoda"));
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

	@SuppressWarnings("unchecked")
	public List<MDRegistroIngresso> findExport() throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDRegistroIngresso> result = null;
		GregorianCalendar gc = null;
		
		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);

			criteria.add(Restrictions.eq("status", 2));
			criteria.add(Restrictions.isNull("timestampExport"));
			
			gc = new GregorianCalendar();
			gc.add(Calendar.DAY_OF_MONTH, -1);
			gc.set(Calendar.HOUR_OF_DAY, 23);
			gc.set(Calendar.MINUTE, 59);
			gc.set(Calendar.SECOND, 59);
			gc.set(Calendar.MILLISECOND, 999);
			criteria.add(Restrictions.le("timestampPub", new Date(gc.getTimeInMillis())));
			
			criteria.addOrder(Order.asc("timestampPub"));
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
