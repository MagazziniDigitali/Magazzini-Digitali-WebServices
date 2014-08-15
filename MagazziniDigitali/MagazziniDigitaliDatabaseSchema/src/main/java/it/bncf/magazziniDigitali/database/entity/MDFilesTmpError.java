/**
 * 
 */
package it.bncf.magazziniDigitali.database.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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

	private Timestamp dataIns;

	private String type;

	private String MsgError;
	
	
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


	public Timestamp getDataIns() {
		return dataIns;
	}


	public void setDataIns(Timestamp dataIns) {
		this.dataIns = dataIns;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMsgError() {
		return MsgError;
	}


	public void setMsgError(String msgError) {
		MsgError = msgError;
	}
}
