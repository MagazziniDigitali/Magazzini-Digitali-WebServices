/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDRegistroIngressoError;
import mx.randalf.hibernate.GenericHibernateDAO;

/**
 * @author massi
 *
 */
public class MDRegistroIngressoErrorDAO extends
		GenericHibernateDAO<MDRegistroIngressoError, String> {

	/**
	 * @param hibernateTemplate
	 */
	public MDRegistroIngressoErrorDAO() {
		super();
	}

}
