/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDArchive;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDNodi;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDArchiveDAO extends GenericHibernateDAO<MDArchive, String> {

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException 
	 */
	public MDArchiveDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	public MDArchive find(MDFilesTmp mdFilesTmp,
			MDNodi mdNodi) throws NamingException, HibernateException,
			ConfigurationException {
		Criteria criteria = null;
		MDArchive result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			if (mdFilesTmp != null) {
				criteria.add(Restrictions.eq("idMdFilesTmp", mdFilesTmp));
			}
			if (mdNodi != null) {
				criteria.add(Restrictions.eq("idNodo", mdNodi));
			}
			paging(criteria);
			result = (MDArchive) criteria.uniqueResult();
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
}
