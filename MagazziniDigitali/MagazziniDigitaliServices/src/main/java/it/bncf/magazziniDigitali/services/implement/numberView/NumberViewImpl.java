/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.numberView;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.ticket.MDTicketBusiness;
import it.depositolegale.www.numberView.NumberView;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class NumberViewImpl {

	private static Logger log = Logger.getLogger(NumberViewImpl.class);

	/**
	 * 
	 */
	public NumberViewImpl() {
	}

	public static BigInteger numberView(NumberView id){
		long count = 0;
		MDTicketBusiness mdTicketBusiness = null;
		HashTable<String, Object> dati = null;
		
		try {
			mdTicketBusiness = new MDTicketBusiness();
			dati = new HashTable<String, Object>();
			dati.put("objectIdentifier", id.getIdObject());
			count = mdTicketBusiness.rowsCount(dati);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			count =0;
		} catch (HibernateUtilException e) {
			log.error(e.getMessage(), e);
			count =0;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			count =0;
		}
		return new BigInteger(Long.toString(count));
	}
}
