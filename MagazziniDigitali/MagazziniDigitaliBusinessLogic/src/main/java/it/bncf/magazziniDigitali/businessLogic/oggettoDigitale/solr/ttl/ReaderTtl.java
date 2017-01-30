/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.exception.ReaderTtlException;

/**
 * @author massi
 *
 */
public class ReaderTtl {

	private ReaderTtlArticle article = null;
	
	/**
	 * @throws FileNotFoundException, IOException 
	 * @throws ReaderTtlException 
	 * 
	 */
	public ReaderTtl(File fTtl) throws FileNotFoundException, IOException, ReaderTtlException {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		Vector<String> lines = null;
		String id = null;

		try {
			fr = new FileReader(fTtl);
			br = new BufferedReader(fr);
			
			while((line=br.readLine()) != null){
				
				if (!line.startsWith("@")){
					if (line.startsWith("<")){
						if (id != null){
							analyze(id, lines);
						}
						lines = null;
						id = line;
					} else {
						line = line.trim();
						if (!line.equals("")){
							if (lines ==null){
								lines = new Vector<String>();
							}
							if (lines.size()==0 ||
									(lines.get(lines.size()-1).endsWith(".") || 
											lines.get(lines.size()-1).endsWith(";"))){
								lines.add(line);
							} else {
								lines.set(lines.size()-1, lines.get(lines.size()-1)+" "+line);
							}
						}
					}
				}
			}
			if (id != null){
				analyze(id, lines);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (ReaderTtlException e) {
			throw e;
		} finally {
			try {
				if (fr != null){
					fr.close();
				}
				if (br != null){
					br.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	private void analyze(String id, Vector<String> lines) throws ReaderTtlException {
		if (lines.get(0).indexOf("foaf:Person") >-1){
			analyzePerson(id, lines);
		} else if (lines.get(0).indexOf("bibo:Journal") >-1){
			analyzeJournal(id, lines);
		} else if (lines.get(0).indexOf("bibo:Article") >-1){
			analyzeArticle(id, lines);
		} else {
			throw new ReaderTtlException("La tipologia ["+lines.get(0).trim()+"] per il tracciato TTL non implementato");
		}
	}

	private void analyzeArticle(String id, Vector<String> lines) {
		if (article ==null){
			article = new ReaderTtlArticle();
		}
		article.analyze(id, lines);
	}

	private void analyzeJournal(String id, Vector<String> lines) {
		ReaderTtlJournal journal = null;
		
		if (article ==null){
			article = new ReaderTtlArticle();
		}
		journal = new ReaderTtlJournal();
		journal.analyze(id, lines);
		article.setJournal(journal);
	}

	private void analyzePerson(String id, Vector<String> lines) {
		ReaderTtlPerson person = null;
		
		if (article ==null){
			article = new ReaderTtlArticle();
		}
		person = new ReaderTtlPerson();
		person.analyze(id, lines);
		article.setPersons(id, person);
	}

	/**
	 * @return the article
	 */
	public ReaderTtlArticle getArticle() {
		return article;
	}

}
