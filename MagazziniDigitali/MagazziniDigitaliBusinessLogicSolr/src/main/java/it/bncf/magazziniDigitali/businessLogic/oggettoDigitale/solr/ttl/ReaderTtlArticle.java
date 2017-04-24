/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl;

import java.util.Hashtable;

/**
 * @author massi
 *
 */
public class ReaderTtlArticle extends ReaderTtlJournal {

	private ReaderTtlJournal journal = null;

	private Hashtable<String, ReaderTtlPerson> persons = null;
	
	/**
	 * 
	 */
	public ReaderTtlArticle() {
	}

	/**
	 * @return the journal
	 */
	public ReaderTtlJournal getJournal() {
		return journal;
	}

	/**
	 * @param journal the journal to set
	 */
	public void setJournal(ReaderTtlJournal journal) {
		this.journal = journal;
	}

	/**
	 * @return the persons
	 */
	public ReaderTtlPerson getPersons(String id) {
		if (persons ==null){
			return null;
		} else {
			return persons.get(id);
		}
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(String id, ReaderTtlPerson person) {
		if (persons ==null){
			persons = new Hashtable<String, ReaderTtlPerson>();
		}
		this.persons.put(id, person);
	}

}
