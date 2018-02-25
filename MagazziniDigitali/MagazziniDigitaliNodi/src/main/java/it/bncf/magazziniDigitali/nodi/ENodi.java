/**
 * 
 */
package it.bncf.magazziniDigitali.nodi;

/**
 * @author massi
 *
 */
public enum ENodi {
	TAR("tar"),
	PREMIS("premis")
	;
	
	private final String value;
	
	private ENodi(String value) {
		this.value=value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return value;
	}

	
}
