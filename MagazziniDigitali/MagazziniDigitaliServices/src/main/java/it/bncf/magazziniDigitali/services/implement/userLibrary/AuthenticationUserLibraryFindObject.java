package it.bncf.magazziniDigitali.services.implement.userLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.HashTable;
import it.bncf.magazziniDigitali.businessLogic.istituzione.MDIstituzioneBusiness;
import it.bncf.magazziniDigitali.businessLogic.rigths.MDRigthsBusiness;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.Regioni;
import it.bncf.magazziniDigitali.services.axis.DepositoLegaleAxisServlet;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.bncf.magazziniDigitali.solr.FindDocumentMD;
import it.bncf.magazziniDigitali.solr.exception.SolrWarning;
import it.depositolegale.www.authenticationUserInput.Agent;
import it.depositolegale.www.authenticationUserInput.AuthenticationUserInput;
import it.depositolegale.www.authenticationUserInput.Rights;
import it.depositolegale.www.authenticationUserInput.RightsRightsDisseminate;
import it.depositolegale.www.authenticationUserInput.RightsRightsDisseminateRightsDisseminateType;
import it.depositolegale.www.authenticationUserInput.RightsRightsIdentifier;
import it.depositolegale.www.authenticationUserOutput.AuthenticationUserOutput;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.solr.exception.SolrException;

public class AuthenticationUserLibraryFindObject extends AuthenticationUserLibraryGenTicket{

	public AuthenticationUserLibraryFindObject() {
	}

	/**
	 * Metodo utilizzato per la verifica della presenza del materiale indicato e degli eventuali diritti da applicare
	 * @param input
	 * @param output
	 * @throws AuthenticationUserLibraryException
	 */
	protected static void findObject(AuthenticationUserInput input, AuthenticationUserOutput output)
			throws AuthenticationUserLibraryException {
		FindDocumentMD findDocumentMD = null;
		SolrDocumentList response = null;
		SolrDocument solrDocument = null;
		SolrDocument solrDocumentPadre = null;
		Rights rights = null;
		String key = "";
		String actualFileName = null;
		String originalFileName = null;

		try {
			findDocumentMD = new FindDocumentMD(
					DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.URL"),
					Boolean.parseBoolean(
							DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.Cloud")),
					DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.collection"),
					Integer.parseInt(DepositoLegaleAxisServlet.mdConfiguration
							.getSoftwareConfigString("solr.connectionTimeOut")),
					Integer.parseInt(
							DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigString("solr.clientTimeOut")));

			if (input.getUserInput().getObjectIdentifier().getObjectIdentifierType().equals("id")) {
				key = "id";
			} else if (input.getUserInput().getObjectIdentifier().getObjectIdentifierType().equals("bid")) {
				key = "bid";
			}
			if (!key.equals("")) {
				response = findDocumentMD.find(key,
						input.getUserInput().getObjectIdentifier().getObjectIdentifierValue());
				if (response != null) {
					if (response.getNumFound() == 0) {
						throw new AuthenticationUserLibraryException("Non risulta l'oggetto richiesto in archivio");
					} else if (response.getNumFound() > 1) {
						throw new AuthenticationUserLibraryException(
								"Non risulta troppi oggetti per la richiesta effettuata");
					} else {
						solrDocument = response.get(0);

						if (solrDocument.getFieldValues("tipoOggetto_show") != null
								&& !solrDocument.getFieldValues("tipoOggetto_show").isEmpty()) {
							output.getUserInput().setTipoOggetto(
									(String) solrDocument.getFieldValues("tipoOggetto_show").iterator().next());
						} else {
							throw new AuthenticationUserLibraryException(
									"Non risulta indicata la tipologia di documento");
						}

						if (solrDocument.getFieldValues("mimeType_show") != null
								&& !solrDocument.getFieldValues("mimeType_show").isEmpty()) {
							output.getUserInput().setMimeType(
									(String) solrDocument.getFieldValues("mimeType_show").iterator().next());
						} else {
							throw new AuthenticationUserLibraryException(
									"Non risulta indicata il MimeType del documento");
						}

						output.getUserInput().setIdentifier((String) solrDocument.getFieldValue("id"));

						if (solrDocument.getFieldValues("originalFileName_show") != null
								&& !solrDocument.getFieldValues("originalFileName_show").isEmpty()) {
							originalFileName = (String) solrDocument.getFieldValues("originalFileName_show").iterator()
									.next();
						}

						output.getUserInput().setOriginalFileName(originalFileName);

						if (!solrDocument.containsKey("rights_show")) {
							if (solrDocument.containsKey("_root_") || solrDocument.containsKey("padre")) {
								if (solrDocument.containsKey("_root_")) {
									response = findDocumentMD.find("id", (String) solrDocument.getFieldValue("_root_"));
								} else {
									response = findDocumentMD.find("id", (String) solrDocument.getFieldValue("padre"));
								}
								if (response != null) {
									if (response.getNumFound() == 0) {
										throw new AuthenticationUserLibraryException(
												"Non risulta l'oggetto richiesto in archivio");
									} else if (response.getNumFound() > 1) {
										throw new AuthenticationUserLibraryException(
												"Non risulta troppi oggetti per la richiesta effettuata");
									} else {
										solrDocumentPadre = response.get(0);
										if (!solrDocumentPadre.containsKey("rights_show")) {
											throw new AuthenticationUserLibraryException(
													"Non risulta le indormazioni relative al Diritto dell'oggetto");
										}
									}
								} else {
									throw new AuthenticationUserLibraryException(
											"Non risulta l'oggetto richiesto in archivio");
								}
							} else {
								throw new AuthenticationUserLibraryException(
										"Non risulta le informazioni relative alla risorsa padre");
							}
						}

						if (solrDocumentPadre != null) {
							rights = initRigths(solrDocumentPadre.getFieldValues("rights_show"));
							if (solrDocumentPadre.getFieldValues("actualFileName_show") != null
									&& !solrDocumentPadre.getFieldValues("actualFileName_show").isEmpty()) {
								actualFileName = (String) solrDocumentPadre.getFieldValues("actualFileName_show")
										.iterator().next();
							}
						} else {
							rights = initRigths(solrDocument.getFieldValues("rights_show"));
							if (solrDocument.getFieldValues("actualFileName_show") != null
									&& !solrDocument.getFieldValues("actualFileName_show").isEmpty()) {
								actualFileName = (String) solrDocument.getFieldValues("actualFileName_show").iterator()
										.next();
							}
						}
						output.getUserInput().setActualFileName(actualFileName);

						checkRights(input, output, rights, actualFileName, originalFileName, solrDocument,
								solrDocumentPadre, findDocumentMD);
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
				if (findDocumentMD != null) {
					findDocumentMD.close();
				}
			} catch (IOException e) {
				throw new AuthenticationUserLibraryException(e.getMessage(), e);
			}
		}
	}

	private static Rights initRigths(Collection<Object> rightss) throws AuthenticationUserLibraryException {
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
			while (values.hasNext()) {
				value = (String) values.next();
				mdRigths = mdRigthsBusiness.findById(value);
				if (mdRigths != null) {
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

	@SuppressWarnings("unchecked")
	private static void checkRights(AuthenticationUserInput input, AuthenticationUserOutput output, Rights rights,
			String actualFileName, String originalFileName, SolrDocument solrDocument, SolrDocument solrDocumentPadre,
			FindDocumentMD findDocumentMD) throws AuthenticationUserLibraryException {
		SolrDocumentList response = null;
		Regioni regioni = null;
		Vector<Agent> agents = null;

		try {
			if (rights != null) {
				if (solrDocumentPadre != null) {
					response = findDocumentMD.findEvent((String) solrDocumentPadre.getFieldValue("id"), "send");
				} else {
					response = findDocumentMD.findEvent((String) solrDocument.getFieldValue("id"), "send");
				}
				if (response != null && response.getNumFound() > 0) {
					if (response.get(0).get("agentDepositante_show") != null &&
							((ArrayList<String>) response.get(0).get("agentDepositante_show")).size()>0) {

						output.getUserInput().setDepositante(((ArrayList<String>) response.get(0).get("agentDepositante_show")).get(0));
						if (rights.getRightsDisseminate().getRightsDisseminateType()
								.equals(RightsRightsDisseminateRightsDisseminateType.A)) {
									output.setRights(rights);
						} else if (rights.getRightsDisseminate().getRightsDisseminateType()
								.equals(RightsRightsDisseminateRightsDisseminateType.B)) {
							if (output.getUserInput().getTipoOggetto().equals("contenitore")) {
								output.setRights(rights);
							} else if (!checkMimeTypeBib(output.getUserInput().getMimeType())){
								output.setRights(rights);
							} else {
								output.setUrl(genTicket(input.getUserInput(), rights, actualFileName, originalFileName));
							}
						} else if (rights.getRightsDisseminate().getRightsDisseminateType()
								.equals(RightsRightsDisseminateRightsDisseminateType.C)) {
							if (output.getUserInput().getTipoOggetto().equals("contenitore")) {
								output.setRights(rights);
							} else if (!checkMimeTypeBib(output.getUserInput().getMimeType())){
								output.setRights(rights);
							} else {
								regioni = getRegione(
										((ArrayList<String>) response.get(0).get("agentDepositante_show")).get(0));
								if (regioni != null) {
									agents = genAgentsBib(output.getUserInput().getIpClient(), regioni);
									if (agents != null && agents.size() > 0) {
										output.setRights(rights);
										output.setAgent(agents.toArray(new Agent[agents.size()]));
									} else {
										throw new AuthenticationUserLibraryException(
												"La postazione chiamante non risulta essere all'interno di un istituto accreditato");
									}
								} else {
									throw new AuthenticationUserLibraryException(
											"Non è stata indicata la regione del depositante");
								}
							}
						} else {
							throw new AuthenticationUserLibraryException("Tipologia di Diritto ["
									+ rights.getRightsDisseminate().getRightsDisseminateType().getValue()
									+ "] non implementata");
						}
					} else {
						throw new AuthenticationUserLibraryException("Manca l'agente depositante");
					}
				} else {
					throw new AuthenticationUserLibraryException(
							"Non individuato le infomrazioni relative all'istituzione che ha sottoposto il materiale");
				}
			} else {
				throw new AuthenticationUserLibraryException("Non risultano diritti associati al materiale richiesto");
			}
		} catch (AuthenticationUserLibraryException e) {
			throw e;
		} catch (SolrServerException e) {
			throw new AuthenticationUserLibraryException(e.getMessage(), e);
		}
	}

	private static Regioni getRegione(String idIstituzione) throws AuthenticationUserLibraryException {
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		MDIstituzione mdIstituzione = null;
		Regioni regioni = null;

		try {
			mdIstituzioneBusiness = new MDIstituzioneBusiness();
			mdIstituzione = mdIstituzioneBusiness.findById(idIstituzione);
			if (mdIstituzione != null) {
				if (mdIstituzione.getIdRegione() != null) {
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

	private static Vector<Agent> genAgentsBib(String ipUtente, Regioni idRegione)
			throws AuthenticationUserLibraryException {
		MDIstituzioneBusiness mdIstituzioneBusiness = null;
		HashTable<String, Object> dati = null;
		List<MDIstituzione> mdIstituziones = null;
		Vector<Agent> agents = null;

		try {
			mdIstituzioneBusiness = new MDIstituzioneBusiness();

			 dati = new HashTable<String, Object>();
			 dati.put("bibliotecaDepositaria", new Integer("1"));
			 dati.put("istitutoCentrale", new Integer("1"));
			// (Integer)dati.get("idRegione"),
			
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

	private static Vector<Agent> checkIstituzione(List<MDIstituzione> mdIstituziones, String ipUtente,
			Vector<Agent> agents) {
		Agent agent = null;
		for (int x = 0; x < mdIstituziones.size(); x++) {
			if (mdIstituziones.get(x).getInterfacciaApiUtente() != null
					&& !mdIstituziones.get(x).getInterfacciaApiUtente().trim().equals("")) {
				if (ToolsServices.testIP(mdIstituziones.get(x).getIpAccreditati(), ipUtente)) {
					if (agents == null) {
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

}
