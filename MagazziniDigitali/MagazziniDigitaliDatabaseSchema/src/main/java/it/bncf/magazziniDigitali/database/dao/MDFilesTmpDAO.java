/**
 * 
 */
package it.bncf.magazziniDigitali.database.dao;

import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingException;

import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.hibernate.GenericHibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author massi
 *
 */
public class MDFilesTmpDAO extends GenericHibernateDAO<MDFilesTmp, String> {

//	private Logger log = Logger.getLogger(getClass());

	/**
	 * Stato Inizio Trasferimento da Client verso Magazzini Digitali
	 */
	public static String INITTRASF = "INITTRASF";

	/**
	 * Stato Fine Trasferimento da Client verso Magazzini Digitali
	 */
	public static String FINETRASF = "FINETRASF";

	/**
	 * Stato Erore
	 */
	public static String ERROR = "ERROR";

	/**
	 * Stato Erore
	 */
	public static String ERRORTRASF = "ERRORTRASF";

	/**
	 * Stato Erore
	 */
	public static String ERRORVAL = "ERRORVAL";

	/**
	 * Stato Erore
	 */
	public static String ERRORDECOMP = "ERRORDECOMP";

	/**
	 * Stato Erore
	 */
	public static String ERRORCOPY = "ERRORCOPY";

	/**
	 * Stato Erore
	 */
	public static String ERRORMOVE = "ERRORMOVE";

	/**
	 * Stato Erore
	 */
	public static String ERRORPUB = "ERRORPUB";

	/**
	 * Stato Erore
	 */
	public static String ERRORDELETE = "ERRORDELETE";

	/**
	 * Stato Inizio Validazione dell'oggetto trasferito
	 */
	public static String INITVALID = "INITVALID";

	/**
	 * Stato Fine Validazione dell'oggetto trasferito
	 */
	public static String FINEVALID = "FINEVALID";

	/**
	 * Stato Inizio Validazione dell'oggetto trasferito
	 */
	public static String INITPUBLISH = "INITPUBLISH";

	/**
	 * Stato Fine Validazione dell'oggetto trasferito
	 */
	public static String FINEPUBLISH = "FINEPUBLISH";

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException 
	 */
	public MDFilesTmpDAO(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	@SuppressWarnings("unchecked")
	public List<MDFilesTmp> find(String idIstituto,
			String nomeFile, String[] stato, String sha1,
			List<Order> orders) throws NamingException, HibernateException,
			ConfigurationException {
		Criteria criteria = null;
		List<MDFilesTmp> result = null;

		try {
			beginTransaction();
			criteria = this.createCriteria();
			if (idIstituto != null) {
				criteria.add(Restrictions.eq("idIstituto", idIstituto));
			}
			if (sha1 != null) {
				criteria.add(Restrictions.eq("sha1", sha1));
			}
			if (nomeFile != null) {
				criteria.add(Restrictions.ilike("nomeFile", nomeFile+"%"));
			}
			if (stato != null) {
				criteria.add(Restrictions.in("stato", stato));
			}
			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			paging(criteria);
			result = criteria.list();
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw e;
		} catch (NamingException e) {
			rollbackTransaction();
			throw e;
		} catch (ConfigurationException e) {
			rollbackTransaction();
			throw e;
		}
		return result;
	}

	/**
	 * Metodo utilizzato per la rierca tramite lo Stato dell'oggetto
	 * 
	 * @param status
	 * @return
	 * @throws NamingException 
	 * @throws SQLException
	public List<MDFilesTmp> findByStatus(String[] status) throws SQLException{
		Vector<MDFilesTmp> res = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			stmt = conn.createStatement();
			sql = "SELECT * FROM MDFilesTmp Where STATO in (";
			for (int x=0; x<status.length; x++){
				sql += (x==0?"":",");
				sql += "'"+status[x]+"'";
			}
			rs = stmt.executeQuery( sql+");" );
			res = (Vector<MDFilesTmp>) convert(rs);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null){
				    rs.close();
				}
				if (stmt != null){
				    stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	    return res;
	}
	 */

	/*
	public List<MDFilesTmp> findByNomeFile(String idIstituto,
			String nomeFile) throws SQLException{
		Vector<MDFilesTmp> res = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT * "
					                + "FROM MDFilesTmp "
					               + "Where ID_ISTITUTO='"+idIstituto+"' AND "
					               		 + "NOMEFILE Like '"+nomeFile+"%';" );
			res = (Vector<MDFilesTmp>) convert(rs);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null){
				    rs.close();
				}
				if (stmt != null){
				    stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	    return res;
	}
*/

	@SuppressWarnings("rawtypes")
	public Hashtable<String, Integer> findCountByIstituto(String idIstituto) 
			throws NamingException, HibernateException, ConfigurationException {
		Criteria criteria = null;
		Hashtable<String, Integer> ris= null;
		List rs = null;
		Object[] row;
		ProjectionList projectionList = null;

		try {
			ris = new Hashtable<String, Integer>();
			beginTransaction();
			criteria = this.createCriteria();
			
			
			criteria.add(Restrictions.eq("idIstituto", idIstituto));
			
			projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("stato"));
			projectionList.add(Projections.count("stato"));
			criteria.setProjection(projectionList);		
			
			rs = criteria.list();

			if (rs !=null && rs.size()>0){
				for(int x=0; x<rs.size(); x++){
					rs.get(x);
					row = (Object[]) rs.get(x);
					ris.put((String)row[0], (Integer)row[1]);
				}
			}
			commitTransaction();
		} catch (HibernateException e) {
			rollbackTransaction();
			throw e;
		} catch (NamingException e) {
			rollbackTransaction();
			throw e;
		} catch (ConfigurationException e) {
			rollbackTransaction();
			throw e;
		}
		return ris;
	}

	/*
	public Hashtable<String, Integer> findCountByIstituto(String Istituto) throws SQLException{
		Hashtable<String, Integer> ris = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT STATO, count(*) as conta "
					                + "FROM MDFilesTmp "
					               + "Where ID_ISTITUTO='"+Istituto+"' "
					            + "GROUP BY STATO;" );
			while ( rs.next() ) {
				if (ris == null){
					ris = new Hashtable<String, Integer>();
				}
				ris.put(rs.getString("STATO"), rs.getInt("conta"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null){
				    rs.close();
				}
				if (stmt != null){
				    stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return ris;
	}
	*/

	/**
	 * Metodo utilizzato per la ricerca tramite lo Sha1
	 * 
	 * @param sha1
	 * @return
	 * @throws SQLException
	public MDFilesTmp findByID(String id) throws SQLException{
		MDFilesTmp res = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM MDFilesTmp Where ID='"+id+"';" );
			if (rs.next()){
				res = convertRecord(rs);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null){
				    rs.close();
				}
				if (stmt != null){
				    stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	    return res;
	}
	 */

	/**
	 * Metodo utilizzato per la ricerca tramite lo Sha1
	 * 
	 * @param sha1
	 * @return
	 * @throws SQLException
	public List<MDFilesTmp> findBySha1(String sha1) throws SQLException{
		Vector<MDFilesTmp> res = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM MDFilesTmp Where SHA1='"+sha1+"';" );
			res = (Vector<MDFilesTmp>) convert(rs);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null){
				    rs.close();
				}
				if (stmt != null){
				    stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	    return res;
	}
	 */

	/**
	 * Questo metodo viene utilzzato per convertore il RecordSet in lista di tipo MDFilesTmp
	 * @param rs
	 * @return
	 * @throws SQLException 
	private List<MDFilesTmp> convert(ResultSet rs) throws SQLException{
		Vector<MDFilesTmp> res = null;

		try {
			while ( rs.next() ) {
				if (res == null){
					res = new Vector<MDFilesTmp>();
				}
				res.add(convertRecord(rs));
			}
		} catch (SQLException e) {
			throw e;
		}
		return res;
	}
	 */

	/**
	 * Questo metodo viene utilzzato per convertore il RecordSet in lista di tipo MDFilesTmp
	 * @param rs
	 * @return
	 * @throws SQLException 
	private MDFilesTmp convertRecord(ResultSet rs) throws SQLException{
		MDFilesTmp record = null;

		try {
				record = new MDFilesTmp();
				record.setId(rs.getString("ID"));
				record.setIdIstituto(rs.getString("ID_ISTITUTO"));
				record.setNomeFile(rs.getString("NOMEFILE"));
				record.setSha1(rs.getString("SHA1"));
				record.setNomeFileMod(rs.getString("NOMEFILE_MOD"));
				record.setStato(rs.getString("STATO"));
				record.setTrasfDataStart(rs.getString("TRASF_DATASTART"));
				if (rs.getString("TRASF_DATAEND")!= null){
					record.setTrasfDataEnd(rs.getString("TRASF_DATAEND"));
				}
				if (rs.getObject("TRASF_ESITO")!= null){
					record.setTrasfEsito(rs.getBoolean("TRASF_ESITO"));
				}
				if (rs.getString("VALID_DATASTART")!= null){
					record.setValidDataStart(rs.getString("VALID_DATASTART"));
				}
				if (rs.getString("VALID_DATAEND")!= null){
					record.setValidDataEnd(rs.getString("VALID_DATAEND"));
				}
				if (rs.getObject("VALID_ESITO")!= null){
					record.setValidEsito(rs.getBoolean("VALID_ESITO"));
				}
				if (rs.getString("DECOMP_DATASTART")!= null){
					record.setDecompDataStart(rs.getString("DECOMP_DATASTART"));
				}
				if (rs.getString("DECOMP_DATAEND")!= null){
					record.setDecompDataEnd(rs.getString("DECOMP_DATAEND"));
				}
				if (rs.getObject("DECOMP_ESITO")!= null){
					record.setDecompEsito(rs.getBoolean("DECOMP_ESITO"));
				}
				
				if (rs.getString("PUBLISH_DATASTART")!= null){
					record.setPublishDataStart(rs.getString("PUBLISH_DATASTART"));
				}
				if (rs.getString("PUBLISH_DATAEND")!= null){
					record.setPublishDataEnd(rs.getString("PUBLISH_DATAEND"));
				}
				if (rs.getObject("PUBLISH_ESITO")!= null){
					record.setPublishEsito(rs.getBoolean("PUBLISH_ESITO"));
				}
				
				if (rs.getString("COPYPREMIS_DATASTART")!= null){
					record.setCopyPremisDataStart(rs.getString("COPYPREMIS_DATASTART"));
				}
				if (rs.getString("COPYPREMIS_DATAEND")!= null){
					record.setCopyPremisDataEnd(rs.getString("COPYPREMIS_DATAEND"));
				}
				if (rs.getObject("COPYPREMIS_ESITO")!= null){
					record.setCopyPremisEsito(rs.getBoolean("COPYPREMIS_ESITO"));
				}
				
				if (rs.getString("MOVEFILE_DATASTART")!= null){
					record.setMoveFileDataStart(rs.getString("MOVEFILE_DATASTART"));
				}
				if (rs.getString("MOVEFILE_DATAEND")!= null){
					record.setMoveFileDataEnd(rs.getString("MOVEFILE_DATAEND"));
				}
				if (rs.getObject("MOVEFILE_ESITO")!= null){
					record.setMoveFileEsito(rs.getBoolean("MOVEFILE_ESITO"));
				}

				if (rs.getString("DELETELOCAL_DATA")!= null){
					record.setDeleteLocalData(rs.getString("DELETELOCAL_DATA"));
				}
				if (rs.getObject("DELETELOCAL_ESITO")!= null){
					record.setDeleteLocalEsito(rs.getBoolean("DELETELOCAL_ESITO"));
				}
				
				if (rs.getString("XMLMIMETYPE")!= null){
					record.setXmlMimeType(rs.getString("XMLMIMETYPE"));
				}
				if (rs.getString("PREMIS_FILE")!= null){
					record.setPremisFile(rs.getString("PREMIS_FILE"));
				}
				findErrorById(rs.getString("ID"), record);
		} catch (SQLException e) {
			throw e;
		}
		return record;
	}
	 */

	/**
	 * Questo metodo viene utilizzato per ricavare la lista dei messaggi di errore
	 * @param id
	 * @return
	 * @throws SQLException
	private void findErrorById(String id, MDFilesTmp record) throws SQLException{
		MDFilesTmpError error = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT * FROM MDFilesTmpError Where ID='"+id+"';" );
			while ( rs.next() ) {
				error = new MDFilesTmpError();
				error.setId(rs.getString("ID"));
				error.setIdMdFilesTmp(rs.getString("ID_MDFILESTMP"));
				error.setDataIns(rs.getString("DATA_INS"));
				error.setType(rs.getString("TYPE"));
				error.setMsgError(rs.getString("MSGERROR"));
				record.addErrors(error);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rs != null){
				    rs.close();
				}
				if (stmt != null){
				    stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	 */
	
	/**
	 * Metodo utilizzato per l'inserimento di un nuovo record nella tabella
	 * 
	 * @param idIstituto Identicativo dell'Istituto
	 * @param nomeFile Nome del file originale
	 * @param sha1 Chiave Sha1 del file originale
	 * @param nomeFileMod Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws SQLException Eccezione SQL
	private String insertNewError(String idMdFileTmp, String type, String msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		String id = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			id = UUID.randomUUID().toString();
			gc = new GregorianCalendar();
			sql = "INSERT INTO MDFilesTmpError" +
					"(ID, ID_MDFILESTMP, DATA_INS, TYPE, MSGERROR) " +
					"VALUES ('"+id+"', '"+idMdFileTmp+"', '"+convert(gc)+"', '"+type+"', '"+msgError+"')";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
				
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return id;
	}
	 */

	/**
	 * Metodo utilizzato per l'inserimento di un nuovo record nella tabella
	 * 
	 * @param idIstituto Identicativo dell'Istituto
	 * @param nomeFile Nome del file originale
	 * @param sha1 Chiave Sha1 del file originale
	 * @param nomeFileMod Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws SQLException Eccezione SQL
	public String insertNewRec(String idIstituto, String nomeFile, String sha1, Calendar nomeFileMod) throws SQLException{
		Statement stmt = null;
		String sql = null;
		String id = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			id = UUID.randomUUID().toString();
			gc = new GregorianCalendar();
			sql = "INSERT INTO MDFilesTmp" +
					"(ID, ID_ISTITUTO, NOMEFILE, SHA1, NOMEFILE_MOD, STATO, TRASF_DATASTART) " +
					"VALUES ('"+id+"', '"+idIstituto+"', '"+nomeFile+"', '"+sha1+"', '"+convert(nomeFileMod)+"', '"+INITTRASF+"', '"+convert(gc)+"')";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
				
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return id;
	}
	 */

	/**
	 * Metodo utilizzato per indicare la Fine dell'invio dell'oggetto
	 * @param id
	 * @param esito
	 * @param msgError
	 * @throws SQLException
	public void updatEndSend(String id, boolean esito, String[] msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			gc = new GregorianCalendar();
			sql = "UPDATE MDFilesTmp " +
					"SET STATO='"+(esito?FINETRASF:ERRORTRASF)+"', " +
					    "TRASF_DATAEND='"+convert(gc)+"', "+
					    "TRASF_ESITO="+(esito?"1":"0");
			sql +=" WHERE id='"+id+"' and STATO='"+INITTRASF+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORTRASF, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		
	}
	 */

	/**
	 * Metodo utilizzato per indicare la Fine dell'invio dell'oggetto
	 * @param id
	 * @param esito
	 * @param msgError
	 * @throws SQLException
	public void confirmDel(String id, boolean esito, String[] msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			gc = new GregorianCalendar();
			sql = "UPDATE MDFilesTmp " +
					"SET DELETELOCAL_DATA='"+convert(gc)+"', "+
					    "DELETELOCAL_ESITO="+(esito?"1":"0");
			sql +=" WHERE id='"+id+"' and STATO='"+FINEPUBLISH+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORDELETE, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		
	}
	 */

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	public GregorianCalendar updateStartValidate(String id) throws SQLException{
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			gc = new GregorianCalendar();
			sql = "UPDATE MDFilesTmp " +
					"SET STATO='"+INITVALID+"', " +
					    "VALID_DATASTART='"+convert(gc)+"' ";
			sql +=" WHERE id='"+id+"' AND STATO='"+FINETRASF+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
				
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return gc;
	}
	 */

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	public GregorianCalendar updateStopValidate(String id, String xmlMimeType, boolean esito, String[] msgError, String premisFile) throws SQLException{
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			gc = new GregorianCalendar();
			sql = "UPDATE MDFilesTmp " +
					"SET STATO='"+(esito?FINEVALID:ERRORVAL)+"', " +
					    "VALID_DATAEND='"+convert(gc)+"', "+
					    "VALID_ESITO="+(esito?"1":"0");
			if (xmlMimeType != null){
				sql += ", XMLMIMETYPE='"+xmlMimeType+"'";
			}
			if (premisFile != null){
				sql += ", PREMIS_FILE='"+premisFile+"'";
			}
			sql +=" WHERE id='"+id+"' AND (STATO='"+INITVALID+"' OR "
					+ "STATO='"+FINETRASF+"')";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORVAL, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return gc;
	}
	 */

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	public GregorianCalendar updateStartPublish(String id) throws SQLException{
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			gc = new GregorianCalendar();
			sql = "UPDATE MDFilesTmp " +
					"SET STATO='"+INITPUBLISH+"', " +
					    "PUBLISH_DATASTART='"+convert(gc)+"' ";
			sql +=" WHERE id='"+id+"' AND STATO='"+FINEVALID+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
				
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return gc;
	}
	 */

	/**
	 * Metodo utilizzato per indicare l'inizio della validazione
	 * 
	 * @param id
	 * @throws SQLException
	public GregorianCalendar updateStopPublish(String id, boolean esito, String[] msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		MDRegistroIngressoDAO regIngresso= null;
		
		try {
			gc = new GregorianCalendar();
			regIngresso = new MDRegistroIngressoDAO();
			if (esito){
				regIngresso.pubblicato(id, gc);
			}else{
				regIngresso.error(id, "ERRORPUB", msgError);
			}
			stmt = conn.createStatement();

			sql = "UPDATE MDFilesTmp " +
					"SET STATO='"+(esito?FINEPUBLISH:ERRORPUB)+"', " +
					    "PUBLISH_DATAEND='"+convert(gc)+"', "+
					    "PUBLISH_ESITO="+(esito?"1":"0");
			sql +=" WHERE id='"+id+"' AND (STATO='"+INITPUBLISH+"' OR "
					+ "STATO='"+FINEVALID+"' OR "
					+ "STATO='"+ERRORPUB+"')";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORPUB, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return gc;
	}
	 */

	/**
	 * Metodo utilizzato per indicare il periodo e l'esito dell'operazione di decompressione
	 * 
	 * @param id
	 * @throws SQLException
	public void updateDecompress(String id, GregorianCalendar compressStart, GregorianCalendar compressStop, boolean esito, String[] msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		
		try {
			stmt = conn.createStatement();

			sql = "UPDATE MDFilesTmp " +
					"SET DECOMP_DATASTART='"+convert(compressStart)+"', "+
					    "DECOMP_DATAEND='"+convert(compressStop)+"', "+
					    "DECOMP_ESITO="+(esito?"1":"0");
			sql +=" WHERE id='"+id+"' AND STATO='"+INITVALID+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORDECOMP, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	 */

	/**
	 * Metodo utilizzato per indicare il periodo e l'esito della procedura di copia del file premis nello Storage
	 * 
	 * @param id
	 * @throws SQLException
	public void updateCopyPremis(String id, GregorianCalendar copyStart, 
			GregorianCalendar copyStop, boolean esito, String[] msgError, 
			String agentDepositor, String agentMachineIngest, String agentSoftwareIngest) throws SQLException{
		Statement stmt = null;
		String sql = null;
		MDFilesTmp rec = null;
		MDRegistroIngressoDAO regIngresso= null;
		int containerType = -1;
		String containerName=null;
		int pos = 0;
		
		try {
			rec = findByID(id);
			if (rec != null){
				regIngresso = new MDRegistroIngressoDAO();
				if (rec.getXmlMimeType().equals("mets")){
					containerType=5;
				}
				containerName = rec.getPremisFile();
				pos = containerName.indexOf(".");
				containerName = containerName.substring(0, pos);
				regIngresso.insert(id, copyStart, agentDepositor, rec.getNomeFile(), containerName, 
						rec.getSha1(), containerType, agentMachineIngest, agentSoftwareIngest, rec.getTrasfDataStart());
			}
			stmt = conn.createStatement();

			sql = "UPDATE MDFilesTmp " +
					"SET COPYPREMIS_DATASTART='"+convert(copyStart)+"', "+
					    "COPYPREMIS_DATAEND='"+convert(copyStop)+"', "+
					    "COPYPREMIS_ESITO="+(esito?"1":"0");
			sql +=" WHERE id='"+id+"' AND STATO='"+INITPUBLISH+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORCOPY, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	 */

	/**
	 * Metodo utilizzato per indicare il periodo e l'esito dell'operazione di decompressione
	 * 
	 * @param id
	 * @throws SQLException
	public void updateMoveFile(String id, GregorianCalendar moveFileStart, GregorianCalendar moveFileStop, boolean esito, String[] msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		MDRegistroIngressoDAO regIngresso= null;
		
		try {
			regIngresso = new MDRegistroIngressoDAO();
			if (esito){
				regIngresso.archiviato(id, moveFileStop);
			}else{
				regIngresso.error(id, "ERRORARC", msgError);
			}
			stmt = conn.createStatement();

			sql = "UPDATE MDFilesTmp " +
					"SET MOVEFILE_DATASTART='"+convert(moveFileStart)+"', "+
					    (moveFileStop== null?"":"MOVEFILE_DATAEND='"+convert(moveFileStop)+"', ")+
					    "MOVEFILE_ESITO="+(esito?"1":"0");
			sql +=" WHERE id='"+id+"' AND STATO='"+INITPUBLISH+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'aggiornamento del record nella tabella");
			} else if (msgError != null){
				for (int x=0; x<msgError.length; x++){
					insertNewError(id, ERRORMOVE, msgError[x]);
				}
			}
		} catch (SQLException e) {
			throw e;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	 */
}