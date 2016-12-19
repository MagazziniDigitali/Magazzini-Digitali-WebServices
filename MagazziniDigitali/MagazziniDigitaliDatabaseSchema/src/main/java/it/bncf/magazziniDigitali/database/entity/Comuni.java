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
@Table(name="comuni")
public class Comuni implements Serializable {

	@Id
	private Integer id;

	private String comune;

	private Province idProvinca;

	private Integer prefisso;

	private String cap;

	private String codFisco;

	private Integer abitanti;

	private String link;

	public Comuni() {
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
	 * @return the comune
	 */
	public String getComune() {
		return comune;
	}

	/**
	 * @param comune the comune to set
	 */
	public void setComune(String comune) {
		this.comune = comune;
	}

	/**
	 * @return the idProvinca
	 */
	public Province getIdProvinca() {
		return idProvinca;
	}

	/**
	 * @param idProvinca the idProvinca to set
	 */
	public void setIdProvinca(Province idProvinca) {
		this.idProvinca = idProvinca;
	}

	/**
	 * @return the prefisso
	 */
	public Integer getPrefisso() {
		return prefisso;
	}

	/**
	 * @param prefisso the prefisso to set
	 */
	public void setPrefisso(Integer prefisso) {
		this.prefisso = prefisso;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the codFisco
	 */
	public String getCodFisco() {
		return codFisco;
	}

	/**
	 * @param codFisco the codFisco to set
	 */
	public void setCodFisco(String codFisco) {
		this.codFisco = codFisco;
	}

	/**
	 * @return the abitanti
	 */
	public Integer getAbitanti() {
		return abitanti;
	}

	/**
	 * @param abitanti the abitanti to set
	 */
	public void setAbitanti(Integer abitanti) {
		this.abitanti = abitanti;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
