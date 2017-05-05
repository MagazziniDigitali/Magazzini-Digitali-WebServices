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
	DOI("DOI"),
	URL("URL"),
	other("other");

	private String type;

	IdentifierType(String type) {
        this.type = type;
    }

    public String value() {
        return type;
    }
}
