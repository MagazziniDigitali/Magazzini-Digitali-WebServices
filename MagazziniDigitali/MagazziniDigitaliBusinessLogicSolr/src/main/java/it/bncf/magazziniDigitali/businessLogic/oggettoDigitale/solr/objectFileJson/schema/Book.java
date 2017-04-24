/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileJson.schema;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


/**
 * @author massi
 *
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 530197602929298224L;

	private String title = null;

	private String url = null;

	private String description = null;

	private String publisher = null;

	private String language = null;

	private String subject = null;

	private String rights = null;

	private String date = null;

	private List<Creator> creator = null;

	private List<Identifier> identifiers = null;

	/**
	 * 
	 */
	public Book() {
	}

	public String toJson(){
		return new Gson().toJson(this);
	}

	public static Book fromJson(File f) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		return new Gson().fromJson(new FileReader(f), Book.class);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return (title != null && title.trim().length()>0?title.trim():null);
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return (url != null && url.trim().length()>0?url.trim():null);
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return (description != null && description.trim().length()>0?description.trim():null);
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return (publisher != null && publisher.trim().length()>0?publisher.trim():null);
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the language
	 */
	public String[] getLanguage() {
		return (language != null && !language.trim().equals("")?language.split(","):null);
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the subject
	 */
	public String[] getSubject() {
		return (subject != null && !subject.trim().equals("")?subject.split(","):null);
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the rights
	 */
	public String getRights() {
		return (rights != null && rights.trim().length()>0?rights.trim():null);
	}
	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return (date != null && date.trim().length()>0?date.trim():null);
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the creator
	 */
	public List<Creator> getCreator() {
		return (creator != null && creator.size()>0?creator:null);
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(List<Creator> creator) {
		this.creator = creator;
	}
	/**
	 * @return the identifiers
	 */
	public List<Identifier> getIdentifiers() {
		return (identifiers != null && identifiers.size()>0?identifiers:null);
	}
	/**
	 * @param identifiers the identifiers to set
	 */
	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

}
