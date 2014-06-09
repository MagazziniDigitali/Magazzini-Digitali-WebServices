/**
 * 
 */
package it.bncf.magazzimiDigitali.databaseSchema.sqlite;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

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

	/* (non-Javadoc)
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
			               " REGISTROERRORI VARCHAR(255)" +
			               " ); "
			               ;
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void insert(String id, String originalContainerName, String containerName, String containerFingerPrint,
			int containerType, String agentMachineIngest,
			String agentSoftwareIngest, GregorianCalendar timeStampInit)
			throws SQLException {
		Statement stmt = null;
		String sql = null;
		GregorianCalendar gc = null;
		
		try {
			stmt = conn.createStatement();

			gc = new GregorianCalendar();
			sql = "INSERT INTO MDRegistroIngresso " +
					        "(ID, TIMESTAMPINGEST, AGENTDEPOSITOR, ORIGINALCONTAINERNAME, CONTAINERNAME, "+
					        " CONTAINERFINGERPRINT, CONTAINERTYPE, AGENTMACHINEINGEST, AGENTSOFTWAREINGEST, " +
					        " STATUS, TIMESTAMPINIT)"+
					"VALUES ('"+id+"', '"+convert(gc)+"', '"+originalContainerName+"', '"+containerName+"', " +
							"'"+containerFingerPrint+"', "+containerType+", '"+agentMachineIngest+"', '"+agentSoftwareIngest+"', " +
							"0, '"+convert(timeStampInit)+"')";
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

			sql = "UPDATE MDFilesTmp " +
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

	public void error(String id, String registroErrori)
			throws SQLException {
		Statement stmt = null;
		String sql = null;
		
		try {
			stmt = conn.createStatement();

			sql = "UPDATE MDFilesTmp " +
					 "SET STATUS=-1, REGISTROERRORI='"+registroErrori+"' "+
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
}
