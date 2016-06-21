/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDStato;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDFilesTmpDAO extends GenericHibernateDAO<MDFilesTmp, String> {

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException 
	 */
	public MDFilesTmpDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	@SuppressWarnings("unchecked")
	public List<MDFilesTmp> find(MDIstituzione idIstituto,
			String nomeFile, MDStato[] stato, String sha1,
			List<Order> orders) throws NamingException, HibernateException,
			ConfigurationException {
		Criteria criteria = null;
		List<MDFilesTmp> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			if (idIstituto != null) {
				criteria.add(Restrictions.eq("idIstituto", idIstituto));
			}
			if (sha1 != null) {
				criteria.add(Restrictions.eq("sha1", sha1));
			}
			if (nomeFile != null) {
				criteria.add(Restrictions.ilike("nomeFile", nomeFile+"%"));
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
		} catch (NamingException e) {
			rollbackTransaction();
			throw e;
		} catch (ConfigurationException e) {
			rollbackTransaction();
			throw e;
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Hashtable<String, Integer> findCountByIstituto(MDIstituzione idIstituto) 
			throws NamingException, HibernateException, ConfigurationException {
		Criteria criteria = null;
		Hashtable<String, Integer> ris= null;
		List rs = null;
		Object[] row;
		ProjectionList projectionList = null;

		try {
			ris = new Hashtable<String, Integer>();
			beginTransaction();
			criteria = this.createCriteria();
			
			
			criteria.add(Restrictions.eq("idIstituto", idIstituto));
			
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
						ris.put(((MDStato)row[0]).getId(), (Integer)row[1]);
					} else {
						ris.put((String)row[0], (Integer)row[1]);
					}
				}
			}
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw e;
		} catch (NamingException e) {
			rollbackTransaction();
			throw e;
		} catch (ConfigurationException e) {
			rollbackTransaction();
			throw e;
		}
		return ris;
	}

	public MDFilesTmp findPremis(String premisFile) 
			throws NamingException, HibernateException, ConfigurationException {
		Criteria criteria = null;
		Object ris= null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			
			criteria.add(Restrictions.like("premisFile", premisFile+".", MatchMode.START));
			
			
			ris = criteria.uniqueResult();
			
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw e;
		} catch (NamingException e) {
			rollbackTransaction();
			throw e;
		} catch (ConfigurationException e) {
			rollbackTransaction();
			throw e;
		}
		return (ris==null?null:(MDFilesTmp)ris);
	}
}
