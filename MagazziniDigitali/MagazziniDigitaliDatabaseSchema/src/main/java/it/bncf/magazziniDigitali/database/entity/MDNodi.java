/**
 * 
 */
package it.bncf.magazziniDigitali.database.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author massi
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name="MDNodi")
public class MDNodi implements Serializable {

	@Id
	private String id;

	private String nome;

	private String descrizione;

	private String tipo;

	private String rsync;

	private String rsyncPassword;

	private String urlCheckStorage;

	private String pathStorage;

	private String s3Url;

	private String s3Region;

	private String s3AccessKey;

	private String s3SecretKey;

	private String s3BucketName;
	
	private Integer active;

	private String code;
	
	public MDNodi() {
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the rsync
	 */
	public String getRsync() {
		return rsync;
	}

	/**
	 * @param rsync the rsync to set
	 */
	public void setRsync(String rsync) {
		this.rsync = rsync;
	}

	/**
	 * @return the rsyncPassword
	 */
	public String getRsyncPassword() {
		return rsyncPassword;
	}

	/**
	 * @param rsyncPassword the rsyncPassword to set
	 */
	public void setRsyncPassword(String rsyncPassword) {
		this.rsyncPassword = rsyncPassword;
	}

	/**
	 * @return the urlCheckStorage
	 */
	public String getUrlCheckStorage() {
		return urlCheckStorage;
	}

	/**
	 * @param urlCheckStorage the urlCheckStorage to set
	 */
	public void setUrlCheckStorage(String urlCheckStorage) {
		this.urlCheckStorage = urlCheckStorage;
	}

	/**
	 * @return the pathStorage
	 */
	public String getPathStorage() {
		return pathStorage;
	}

	/**
	 * @param pathStorage the pathStorage to set
	 */
	public void setPathStorage(String pathStorage) {
		this.pathStorage = pathStorage;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the s3Url
	 */
	public String getS3Url() {
		return s3Url;
	}

	/**
	 * @param s3Url the s3Url to set
	 */
	public void setS3Url(String s3Url) {
		this.s3Url = s3Url;
	}

	/**
	 * @return the s3Region
	 */
	public String getS3Region() {
		return s3Region;
	}

	/**
	 * @param s3Region the s3Region to set
	 */
	public void setS3Region(String s3Region) {
		this.s3Region = s3Region;
	}

	/**
	 * @return the s3AccessKey
	 */
	public String getS3AccessKey() {
		return s3AccessKey;
	}

	/**
	 * @param s3AccessKey the s3AccessKey to set
	 */
	public void setS3AccessKey(String s3AccessKey) {
		this.s3AccessKey = s3AccessKey;
	}

	/**
	 * @return the s3SecretKey
	 */
	public String getS3SecretKey() {
		return s3SecretKey;
	}

	/**
	 * @param s3SecretKey the s3SecretKey to set
	 */
	public void setS3SecretKey(String s3SecretKey) {
		this.s3SecretKey = s3SecretKey;
	}

	/**
	 * @return the s3BucketName
	 */
	public String getS3BucketName() {
		return s3BucketName;
	}

	/**
	 * @param s3BucketName the s3BucketName to set
	 */
	public void setS3BucketName(String s3BucketName) {
		this.s3BucketName = s3BucketName;
	}

}
