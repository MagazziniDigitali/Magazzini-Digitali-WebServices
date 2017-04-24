/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema;

import java.io.Serializable;

/**
 * @author massi
 *
 */
public class Creator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7400148176784248636L;

	private CreatorType type = null;

	private String name = null;

	/**
	 * 
	 */
	public Creator() {
	}

	/**
	 * @return the type
	 */
	public CreatorType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CreatorType type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
