/**
 * 
 */
package it.bncf.magazzimiDigitali.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author massi
 *
 */
@Entity
public class MDFilesTmpError {

	@Id
	private String id;

	private String idMdFilesTmp;

	private String dataIns;

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

	public String getIdMdFilesTmp() {
		return idMdFilesTmp;
	}

	public void setIdMdFilesTmp(String idMdFilesTmp) {
		this.idMdFilesTmp = idMdFilesTmp;
	}

	public String getDataIns() {
		return dataIns;
	}

	public void setDataIns(String dataIns) {
		this.dataIns = dataIns;
	}

	public String getMsgError() {
		return MsgError;
	}

	public void setMsgError(String msgError) {
		MsgError = msgError;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
