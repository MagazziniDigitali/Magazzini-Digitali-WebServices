/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema;

import java.io.Serializable;

/**
 * @author massi
 *
 */
public class Identifier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3986139169125674672L;

	/**
	 * @return the type
	 */
	public IdentifierType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(IdentifierType type) {
		this.type = type;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	private IdentifierType type = null;

	private String ID =null;

	/**
	 * 
	 */
	public Identifier() {
	}

}
