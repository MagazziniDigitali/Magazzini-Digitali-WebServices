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
@Table(name="MDConfigDefaultsRow")
public class MDConfigDefaultsRow implements Serializable {

	@Id
	private String id;

	private MDConfigDefaults idConfigDefaults;

	private String idConfigDefaultsID;

	private String name;

	private String descrizione;

	private String value;

	public MDConfigDefaultsRow() {
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

  public MDConfigDefaults getIdConfigDefaults() {
    return idConfigDefaults;
  }

  public void setIdConfigDefaults(MDConfigDefaults idConfigDefaults) {
    this.idConfigDefaults = idConfigDefaults;
    if (this.idConfigDefaults != null) {
      this.idConfigDefaultsID = this.idConfigDefaults.getId();
    }
  }

  public String getIdConfigDefaultsID() {
    return idConfigDefaultsID;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
}
