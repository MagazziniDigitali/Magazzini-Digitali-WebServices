/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.Regioni;
import mx.randalf.hibernate.GenericHibernateDAO;

/**
 * @author massi
 *
 */
public class RegioniDAO extends GenericHibernateDAO<Regioni, Integer> {

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public RegioniDAO() {
		super();
	}
}
