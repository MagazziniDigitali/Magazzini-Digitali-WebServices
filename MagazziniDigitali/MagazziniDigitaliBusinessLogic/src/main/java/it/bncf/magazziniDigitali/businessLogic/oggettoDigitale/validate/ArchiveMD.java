/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDIstituzione;
import mx.randalf.archive.check.ArchiveImp;
import mx.randalf.archive.info.DigestType;

/**
 * @author massi
 *
 */
public class ArchiveMD extends ArchiveImp {

	private IMDConfiguration<?> configuration = null;
	/**
	 * 
	 */
	public ArchiveMD(IMDConfiguration<?> configuration) {
		this.configuration = configuration;
	}

	/**
	 * @see mx.randalf.archive.check.ArchiveImp#getID()
	 */
	@Override
	public String getID() {
		
		return getIDMD(configuration);
	}

	public static String getIDMD(IMDConfiguration<?> configuration) {
		UUID uuid = null;
		String ris = null;
		GregorianCalendar gc = null;
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");

		try {
			uuid = UUID.randomUUID();
			ris = uuid.toString();
			ris += configuration.getSoftwareConfigString("validate.nodo");
//			ris += Configuration.getValue("demoni.Validate.nodo");
			gc = new GregorianCalendar();
			ris += df4.format(gc.get(Calendar.YEAR));
			ris += df2.format(gc.get(Calendar.MONTH)+1);
			ris += df2.format(gc.get(Calendar.DAY_OF_MONTH));
		} catch (MDConfigurationException e) {
			e.printStackTrace();
		}
		
		return ris;
	}
	
	public boolean checkMimetype(String mimetype){
		boolean ris = false;
		if (this.getType().getMimetype() != null
				&& this.getType().getMimetype().size()>0){
			for(int x=0; x<this.getType().getMimetype().size(); x++){
				if (this.getType().getMimetype().get(x).equals(mimetype)) {
					ris = true;
				}
			}
		}

		return ris;
	}

	public String getDigest(DigestType digest){
		String ris = null;
		if (this.getType().getDigest() != null
				&& this.getType().getDigest().size()>0){
			for(int x=0; x<this.getType().getDigest().size(); x++){
				if (this.getType().getDigest().get(x).getType().equals(digest)) {
					ris = this.getType().getDigest().get(x).getValue();
				}
			}
		}

		return ris;
	}

	public String getMimetype(){
		String ris = null;
		if (this.getType().getMimetype() != null
				&& this.getType().getMimetype().size()>0){
			ris = "";
			for(int x=0; x<this.getType().getMimetype().size(); x++){
				ris += (x==0?"":",");
				ris += this.getType().getMimetype().get(x);
			}
		}

		return ris;
	}
}
