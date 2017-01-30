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
@Table(name="MDTicket")
public class MDTicket implements Serializable {

	@Id
	private String id;

	private String objectIdentifier;

	private Timestamp dataStart;

	private Timestamp dataEnd;

	private String modalitaAccesso;

	private String  idRights;

	private MDIstituzione idIstituzione;

	private String loginUtente;

	private String ipClient;

	private String actualFileName;

	private String originalFileName;

	public MDTicket() {
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
	 * @return the objectIdentifier
	 */
	public String getObjectIdentifier() {
		return objectIdentifier;
	}

	/**
	 * @param objectIdentifier the objectIdentifier to set
	 */
	public void setObjectIdentifier(String objectIdentifier) {
		this.objectIdentifier = objectIdentifier;
	}

	/**
	 * @return the dataStart
	 */
	public Timestamp getDataStart() {
		return dataStart;
	}

	/**
	 * @param dataStart the dataStart to set
	 */
	public void setDataStart(Timestamp dataStart) {
		this.dataStart = dataStart;
	}

	/**
	 * @return the dataEnd
	 */
	public Timestamp getDataEnd() {
		return dataEnd;
	}

	/**
	 * @param dataEnd the dataEnd to set
	 */
	public void setDataEnd(Timestamp dataEnd) {
		this.dataEnd = dataEnd;
	}

	/**
	 * @return the modalitaAccesso
	 */
	public String getModalitaAccesso() {
		return modalitaAccesso;
	}

	/**
	 * @param modalitaAccesso the modalitaAccesso to set
	 */
	public void setModalitaAccesso(String modalitaAccesso) {
		this.modalitaAccesso = modalitaAccesso;
	}

	/**
	 * @return the idRights
	 */
	public String getIdRights() {
		return idRights;
	}

	/**
	 * @param idRights the idRights to set
	 */
	public void setIdRights(String idRights) {
		this.idRights = idRights;
	}

	/**
	 * @return the idIstituzione
	 */
	public MDIstituzione getIdIstituzione() {
		return idIstituzione;
	}

	/**
	 * @param idIstituzione the idIstituzione to set
	 */
	public void setIdIstituzione(MDIstituzione idIstituzione) {
		this.idIstituzione = idIstituzione;
	}

	/**
	 * @return the loginUtente
	 */
	public String getLoginUtente() {
		return loginUtente;
	}

	/**
	 * @param loginUtente the loginUtente to set
	 */
	public void setLoginUtente(String loginUtente) {
		this.loginUtente = loginUtente;
	}

	/**
	 * @return the ipClient
	 */
	public String getIpClient() {
		return ipClient;
	}

	/**
	 * @param ipClient the ipClient to set
	 */
	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}

	/**
	 * @return the actualFileName
	 */
	public String getActualFileName() {
		return actualFileName;
	}

	/**
	 * @param actualFileName the actualFileName to set
	 */
	public void setActualFileName(String actualFileName) {
		this.actualFileName = actualFileName;
	}

	/**
	 * @return the originalFileName
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}

	/**
	 * @param originalFileName the originalFileName to set
	 */
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
}
