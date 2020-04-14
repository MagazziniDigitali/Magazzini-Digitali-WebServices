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

import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.database.entity.MDSoftwareConfig;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDSoftwareConfigDAO extends GenericHibernateDAO<MDSoftwareConfig, String> {

	private Logger log = LogManager.getLogger(MDSoftwareConfigDAO.class);

	/**
	 */
	public MDSoftwareConfigDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDSoftwareConfig> find(MDSoftware idSoftware, String nome,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDSoftwareConfig> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			criteria.add(Restrictions.eq("idSoftware", idSoftware));
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
			for (int x=0; x<result.size(); x++){
				if (result.get(x).getIdNodo() != null){
					FactoryDAO.initialize(result.get(x).getIdNodo());
				}
				FactoryDAO.initialize(result.get(x).getIdSoftware());
				if (result.get(x).getIdSoftware().getIdIstituzione() != null){
					FactoryDAO.initialize(result.get(x).getIdSoftware().getIdIstituzione());
					if (result.get(x).getIdSoftware().getIdIstituzione().getIdRegione() != null){
						FactoryDAO.initialize(result.get(x).getIdSoftware().getIdIstituzione().getIdRegione());
					}
				}
				if (result.get(x).getIdSoftware().getIdRigths() != null){
					FactoryDAO.initialize(result.get(x).getIdSoftware().getIdRigths());
					if (result.get(x).getIdSoftware().getIdRigths().getIdModalitaAccesso() != null){
						FactoryDAO.initialize(result.get(x).getIdSoftware().getIdRigths().getIdModalitaAccesso());
					}
				}
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

	/**
	 * @see mx.randalf.hibernate.GenericHibernateDAO#initTableJoin(org.hibernate.Criteria)
	 */
	@Override
	protected void initTableJoin(Criteria crit) {
		crit.createAlias("idSoftware", "idSoftware", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("idNodo", "idNodo", JoinType.LEFT_OUTER_JOIN);
		super.initTableJoin(crit);
	}

}
