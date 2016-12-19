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
@Table(name="province")
public class Province implements Serializable {

	@Id
	private Integer id;

	private Regioni idRegione;

	private String nomeProvincia;

	private String siglaProvincia;

	public Province() {
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
	 * @return the idRegioni
	 */
	public Regioni getIdRegione() {
		return idRegione;
	}

	/**
	 * @param idRegioni the idRegioni to set
	 */
	public void setIdRegione(Regioni idRegione) {
		this.idRegione = idRegione;
	}

	/**
	 * @return the nomeProvincia
	 */
	public String getNomeProvincia() {
		return nomeProvincia;
	}

	/**
	 * @param nomeProvincia the nomeProvincia to set
	 */
	public void setNomeProvincia(String nomeProvincia) {
		this.nomeProvincia = nomeProvincia;
	}

	/**
	 * @return the siglaProvincia
	 */
	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	/**
	 * @param siglaProvincia the siglaProvincia to set
	 */
	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}
}
