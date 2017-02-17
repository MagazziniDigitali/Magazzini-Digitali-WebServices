/**
 * 
 */
package it.bncf.magazziniDigitali.services.implement.userLibrary;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.businessLogic.rigths.MDRigthsBusiness;
import it.bncf.magazziniDigitali.businessLogic.ticket.MDTicketBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.Regioni;
import it.bncf.magazziniDigitali.external.user.IExternalUser;
import it.bncf.magazziniDigitali.external.user.exception.ExternalUserException;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.software.SoftwareTools;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.bncf.magazziniDigitali.solr.FindDocumentMD;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.depositolegale.www.authenticationUserInput.Agent;
import it.depositolegale.www.authenticationUserInput.AuthenticationUserInput;
import it.depositolegale.www.authenticationUserInput.Rights;
import it.depositolegale.www.authenticationUserInput.RightsRightsDisseminate;
import it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType;
import it.depositolegale.www.authenticationUserInput.RightsRightsIdentifier;
import it.depositolegale.www.authenticationUserInput.UserInput;
import it.depositolegale.www.authenticationUserOutput.AuthenticationUserOutput;
import it.depositolegale.www.errorMsg.ErrorMsg;
import it.depositolegale.www.errorMsg.ErrorType_type;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class AuthenticationUserLibrary {

	private static Logger log = Logger.getLogger(AuthenticationUserLibrary.class);

	/**
	 * 
	 */
	public AuthenticationUserLibrary() {
	}

	public static AuthenticationUserOutput AuthenticationUserLibraryOperation(AuthenticationUserInput input){
		AuthenticationUserOutput output = null;
		Vector<ErrorMsg> errorMsgs = null;

		output = new AuthenticationUserOutput();
		output.setUserInput(input.getUserInput());
		errorMsgs = new Vector<ErrorMsg>();
		try {
			if (SoftwareTools.checkSoftware(input.getUserInput().getSoftware(), 
											input.getUserInput().getSoftware().getAuthentication().getLogin())){
				if (input.getUserInput() != null &&
						input.getUserInput().getObjectIdentifier()!= null &&
						input.getUserInput().getObjectIdentifier().getObjectIdentifierType() != null &&
						input.getUserInput().getObjectIdentifier().getObjectIdentifierValue() != null &&
						input.getUserInput().getIpClient() != null){
					if (input.getUserInput().getAgent() != null &&
							input.getUserInput().getRights() != null &&
							input.getUserInput().getAuthentication() != null){
						// verifico le credenziali e costruisco il ticket per soddisfare la richiesta
						output.setRights(input.getUserInput().getRights());
						output.setUrl(genTicket(input.getUserInput(), 
								input.getUserInput().getRights(),
								input.getUserInput().getActualFileName(),
								input.getUserInput().getOriginalFileName()));
					} else {
						// verifico le infomrazioni relative all'oggetto richiesto e sottoconto le credenziali disponibili
						findObject(input, output);
					}
				} else {
					errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, "Dati inseriti non sufficenti controllare il tracciato"));
				}
			} else {
				errorMsgs.add(new ErrorMsg(ErrorType_type.SOFTWARE_ERROR, "Le credenziali del Software non sono valide"));
			}
		} catch (HibernateException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		} catch (AuthenticationUserLibraryException e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
//			log.error(e.getMessage(), e);
		} catch (Exception e) {
			errorMsgs.add(new ErrorMsg(ErrorType_type.ERROR, e.getMessage()));
			log.error(e.getMessage(), e);
		}finally {
			if (errorMsgs.size()>0){
				output.setErrorMsg(errorMsgs.toArray(new ErrorMsg[errorMsgs.size()]));
			}
		}
		return output;
	}

	private static String genTicket(UserInput userInput, Rights rights, String actualFileName, String originalFileName) throws AuthenticationUserLibraryException{
		String url = null;
		
		try {
			if (rights.getRightsDisseminate().getRightsDisseminateType().
					equals(RightsRightsDisseminateRightsDisseminateType.B)){
				url = verifyB(userInput, rights, actualFileName, originalFileName);
			} else 	if (rights.getRightsDisseminate().getRightsDisseminateType().
					equals(RightsRightsDisseminateRightsDisseminateType.C)){
				url = verifyC(userInput, rights, actualFileName, originalFileName);
			}
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		}
		return url;
	}

    private static String verifyC(UserInput userInput, Rights rights, String actualFileName, String originalFileName) throws AuthenticationUserLibraryException{
		MDTicketBusiness mdTicketBusiness = null;
		HashTable<String, Object> dati = null;
		GregorianCalendar gc = null;
		String url = null;
		String idTicket = null;
		MDIstituzioneBusiness mdIstituzioneBusiness = null;

		try {
			if (checkLogin(userInput)){
				
				mdTicketBusiness = new MDTicketBusiness();
				
				dati = new HashTable<String, Object>();
				dati.put("objectIdentifier", userInput.getIdentifier());
				gc = new GregorianCalendar();
				dati.put("dataStart", new Timestamp(gc.getTimeInMillis()));
				gc.add(Calendar.MINUTE, new Integer(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.period")));
				dati.put("dataEnd", new Timestamp(gc.getTimeInMillis()));
				dati.put("modalitaAccesso", rights.getRightsDisseminate().getRightsDisseminateType().getValue());
				dati.put("idRights", rights.getRightsIdentifier().getRightsIdentifierValue());
				dati.put("actualFileName", actualFileName);
				dati.put("originalFileName", originalFileName);
				mdIstituzioneBusiness = new MDIstituzioneBusiness();
				dati.put("idIstituzione", mdIstituzioneBusiness.findById(userInput.getAgent().getAgentIdentifier()));
				dati.put("loginUtente", userInput.getAuthentication().getLogin());
				dati.put("ipClient", userInput.getIpClient());
				idTicket = mdTicketBusiness.save(dati);
				
				url = appendUri(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.dockerUrl"),
						"ticket="+idTicket);
			} else {
				throw new AuthenticationUserLibraryException("Utente e password non validi riprovare");
			}
		} catch (NumberFormatException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		return url;
    }

    private static boolean checkLogin(UserInput userInput) throws AuthenticationUserLibraryException{
    	MDIstituzioneBusiness mdIstituzioneBusiness = null;
    	MDIstituzione mdIstituzione = null;
    	IExternalUser externalUser = null;
    	boolean result = false;
    	
    	try {
			mdIstituzioneBusiness = new MDIstituzioneBusiness();
			mdIstituzione = mdIstituzioneBusiness.findById(userInput.getAgent().getAgentIdentifier());
			
			if (mdIstituzione != null){
				if (mdIstituzione.getLibreriaApiUtente() != null){
					externalUser = (IExternalUser) Class.forName(mdIstituzione.getLibreriaApiUtente()).newInstance();
					result = externalUser.isValid(mdIstituzione.getInterfacciaApiUtente(), 
							userInput.getAuthentication().getLogin(), 
							userInput.getAuthentication().getPassword(), 
							userInput.getIpClient());
				} else {
					throw new AuthenticationUserLibraryException("Le librerie relative alla chiamata remota non sono state indicate");
				}
				
			} else {
				throw new AuthenticationUserLibraryException("L'istituzione indicata non esiste verificare");
			}
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (ExternalUserException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		}
    	return result;
    }

    private static String verifyB(UserInput userInput, Rights rights, String actualFileName, String originalFileName) throws AuthenticationUserLibraryException{
		MDTicketBusiness mdTicketBusiness = null;
		HashTable<String, Object> dati = null;
		GregorianCalendar gc = null;
		String url = null;
		String idTicket = null;

		try {
			mdTicketBusiness = new MDTicketBusiness();
			
			dati = new HashTable<String, Object>();
			dati.put("objectIdentifier", userInput.getIdentifier());
			gc = new GregorianCalendar();
			dati.put("dataStart", new Timestamp(gc.getTimeInMillis()));
			gc.add(Calendar.MINUTE, new Integer(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.period")));
			dati.put("dataEnd", new Timestamp(gc.getTimeInMillis()));
			dati.put("modalitaAccesso", rights.getRightsDisseminate().getRightsDisseminateType().getValue());
			dati.put("idRights", rights.getRightsIdentifier().getRightsIdentifierValue());
			dati.put("actualFileName", actualFileName);
			dati.put("originalFileName", originalFileName);
//		dati.put("idIstituzione", (MDIstituzione)value);
//		dati.put("loginUtente", value);
			dati.put("ipClient", userInput.getIpClient());
			idTicket = mdTicketBusiness.save(dati);
			
			url = appendUri(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("ticket.externalUrl"),
					"idTicket="+idTicket);
		} catch (NumberFormatException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		return url;
    }

    private static String appendUri(String uri, String appendQuery) throws AuthenticationUserLibraryException  {
        URI oldUri = null;
        String newQuery = null;
        URI newUri = null;

        try {
			oldUri = new URI(uri);
			newQuery = oldUri.getQuery();
			if (newQuery == null) {
			    newQuery = appendQuery;
			} else {
			    newQuery += "&" + appendQuery;  
			}

			newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
			        oldUri.getPath(), newQuery, oldUri.getFragment());
		} catch (URISyntaxException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}

        return newUri.toString();
    }

    @SuppressWarnings("unchecked")
	private static void findObject(AuthenticationUserInput input, AuthenticationUserOutput output) 
			throws AuthenticationUserLibraryException{
		FindDocumentMD findDocumentMD = null;
		SolrDocumentList response = null;
		SolrDocument solrDocument = null;
		SolrDocument solrDocumentPadre = null;
		Rights rights = null;
		Vector<Agent> agents = null;
		String key = "";
		Regioni regioni = null;
		String actualFileName = null;
		String originalFileName = null;

		try {
			findDocumentMD = new FindDocumentMD(
					DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.URL"),
					Boolean.parseBoolean(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.Cloud")),
					DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.collection"),
					Integer.parseInt(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.connectionTimeOut")),
					Integer.parseInt(DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.clientTimeOut")));

			
			if (input.getUserInput().getObjectIdentifier().getObjectIdentifierType().equals("id")){
				key = "id";
			} else if (input.getUserInput().getObjectIdentifier().getObjectIdentifierType().equals("bid")){
				key = "bid";
			}
			if (!key.equals("")){
				response = findDocumentMD.find(key, 
						input.getUserInput().getObjectIdentifier().getObjectIdentifierValue());
				if (response != null){
					if (response.getNumFound()==0){
						throw new AuthenticationUserLibraryException("Non risulta l'oggetto richiesto in archivio");
					} else if (response.getNumFound()>1){
						throw new AuthenticationUserLibraryException("Non risulta troppi oggetti per la richiesta effettuata");
					} else {
						solrDocument = response.get(0);
						output.getUserInput().setIdentifier((String) solrDocument.getFieldValue("id"));
						if (solrDocument.getFieldValues("originalFileName_show") != null &&
								!solrDocument.getFieldValues("originalFileName_show").isEmpty()){
							originalFileName = (String) solrDocument.getFieldValues("originalFileName_show").iterator().next();
						}
						output.getUserInput().setOriginalFileName(originalFileName);
						if (!solrDocument.containsKey("rights_show")){
							if (solrDocument.containsKey("_root_") || solrDocument.containsKey("padre")){
								if (solrDocument.containsKey("_root_")){
									response = findDocumentMD.find("id", (String)solrDocument.getFieldValue("_root_"));
								} else {
									response = findDocumentMD.find("id", (String)solrDocument.getFieldValue("padre"));
								}
								if (response != null){
									if (response.getNumFound()==0){
										throw new AuthenticationUserLibraryException("Non risulta l'oggetto richiesto in archivio");
									} else if (response.getNumFound()>1){
										throw new AuthenticationUserLibraryException("Non risulta troppi oggetti per la richiesta effettuata");
									} else {
										solrDocumentPadre = response.get(0);
										if (!solrDocumentPadre.containsKey("rights_show")){
											throw new AuthenticationUserLibraryException("Non risulta le indormazioni relative al Diritto dell'oggetto");
										}
									}
								} else {
									throw new AuthenticationUserLibraryException("Non risulta l'oggetto richiesto in archivio");
								}
							} else {
								throw new AuthenticationUserLibraryException("Non risulta le informazioni relative alla risorsa padre");
							}
						}
						
						if (solrDocumentPadre != null){
							rights = initRigths(solrDocumentPadre.getFieldValues("rights_show"));
							if (solrDocumentPadre.getFieldValues("actualFileName_show") != null &&
									!solrDocumentPadre.getFieldValues("actualFileName_show").isEmpty()){
								actualFileName = (String) solrDocumentPadre.getFieldValues("actualFileName_show").iterator().next();
							}
						} else {
							rights = initRigths(solrDocument.getFieldValues("rights_show"));
							if (solrDocument.getFieldValues("actualFileName_show") != null &&
									!solrDocument.getFieldValues("actualFileName_show").isEmpty()){
								actualFileName = (String) solrDocument.getFieldValues("actualFileName_show").iterator().next();
							}
						}
						output.getUserInput().setActualFileName(actualFileName);
						if (rights != null){
							if (rights.getRightsDisseminate().getRightsDisseminateType().equals(RightsRightsDisseminateRightsDisseminateType.A)){
								throw new AuthenticationUserLibraryException("Il materiale richiesto è di tipo archivio per cui non è possibile visionarlo");
							} else if (rights.getRightsDisseminate().getRightsDisseminateType().equals(RightsRightsDisseminateRightsDisseminateType.B)){
								
								output.setUrl(genTicket(input.getUserInput(), rights, actualFileName, originalFileName));
							} else if (rights.getRightsDisseminate().getRightsDisseminateType().equals(RightsRightsDisseminateRightsDisseminateType.C)){
								if (solrDocumentPadre != null){
									response =  findDocumentMD.findEvent((String) solrDocumentPadre.getFieldValue("id"), "send");
								} else {
									response =  findDocumentMD.findEvent((String) solrDocument.getFieldValue("id"), "send");
								}
								if (response != null &&
										response.getNumFound()>0){
									if (response.get(0).get("agentDepositante_show")!= null){
										regioni = getRegione(((ArrayList<String>) response.get(0).get("agentDepositante_show")).get(0));
										if (regioni != null){
											agents = genAgents(output.getUserInput().getIpClient(), regioni);
											if (agents != null &&
													agents.size()>0){
												output.setRights(rights);
												output.setAgent(agents.toArray(new Agent[agents.size()]));
											} else {
												throw new AuthenticationUserLibraryException("La postazione chiamante non risulta essere all'interno di un istituto accreditato");
											}
										} else {
											throw new AuthenticationUserLibraryException("Non è stata indicata la regione del depositante");
										}
									} else {
										throw new AuthenticationUserLibraryException("Manca l'agente depositante");
									}
								} else {
									throw new AuthenticationUserLibraryException("Non individuato le infomrazioni relative all'istituzione che ha sottoposto il materiale");
								}
							} else {
								throw new AuthenticationUserLibraryException("Tipologia di Diritto ["+rights.getRightsDisseminate().getRightsDisseminateType().getValue()+"] non implementata");
							}
						} else {
							throw new AuthenticationUserLibraryException("Non risultano diritti associati al materiale richiesto");
						}
					}
				} else {
					throw new AuthenticationUserLibraryException("Non risulta l'oggetto richiesto in archivio");
				}
			} else {
				throw new AuthenticationUserLibraryException("Credenziali per la ricerca non valide");
			}
		} catch (NumberFormatException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (SolrException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (MDConfigurationException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (SolrServerException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		} finally {
			try {
				if (findDocumentMD != null){
					findDocumentMD.close();
				}
			} catch (IOException e) {
				throw new AuthenticationUserLibraryException(e.getMessage(), e);
			}
		}
	}

	private static Regioni getRegione(String idIstituzione) throws AuthenticationUserLibraryException 
			{
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		MDIstituzione mdIstituzione = null;
		Regioni regioni = null;

		try {
			mdIstituzioneBusiness = new MDIstituzioneBusiness();
			mdIstituzione = mdIstituzioneBusiness.findById(idIstituzione);
			if (mdIstituzione != null){
				if (mdIstituzione.getIdRegione() != null){
					regioni = mdIstituzione.getIdRegione();
				}
			} else {
				throw new SolrWarning("L'istitutzione indicata non è presente in base dati");
			}
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (SolrWarning e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		return regioni;
	}

	private static Vector<Agent> genAgents(String ipUtente, Regioni idRegione) throws AuthenticationUserLibraryException 
			{
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		HashTable<String, Object> dati = null;
		List<MDIstituzione> mdIstituziones = null;
		Vector<Agent> agents = null;

		try {
			mdIstituzioneBusiness = new MDIstituzioneBusiness();
			
			dati = new HashTable<String, Object>();
			dati.put("bibliotecaDepositaria", new Integer("1"));
			dati.put("istitutoCentrale", new Integer("1"));
//		(Integer)dati.get("idRegione"),

			mdIstituziones = mdIstituzioneBusiness.find(dati, 1, 100);
			agents = checkIstituzione(mdIstituziones, ipUtente, agents);

			dati = new HashTable<String, Object>();
			dati.put("bibliotecaDepositaria", new Integer("1"));
			dati.put("istitutoCentrale", new Integer("0"));
			dati.put("idRegione", idRegione);

			mdIstituziones = mdIstituzioneBusiness.find(dati, 1, 100);
			agents = checkIstituzione(mdIstituziones, ipUtente, agents);
		} catch (NumberFormatException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		
		return agents;
	}

	private static Vector<Agent> checkIstituzione(List<MDIstituzione> mdIstituziones, String ipUtente, Vector<Agent> agents){
		Agent agent = null;
		for (int x = 0; x<mdIstituziones.size(); x++){
			if (mdIstituziones.get(x).getInterfacciaApiUtente() != null &&
					!mdIstituziones.get(x).getInterfacciaApiUtente().trim().equals("")){
				if (ToolsServices.testIP(mdIstituziones.get(x).getIpAccreditati(), ipUtente)){
					if (agents == null){
						agents = new Vector<Agent>();
					}
					agent = new Agent();
					agent.setAgentIdentifier(mdIstituziones.get(x).getId());
					agent.setAgentName(mdIstituziones.get(x).getNome());
					agents.add(agent);
				}
			}
		}
		return agents;
	}

	private static Rights initRigths(Collection<Object> rightss) throws AuthenticationUserLibraryException 
			{
		Iterator<Object> values = null;
		String value = null;
		Rights rights = null;
		RightsRightsIdentifier rightsRightsIdentifier = null;
		RightsRightsDisseminate rightsRightsDisseminate = null;
		
		try {
			values = rightss.iterator();
			MDRigthsBusiness mdRigthsBusiness = null;
			MDRigths mdRigths = null;
			
			mdRigthsBusiness = new MDRigthsBusiness();
			while (values.hasNext()){
				value = (String) values.next();
				mdRigths = mdRigthsBusiness.findById(value);
				if (mdRigths != null){
					rightsRightsIdentifier = new RightsRightsIdentifier("id", mdRigths.getId()); 
					rightsRightsDisseminate = new RightsRightsDisseminate(
							RightsRightsDisseminateRightsDisseminateType.fromString(mdRigths.getIdModalitaAccessoID()));

					rights = new Rights(rightsRightsIdentifier, rightsRightsDisseminate);
					break;
				}
			}
		} catch (HibernateException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		} catch (HibernateUtilException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
		return rights;
	}
}
