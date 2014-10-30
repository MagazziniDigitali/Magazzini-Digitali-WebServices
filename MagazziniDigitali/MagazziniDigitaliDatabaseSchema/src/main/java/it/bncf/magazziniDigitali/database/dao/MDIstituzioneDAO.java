/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDIstituzione;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDIstituzioneDAO extends GenericHibernateDAO<MDIstituzione, String> {

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException 
	 */
	public MDIstituzioneDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}
}
