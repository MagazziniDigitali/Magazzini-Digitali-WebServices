/**
 * 
 */
package it.bncf.magazziniDigitali.utils.folder;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * @author massi
 *
 */
public class FolderSyncElab {

	private Logger log = Logger.getLogger(FolderSyncElab.class);

	private File pathElab = null;

	/**
	 * 
	 */
	public FolderSyncElab(File pathElab) {
		this.pathElab = pathElab;
	}

	public void syncElab(){
		File[] fl = null;
		File f = null;
		File fOri = null;
		
		fl = pathElab.listFiles(new FileFilter() {

			@Override
			public boolean accept(File f) {
				boolean ris = false;

				// Verifico che il file/cartella non sia di tipo nascosto
				if (!f.getName().startsWith(".") && !f.isHidden() && f.isFile()) {
					// controllo se è una cartella
					if (f.getName().toLowerCase().endsWith(".elab")) {
						ris = true;
					}
				}
				return ris;
			}
		});
		Arrays.sort(fl);

		if (fl.length>0){
			for (int x = 0; x < fl.length; x++) {
				f = fl[x];
				if (f.exists()){
					fOri = new File(f.getParentFile().getAbsolutePath()+
							File.separator+
							f.getName().replace(".elab", ""));
					if (fOri.exists()){
						moveFile(pathElab, fOri.getName());
//						moveFile(f.getName());
					}
				}
			}
		}
	}

	private void moveFile(File fElab, String fName) {
		File folderDest = null;
		File fOrig = null;
		File fDest = null;
		int conta = 0;
		int pos = 0;
		DecimalFormat df = new DecimalFormat("0000");
		
		try {
			folderDest = new File(fElab.getAbsolutePath()+ File.separator+
					fName.substring(0,4)+File.separator+
					fName.substring(4,6)+File.separator+
					fName.substring(6,8)
					);
			if (!folderDest.exists()){
				if (!folderDest.mkdirs()){
					throw new FileNotFoundException("Problemi nelal cartella ["+folderDest.getAbsolutePath()+"]");
				}
			}
			fDest = new File(folderDest.getAbsolutePath()+File.separator+fName);
			while(fDest.exists()){
				conta++;
				pos = fName.indexOf(".");
				fDest = new File(folderDest.getAbsolutePath()+File.separator+
						fName.substring(0,pos-1)+"_"+df.format(conta)+fName.substring(pos));
			}
			fOrig = new File(fElab.getAbsolutePath()+File.separator+fName);
			
			if (fOrig.renameTo(fDest)){
				fOrig = new File(fOrig.getAbsolutePath()+".elab");
				fDest = new File(fDest.getAbsolutePath()+".elab");
				if (!fOrig.renameTo(fDest)){
					throw new IOException("Problemi nello spostamento del file ["+fOrig.getAbsolutePath()+"] in ["+fDest.getAbsolutePath()+"]");
				}
			} else {
				throw new IOException("Problemi nello spostamento del file ["+fOrig.getAbsolutePath()+"] in ["+fDest.getAbsolutePath()+"]");
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
