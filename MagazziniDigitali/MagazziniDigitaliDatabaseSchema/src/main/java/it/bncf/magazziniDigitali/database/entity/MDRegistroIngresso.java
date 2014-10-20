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
@Table(name="MDRegistroIngresso")
public class MDRegistroIngresso implements Serializable {

	@Id
	private String id;

	private Timestamp timestampIngest;

	private String agentDepositor;

	private String originalContainerName;

	private String containerName;

	private String containerFingerPrint;

	private String containerFingerPrintChain;

	private Integer containerType;

	private String agentMachineIngest;

	private String agentSoftwareIngest;

	private Integer status;

	private Timestamp timestampInit;

	private Timestamp timestampElab;

	private Timestamp timestampPub;

	private Timestamp timestampErr;

	private Timestamp timestampCoda;
	
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

	public Timestamp getTimestampIngest() {
		return timestampIngest;
	}

	public void setTimestampIngest(Timestamp timestampIngest) {
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

	public Timestamp getTimestampInit() {
		return timestampInit;
	}

	public void setTimestampInit(Timestamp timestampInit) {
		this.timestampInit = timestampInit;
	}

	public Timestamp getTimestampElab() {
		return timestampElab;
	}

	public void setTimestampElab(Timestamp timestampElab) {
		this.timestampElab = timestampElab;
	}

	public Timestamp getTimestampPub() {
		return timestampPub;
	}

	public void setTimestampPub(Timestamp timestampPub) {
		this.timestampPub = timestampPub;
	}

	public Timestamp getTimestampErr() {
		return timestampErr;
	}

	public void setTimestampErr(Timestamp timestampErr) {
		this.timestampErr = timestampErr;
	}

	/**
	 * @return the timestampCoda
	 */
	public Timestamp getTimestampCoda() {
		return timestampCoda;
	}

	/**
	 * @param timestampCoda the timestampCoda to set
	 */
	public void setTimestampCoda(Timestamp timestampCoda) {
		this.timestampCoda = timestampCoda;
	}
}
