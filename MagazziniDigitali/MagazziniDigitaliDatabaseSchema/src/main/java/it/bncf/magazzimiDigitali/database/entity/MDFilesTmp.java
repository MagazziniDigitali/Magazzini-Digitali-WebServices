/**
 * 
 */
package it.bncf.magazzimiDigitali.database.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author massi
 *
 */
@Entity
public class MDFilesTmp {

	@Id
	private String id;

	private String idIstituto;

	private String nomeFile;

	private String sha1;

	private String nomeFileMod;

	private String stato;

	private String trasfDataStart;

	private String trasfDataEnd;

	private boolean trasfEsito;
	
	private String validDataStart;
	
	private String decompDataStart;

	private String decompDataEnd;

	private boolean decompEsito;

	private String validDataEnd;

	private boolean validEsito;
	
	private String xmlMimeType;
	
	private String publishDataStart;

	private String publishDataEnd;

	private boolean publishEsito;
	
	private String copyPremisDataStart;

	private String copyPremisDataEnd;

	private boolean copyPremisEsito;
	
	private String moveFileDataStart;

	private String moveFileDataEnd;

	private boolean moveFileEsito;

	private String deleteLocalData;

	private boolean deleteLocalEsito;

	private String premisFile;

	private List<MDFilesTmpError> errors;

	public MDFilesTmp() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdIstituto() {
		return idIstituto;
	}

	public void setIdIstituto(String idIstituto) {
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

	public String getNomeFileMod() {
		return nomeFileMod;
	}

	public void setNomeFileMod(String nomeFileMod) {
		this.nomeFileMod = nomeFileMod;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTrasfDataStart() {
		return trasfDataStart;
	}

	public void setTrasfDataStart(String trasfDataStart) {
		this.trasfDataStart = trasfDataStart;
	}

	public String getTrasfDataEnd() {
		return trasfDataEnd;
	}

	public void setTrasfDataEnd(String trasfDataEnd) {
		this.trasfDataEnd = trasfDataEnd;
	}

	public String getValidDataStart() {
		return validDataStart;
	}

	public void setValidDataStart(String validDataStart) {
		this.validDataStart = validDataStart;
	}

	public String getValidDataEnd() {
		return validDataEnd;
	}

	public void setValidDataEnd(String validDataEnd) {
		this.validDataEnd = validDataEnd;
	}

	public boolean isTrasfEsito() {
		return trasfEsito;
	}

	public void setTrasfEsito(boolean trasfEsito) {
		this.trasfEsito = trasfEsito;
	}

	public boolean isValidEsito() {
		return validEsito;
	}

	public void setValidEsito(boolean validEsito) {
		this.validEsito = validEsito;
	}

	public List<MDFilesTmpError> getErrors() {
		return errors;
	}

	public void addErrors(MDFilesTmpError error) {
		if (this.errors == null){
			this.errors= new Vector<MDFilesTmpError>();
		}
		
		this.errors.add(error);
	}

	public String getXmlMimeType() {
		return xmlMimeType;
	}

	public void setXmlMimeType(String xmlMimeType) {
		this.xmlMimeType = xmlMimeType;
	}

	public String getDecompDataStart() {
		return decompDataStart;
	}

	public void setDecompDataStart(String decompDataStart) {
		this.decompDataStart = decompDataStart;
	}

	public String getDecompDataEnd() {
		return decompDataEnd;
	}

	public void setDecompDataEnd(String decompDataEnd) {
		this.decompDataEnd = decompDataEnd;
	}

	public boolean isDecompEsito() {
		return decompEsito;
	}

	public void setDecompEsito(boolean decompEsito) {
		this.decompEsito = decompEsito;
	}

	public String getPremisFile() {
		return premisFile;
	}

	public void setPremisFile(String premisFile) {
		this.premisFile = premisFile;
	}

	public String getPublishDataStart() {
		return publishDataStart;
	}

	public void setPublishDataStart(String publishDataStart) {
		this.publishDataStart = publishDataStart;
	}

	public String getPublishDataEnd() {
		return publishDataEnd;
	}

	public void setPublishDataEnd(String publishDataEnd) {
		this.publishDataEnd = publishDataEnd;
	}

	public boolean isPublishEsito() {
		return publishEsito;
	}

	public void setPublishEsito(boolean publishEsito) {
		this.publishEsito = publishEsito;
	}

	public String getCopyPremisDataStart() {
		return copyPremisDataStart;
	}

	public void setCopyPremisDataStart(String copyPremisDataStart) {
		this.copyPremisDataStart = copyPremisDataStart;
	}

	public String getCopyPremisDataEnd() {
		return copyPremisDataEnd;
	}

	public void setCopyPremisDataEnd(String copyPremisDataEnd) {
		this.copyPremisDataEnd = copyPremisDataEnd;
	}

	public boolean isCopyPremisEsito() {
		return copyPremisEsito;
	}

	public void setCopyPremisEsito(boolean copyPremisEsito) {
		this.copyPremisEsito = copyPremisEsito;
	}

	public String getMoveFileDataStart() {
		return moveFileDataStart;
	}

	public void setMoveFileDataStart(String moveFileDataStart) {
		this.moveFileDataStart = moveFileDataStart;
	}

	public String getMoveFileDataEnd() {
		return moveFileDataEnd;
	}

	public void setMoveFileDataEnd(String moveFileDataEnd) {
		this.moveFileDataEnd = moveFileDataEnd;
	}

	public boolean isMoveFileEsito() {
		return moveFileEsito;
	}

	public void setMoveFileEsito(boolean moveFileEsito) {
		this.moveFileEsito = moveFileEsito;
	}

	public String getDeleteLocalData() {
		return deleteLocalData;
	}

	public void setDeleteLocalData(String deleteLocalData) {
		this.deleteLocalData = deleteLocalData;
	}

	public boolean isDeleteLocalEsito() {
		return deleteLocalEsito;
	}

	public void setDeleteLocalEsito(boolean deleteLocalEsito) {
		this.deleteLocalEsito = deleteLocalEsito;
	}

}
