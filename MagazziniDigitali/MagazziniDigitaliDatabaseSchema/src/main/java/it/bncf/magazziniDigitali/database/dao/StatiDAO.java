/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.Stati;
import mx.randalf.hibernate.GenericHibernateDAO;

/**
 * @author massi
 *
 */
public class StatiDAO extends GenericHibernateDAO<Stati, Integer> {

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public StatiDAO() {
		super();
	}
}
