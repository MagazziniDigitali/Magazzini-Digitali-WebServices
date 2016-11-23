/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import mx.randalf.archive.check.CheckArchive;

/**
 * @author massi
 *
 */
public class CheckArchiveMD extends CheckArchive<ArchiveMD> {
	private IMDConfiguration<?> configuration = null;
	
	public CheckArchiveMD(String fileDroid, IMDConfiguration<?> configuration) {
		super(fileDroid);
		this.configuration = configuration;
	}

	@Override
	public ArchiveMD initArchive() {
		return new ArchiveMD(configuration);
	}

}
