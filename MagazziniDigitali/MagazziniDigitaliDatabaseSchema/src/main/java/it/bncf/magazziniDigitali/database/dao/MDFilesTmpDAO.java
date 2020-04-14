/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import java.util.Hashtable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;

import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDStato;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDFilesTmpDAO extends GenericHibernateDAO<MDFilesTmp, String> {

	private Logger log = LogManager.getLogger(MDFilesTmpDAO.class);

	/**
	 */
	public MDFilesTmpDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<MDFilesTmp> find(MDIstituzione idIstituto,
			String nomeFile, MDStato[] stato, String md5, String md564base, 
			String sha1, String sha164base, String sha256, String sha25664base,
			List<Order> orders) throws HibernateException,
			HibernateUtilException {
		Criteria criteria = null;
		List<MDFilesTmp> result = null;
		SimpleExpression val1 = null;
		LogicalExpression log1 = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			if (idIstituto != null) {
				criteria.add(Restrictions.eq("idIstituto", idIstituto));
			}

			if (md5 != null || md564base != null ||
					sha1 != null || sha164base != null ||
					sha256 != null || sha25664base != null) {

			
				if (md5 != null) {
					val1 = Restrictions.eq("md5", md5);
				}
				if (md564base != null) {
					if (val1 == null) {
						val1 = Restrictions.eq("md564base", md564base);
					} else {
						log1 = Restrictions.or(val1, Restrictions.eq("md564base", md564base));
					}
				}
				if (sha1 != null) {
					if (val1 == null) {
						val1 = Restrictions.eq("sha1", sha1);
					} else if (log1 ==null) {
						log1 = Restrictions.or(val1, Restrictions.eq("sha1", sha1));
					} else {
						log1 = Restrictions.or(log1, Restrictions.eq("sha1", sha1));
					}
				}
				if (sha164base != null) {
					if (val1 == null) {
						val1 = Restrictions.eq("sha164base", sha164base);
					} else if (log1 ==null) {
						log1 = Restrictions.or(val1, Restrictions.eq("sha164base", sha164base));
					} else {
						log1 = Restrictions.or(log1, Restrictions.eq("sha164base", sha164base));
					}
				}
				if (sha256 != null) {
					if (val1 == null) {
						val1 = Restrictions.eq("sha256", sha256);
					} else if (log1 ==null) {
						log1 = Restrictions.or(val1, Restrictions.eq("sha256", sha256));
					} else {
						log1 = Restrictions.or(log1, Restrictions.eq("sha256", sha256));
					}
				}
				if (sha25664base != null) {
					if (val1 == null) {
						val1 = Restrictions.eq("sha25664base", sha25664base);
					} else if (log1 ==null) {
						log1 = Restrictions.or(val1, Restrictions.eq("sha25664base", sha25664base));
					} else {
						log1 = Restrictions.or(log1, Restrictions.eq("sha25664base", sha25664base));
					}
				}
				
				if (log1 != null) {
					criteria.add(log1);
				} else if (val1 != null) {
					criteria.add(val1);
				}
			}
			if (nomeFile != null) {
				criteria.add(Restrictions.ilike("nomeFile", "%"+nomeFile+"%"));
			}
			if (stato != null) {
				criteria.add(Restrictions.in("stato", stato));
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

	@SuppressWarnings("rawtypes")
	public Hashtable<String, Long> findCountByIstituto(MDIstituzione idIstituto) 
			throws HibernateException, HibernateUtilException {
		Criteria criteria = null;
		Hashtable<String, Long> ris= null;
		List rs = null;
		Object[] row;
		ProjectionList projectionList = null;

		try {
			ris = new Hashtable<String, Long>();
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			
			if (idIstituto != null){
				criteria.add(Restrictions.eq("idIstituto", idIstituto));
			}
			
			projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("stato"));
			projectionList.add(Projections.count("stato"));
			criteria.setProjection(projectionList);		
			
			rs = criteria.list();

			if (rs !=null && rs.size()>0){
				for(int x=0; x<rs.size(); x++){
					rs.get(x);
					row = (Object[]) rs.get(x);
					if (row[0] instanceof MDStato){
						FactoryDAO.initialize(row[0]);
						ris.put(((MDStato)row[0]).getId(), (Long)row[1]);
					} else {
						ris.put((String)row[0], (Long)row[1]);
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
		return ris;
	}

	public MDFilesTmp findPremis(String premisFile) 
			throws HibernateException, HibernateUtilException {
		Criteria criteria = null;
		Object ris= null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			initTableJoin(criteria);
			
			criteria.add(Restrictions.like("premisFile", premisFile+".", MatchMode.START));
			
			
			ris = criteria.uniqueResult();
			
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
		return (ris==null?null:(MDFilesTmp)ris);
	}

	/**
	 * @see mx.randalf.hibernate.GenericHibernateDAO#initTableJoin(org.hibernate.Criteria)
	 */
	@Override
	protected void initTableJoin(Criteria crit) {
		crit.createAlias("idIstituto", "idIstituto", JoinType.LEFT_OUTER_JOIN);
		crit.createAlias("stato", "stato", JoinType.LEFT_OUTER_JOIN);
		super.initTableJoin(crit);
	}
}
