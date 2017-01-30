/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.ReaderTtl;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.ReaderTtlArticle;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.ReaderTtlJournal;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.ReaderTtlPerson;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.ttl.exception.ReaderTtlException;

/**
 * @author massi
 *
 */
public class ReaderTtlTest {

	/**
	 * 
	 */
	public ReaderTtlTest() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReaderTtlTest readerTtl = null;
		File fTtl = null;
		
		if (args.length==1){
			fTtl = new File(args[0]);
			if (fTtl.exists()){
				readerTtl = new ReaderTtlTest();
				readerTtl.esegui(fTtl);
			} else {
				System.out.println("Il file ["+fTtl.getAbsolutePath()+"] non esiste");
			}
		} else {
			System.out.println("E' necessario indicare il file da analizzare");
		}

	}

	public void esegui(File fTtl){
		ReaderTtl readerTtl = null;

		try {
			readerTtl = new ReaderTtl(fTtl);
			
			System.out.println("Scheda Articolo\n");
			print(readerTtl.getArticle(), readerTtl.getArticle());
			System.out.println("\nScheda Padre\n");
			print(readerTtl.getArticle(), readerTtl.getArticle().getJournal());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ReaderTtlException e) {
			e.printStackTrace();
		}
	}

	private void print(ReaderTtlArticle article, ReaderTtlJournal object){
		print("id", object.getId(), article);
		print("bid", object.getBid(), article);
		print("titolo", object.getTitolo(), article);
		print("autore", object.getAutore(), article);
		print("pubblicazione", object.getPubblicazione(), article);
		print("soggetto", object.getSoggetto(), article);
		print("descrizione", object.getDescrizione(), article);
		print("contributo", object.getContributo(), article);
		print("data", object.getData(), article);
		print("tipoRisorsa", object.getTipoRisorsa(), article);
		print("formato", object.getFormato(), article);
		print("fonte", object.getFonte(), article);
		print("lingua", object.getLingua(), article);
		print("relazione", object.getRelazione(), article);
		print("copertura", object.getCopertura(), article);
		print("gestioneDiritti", object.getGestioneDiritti(), article);
		print("startPage", object.getStartPage(), article);
		print("endPage", object.getEndPage(), article);
	}

	private void print(String key, Vector<String> values, ReaderTtlArticle article) {
		if (values != null && values.size()>0){
			for (int x=0; x<values.size(); x++){
				print((x==0?key:"\t"), values.get(x), article);
			}
		}
	}

	private void print(String key, String value, ReaderTtlArticle article) {
		ReaderTtlPerson person = null;

		if (value != null && !value.trim().equals("")){
			System.out.println("\t"+key+": "+value);
			if (value.startsWith("<")){
				person = article.getPersons(value);
				if (person!= null){
					System.out.println("\t\tFamilyName: "+person.getFamilyName());
					System.out.println("\t\tGivenName: "+person.getGivenName());
					System.out.println("\t\tName: "+person.getName());
				}
			}
		}
	}
}
