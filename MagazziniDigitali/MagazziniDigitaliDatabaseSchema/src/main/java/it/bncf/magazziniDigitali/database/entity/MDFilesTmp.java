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
@Table(name="MDFilesTmp")
public class MDFilesTmp implements Serializable {

	@Id
	private String id;

	private MDIstituzione idIstituto;

	private String idIstitutoID;

	private MDSoftware idSoftware;

	private String idSoftwareID;

	private String nomeFile;

	private String sha1;

	private Date nomeFileMod;

	private MDStato stato;

	private Date trasfDataStart;

	private Date trasfDataEnd;

	private Boolean trasfEsito;
	
	private Date validDataStart;

	private Date validDataEnd;

	private Boolean validEsito;
	
	private String xmlMimeType;
	
	private Date decompDataStart;

	private Date decompDataEnd;

	private Boolean decompEsito;
	
	private Date publishDataStart;

	private Date publishDataEnd;

	private Boolean publishEsito;
	
	private Date copyPremisDataStart;

	private Date copyPremisDataEnd;

	private Boolean copyPremisEsito;
	
	private Date moveFileDataStart;

	private Date moveFileDataEnd;

	private Boolean moveFileEsito;

	private Date deleteLocalData;

	private Boolean deleteLocalEsito;

	private String premisFile;
	
	private Date archiveDataStart;

	private Date archiveDataEnd;

	private Boolean archiveEsito;

	private MDNodi idNodo;
	
	private Date indexDataStart;

	private Date indexDataEnd;

	private Boolean indexEsito;

	private String indexPremis;

	private String tarTmpFile;
	
	private String publishPremis;

	private String geoReplicaPremis;
	
	public MDFilesTmp() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MDIstituzione getIdIstituto() {
		return idIstituto;
	}

	public void setIdIstituto(MDIstituzione idIstituto) {
		this.idIstituto = idIstituto;
		if (idIstituto != null){
			idIstitutoID = idIstituto.getId();
		}
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getSha1() {
		return sha1;
	}

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	public Date getNomeFileMod() {
		return nomeFileMod;
	}

	public void setNomeFileMod(Date nomeFileMod) {
		this.nomeFileMod = nomeFileMod;
	}

	public MDStato getStato() {
		return stato;
	}

	public void setStato(MDStato stato) {
		this.stato = stato;
	}

	public Date getTrasfDataStart() {
		return trasfDataStart;
	}

	public void setTrasfDataStart(Date trasfDataStart) {
		this.trasfDataStart = trasfDataStart;
	}

	public Date getTrasfDataEnd() {
		return trasfDataEnd;
	}

	public void setTrasfDataEnd(Date trasfDataEnd) {
		this.trasfDataEnd = trasfDataEnd;
	}

	public Boolean getTrasfEsito() {
		return trasfEsito;
	}

	public void setTrasfEsito(Boolean trasfEsito) {
		this.trasfEsito = trasfEsito;
	}

	public Date getValidDataStart() {
		return validDataStart;
	}

	public void setValidDataStart(Date validDataStart) {
		this.validDataStart = validDataStart;
	}

	public Date getValidDataEnd() {
		return validDataEnd;
	}

	public void setValidDataEnd(Date validDataEnd) {
		this.validDataEnd = validDataEnd;
	}

	public Boolean getValidEsito() {
		return validEsito;
	}

	public void setValidEsito(Boolean validEsito) {
		this.validEsito = validEsito;
	}

	public String getXmlMimeType() {
		return xmlMimeType;
	}

	public void setXmlMimeType(String xmlMimeType) {
		this.xmlMimeType = xmlMimeType;
	}

	public Date getDecompDataStart() {
		return decompDataStart;
	}

	public void setDecompDataStart(Date decompDataStart) {
		this.decompDataStart = decompDataStart;
	}

	public Date getDecompDataEnd() {
		return decompDataEnd;
	}

	public void setDecompDataEnd(Date decompDataEnd) {
		this.decompDataEnd = decompDataEnd;
	}

	public Boolean getDecompEsito() {
		return decompEsito;
	}

	public void setDecompEsito(Boolean decompEsito) {
		this.decompEsito = decompEsito;
	}

	public Date getPublishDataStart() {
		return publishDataStart;
	}

	public void setPublishDataStart(Date publishDataStart) {
		this.publishDataStart = publishDataStart;
	}

	public Date getPublishDataEnd() {
		return publishDataEnd;
	}

	public void setPublishDataEnd(Date publishDataEnd) {
		this.publishDataEnd = publishDataEnd;
	}

	public Boolean getPublishEsito() {
		return publishEsito;
	}

	public void setPublishEsito(Boolean publishEsito) {
		this.publishEsito = publishEsito;
	}

	public Date getCopyPremisDataStart() {
		return copyPremisDataStart;
	}

	public void setCopyPremisDataStart(Date copyPremisDataStart) {
		this.copyPremisDataStart = copyPremisDataStart;
	}

	public Date getCopyPremisDataEnd() {
		return copyPremisDataEnd;
	}

	public void setCopyPremisDataEnd(Date copyPremisDataEnd) {
		this.copyPremisDataEnd = copyPremisDataEnd;
	}

	public Boolean getCopyPremisEsito() {
		return copyPremisEsito;
	}

	public void setCopyPremisEsito(Boolean copyPremisEsito) {
		this.copyPremisEsito = copyPremisEsito;
	}

	public Date getMoveFileDataStart() {
		return moveFileDataStart;
	}

	public void setMoveFileDataStart(Date moveFileDataStart) {
		this.moveFileDataStart = moveFileDataStart;
	}

	public Date getMoveFileDataEnd() {
		return moveFileDataEnd;
	}

	public void setMoveFileDataEnd(Date moveFileDataEnd) {
		this.moveFileDataEnd = moveFileDataEnd;
	}

	public Boolean getMoveFileEsito() {
		return moveFileEsito;
	}

	public void setMoveFileEsito(Boolean moveFileEsito) {
		this.moveFileEsito = moveFileEsito;
	}

	public Date getDeleteLocalData() {
		return deleteLocalData;
	}

	public void setDeleteLocalData(Date deleteLocalData) {
		this.deleteLocalData = deleteLocalData;
	}

	public Boolean getDeleteLocalEsito() {
		return deleteLocalEsito;
	}

	public void setDeleteLocalEsito(Boolean deleteLocalEsito) {
		this.deleteLocalEsito = deleteLocalEsito;
	}

	public String getPremisFile() {
		return premisFile;
	}

	public void setPremisFile(String premisFile) {
		this.premisFile = premisFile;
	}

	/**
	 * @return the archiveDataStart
	 */
	public Date getArchiveDataStart() {
		return archiveDataStart;
	}

	/**
	 * @param archiveDataStart the archiveDataStart to set
	 */
	public void setArchiveDataStart(Date archiveDataStart) {
		this.archiveDataStart = archiveDataStart;
	}

	/**
	 * @return the archiveDataEnd
	 */
	public Date getArchiveDataEnd() {
		return archiveDataEnd;
	}

	/**
	 * @param archiveDataEnd the archiveDataEnd to set
	 */
	public void setArchiveDataEnd(Date archiveDataEnd) {
		this.archiveDataEnd = archiveDataEnd;
	}

	/**
	 * @return the archiveEsito
	 */
	public Boolean getArchiveEsito() {
		return archiveEsito;
	}

	/**
	 * @param archiveEsito the archiveEsito to set
	 */
	public void setArchiveEsito(Boolean archiveEsito) {
		this.archiveEsito = archiveEsito;
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
	 * @return the indexDataStart
	 */
	public Date getIndexDataStart() {
		return indexDataStart;
	}

	/**
	 * @param indexDataStart the indexDataStart to set
	 */
	public void setIndexDataStart(Date indexDataStart) {
		this.indexDataStart = indexDataStart;
	}

	/**
	 * @return the indexDataEnd
	 */
	public Date getIndexDataEnd() {
		return indexDataEnd;
	}

	/**
	 * @param indexDataEnd the indexDataEnd to set
	 */
	public void setIndexDataEnd(Date indexDataEnd) {
		this.indexDataEnd = indexDataEnd;
	}

	/**
	 * @return the indexEsito
	 */
	public Boolean getIndexEsito() {
		return indexEsito;
	}

	/**
	 * @param indexEsito the indexEsito to set
	 */
	public void setIndexEsito(Boolean indexEsito) {
		this.indexEsito = indexEsito;
	}

	/**
	 * @return the indexPremis
	 */
	public String getIndexPremis() {
		return indexPremis;
	}

	/**
	 * @param indexPremis the indexPremis to set
	 */
	public void setIndexPremis(String indexPremis) {
		this.indexPremis = indexPremis;
	}

	/**
	 * @return the idSoftware
	 */
	public MDSoftware getIdSoftware() {
		return idSoftware;
	}

	/**
	 * @param idSoftware the idSoftware to set
	 */
	public void setIdSoftware(MDSoftware idSoftware) {
		this.idSoftware = idSoftware;
		if (idSoftware != null){
			idSoftwareID = idSoftware.getId();
		}
	}

	/**
	 * @return the idIstitutoID
	 */
	public String getIdIstitutoID() {
		return idIstitutoID;
	}

	/**
	 * @return the idSoftwareID
	 */
	public String getIdSoftwareID() {
		return idSoftwareID;
	}

	/**
	 * @return the tarTmpFile
	 */
	public String getTarTmpFile() {
		return tarTmpFile;
	}

	/**
	 * @param tarTmpFile the tarTmpFile to set
	 */
	public void setTarTmpFile(String tarTmpFile) {
		this.tarTmpFile = tarTmpFile;
	}

	/**
	 * @return the publishPremis
	 */
	public String getPublishPremis() {
		return publishPremis;
	}

	/**
	 * @param publishPremis the publishPremis to set
	 */
	public void setPublishPremis(String publishPremis) {
		this.publishPremis = publishPremis;
	}

	/**
	 * @return the geoReplicaPremis
	 */
	public String getGeoReplicaPremis() {
		return geoReplicaPremis;
	}

	/**
	 * @param geoReplicaPremis the geoReplicaPremis to set
	 */
	public void setGeoReplicaPremis(String geoReplicaPremis) {
		this.geoReplicaPremis = geoReplicaPremis;
	}

}
