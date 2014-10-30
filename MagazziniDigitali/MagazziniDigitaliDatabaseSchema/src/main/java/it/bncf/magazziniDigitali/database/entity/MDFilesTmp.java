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

	private String nomeFile;

	private String sha1;

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

}
