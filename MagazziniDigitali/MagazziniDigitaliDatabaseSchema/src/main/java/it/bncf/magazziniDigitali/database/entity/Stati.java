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
@Table(name="stati")
public class Stati implements Serializable {

	@Id
	private Integer id;

	private String nomeStati;

	private String siglaNumericaStati;

	private String siglaIso31661Alpha3Stati;

	private String siglaIso31661Alpha2Stati;

	public Stati() {
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
	 * @return the nomeStati
	 */
	public String getNomeStati() {
		return nomeStati;
	}

	/**
	 * @param nomeStati the nomeStati to set
	 */
	public void setNomeStati(String nomeStati) {
		this.nomeStati = nomeStati;
	}

	/**
	 * @return the siglaNumericaStati
	 */
	public String getSiglaNumericaStati() {
		return siglaNumericaStati;
	}

	/**
	 * @param siglaNumericaStati the siglaNumericaStati to set
	 */
	public void setSiglaNumericaStati(String siglaNumericaStati) {
		this.siglaNumericaStati = siglaNumericaStati;
	}

	/**
	 * @return the siglaIso31661Alpha3Stati
	 */
	public String getSiglaIso31661Alpha3Stati() {
		return siglaIso31661Alpha3Stati;
	}

	/**
	 * @param siglaIso31661Alpha3Stati the siglaIso31661Alpha3Stati to set
	 */
	public void setSiglaIso31661Alpha3Stati(String siglaIso31661Alpha3Stati) {
		this.siglaIso31661Alpha3Stati = siglaIso31661Alpha3Stati;
	}

	/**
	 * @return the siglaIso31661Alpha2Stati
	 */
	public String getSiglaIso31661Alpha2Stati() {
		return siglaIso31661Alpha2Stati;
	}

	/**
	 * @param siglaIso31661Alpha2Stati the siglaIso31661Alpha2Stati to set
	 */
	public void setSiglaIso31661Alpha2Stati(String siglaIso31661Alpha2Stati) {
		this.siglaIso31661Alpha2Stati = siglaIso31661Alpha2Stati;
	}
}
