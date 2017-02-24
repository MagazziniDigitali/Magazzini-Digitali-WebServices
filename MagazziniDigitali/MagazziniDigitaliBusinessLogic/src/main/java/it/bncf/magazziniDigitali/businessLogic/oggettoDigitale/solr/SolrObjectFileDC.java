/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.purl.dc.elements._1.SimpleLiteral;

import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.metadata.Metadata;
import it.magazziniDigitali.xsd.metadata.MetadataXsd;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class SolrObjectFileDC extends SolrObjectFileRegistro {

	/**
	 * 
	 */
	public SolrObjectFileDC() {
	}

	protected void publicSolrDc(File pathTar, AddDocumentMD admd) throws SolrException {
		File fName = null;
		MetadataXsd metadataXsd = null;
		Metadata metadata = null;
		List<JAXBElement<SimpleLiteral>> nodes = null;
		JAXBElement<SimpleLiteral> node = null;
		String output = null;
		
		try {
			fName = new java.io.File(pathTar.getAbsolutePath());
			if (fName.exists()){
				params.getParams().clear();
				params.add(ItemMD.ID, objectIdentifier+"-DC");
				params.add(ItemMD._ROOT_, objectIdentifier);
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DOCUMENTO);
				params.add(ItemMD.ORIGINALFILENAME, filename);
				
				metadataXsd = new MetadataXsd();
				
				output = read(fName);
				if (output.indexOf("xmlns=")== -1){
					output = output.replace("<metadata", 
							"<metadata xmlns=\"http://www.depositolegale.it/metadata\" xsi:schemaLocation=\"http://www.depositolegale.it/metadata metadata.xsd\"");
				}
				
				metadata = metadataXsd.read(output);
				
//				read(ItemMD.BID, metadata.getIdentifier());
//				read(ItemMD.TITOLO, metadata.getTitle());
//				read(ItemMD.AUTORE, metadata.getCreator());
//				read(ItemMD.PUBBLICAZIONE, metadata.getPublisher());
//				read(ItemMD.SOGGETTO, metadata.getSubject());
//				read(ItemMD.DESCRIZIONE, metadata.getDescription());
//				read(ItemMD.CONTRIBUTO, metadata.getContributor());
//				read(ItemMD.DATA, metadata.getDate());
//				read(ItemMD.TIPORISORSA, metadata.getType());
//				read(ItemMD.FORMATO, metadata.getFormat());
//				read(ItemMD.FONTE, metadata.getSource());
//				read(ItemMD.LINGUA, metadata.getLanguage());
//				read(ItemMD.RELAZIONE, metadata.getRelation());
//				read(ItemMD.COPERTURA, metadata.getCoverage());
//				read(ItemMD.GESTIONEDIRITTI, metadata.getRights());

				nodes = metadata.getAny();
				if (nodes != null){
					for (int x=0; x<nodes.size(); x++){
						node = nodes.get(x);
						switch (node.getName().getLocalPart()) {
						case "source":
							read(ItemMD.FONTE, node.getValue());
							break;
						case "coverage":
							read(ItemMD.COPERTURA, node.getValue());
							break;
						case "creator":
							read(ItemMD.AUTORE, node.getValue());
							break;
						case "relation":
							read(ItemMD.RELAZIONE, node.getValue());
							break;
						case "identifier":
							read(ItemMD.BID, node.getValue());
							break;
						case "description":
							read(ItemMD.DESCRIZIONE, node.getValue());
							break;
						case "date":
							read(ItemMD.DATA, node.getValue());
							break;
						case "contributor":
							read(ItemMD.CONTRIBUTO, node.getValue());
							break;
						case "format":
							read(ItemMD.FORMATO, node.getValue());
							break;
						case "type":
							read(ItemMD.TIPORISORSA, node.getValue());
							break;
						case "publisher":
							read(ItemMD.PUBBLICAZIONE, node.getValue());
							break;
						case "subject":
							read(ItemMD.SOGGETTO, node.getValue());
							break;
						case "title":
							read(ItemMD.TITOLO, node.getValue());
							break;
						case "language":
							read(ItemMD.LINGUA, node.getValue());
							break;
						case "rights":
							read(ItemMD.GESTIONEDIRITTI, node.getValue());
							break;

						default:
							break;
						}
					}
				}
				
				admd.add(params.getParams(), new ItemMD());
			}
		} catch (SolrException e) {
			throw e;
		} catch (XsdException e) {
			throw new SolrException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			throw new SolrException(e.getMessage(), e);
		} catch (IOException e) {
			throw new SolrException(e.getMessage(), e);
		}
	}

	private void read(String key, SimpleLiteral value) {
		if (value != null && value.getContent() != null){
			for(int y=0; y<value.getContent().size(); y++){
				read(key, value.getContent().get(y));
			}
		}
	}

	private String read(File f) throws FileNotFoundException, IOException{
		FileReader fr = null;
		BufferedReader br = null;
		String output = "";
		String line = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			while((line = br.readLine())!=null){
				output += line+"\n";
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally {
			try {
				if (br != null){
					br.close();
				}
				
				if (fr != null){
					fr.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return output;
	}
}
