/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.database.entity.MDStato;
import mx.randalf.hibernate.GenericHibernateDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
public class MDStatoDAO extends GenericHibernateDAO<MDStato, String> {

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public MDStatoDAO() {
		super();
	}

	public static String INITTRASF = "INITTRASF";
	
	/**
	 * Stato Inizio Trasferimento da Client verso Magazzini Digitali
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato INITTRASF() throws HibernateException, HibernateUtilException{
		return this.findById(INITTRASF);
	}

	public static String FINETRASF = "FINETRASF";

	/**
	 * Stato Fine Trasferimento da Client verso Magazzini Digitali
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato FINETRASF() throws HibernateException, HibernateUtilException{
		return this.findById(FINETRASF);
	}

	/**
	 * Stato Erore Generico
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERROR() throws HibernateException, HibernateUtilException{
		return this.findById("ERROR");
	}

	/**
	 * Stato Erore riscontrato durante la fase di trasferimento
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORTRASF() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORTRASF");
	}

	/**
	 * Stato Erore riscontrato durante la fase di Validazione
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORVAL() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORVAL");
	}

	/**
	 * Stato Erore riscontrato durante la fase di decompressione
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORDECOMP() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORDECOMP");
	}

	/**
	 * Stato Erore riscontrato durante la fase di Copia
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORCOPY() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORCOPY");
	}

	/**
	 * Stato Erore durante la fase di spostamento
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORMOVE() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORMOVE");
	}

	/**
	 * Stato Erore riscontrato durante la fase di pubblicazione
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORPUB() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORPUB");
	}

	/**
	 * Stato Erore riscontrato durante la fase di cancellazione
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORDELETE() throws HibernateException, HibernateUtilException{
		return this.findById("ERRORDELETE");
	}

	public static String ERRORARCHIVE = "ERRORARCHIVE";
	/**
	 * Stato Erore riscontrato durante la fase di replica negli altri archivi
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORARCHIVE() throws HibernateException, HibernateUtilException{
		return this.findById(ERRORARCHIVE);
	}

	public static String INITVALID = "INITVALID";

	/**
	 * Stato Inizio Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato INITVALID() throws HibernateException, HibernateUtilException{
		return this.findById(INITVALID);
	}

	public static String FINEVALID = "FINEVALID";

	/**
	 * Stato Fine Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato FINEVALID() throws HibernateException, HibernateUtilException{
		return this.findById(FINEVALID);
	}

	public static String INITPUBLISH = "INITPUBLISH";
	/**
	 * Stato Inizio Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato INITPUBLISH() throws HibernateException, HibernateUtilException{
		return this.findById(INITPUBLISH);
	}

	public static String FINEPUBLISH = "FINEPUBLISH";

	/**
	 * Stato Fine Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato FINEPUBLISH() throws HibernateException, HibernateUtilException{
		return this.findById(FINEPUBLISH);
	}

	public static String INITARCHIVE = "INITARCHIVE";
	/**
	 * Stato Inizio archivizione sugli altri nodi
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato INITARCHIVE() throws HibernateException, HibernateUtilException{
		return this.findById(INITARCHIVE);
	}

	public static String FINEARCHIVE = "FINEARCHIVE";
	/**
	 * Stato Fine archivizione sugli altri nodi
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato FINEARCHIVE() throws HibernateException, HibernateUtilException{
		return this.findById(FINEARCHIVE);
	}

	public static String INITINDEX = "INITINDEX";
	/**
	 * Stato Inizio indicizzazione del materiale in Solr
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato INITINDEX() throws HibernateException, HibernateUtilException{
		return this.findById(INITINDEX);
	}

	public static String CHECKINDEX = "CHECKINDEX";
	/**
	 * Stato Inizio indicizzazione del materiale in Solr
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato CHECKINDEX() throws HibernateException, HibernateUtilException{
		return this.findById(CHECKINDEX);
	}

	public static String FINEINDEX = "FINEINDEX";
	/**
	 * Stato Fine indicizzazione del materiale in Solr
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato FINEINDEX() throws HibernateException, HibernateUtilException{
		return this.findById(FINEINDEX);
	}

	public static String ERRORINDEX = "ERRORINDEX";
	/**
	 * Stato Erore riscontrato durante la fase di replica negli altri archivi
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public MDStato ERRORINDEX() throws HibernateException, HibernateUtilException{
		return this.findById(ERRORINDEX);
	}
}
