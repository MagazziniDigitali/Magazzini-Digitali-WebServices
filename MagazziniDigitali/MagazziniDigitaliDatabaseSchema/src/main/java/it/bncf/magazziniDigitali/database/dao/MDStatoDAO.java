/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDStato;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDStatoDAO extends GenericHibernateDAO<MDStato, String> {

	/**
	 * 
	 * @param hibernateTemplate
	 */
	public MDStatoDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	public static String INITTRASF = "INITTRASF";
	
	/**
	 * Stato Inizio Trasferimento da Client verso Magazzini Digitali
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato INITTRASF() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(INITTRASF);
	}

	public static String FINETRASF = "FINETRASF";

	/**
	 * Stato Fine Trasferimento da Client verso Magazzini Digitali
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato FINETRASF() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(FINETRASF);
	}

	/**
	 * Stato Erore Generico
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERROR() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERROR");
	}

	/**
	 * Stato Erore riscontrato durante la fase di trasferimento
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORTRASF() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORTRASF");
	}

	/**
	 * Stato Erore riscontrato durante la fase di Validazione
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORVAL() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORVAL");
	}

	/**
	 * Stato Erore riscontrato durante la fase di decompressione
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORDECOMP() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORDECOMP");
	}

	/**
	 * Stato Erore riscontrato durante la fase di Copia
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORCOPY() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORCOPY");
	}

	/**
	 * Stato Erore durante la fase di spostamento
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORMOVE() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORMOVE");
	}

	/**
	 * Stato Erore riscontrato durante la fase di pubblicazione
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORPUB() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORPUB");
	}

	/**
	 * Stato Erore riscontrato durante la fase di cancellazione
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORDELETE() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("ERRORDELETE");
	}

	public static String ERRORARCHIVE = "ERRORARCHIVE";
	/**
	 * Stato Erore riscontrato durante la fase di replica negli altri archivi
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato ERRORARCHIVE() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(ERRORARCHIVE);
	}

	public static String INITVALID = "INITVALID";

	/**
	 * Stato Inizio Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato INITVALID() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(INITVALID);
	}

	public static String FINEVALID = "FINEVALID";

	/**
	 * Stato Fine Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato FINEVALID() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(FINEVALID);
	}

	public static String INITPUBLISH = "INITPUBLISH";
	/**
	 * Stato Inizio Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato INITPUBLISH() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(INITPUBLISH);
	}

	public static String FINEPUBLISH = "FINEPUBLISH";

	/**
	 * Stato Fine Validazione dell'oggetto trasferito
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato FINEPUBLISH() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(FINEPUBLISH);
	}

	public static String INITARCHIVE = "INITARCHIVE";
	/**
	 * Stato Inizio archivizione sugli altri nodi
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato INITARCHIVE() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(INITARCHIVE);
	}

	public static String FINEARCHIVE = "FINEARCHIVE";
	/**
	 * Stato Fine archivizione sugli altri nodi
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato FINEARCHIVE() throws HibernateException, NamingException, ConfigurationException{
		return this.findById(FINEARCHIVE);
	}

	/**
	 * Stato Inizio indicizzazione del materiale in Solr
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato INITINDEX() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("INITINDEX");
	}

	/**
	 * Stato Fine indicizzazione del materiale in Solr
	 * 
	 * @return
	 * @throws HibernateException
	 * @throws NamingException
	 * @throws ConfigurationException
	 */
	public MDStato FINEINDEX() throws HibernateException, NamingException, ConfigurationException{
		return this.findById("FINEINDEX");
	}
}
