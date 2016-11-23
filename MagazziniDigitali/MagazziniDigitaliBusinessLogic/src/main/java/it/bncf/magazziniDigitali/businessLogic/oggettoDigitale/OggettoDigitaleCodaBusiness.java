package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.registroIngresso.MDRegistroIngressoBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDRegistroIngresso;
import mx.randalf.hibernate.exception.HibernateUtilException;

public class OggettoDigitaleCodaBusiness extends OggettoDigitaleBusiness{

	
	public Logger log = Logger.getLogger(OggettoDigitaleCodaBusiness.class);

	public OggettoDigitaleCodaBusiness() {
		super();
	}

	/**
	 * Questo metodo viene utilizzato per gestire la coda del materiale da inviare negli altri Storage
	 * 
	 * @param application
	 */
	public void coda(Logger logCoda, 
			IMDConfiguration<?> configuration)
					throws MDConfigurationException, SQLException{
		MDRegistroIngressoBusiness mdRegistroIngresso = null;
		List<MDRegistroIngresso> rs = null;
		String data = "";
		boolean elabora = false;
		GregorianCalendar gc = null;
		try {
			gc = new GregorianCalendar();
			gc.add(Calendar.DAY_OF_MONTH, -1);
			gc.set(Calendar.HOUR_OF_DAY, 23);
			gc.set(Calendar.MINUTE, 59);
			gc.set(Calendar.SECOND, 59);
			gc.set(Calendar.MILLISECOND, 999);
			logCoda.debug("Ricerco oggetti da mettere in coda");
			mdRegistroIngresso = new MDRegistroIngressoBusiness();
			
			rs = mdRegistroIngresso.findCoda();
			if (rs != null && 
					rs.size()>0){
				for (int x=0; x<rs.size(); x++){
					elabora = false;
					if (rs.get(x).getTimestampPub()!= null){
						if (rs.get(x).getTimestampPub().getTime()<=gc.getTimeInMillis()){
							data = componiData(rs.get(x).getTimestampPub());
							elabora = true;
						}
					} else {
						if (rs.get(x).getTimestampElab().getTime()<=gc.getTimeInMillis()){
							data = componiData(rs.get(x).getTimestampElab());
							elabora = true;
						}
					}
					if (elabora){
						writeFileCoda(data, rs.get(x).getContainerName(), configuration);
						mdRegistroIngresso.coda(rs.get(x).getId());
					}
				}
			}
		} catch (HibernateException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (FileNotFoundException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (NamingException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (MDConfigurationException e) {
			throw e;
		} catch (IOException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			throw new SQLException(e.getMessage(),e);
		} catch (HibernateUtilException e) {
			throw new SQLException(e.getMessage(),e);
		}
	}

	/**
	 * Metodo utilizzato per scrivere il file Coda
	 */
	private void writeFileCoda(String data, String containerName, IMDConfiguration<?> configuration) 
			throws FileNotFoundException, MDConfigurationException,
			IOException{
		File coda = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		FileWriter fw = null;
		FileReader fr = null;
		String line = "";
		boolean trovato = false;
		
		try {
			coda = new File(configuration.getSoftwareConfigString("coda.path")+
//					Configuration.getValue("demoni.Coda.path")+
					File.separator+data+".coda");
			
			if (coda.exists()){
				try {
					fr = new FileReader(coda);
					br = new BufferedReader(fr);
					while((line= br.readLine()) != null){
						if (line.equals(containerName)){
							trovato = true;
							break;
						}
					}
				} catch (FileNotFoundException e) {
					throw e;
				} catch (IOException e) {
					throw e;
				} finally {
					try {
						if (br != null){
							br.close();
						}
						if (fr != null){
							fr.close();
						}
					} catch (IOException e) {
						throw e;
					}
				}
			}
			if (! trovato){
				try {
					if (!coda.getParentFile().exists()){
						if (!coda.getParentFile().mkdirs()){
							throw new IOException("Riscontrato un problema nella creazione della cartella ["+coda.getParentFile().getAbsolutePath()+"]");
						}
					}
					fw = new FileWriter(coda, true);
					bw = new BufferedWriter(fw);
					bw.write(containerName+"\n");
				} catch (IOException e) {
					throw e;
				} finally {
					try {
						if (bw != null){
							bw.flush();
							bw.close();
						}
						if (fw != null){
							fw.close();
						}
					} catch (IOException e) {
						throw e;
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Metodo utilizzato per la compilazione della Data
	 * 
	 * @param data
	 * @return
	 */
	private String componiData(Date data){
		GregorianCalendar gc = null;
		String result = "";
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		
		gc = new GregorianCalendar();
		gc.setTimeInMillis(data.getTime());
		result = df4.format(gc.get(Calendar.YEAR));
		result += df2.format(gc.get(Calendar.MONTH)+1);
		result += df2.format(gc.get(Calendar.DAY_OF_MONTH));
		return result;
	}
}
