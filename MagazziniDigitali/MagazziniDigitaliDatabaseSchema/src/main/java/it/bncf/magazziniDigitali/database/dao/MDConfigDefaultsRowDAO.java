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
import it.bncf.magazziniDigitali.database.entity.MDConfigDefaultsRow;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDConfigDefaultsRowDAO
    extends
      GenericHibernateDAO<MDConfigDefaultsRow, String> {

  private Logger log = LogManager.getLogger(MDConfigDefaultsRowDAO.class);

  public MDConfigDefaultsRowDAO() {
    super();
  }

  @SuppressWarnings("unchecked")

  public List<MDConfigDefaultsRow> find(MDConfigDefaults idConfigDefaults,
      String name, List<Order> orders)
      throws HibernateException, HibernateUtilException {
    Criteria criteria = null;
    List<MDConfigDefaultsRow> result = null;

    try {
      beginTransaction();
      criteria = this.createCriteria();
      initTableJoin(criteria);
      criteria.add(Restrictions.eq("idConfigDefaults", idConfigDefaults));
      if (name != null) {
        criteria.add(Restrictions.ilike("name", "%" + name + "%"));
      }
      if (orders != null) {
        for (Order order : orders) {
          criteria.addOrder(order);
        }
      }
      paging(criteria);
      result = criteria.list();
      for (int x = 0; x < result.size(); x++) {
        FactoryDAO.initialize(result.get(x).getIdConfigDefaults());
      }
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
