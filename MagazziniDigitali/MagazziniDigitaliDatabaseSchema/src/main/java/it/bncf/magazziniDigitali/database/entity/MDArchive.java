/**
 * 
 */
package it.bncf.magazziniDigitali.database.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author massi
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name="MDArchive")
public class MDArchive implements Serializable {

	@Id
	private String id;

	private MDFilesTmp idMdFilesTmp;

	private MDNodi idNodo;

	private Date dataStart;

	private Date dataEnd;

	private Boolean esito;

	public MDArchive() {
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
	 * @return the idFilesTmp
	 */
	public MDFilesTmp getIdMdFilesTmp() {
		return idMdFilesTmp;
	}

	/**
	 * @param idFilesTmp the idFilesTmp to set
	 */
	public void setIdMdFilesTmp(MDFilesTmp idMdFilesTmp) {
		this.idMdFilesTmp = idMdFilesTmp;
	}

	/**
	 * @return the idNodo
	 */
	public MDNodi getIdNodo() {
		return idNodo;
	}

	/**
	 * @param idNodo the idNodo to set
	 */
	public void setIdNodo(MDNodi idNodo) {
		this.idNodo = idNodo;
	}

	/**
	 * @return the dataStart
	 */
	public Date getDataStart() {
		return dataStart;
	}

	/**
	 * @param dataStart the dataStart to set
	 */
	public void setDataStart(Date dataStart) {
		this.dataStart = dataStart;
	}

	/**
	 * @return the dataEnd
	 */
	public Date getDataEnd() {
		return dataEnd;
	}

	/**
	 * @param dataEnd the dataEnd to set
	 */
	public void setDataEnd(Date dataEnd) {
		this.dataEnd = dataEnd;
	}

	/**
	 * @return the esito
	 */
	public Boolean getEsito() {
		return esito;
	}

	/**
	 * @param esito the esito to set
	 */
	public void setEsito(Boolean esito) {
		this.esito = esito;
	}
}
