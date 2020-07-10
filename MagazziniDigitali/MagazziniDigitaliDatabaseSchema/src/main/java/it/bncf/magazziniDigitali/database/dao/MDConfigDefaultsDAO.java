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

import it.bncf.magazziniDigitali.database.entity.MDConfigDefaults;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDConfigDefaultsDAO extends GenericHibernateDAO<MDConfigDefaults, String> {

  private Logger log = LogManager.getLogger(MDConfigDefaultsDAO.class);

  public MDConfigDefaultsDAO() {
		super();
	}

  @SuppressWarnings("unchecked")
  public List<MDConfigDefaults> find(String name,
      String[] tipoIstituto,
      List<Order> orders) throws HibernateException,
      HibernateUtilException {
    Criteria criteria = null;
    List<MDConfigDefaults> result = null;

    try {
      beginTransaction();
      criteria = this.createCriteria();
      initTableJoin(criteria);
      if (name != null) {
        criteria.add(Restrictions.ilike("name", "%"+name+"%"));
      }
      if (tipoIstituto != null) {
        criteria.add(Restrictions.in("tipoIstituto", tipoIstituto));
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
