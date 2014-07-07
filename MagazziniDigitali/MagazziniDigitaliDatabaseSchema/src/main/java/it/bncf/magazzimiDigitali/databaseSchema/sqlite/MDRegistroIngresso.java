/**
 * 
 */
package it.bncf.magazzimiDigitali.databaseSchema.sqlite;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.UUID;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

/**
 * @author massi
 *
 */
public class MDRegistroIngresso extends SqliteCore {

	/**
	 * @param fileDb
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConfigurationException
	 */
	public MDRegistroIngresso() throws FileNotFoundException,
			ClassNotFoundException, SQLException, ConfigurationException {
		super(Configuration.getValue("db.MDRegistroIngresso"));
	}

	/**
	 * @see it.bncf.magazzimiDigitali.databaseSchema.sqlite.SqliteCore#initDb()
	 */
	@Override
	protected void initDb() throws SQLException {
		Statement stmt = null;
		String sql  = null;

		try {
			stmt = conn.createStatement();
			sql = "CREATE TABLE MDRegistroIngresso " +
			               "(ID INT PRIMARY KEY     NOT NULL," +
			               " TIMESTAMPINGEST TIMESTAMP NOT NULL, " + 
			               " AGENTDEPOSITOR VARCHAR(255) NOT NULL, " + 
			               " ORIGINALCONTAINERNAME VARCHAR(255) NOT NULL, " + 
			               " CONTAINERNAME VARCHAR(100) NOT NULL," +
			               " CONTAINERFINGERPRINT VARCHAR(50) NOT NULL, " + 
			               " CONTAINERFINGERPRINTCHAIN VARCHAR(100) NULL, " + 
			               " CONTAINERTYPE INTEGER NOT NULL," +
			               " AGENTMACHINEINGEST VARCHAR(100) NOT NULL," +
			               " AGENTSOFTWAREINGEST VRCHAR(100) NOT NULL, " +
			               " STATUS INTEGER NOT NULL, "+
			               " TIMESTAMPINIT TIMESTAMP," +
			               " TIMESTAMPELAB TIMESTAMP," +
			               " TIMESTAMPPUB TIMESTAMP, "+
			               " TIMESTAMPERR TIMESTAMP " +
			               " ); "+
							"CREATE INDEX MDRegistroIngresso01 on MDRegistroIngresso(ID); "+
							"CREATE INDEX MDRegistroIngresso02 on MDRegistroIngresso(TIMESTAMPINGEST); "
											               ;
						sql += "CREATE TABLE MDRegistroIngressoError " +
					               "(ID VARCHAR(36) PRIMARY KEY     NOT NULL," +
					               " ID_MDREGISTROINGRESSO VARCHAR(36) NOT NULL, " + 
					               " DATA_INS TIMESTAMP NOT NULL," +
					               " TYPE VARCHAR(10) NOT NULL, " + 
					               " MSGERROR VARCHAR(500) NOT NULL "+
					               " ); "+
					       "CREATE INDEX MDRegistroIngressoError01 on MDRegistroIngressoError(ID_MDREGISTROINGRESSO); "
			               ;
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Metodo utilizzato per la ricerca tramite lo Sha1
	 * 
	 * @param sha1
	 * @return
	 * @throws SQLException
	 */
	private String findLastKey() throws SQLException{
		String res = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	    try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery( "SELECT CONTAINERFINGERPRINT " + 
			                          "FROM MDRegistroIngresso " +
			                        "ORDER BY TIMESTAMPINGEST desc;" );
			if (rs.next()){
				res = rs.getString("CONTAINERFINGERPRINT");
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

	/**
	 * 
	 * @param id
	 * @param originalContainerName
	 * @param containerName
	 * @param containerFingerPrint
	 * @param containerType
	 * @param agentMachineIngest
	 * @param agentSoftwareIngest
	 * @param timeStampInit
	 * @throws SQLException
	 */
	public void insert(String id, GregorianCalendar timeStampIngest, String agentDepositor, String originalContainerName, String containerName, 
			String containerFingerPrint,
			int containerType, String agentMachineIngest,
			String agentSoftwareIngest, String timeStampInit)
			throws SQLException {
		Statement stmt = null;
		String sql = null;
		String containerFingerPrintChain = null;
		
		try {
			containerFingerPrintChain = findLastKey();
			if (containerFingerPrintChain != null){
				containerFingerPrintChain = containerFingerPrint+containerFingerPrintChain;
			}
			stmt = conn.createStatement();

			sql = "INSERT INTO MDRegistroIngresso " +
					        "(ID, TIMESTAMPINGEST, AGENTDEPOSITOR, ORIGINALCONTAINERNAME, CONTAINERNAME, "+
					        " CONTAINERFINGERPRINT, CONTAINERFINGERPRINTCHAIN, CONTAINERTYPE, AGENTMACHINEINGEST, AGENTSOFTWAREINGEST, " +
					        " STATUS, TIMESTAMPINIT)"+
					"VALUES ('"+id+"', '"+convert(timeStampIngest)+"', '"+agentDepositor+"','"+originalContainerName+"', '"+containerName+"', " +
							"'"+containerFingerPrint+"', '"+containerFingerPrintChain+"', "+containerType+", '"+agentMachineIngest+"', '"+agentSoftwareIngest+"', " +
							"0, '"+timeStampInit+"'); "
					;
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
				
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage()+" - "+sql, e);
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

	public void archiviato(String id, GregorianCalendar timeStampElab)
			throws SQLException {
		Statement stmt = null;
		String sql = null;
		
		try {
			stmt = conn.createStatement();

			sql = "UPDATE MDRegistroIngresso " +
					 "SET STATUS=1, TIMESTAMPELAB='"+convert(timeStampElab)+"' "+
				   "WHERE ID='"+id+"'";
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
	}

	public void pubblicato(String id, GregorianCalendar timeStampPub)
			throws SQLException {
		Statement stmt = null;
		String sql = null;
		
		try {
			stmt = conn.createStatement();

			sql = "UPDATE MDRegistroIngresso " +
					 "SET STATUS=2, TIMESTAMPPUB='"+convert(timeStampPub)+"' "+
				   "WHERE ID='"+id+"'";
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
	}

	public void error(String id, String type, String[] registroErrori)
			throws SQLException {
		Statement stmt = null;
		String sql = null;
		
		try {
			stmt = conn.createStatement();

			sql = "UPDATE MDRegistroIngresso " +
					 "SET STATUS=-1 "+
				   "WHERE ID='"+id+"'";
			if (stmt.executeUpdate(sql)==0){
				throw new SQLException("Riscontrato un problema nell'inserimento del record nella tabella");
			} else {
				for(int x=0; x<registroErrori.length; x++){
					insertNewError(id,  type, registroErrori[x]);
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
	
	/**
	 * Metodo utilizzato per l'inserimento di un nuovo record nella tabella
	 * 
	 * @param idIstituto Identicativo dell'Istituto
	 * @param nomeFile Nome del file originale
	 * @param sha1 Chiave Sha1 del file originale
	 * @param nomeFileMod Data dell'ultima modifica del file originale
	 * @return Identificativo dell'oggetto digitale
	 * @throws SQLException Eccezione SQL
	 */
	private String insertNewError(String idMdFileTmp, String type, String msgError) throws SQLException{
		Statement stmt = null;
		String sql = null;
		String id = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			id = UUID.randomUUID().toString();
			gc = new GregorianCalendar();
			sql = "INSERT INTO MDRegistroIngressoError" +
					"(ID, ID_MDREGISTROINGRESSO, DATA_INS, TYPE, MSGERROR) " +
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
}
