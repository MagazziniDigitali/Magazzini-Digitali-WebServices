/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDFilesTmpError;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDFilesTmpErrorDAO extends
		GenericHibernateDAO<MDFilesTmpError, String> {

	/**
	 * @param hibernateTemplate
	 */
	public MDFilesTmpErrorDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

}
