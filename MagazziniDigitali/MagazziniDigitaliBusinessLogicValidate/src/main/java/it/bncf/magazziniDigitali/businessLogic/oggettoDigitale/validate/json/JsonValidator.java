/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author massi
 *
 */
public class JsonValidator {

	private Logger log = Logger.getLogger(JsonValidator.class);

	/**
	 * 
	 */
	public JsonValidator() {
	}

	public void validate(String fSchema, String fJson)
			throws FileNotFoundException, JSONException, IOException, ValidationException {
		Schema schema = null;

		try {
			log.debug("\n"+"File Schema: "+fSchema);
			schema = SchemaLoader.load(readJson(fSchema));
			log.debug("\n"+"File Json:   "+fJson);
			schema.validate(readJson(fJson));
			log.debug("\n"+"File Validato: ");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (JSONException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (ValidationException e) {
			log.error(e.getMessage(), e);
			for(ValidationException ve :e.getCausingExceptions()){
				log.error(ve.getMessage(), ve);
			}
			throw e;
		}

	}

	private JSONObject readJson(String fileSchema) throws FileNotFoundException, JSONException, IOException {
		File fSchema = null;
		InputStream isSchema = null;
		JSONTokener jsonTokener = null;
		JSONObject jsonObject = null;

		try {
			fSchema = new File(fileSchema);
			isSchema = new FileInputStream(fSchema);
			jsonTokener = new JSONTokener(isSchema);
			jsonObject = new JSONObject(jsonTokener);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (JSONException e) {
			throw e;
		} finally {
			try {
				if (isSchema != null) {
					isSchema.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return jsonObject;
	}

}
