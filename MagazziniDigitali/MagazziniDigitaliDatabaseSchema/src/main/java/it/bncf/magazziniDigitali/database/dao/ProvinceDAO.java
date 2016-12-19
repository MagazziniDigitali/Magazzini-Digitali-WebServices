/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.Province;
import mx.randalf.hibernate.GenericHibernateDAO;

/**
 * @author massi
 *
 */
public class ProvinceDAO extends GenericHibernateDAO<Province, Integer> {

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public ProvinceDAO() {
		super();
	}
}
