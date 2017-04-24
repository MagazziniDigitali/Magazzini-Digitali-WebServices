/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.json.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.everit.json.schema.ValidationException;
import org.json.JSONException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.json.JsonValidator;

/**
 * @author massi
 *
 */
public class JsonValidatorTest {

	/**
	 * 
	 */
	public JsonValidatorTest() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonValidator jsonValidatorTest = null;
		
		
		try {
			jsonValidatorTest = new JsonValidator();
			System.out.println("schema: "+args[0]);
			System.out.println("file: "+args[1]);
			jsonValidatorTest.validate(args[0], args[1]);
			System.out.println("Fine");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
