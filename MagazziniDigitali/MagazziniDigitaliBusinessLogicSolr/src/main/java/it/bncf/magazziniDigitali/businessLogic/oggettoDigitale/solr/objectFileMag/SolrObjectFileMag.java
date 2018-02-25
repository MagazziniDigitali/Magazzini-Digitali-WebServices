/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileMag;

import java.io.File;
import java.util.List;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFile.SolrObjectFile;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.md.BibliographicLevel;
import it.sbn.iccu.metaag1.Bib.Holdings;
import it.sbn.iccu.metaag1.Bib.Piece;
import it.sbn.iccu.metaag1.Metadigit;
import mx.randalf.mag.MagXsd;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class SolrObjectFileMag extends SolrObjectFile {

	/**
	 * 
	 */
	public SolrObjectFileMag() {
	}

	protected void publicSolrMag(File pathTar, AddDocumentMD admd, 
			IMDConfiguration<?> configuration) 
			throws XsdException, SolrException 
			//throws SolrException
	{
		File fName = null;
		MagXsd magXsd = null;
		Metadigit metadigit = null;

		try {
			fName = new java.io.File(pathTar.getAbsolutePath()+
					java.io.File.separator+ filename);
			if (fName.exists()){
				params.getParams().clear();
				params.add(ItemMD.ID, objectIdentifier+"-DC");
				params.add(ItemMD._ROOT_, objectIdentifier);
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DOCUMENTO);
				params.add(ItemMD.ORIGINALFILENAME, filename);
				
				magXsd = new MagXsd();
				metadigit = magXsd.read(fName);
				
				if (metadigit.getBib() != null){
					if (metadigit.getBib().getLevel() != null){
						params.add(ItemMD.TIPODOCUMENTO, 
								(metadigit.getBib().getLevel().value().equals(BibliographicLevel.M.value())
										?ItemMD.TIPODOCUMENTO_LIBRODIGITALIZZATO:
											ItemMD.TIPODOCUMENTO_PERIODICODIGITALIZZATO));
					}
					read(ItemMD.BID, metadigit.getBib().getIdentifier());
					read(ItemMD.TITOLO, metadigit.getBib().getTitle());
					read(ItemMD.AUTORE, metadigit.getBib().getCreator());
					read(ItemMD.PUBBLICAZIONE, metadigit.getBib().getPublisher());
					read(ItemMD.SOGGETTO, metadigit.getBib().getSubject());
					read(ItemMD.DESCRIZIONE, metadigit.getBib().getDescription());
					read(ItemMD.CONTRIBUTO, metadigit.getBib().getContributor());
					read(ItemMD.DATA, metadigit.getBib().getDate());
					read(ItemMD.TIPORISORSA, metadigit.getBib().getType());
					read(ItemMD.FORMATO, metadigit.getBib().getFormat());
					read(ItemMD.FONTE, metadigit.getBib().getSource());
					read(ItemMD.LINGUA, metadigit.getBib().getLanguage());
					read(ItemMD.RELAZIONE, metadigit.getBib().getRelation());
					read(ItemMD.COPERTURA, metadigit.getBib().getCoverage());
					read(ItemMD.GESTIONEDIRITTI, metadigit.getBib().getRights());
					
					if (metadigit.getBib().getPiece() != null){
						read(metadigit.getBib().getPiece());
					}
					
					if (metadigit.getBib().getHoldings() != null){
						read(metadigit.getBib().getHoldings());
					}
				}
//			if (metadigit.getGen()..getAgent() != null){
//				for(int x=0; x<md.getAgent().size(); x++){
//					for(int y=0; y<md.getAgent().get(x).getAgentIdentifier().size(); y++){
//						params.add(ItemMD.PROVENIENZAOGGETTO, 
//								md.getAgent().get(x).
//									getAgentIdentifier().get(y).
//									getAgentIdentifierValue());
//					}
//				}
//			}
				admd.add(params.getParams(), new ItemMD());
			}
		} catch (XsdException e) {
			throw e;
		} catch (SolrException e) {
			throw e;
		}
	}

	private void read(Piece piece) {
		if (piece.getYear() != null && piece.getIssue() != null){
			params.add(ItemMD.PIECEDT, piece.getYear() );
			params.add(ItemMD.PIECEIN, piece.getIssue());
			if (piece.getStpiecePer() != null){
				params.add(ItemMD.PIECEGR, piece.getStpiecePer());
			}
		} else if (piece.getPartNumber() != null && piece.getPartName() != null){
			params.add(ItemMD.PIECEDT, piece.getPartNumber() );
			params.add(ItemMD.PIECEIN, piece.getPartName());
			if (piece.getStpieceVol() != null){
				params.add(ItemMD.PIECEGR, piece.getStpieceVol());
			}
		}
	}

	private void read(List<Holdings> values) {
		for (int x=0; x<values.size(); x++){
			if (values.get(x).getInventoryNumber() != null){
				params.add(ItemMD.INVENTARIO, values.get(x).getInventoryNumber());
			}
			if (values.get(x).getLibrary() != null){
				params.add(ItemMD.BIBLIOTECA, values.get(x).getInventoryNumber());
			}
			
			if (values.get(x).getShelfmark() != null){
				for (int y=0; y<values.get(x).getShelfmark().size(); y++){
					params.add(ItemMD.COLLOCAZIONE, 
							values.get(x).getShelfmark().get(y).getContent());
				}
			}
			
		}
	}
	
}
