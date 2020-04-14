/**
 * 
 */
package it.bncf.magazziniDigitali.solr;

import mx.randalf.solr.AddDocument;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class AddDocumentMD extends AddDocument {

	/**
	 * @param url
	 * @param cloud
	 * @throws SolrException
	 */
	public AddDocumentMD(String url, boolean cloud, String collection, String optional) throws SolrException {
		super(url, cloud, collection, optional);
	}

	/**
	 * @param url
	 * @param cloud
	 * @param connectionTimeout
	 * @param clientTimeout
	 * @throws SolrException
	 */
	public AddDocumentMD(String url, boolean cloud, String collection, int connectionTimeout,
			int clientTimeout, String optional) throws SolrException {
		super(url, cloud, collection, connectionTimeout, clientTimeout, optional);
	}

//	/**
//	 * @see mx.randalf.solr.AddDocument#add(java.util.Hashtable, java.util.Hashtable)
//	 */
//	@Override
//	public Hashtable<String, String> add(Hashtable<String, List<Object>> params,
//			Hashtable<String, String> idLegati) throws SolrException {
//		SolrInputDocument item = null;
//		String query = "";
//		QueryResponse response = null;
//		String id = null;
//
//		try {
//			id = (String) params.get("id").get(0);
//			query = "id:\"" + id+"\"";
//			response = find(query);
//			if (response.getResults().getNumFound() == 1) {
//				if (request == null){
//					request = new UpdateRequest();
//					request.setAction(ACTION.OPTIMIZE, false, false);
//				}
//				request.deleteByQuery(query);
////				delete(query);
//			} else if (response.getResults().getNumFound() > 1) {
//				throw new SolrException(
//						"Riscontrato più di un record Padre per la query ["
//								+ query + "] verificare");
//			}
//			item = initItem(id, params, idLegati);
//
//			if (request == null){
//				request = new UpdateRequest();
//				request.setAction(ACTION.OPTIMIZE, false, false);
//			}
//			request.add(item);
////			resp = addBean(item);
//		} catch (SolrServerException e) {
//			log.error(e.getMessage(), e);
//			throw new SolrException("Solr Server: " + e.getMessage(), e);
////		} catch (IOException e) {
////			log.error(e.getMessage(), e);
////			throw new SolrException("Solr Server: " + e.getMessage(), e);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new SolrException("Solr Server: " + e.getMessage(), e);
//		}
//		return null;
//	}

//	private SolrInputDocument initItem(String id,
//			Hashtable<String, List<Object>> params,
//			Hashtable<String, String> idLegati) {
//		SolrInputDocument item = null;
//		Vector<Object> objs = null;
//		Object obj = null;
//
//		item = new SolrInputDocument();
//		
////		item.addField("_version_",new Long(1));
//
//		//
//		objs = (Vector<Object>) params.get("_root_");
//		if (objs != null) {
//			item.addField("_root_",objs.get(0));
//		}
//		
//		//
//		System.out.println("ID: "+id);
//		item.addField("id",id);
//
//		objs = (Vector<Object>) params.get("tipoDoc");
//		if (objs != null) {
//			add(item,"tipoDoc", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("documento");
//		if (objs != null) {
//			add(item,"documento", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("objectIdentifier");
//		if (objs != null) {
//			add(item,"objectIdentifier", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("fileType");
//		if (objs != null) {
//			add(item,"fileType", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("actualFileName");
//		if (objs != null) {
//			add(item,"actualFileName", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("compositionLevel");
//		if (objs != null) {
//			add(item,"compositionLevel", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("sha1");
//		if (objs != null) {
//			add(item,"sha1", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("size");
//		if (objs != null) {
//			add(item,"size", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("mimeType");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				add(item,"mimeType", (String) obj,true, true, true, true);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("promon");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				add(item,"promon", (String) obj,true, true, true, true);
//			}
//		}
//
//		//
//		objs = (Vector<Object>) params.get("originalFileName");
//		if (objs != null) {
//			add(item,"originalFileName", objs.get(0),true, true, true, true);
//		}
//
//		objs = (Vector<Object>) params.get("rights");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				add(item,"rights", (String) obj,true, true, true, true);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("tarIndex");
//		if (objs != null) {
//			add(item,"tarIndex", objs.get(0),true, true, true, true);
//		}
//
//		//
//		objs = (Vector<Object>) params.get("relationshipType");
//		if (objs != null) {
//			add(item,"relationshipType", objs.get(0),true, true, true, true);
//		}
//
////		objs = (Vector<Object>) params.get("eventID");
////		if (objs != null) {
////			item.addEventID((String) objs.get(0));
////		}
////
////		objs = (Vector<Object>) params.get("eventType");
////		if (objs != null) {
////			item.addEventType((String) objs.get(0));
////		}
////
////		objs = (Vector<Object>) params.get("eventDate");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addEventDate((Date) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("eventOutCome");
////		if (objs != null) {
////			item.addEventOutCome((String) objs.get(0));
////		}
////
////		objs = (Vector<Object>) params.get("agentDepositante");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addAgentDepositante((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("agentSoftware");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addAgentSoftware((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("nodo");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addNodo((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("level");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addLevel((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("bid");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addBid((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("bni");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addBni((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("titolo");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addTitolo((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("autore");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addAutore((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("pubblicazione");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addPubblicazione((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("soggetto");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addSoggetto((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("descrizione");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addDescrizione((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("contributo");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addContributo((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("data");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addData((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("tiporisorsa");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addTiporisorsa((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("formato");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addFormato((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("fonte");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addFonte((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("lingua");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addLingua((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("relazione");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addRelazione((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("copertura");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addCopertura((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("gestionediritti");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addGestionediritti((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("biblioteca");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addBiblioteca((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("inventario");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addInventario((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("collocazione");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addCollocazione((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("piecegr");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addPiecegr((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("piecedt");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addPiecedt((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("piecein");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				item.addPiecein((String) obj);
////			}
////		}
////
////		objs = (Vector<Object>) params.get("keywordsDoc");
////		if (objs != null) {
////			for (int x = 0; x < objs.size(); x++) {
////				obj = objs.get(x);
////				if (item.keywordsDoc == null){
////					item.keywordsDoc = new Vector<String>();
////				}
////				item.keywordsDoc.add((String) obj);
////			}
////		}
//
//		return item;
//	}
//
//	private void add(SolrInputDocument item, String key, Object value, boolean show, boolean sort, boolean kw, boolean fc){
//		String valore = null;
//		SimpleDateFormat df = null;
//		String vSort = null;
//
//		df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
//		
//		item.addField(key, value);
//		if (value instanceof String){
//			valore = (String) value;
//		} else if (value instanceof Long){
//			valore = ((Long) value).toString();
//		} else if (value instanceof Date){
//			valore = df.format((Date) value);
//		}
//
//		if (show){
//			item.addField(key+"_show", valore);
//		}
//		if (sort){
//			vSort = (String) item.getFieldValue(key+"_sort");
//			if (vSort!= null){
//				item.setField(key+"_sort", vSort +" "+value);
//			} else {
//				item.addField(key+"_sort", valore);
//			}
//		}
//		if (kw){
//			item.addField(key+"_kw", valore);
//		}
//		if (fc){
//			item.addField(key+"_fc", valore.replace(" ", "_"));
//		}
//	}

//	private SolrInputDocument initItem(String id,
//			Hashtable<String, List<Object>> params,
//			Hashtable<String, String> idLegati) {
//		SolrInputDocument item = null;
//		Vector<Object> objs = null;
//		Object obj = null;
//
//		item = new SolrInputDocument();
//		
//		item._version_ = new Long(1);
//
//		objs = (Vector<Object>) params.get("_root_");
//		if (objs != null) {
//			item._root_ = (String) objs.get(0);
//		}
//		
//		item.id = id;
//
//		objs = (Vector<Object>) params.get("tipoDoc");
//		if (objs != null) {
//			item.addTipoDoc((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("documento");
//		if (objs != null) {
//			item.addDocumento((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("objectIdentifier");
//		if (objs != null) {
//			item.addObjectIdentifier((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("fileType");
//		if (objs != null) {
//			item.addFileType((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("actualFileName");
//		if (objs != null) {
//			item.addActualFileName((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("compositionLevel");
//		if (objs != null) {
//			item.addCompositionLevel((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("sha1");
//		if (objs != null) {
//			item.addSha1((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("size");
//		if (objs != null) {
//			item.addSize((Long) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("mimeType");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addMimeType((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("promon");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addPromon((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("originalFileName");
//		if (objs != null) {
//			item.addOriginalFileName((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("rights");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addRights((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("tarIndex");
//		if (objs != null) {
//			item.addTarIndex((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("relationshipType");
//		if (objs != null) {
//			item.addRelationshipType((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("eventID");
//		if (objs != null) {
//			item.addEventID((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("eventType");
//		if (objs != null) {
//			item.addEventType((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("eventDate");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addEventDate((Date) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("eventOutCome");
//		if (objs != null) {
//			item.addEventOutCome((String) objs.get(0));
//		}
//
//		objs = (Vector<Object>) params.get("agentDepositante");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addAgentDepositante((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("agentSoftware");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addAgentSoftware((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("nodo");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addNodo((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("level");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addLevel((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("bid");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addBid((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("bni");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addBni((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("titolo");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addTitolo((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("autore");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addAutore((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("pubblicazione");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addPubblicazione((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("soggetto");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addSoggetto((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("descrizione");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addDescrizione((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("contributo");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addContributo((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("data");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addData((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("tiporisorsa");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addTiporisorsa((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("formato");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addFormato((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("fonte");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addFonte((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("lingua");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addLingua((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("relazione");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addRelazione((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("copertura");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addCopertura((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("gestionediritti");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addGestionediritti((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("biblioteca");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addBiblioteca((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("inventario");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addInventario((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("collocazione");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addCollocazione((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("piecegr");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addPiecegr((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("piecedt");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addPiecedt((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("piecein");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				item.addPiecein((String) obj);
//			}
//		}
//
//		objs = (Vector<Object>) params.get("keywordsDoc");
//		if (objs != null) {
//			for (int x = 0; x < objs.size(); x++) {
//				obj = objs.get(x);
//				if (item.keywordsDoc == null){
//					item.keywordsDoc = new Vector<String>();
//				}
//				item.keywordsDoc.add((String) obj);
//			}
//		}
//
//		return item;
//	}
}
