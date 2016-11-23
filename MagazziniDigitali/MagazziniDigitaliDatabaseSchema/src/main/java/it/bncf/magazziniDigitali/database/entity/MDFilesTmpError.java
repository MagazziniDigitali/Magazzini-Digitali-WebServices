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
@Table(name="MDFilesTmpError")
public class MDFilesTmpError implements Serializable {

	@Id
	private String id;

	private MDFilesTmp idMdFilesTmp;

	private Date dataIns;

	private MDStato type;

	private String msgError;
	
	private String traceError;
	
	
	public MDFilesTmpError() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public MDFilesTmp getIdMdFilesTmp() {
		return idMdFilesTmp;
	}


	public void setIdMdFilesTmp(MDFilesTmp idMdFilesTmp) {
		this.idMdFilesTmp = idMdFilesTmp;
	}


	public Date getDataIns() {
		return dataIns;
	}


	public void setDataIns(Date dataIns) {
		this.dataIns = dataIns;
	}


	public MDStato getType() {
		return type;
	}


	public void setType(MDStato type) {
		this.type = type;
	}


	public String getMsgError() {
		return msgError;
	}


	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}


	/**
	 * @return the traceError
	 */
	public String getTraceError() {
		return traceError;
	}


	/**
	 * @param traceError the traceError to set
	 */
	public void setTraceError(String traceError) {
		this.traceError = traceError;
	}
}
