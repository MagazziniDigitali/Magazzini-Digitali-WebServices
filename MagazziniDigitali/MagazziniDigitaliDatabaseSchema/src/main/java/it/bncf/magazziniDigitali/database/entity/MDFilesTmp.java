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
@Table(name="MDFilesTmp")
public class MDFilesTmp implements Serializable {

	@Id
	private String id;

	private MDIstituzione idIstituto;

	private String idIstitutoID;

	private MDSoftware idSoftware;

	private String idSoftwareID;

	private String nomeFile;

	private String md5;

	private String md564base;

	private String sha1;

	private String sha164base;

	private String sha256;

	private String sha25664base;

	private Timestamp nomeFileMod;

	private MDStato stato;

	private Timestamp trasfDataStart;

	private Timestamp trasfDataEnd;

	private Boolean trasfEsito;
	
	private Timestamp validDataStart;

	private Timestamp validDataEnd;

	private Boolean validEsito;
	
	private String xmlMimeType;
	
	private Timestamp decompDataStart;

	private Timestamp decompDataEnd;

	private Boolean decompEsito;
	
	private Timestamp publishDataStart;

	private Timestamp publishDataEnd;

	private Boolean publishEsito;
	
	private Timestamp copyPremisDataStart;

	private Timestamp copyPremisDataEnd;

	private Boolean copyPremisEsito;
	
	private Timestamp moveFileDataStart;

	private Timestamp moveFileDataEnd;

	private Boolean moveFileEsito;

	private Timestamp deleteLocalData;

	private Boolean deleteLocalEsito;

	private String premisFile;
	
	private Timestamp archiveDataStart;

	private Timestamp archiveDataEnd;

	private Boolean archiveEsito;

	private MDNodi idNodo;
	
	private Timestamp indexDataStart;

	private Timestamp indexDataEnd;

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

	public Timestamp getNomeFileMod() {
		return nomeFileMod;
	}

	public void setNomeFileMod(Timestamp nomeFileMod) {
		this.nomeFileMod = nomeFileMod;
	}

	public MDStato getStato() {
		return stato;
	}

	public void setStato(MDStato stato) {
		this.stato = stato;
	}

	public Timestamp getTrasfDataStart() {
		return trasfDataStart;
	}

	public void setTrasfDataStart(Timestamp trasfDataStart) {
		this.trasfDataStart = trasfDataStart;
	}

	public Timestamp getTrasfDataEnd() {
		return trasfDataEnd;
	}

	public void setTrasfDataEnd(Timestamp trasfDataEnd) {
		this.trasfDataEnd = trasfDataEnd;
	}

	public Boolean getTrasfEsito() {
		return trasfEsito;
	}

	public void setTrasfEsito(Boolean trasfEsito) {
		this.trasfEsito = trasfEsito;
	}

	public Timestamp getValidDataStart() {
		return validDataStart;
	}

	public void setValidDataStart(Timestamp validDataStart) {
		this.validDataStart = validDataStart;
	}

	public Timestamp getValidDataEnd() {
		return validDataEnd;
	}

	public void setValidDataEnd(Timestamp validDataEnd) {
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

	public Timestamp getDecompDataStart() {
		return decompDataStart;
	}

	public void setDecompDataStart(Timestamp decompDataStart) {
		this.decompDataStart = decompDataStart;
	}

	public Timestamp getDecompDataEnd() {
		return decompDataEnd;
	}

	public void setDecompDataEnd(Timestamp decompDataEnd) {
		this.decompDataEnd = decompDataEnd;
	}

	public Boolean getDecompEsito() {
		return decompEsito;
	}

	public void setDecompEsito(Boolean decompEsito) {
		this.decompEsito = decompEsito;
	}

	public Timestamp getPublishDataStart() {
		return publishDataStart;
	}

	public void setPublishDataStart(Timestamp publishDataStart) {
		this.publishDataStart = publishDataStart;
	}

	public Timestamp getPublishDataEnd() {
		return publishDataEnd;
	}

	public void setPublishDataEnd(Timestamp publishDataEnd) {
		this.publishDataEnd = publishDataEnd;
	}

	public Boolean getPublishEsito() {
		return publishEsito;
	}

	public void setPublishEsito(Boolean publishEsito) {
		this.publishEsito = publishEsito;
	}

	public Timestamp getCopyPremisDataStart() {
		return copyPremisDataStart;
	}

	public void setCopyPremisDataStart(Timestamp copyPremisDataStart) {
		this.copyPremisDataStart = copyPremisDataStart;
	}

	public Timestamp getCopyPremisDataEnd() {
		return copyPremisDataEnd;
	}

	public void setCopyPremisDataEnd(Timestamp copyPremisDataEnd) {
		this.copyPremisDataEnd = copyPremisDataEnd;
	}

	public Boolean getCopyPremisEsito() {
		return copyPremisEsito;
	}

	public void setCopyPremisEsito(Boolean copyPremisEsito) {
		this.copyPremisEsito = copyPremisEsito;
	}

	public Timestamp getMoveFileDataStart() {
		return moveFileDataStart;
	}

	public void setMoveFileDataStart(Timestamp moveFileDataStart) {
		this.moveFileDataStart = moveFileDataStart;
	}

	public Timestamp getMoveFileDataEnd() {
		return moveFileDataEnd;
	}

	public void setMoveFileDataEnd(Timestamp moveFileDataEnd) {
		this.moveFileDataEnd = moveFileDataEnd;
	}

	public Boolean getMoveFileEsito() {
		return moveFileEsito;
	}

	public void setMoveFileEsito(Boolean moveFileEsito) {
		this.moveFileEsito = moveFileEsito;
	}

	public Timestamp getDeleteLocalData() {
		return deleteLocalData;
	}

	public void setDeleteLocalData(Timestamp deleteLocalData) {
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
	public Timestamp getArchiveDataStart() {
		return archiveDataStart;
	}

	/**
	 * @param archiveDataStart the archiveDataStart to set
	 */
	public void setArchiveDataStart(Timestamp archiveDataStart) {
		this.archiveDataStart = archiveDataStart;
	}

	/**
	 * @return the archiveDataEnd
	 */
	public Timestamp getArchiveDataEnd() {
		return archiveDataEnd;
	}

	/**
	 * @param archiveDataEnd the archiveDataEnd to set
	 */
	public void setArchiveDataEnd(Timestamp archiveDataEnd) {
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
	public Timestamp getIndexDataStart() {
		return indexDataStart;
	}

	/**
	 * @param indexDataStart the indexDataStart to set
	 */
	public void setIndexDataStart(Timestamp indexDataStart) {
		this.indexDataStart = indexDataStart;
	}

	/**
	 * @return the indexDataEnd
	 */
	public Timestamp getIndexDataEnd() {
		return indexDataEnd;
	}

	/**
	 * @param indexDataEnd the indexDataEnd to set
	 */
	public void setIndexDataEnd(Timestamp indexDataEnd) {
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

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5 the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the md564base
	 */
	public String getMd564base() {
		return md564base;
	}

	/**
	 * @param md564base the md564base to set
	 */
	public void setMd564base(String md564base) {
		this.md564base = md564base;
	}

	/**
	 * @return the sha164base
	 */
	public String getSha164base() {
		return sha164base;
	}

	/**
	 * @param sha164base the sha164base to set
	 */
	public void setSha164base(String sha164base) {
		this.sha164base = sha164base;
	}

	/**
	 * @return the sha256
	 */
	public String getSha256() {
		return sha256;
	}

	/**
	 * @param sha256 the sha256 to set
	 */
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}

	/**
	 * @return the sha25664base
	 */
	public String getSha25664base() {
		return sha25664base;
	}

	/**
	 * @param sha25664base the sha25664base to set
	 */
	public void setSha25664base(String sha25664base) {
		this.sha25664base = sha25664base;
	}

}
