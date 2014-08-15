/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

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
	public MDRegistroIngressoErrorDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

}
