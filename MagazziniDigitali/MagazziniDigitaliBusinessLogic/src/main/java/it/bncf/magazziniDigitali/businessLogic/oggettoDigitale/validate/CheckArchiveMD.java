/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import mx.randalf.archive.check.CheckArchive;

/**
 * @author massi
 *
 */
public class CheckArchiveMD extends CheckArchive<ArchiveMD> {

	public CheckArchiveMD(String fileDroid) {
		super(fileDroid);
	}

	@Override
	public ArchiveMD initArchive() {
		return new ArchiveMD();
	}

}
