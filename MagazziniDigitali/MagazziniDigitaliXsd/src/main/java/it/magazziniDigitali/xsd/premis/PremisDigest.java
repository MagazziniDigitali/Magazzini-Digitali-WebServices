/**
 * 
 */
package it.magazziniDigitali.xsd.premis;

import info.lc.xmlns.premis_v2.CHECKSUMTYPEDefinition;

/**
 * @author massi
 *
 */
public class PremisDigest {

	private CHECKSUMTYPEDefinition algorithm = null;
	
	private String value = null;

	/**
	 * 
	 */
	public PremisDigest(CHECKSUMTYPEDefinition algorithm, String value) {
		this.algorithm = algorithm;
		this.value = value;
	}

	/**
	 * @return the algorithm
	 */
	public CHECKSUMTYPEDefinition getAlgorithm() {
		return algorithm;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
