/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileRegistro;

import java.io.File;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileTtl.SolrObjectFileTtl;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.registroIngressi.MdRegistroIngressi;
import it.magazziniDigitali.xsd.registroIngressi.RegistroIngressiXsd;
import mx.randalf.solr.exception.SolrException;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class SolrObjectFileRegistro extends SolrObjectFileTtl {

	/**
	 * 
	 */
	public SolrObjectFileRegistro() {
	}

	protected void publicSolrRegistro(File pathTar, AddDocumentMD admd, 
			IMDConfiguration<?> configuration) throws SolrException {
		File fName = null;
		RegistroIngressiXsd registroIngressiXsd = null;
		MdRegistroIngressi mdRegistroIngressi= null;
		
		try {
			fName = new java.io.File(pathTar.getAbsolutePath()+
					java.io.File.separator+ filename);
			if (fName.exists()){
				params.getParams().clear();
				params.add(ItemMD.ID, objectIdentifier+"-DC");
				params.add(ItemMD._ROOT_, objectIdentifier);
				params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_REGISTRO);
				params.add(ItemMD.ORIGINALFILENAME, filename);
				
				registroIngressiXsd = new RegistroIngressiXsd();
				mdRegistroIngressi = registroIngressiXsd.read(fName);
				
				params.add(ItemMD.REGISTRO_ID, mdRegistroIngressi.getId().getObjectIdentifierValue());
				params.add(ItemMD.REGISTRO_TIMESTAMPINGEST, mdRegistroIngressi.getTimeStampIngest());
				params.add(ItemMD.AGENTDEPOSITANTE, mdRegistroIngressi.getAgentDepositor().getLinkingAgentIdentifierValue());
				params.add(ItemMD.ORIGINALFILENAME, mdRegistroIngressi.getOriginalContainerName().getValue());
				params.add(ItemMD.ACTUALFILENAME, mdRegistroIngressi.getContainerName().getValue());
				params.add(ItemMD.REGISTRO_CONTAINERFINGERPRINT, mdRegistroIngressi.getContainerFingerPrint());
				if (mdRegistroIngressi.getContainerFingerPrintChain() != null){
					params.add(ItemMD.REGISTRO_CONTAINERFINGERPRINTCHAIN, mdRegistroIngressi.getContainerFingerPrintChain());
				}

				
				if (mdRegistroIngressi.getContainerType().intValue()==5){
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, "mets");
				} else if (mdRegistroIngressi.getContainerType().intValue()==4){
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, "mag");
				} else if (mdRegistroIngressi.getContainerType().intValue()==3){
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, "metatape");
				} else if (mdRegistroIngressi.getContainerType().intValue()==2){
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, "bagit");
				} else if (mdRegistroIngressi.getContainerType().intValue()==1){
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, "warc");
				} else if (mdRegistroIngressi.getContainerType().intValue()==0){
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, "admtape");
				} else {
					params.add(ItemMD.REGISTRO_CONTAINERTYPE, mdRegistroIngressi.getContainerType());
				}

				params.add(ItemMD.AGENTMACHINE, mdRegistroIngressi.getAgentMachineIngest().getLinkingAgentIdentifierValue());
				params.add(ItemMD.AGENTSOFTWARE, mdRegistroIngressi.getAgentSoftwareIngest().getLinkingAgentIdentifierValue());
				params.add(ItemMD.REGISTRO_STATUS, mdRegistroIngressi.getStatus().toString());
				params.add(ItemMD.REGISTRO_TIMESTAMPINIT, mdRegistroIngressi.getTimestampInit());
				params.add(ItemMD.REGISTRO_TIMESTAMPELAB, mdRegistroIngressi.getTimestampElab());
				params.add(ItemMD.REGISTRO_TIMESTAMPCODA, mdRegistroIngressi.getTimestampCoda());
				params.add(ItemMD.REGISTRO_TIMESTAMPPUB, mdRegistroIngressi.getTimestampPub());

				if (mdRegistroIngressi.getTimestampErr() != null){
					params.add(ItemMD.REGISTRO_TIMESTAMPERR, mdRegistroIngressi.getTimestampErr());
				}
				
				admd.add(params.getParams(), new ItemMD());
			}
		} catch (SolrException e) {
			throw e;
		} catch (XsdException e) {
			throw new SolrException(e.getMessage(), e);
		}
	}

}
