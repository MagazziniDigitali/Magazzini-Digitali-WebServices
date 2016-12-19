/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.Comuni;
import mx.randalf.hibernate.GenericHibernateDAO;

/**
 * @author massi
 *
 */
public class ComuniDAO extends GenericHibernateDAO<Comuni, Integer> {

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public ComuniDAO() {
		super();
	}
}
