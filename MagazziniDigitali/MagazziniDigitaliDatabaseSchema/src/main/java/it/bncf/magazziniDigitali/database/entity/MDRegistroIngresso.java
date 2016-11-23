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
@Table(name="MDRegistroIngresso")
public class MDRegistroIngresso implements Serializable {

	@Id
	private String id;

	private Date timestampIngest;

	private String agentDepositor;

	private String originalContainerName;

	private String containerName;

	private String containerFingerPrint;

	private String containerFingerPrintChain;

	private Integer containerType;

	private String agentMachineIngest;

	private String agentSoftwareIngest;

	private Integer status;

	private Date timestampInit;

	private Date timestampElab;

	private Date timestampPub;

	private Date timestampErr;

	private Date timestampCoda;
	
	/**
	 * 
	 */
	public MDRegistroIngresso() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestampIngest() {
		return timestampIngest;
	}

	public void setTimestampIngest(Date timestampIngest) {
		this.timestampIngest = timestampIngest;
	}

	public String getAgentDepositor() {
		return agentDepositor;
	}

	public void setAgentDepositor(String agentDepositor) {
		this.agentDepositor = agentDepositor;
	}

	public String getOriginalContainerName() {
		return originalContainerName;
	}

	public void setOriginalContainerName(String originalContainerName) {
		this.originalContainerName = originalContainerName;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getContainerFingerPrint() {
		return containerFingerPrint;
	}

	public void setContainerFingerPrint(String containerFingerPrint) {
		this.containerFingerPrint = containerFingerPrint;
	}

	public String getContainerFingerPrintChain() {
		return containerFingerPrintChain;
	}

	public void setContainerFingerPrintChain(String containerFingerPrintChain) {
		this.containerFingerPrintChain = containerFingerPrintChain;
	}

	public Integer getContainerType() {
		return containerType;
	}

	public void setContainerType(Integer containerType) {
		this.containerType = containerType;
	}

	public String getAgentMachineIngest() {
		return agentMachineIngest;
	}

	public void setAgentMachineIngest(String agentMachineIngest) {
		this.agentMachineIngest = agentMachineIngest;
	}

	public String getAgentSoftwareIngest() {
		return agentSoftwareIngest;
	}

	public void setAgentSoftwareIngest(String agentSoftwareIngest) {
		this.agentSoftwareIngest = agentSoftwareIngest;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getTimestampInit() {
		return timestampInit;
	}

	public void setTimestampInit(Date timestampInit) {
		this.timestampInit = timestampInit;
	}

	public Date getTimestampElab() {
		return timestampElab;
	}

	public void setTimestampElab(Date timestampElab) {
		this.timestampElab = timestampElab;
	}

	public Date getTimestampPub() {
		return timestampPub;
	}

	public void setTimestampPub(Date timestampPub) {
		this.timestampPub = timestampPub;
	}

	public Date getTimestampErr() {
		return timestampErr;
	}

	public void setTimestampErr(Date timestampErr) {
		this.timestampErr = timestampErr;
	}

	/**
	 * @return the timestampCoda
	 */
	public Date getTimestampCoda() {
		return timestampCoda;
	}

	/**
	 * @param timestampCoda the timestampCoda to set
	 */
	public void setTimestampCoda(Date timestampCoda) {
		this.timestampCoda = timestampCoda;
	}
}
