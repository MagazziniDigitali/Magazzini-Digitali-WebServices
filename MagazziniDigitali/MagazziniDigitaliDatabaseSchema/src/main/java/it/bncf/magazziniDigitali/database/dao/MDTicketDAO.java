/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import it.bncf.magazziniDigitali.database.entity.MDTicket;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDTicketDAO extends GenericHibernateDAO<MDTicket, String> {

	private Logger log = LogManager.getLogger(MDTicketDAO.class);
			
	/**
	 */
	public MDTicketDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDTicket> findExport() throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDTicket> result = null;
		GregorianCalendar gc = null;
		
		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);

			criteria.add(Restrictions.isNull("dataExport"));
			
			gc = new GregorianCalendar();
			gc.add(Calendar.DAY_OF_MONTH, -1);
			gc.set(Calendar.HOUR_OF_DAY, 23);
			gc.set(Calendar.MINUTE, 59);
			gc.set(Calendar.SECOND, 59);
			gc.set(Calendar.MILLISECOND, 999);
			criteria.add(Restrictions.le("dataStart", new Date(gc.getTimeInMillis())));
			
			criteria.addOrder(Order.asc("dataStart"));
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
