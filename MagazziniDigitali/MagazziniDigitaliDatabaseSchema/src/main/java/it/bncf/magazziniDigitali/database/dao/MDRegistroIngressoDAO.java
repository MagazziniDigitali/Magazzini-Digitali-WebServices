/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDRegistroIngresso;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDRegistroIngressoDAO extends GenericHibernateDAO<MDRegistroIngresso, String> {

	/**
	 * @param fileDb
	 */
	public MDRegistroIngressoDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}


	/**
	 * Metodo utilizzato per la ricerca tramite lo Sha1
	 * 
	 * @param sha1
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public String findLastKey() throws HibernateException, NamingException, 
			ConfigurationException{
		List<MDRegistroIngresso> result = null;
		Criteria criteria = null;
		String res = null;
		
	    try {
			beginTransaction();
			criteria = this.createCriteria();

			criteria.addOrder(Order.desc("timestampIngest"));
			paging(criteria);
			result = criteria.list();
			if (result != null &&
					result.size()>0){
				res = result.get(0).getContainerFingerPrint();
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
	    return res;
	}

}
