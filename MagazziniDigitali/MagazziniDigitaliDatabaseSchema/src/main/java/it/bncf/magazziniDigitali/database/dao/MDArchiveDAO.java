/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDArchive;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.GenericHibernateDAO;

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
}
