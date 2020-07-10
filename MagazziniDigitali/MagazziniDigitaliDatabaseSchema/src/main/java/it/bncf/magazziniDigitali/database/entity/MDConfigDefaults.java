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
@Table(name="MDConfigDefaults")
public class MDConfigDefaults implements Serializable {

	@Id
	private String id;

	private String name;

	private String tipoIstituto;

	public MDConfigDefaults() {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTipoIstituto() {
    return tipoIstituto;
  }

  public void setTipoIstituto(String tipoIstituto) {
    this.tipoIstituto = tipoIstituto;
  }
}
