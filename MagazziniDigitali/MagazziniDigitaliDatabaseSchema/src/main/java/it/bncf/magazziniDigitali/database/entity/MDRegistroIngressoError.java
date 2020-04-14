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
@Table(name="MDRegistroIngressoError")
public class MDRegistroIngressoError implements Serializable {

	@Id
	private String id;

	private MDRegistroIngresso idMDRegistroIngresso;

	private Timestamp dataIns;

	private MDStato type;

	private String msgError;
	
	private String traceError;

	/**
	 * 
	 */
	public MDRegistroIngressoError() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MDRegistroIngresso getIdMDRegistroIngresso() {
		return idMDRegistroIngresso;
	}

	public void setIdMDRegistroIngresso(MDRegistroIngresso idMDRegistroIngresso) {
		this.idMDRegistroIngresso = idMDRegistroIngresso;
	}

	public Timestamp getDataIns() {
		return dataIns;
	}

	public void setDataIns(Timestamp dataIns) {
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
