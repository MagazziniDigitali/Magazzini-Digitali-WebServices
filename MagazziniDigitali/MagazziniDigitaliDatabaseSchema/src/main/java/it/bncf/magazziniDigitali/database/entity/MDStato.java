/**
 * 
 */
package it.bncf.magazziniDigitali.database.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author massi
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name="MDStato")
public class MDStato implements Serializable {

	@Id
	private String id;

	private String descrizione;

	private Integer sequenza;
	
	private String optGroup;

	public MDStato() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the sequenza
	 */
	public Integer getSequenza() {
		return sequenza;
	}

	/**
	 * @param sequenza the sequenza to set
	 */
	public void setSequenza(Integer sequenza) {
		this.sequenza = sequenza;
	}

	/**
	 * @return the optGroup
	 */
	public String getOptGroup() {
		return optGroup;
	}

	/**
	 * @param optGroup the optGroup to set
	 */
	public void setOptGroup(String optGroup) {
		this.optGroup = optGroup;
	}
}
