/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema;

/**
 * @author massi
 *
 */
public enum CreatorType {
	author("author"),
	translator("translator");

	private String type;

	CreatorType(String type) {
        this.type = type;
    }

    public String value() {
        return type;
    }
}
