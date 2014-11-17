/**
 * 
 */
package it.bncf.magazziniDigitali.utils;

import java.util.Vector;

/**
 * @author massi
 *
 */
public class Records {

	private int recStart = 0;
	private int recStop = 0;
	private int recTot = 0;
	private Vector<Record> records = null;

	/**
	 * 
	 */
	public Records() {
	}

	/**
	 * @return the recStart
	 */
	public int getRecStart() {
		return recStart;
	}

	/**
	 * @param recStart the recStart to set
	 */
	public void setRecStart(int recStart) {
		this.recStart = recStart;
	}

	/**
	 * @return the recStop
	 */
	public int getRecStop() {
		return recStop;
	}

	/**
	 * @param recStop the recStop to set
	 */
	public void setRecStop(int recStop) {
		this.recStop = recStop;
	}

	/**
	 * @return the recTot
	 */
	public int getRecTot() {
		return recTot;
	}

	/**
	 * @param recTot the recTot to set
	 */
	public void setRecTot(int recTot) {
		this.recTot = recTot;
	}

	/**
	 * @return the records
	 */
	public Vector<Record> getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void addRecords(Record record) {
		if (this.records == null){
			records = new Vector<Record>();
		}
		this.records.add(record);
	}

}
