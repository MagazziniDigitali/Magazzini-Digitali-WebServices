/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema;

/**
 * @author massi
 *
 */
public enum IdentifierType {
	ISBN("ISBN"),
	ISSN("ISSN"),
	DOI("DOI");

	private String type;

	IdentifierType(String type) {
        this.type = type;
    }

    public String value() {
        return type;
    }
}
