/**
 * 
 */
package it.bncf.magazziniDigitali.nodi;

import java.io.File;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;

import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.Storage;
import it.depositolegale.www.webservice_checkStorageMD.CheckStorageMDPortTypeProxy;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.tools.rsync.RSync;
import mx.randalf.tools.rsync.exception.RSyncException;

/**
 * @author massi
 *
 */
class NodiMDRsync extends INodi<File> {

	/**
	 * 
	 */
	public NodiMDRsync(MDNodi mdNodi, String nomeFileJar, String nomeFilePremis) {
		super(mdNodi, nomeFileJar, nomeFilePremis);
	}

	@Override
	public boolean isStorageActive() throws NodiException {
		URL  url = null;
		Socket socket = null;
		int port = 873;
		boolean result = false;

		try {
			url = new URL(mdNodi.getRsync());
			
			if (url.getPort()==-1) {
				if (url.getProtocol().equals("http")) {
					port = 80;
				} else if (url.getProtocol().equals("https")) {
					port = 443;
				} else if (url.getProtocol().equals("rsync")) {
					port = 873;
				} else if (url.getProtocol().equals("ssh")) {
					port = 22;
				}
			} else {
				port = url.getPort();
			}
			
			socket = new Socket(url.getHost(), port);
			socket.close();
			result = true;
		} catch (NumberFormatException e) {
			throw new NodiException(e.getMessage(),e);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public boolean isFileExists(ENodi eNodi) throws NodiException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String pathStorageActive() throws NodiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String genFileDest(String nomeFile) {
		String result = null;

		result = mdNodi.getRsync();
		result += splitFolder(nomeFile);
		result += nomeFile;
		return result;
	}

	@Override
	public boolean copyFile(File fSend, long lengthFile, String md5Ori, ENodi eNodi) throws NodiException {
		boolean result = false;
		
		try {
			RSync.send(
					Configuration.getValue("md.sendRsync.path"), 
					mdNodi.getRsyncPassword(), 
					RSync.checkPath(fSend), 
					genFileDest(eNodi));
			result = true;
		} catch (RSyncException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new NodiException(e.getMessage(), e);
		}

		return result;
	}

	@Override
	public Storage checkStorage(Documenti documenti) throws NodiException {
		CheckStorageMDPortTypeProxy proxy = null;
		Storage storage = null;
		String wsdlCheckMD = null;
		
		try {
			wsdlCheckMD = mdNodi.getUrlCheckStorage();
			if (wsdlCheckMD.toLowerCase().trim().startsWith("https")){
				Protocol.registerProtocol("https", 
						new Protocol("https", new DefaultProtocolSocketFactory(), 443));
			}
			proxy = new CheckStorageMDPortTypeProxy(wsdlCheckMD);
			storage = proxy.checkStorageMDOperation(documenti);
		} catch (RemoteException e) {
			throw new NodiException(e.getMessage(), e);
		}
		return storage;
	}

	@Override
	public Documenti genDocumenti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(ENodi eNodi) {
		// TODO Auto-generated method stub
		return null;
	}

}
