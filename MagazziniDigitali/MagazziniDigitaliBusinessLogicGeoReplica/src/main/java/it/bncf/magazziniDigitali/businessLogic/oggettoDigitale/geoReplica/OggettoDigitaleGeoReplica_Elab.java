/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.hibernate.exception.HibernateUtilException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
class OggettoDigitaleGeoReplica_Elab extends OggettoDigitaleGeoReplica_Verify {

	private Logger log = LogManager.getLogger(OggettoDigitaleGeoReplica_Elab.class);

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_Elab(Logger logPublish, String name) {
		super(logPublish, name);
	}

	protected boolean elabGeoReplica(
			IMDConfiguration<?> configuration
			, MDFilesTmp mdFilesTmp
			, String objectIdentifierPremis
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
			, MDFilesTmpBusiness mdFileTmpBusiness
			, File filePremis
//			, String application
			) throws SQLException, MDConfigurationException {
//		File fileElabPremis = null;
//		File fileElab = null;
		GregorianCalendar gcStart = null;
		boolean esito = true;
		String pFile = "";
		int pos = 0;
		Nodi nodoInput = null; 

		try {
			pFile = mdFilesTmp.getPremisFile();
			pos = pFile.lastIndexOf("/");
			pFile = pFile.substring(pos+1);

			nodoInput = new Nodi(configuration.getSoftwareConfigMDNodi("nodo"), pFile);
//			fileElabPremis = GenFileDest.genFileDest(
//					configuration.getSoftwareConfigMDNodi("nodo")
////					Configuration.getValue("demoni.Publish.pathStorage")
//					,pFile);
//			fileElab = new File(fileElabPremis.getAbsolutePath().replace(".premis", ""));
			log.info("\n"+name+" ["+objectIdentifierPremis+"]\n"+"\tInizio l'elaborazione del file ["+nodoInput.genFileTarDest()+"]");
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEPUBLISH)){
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"]\n"+"\tInizio l'archiviazione del file ["
						+ nodoInput.genFileTarDest() + "]");
				gcStart = mdFileTmpBusiness
						.updateStartArchive(mdFilesTmp.getId());
			} else {
				logPublish.info("\n"+name+" ["+objectIdentifierPremis+"]\n"+"\tContinuo l'archivizione del file ["
						+ nodoInput.genFileTarDest() + "]");
				gcStart = new GregorianCalendar();
				if (mdFilesTmp.getArchiveDataStart() ==null) {
					gcStart = mdFileTmpBusiness
							.updateStartArchive(mdFilesTmp.getId());
				} else {
					gcStart.setTimeInMillis(mdFilesTmp.getArchiveDataStart().getTime());
				}
			}
			premisElab
			.addObjectFileContainer(
					objectIdentifierPremis,
					null,
					null,
					new BigInteger("0"),
					null,
					null,
					null, null, null,
					null, null);
			if (nodoInput.isFileTarExists()){
//			if (fileElab.exists()){
				esito = verifyFileElabPremis(
						nodoInput
						//fileElabPremis, 
						, objectIdentifierPremis
						, premisElab
						, configuration
						, mdFileTmpBusiness
						, mdFilesTmp
						//, fileElab
//						, application
						, filePremis);
			} else {
				esito = false;
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"]\n"+"\tIl file ["+nodoInput.genFileTarDest()+"] non è presente");
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { "Il file ["+nodoInput.genFileTarDest()+"] non è presente" },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
				mdFileTmpBusiness
				.updateStopArchive(
						mdFilesTmp.getId(),
						false, null, 
						new String[] { "Il file ["+nodoInput.genFileTarDest()+"] non è presente" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (MDConfigurationException e) {
			esito = false;
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (SQLException e) {
			esito = false;
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateException e) {
			esito = false;
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
//		} catch (NoSuchAlgorithmException e) {
//			esito = false;
//			if (premisElab != null) {
//				premisElab.addEvent(
//						"Error",
//						null,
//						null,
//						null,
//						"KO",
//						new String[] { e.getMessage() },
//						null,
//						configuration.getMDSoftware()
////						Configuration.getValue("demoni."
////								+ application + ".UUID")
//						, null);
//			}
//			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
//					new Exception[] { e },
//					null,
//					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
//			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
//		} catch (IOException e) {
//			esito = false;
//			if (premisElab != null) {
//				premisElab.addEvent(
//						"Error",
//						null,
//						null,
//						null,
//						"KO",
//						new String[] { e.getMessage() },
//						null,
//						configuration.getMDSoftware()
////						Configuration.getValue("demoni."
////								+ application + ".UUID")
//						, null);
//			}
//			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
//					new Exception[] { e },
//					null,
//					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
//			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateUtilException e) {
			esito = false;
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (NodiException e) {
			esito = false;
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (Exception e) {
			esito = false;
			if (premisElab != null) {
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { e.getMessage() },
						null,
						configuration.getMDSoftware()
//						Configuration.getValue("demoni."
//								+ application + ".UUID")
						, null);
			}
			mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false,
					new Exception[] { e },
					null,
					writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}finally{
			log.info("\n"+name+" Fine l'elaborazione del file ["+objectIdentifierPremis+"]");
			try {
				if (premisElab != null){
					premisElab.write(filePremis, false);
				}
			} catch (PremisXsdException e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (XsdException e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (IOException e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} catch (Exception e) {
				esito = false;
				log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
				mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(),
						false, 
						new Exception[] { e },
						null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		}
		return esito;
	}

}
