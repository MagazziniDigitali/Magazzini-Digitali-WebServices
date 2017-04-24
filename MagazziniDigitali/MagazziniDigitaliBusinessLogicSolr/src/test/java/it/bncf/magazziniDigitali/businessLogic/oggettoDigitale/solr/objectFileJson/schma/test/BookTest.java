/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schma.test;

import java.io.File;
import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema.Book;

/**
 * @author massi
 *
 */
public class BookTest {

	/**
	 * 
	 */
	public BookTest() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Book book = null;

		try {
			book = Book.fromJson(new File(args[0]));
			if (book.getTitle() != null) {
				System.out.println("Title: " + book.getTitle());
			}
			if (book.getUrl() != null) {
				System.out.println("Url: " + book.getUrl());
			}
			if (book.getDescription() != null) {
				System.out.println("Description: " + book.getDescription());
			}
			if (book.getPublisher() != null) {
				System.out.println("Publisher: " + book.getPublisher());
			}
			if (book.getLanguage() != null && book.getLanguage().length > 0) {
				System.out.println("Language:");
				for (int x = 0; x < book.getLanguage().length; x++) {
					System.out.println("\t" + book.getLanguage()[x].trim());
				}
			}
			if (book.getSubject() != null && book.getSubject().length > 0) {
				System.out.println("Subject:");
				for (int x = 0; x < book.getSubject().length; x++) {
					System.out.println("\t" + book.getSubject()[x].trim());
				}
			}
			if (book.getRights() != null) {
				System.out.println("Rights: " + book.getRights());
			}
			if (book.getDate() != null) {
				System.out.println("Date: " + book.getDate());
			}
			if (book.getCreator() != null && book.getCreator().size() > 0) {
				for (int x = 0; x < book.getCreator().size(); x++) {
					System.out.println("Creator:");
					if (book.getCreator().get(x).getType() != null) {
						System.out.println("\tType: " + book.getCreator().get(x).getType().value());
					}
					System.out.println("\tName: " + book.getCreator().get(x).getName());
				}
			}
			if (book.getIdentifiers() != null && book.getIdentifiers().size() > 0) {
				for (int x = 0; x < book.getIdentifiers().size(); x++) {
					System.out.println("Identifier:");
					if (book.getIdentifiers().get(x).getType() != null) {
						System.out.println("\tType: " + book.getIdentifiers().get(x).getType().value());
					}
					System.out.println("\tName: " + book.getIdentifiers().get(x).getID());
				}
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
