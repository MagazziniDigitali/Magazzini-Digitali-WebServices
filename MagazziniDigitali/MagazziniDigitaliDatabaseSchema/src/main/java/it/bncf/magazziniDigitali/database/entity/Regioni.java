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
@Table(name="regioni")
public class Regioni implements Serializable {

	@Id
	private Integer id;

	private String abb;

	private String nomeRegione;

	public Regioni() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the abb
	 */
	public String getAbb() {
		return abb;
	}

	/**
	 * @param abb the abb to set
	 */
	public void setAbb(String abb) {
		this.abb = abb;
	}

	/**
	 * @return the nomeRegione
	 */
	public String getNomeRegione() {
		return nomeRegione;
	}

	/**
	 * @param nomeRegione the nomeRegione to set
	 */
	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}
}
