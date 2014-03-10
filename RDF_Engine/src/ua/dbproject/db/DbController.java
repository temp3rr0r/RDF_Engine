package ua.dbproject.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Concrete Class used to 
 * */
public class DbController {

	private Connection connection = null;
	private String connectionInfo = null;
	private String databaseSchemaName = null;
	private String databaseUserName = null;
	private String databasePassword = null;
	private String port = null;
	private String ip = null;
	private Statement stmt = null;
	
	/** Default DbController constructor which calls the extended
	 * public DbController(String inIp, String inPort, String inDatabaseSchemaName, String inDatabaseUserName, String inDatabasePassword)
	 * constructor. Remarks: Local PostgreSQL instance uses different port (5433 vs the default 5432)*/
	public DbController() {
		this("127.0.0.1", "5433", "dbProject", "madks", "ma121284");
	}
	
	/** Custom DbController constructor with the ability to set custom details i.e */
	public DbController(String inIp, String inPort, String inDatabaseSchemaName, String inDatabaseUserName, String inDatabasePassword) {
		port = inPort;
		ip = inIp;
		databaseSchemaName = inDatabaseSchemaName;
		connectionInfo = String.format("jdbc:postgresql://%s:%s/%s", ip, port, databaseSchemaName);
		databaseUserName = inDatabaseUserName;
		databasePassword = inDatabasePassword;
	}
	
	private boolean getJDBCDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	/** Do connect with the locally set db instance/engine */
	public boolean connect() {		
		if (getJDBCDriver()) {
			try {
				connection = DriverManager.getConnection(connectionInfo, databaseUserName, databasePassword);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			if(connection != null)				
				return true;
			else
				return false;
		}
		
		return true;
	}
	
	/** Execute a SQL update-type command, with no expected result set i.e INSERT, UPDATE, etc */
	public boolean executeUpdate(String query) {
		try {
			if (connection != null) {
				stmt = connection.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
				//connection.commit();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	
	/** Perform a SELECT string query and get the ResultSet in a ArrayList of Triplet objects */
	public ArrayList<Triple> getTriplets(String query) {
		ArrayList<Triple> tripletList = new ArrayList<Triple>();
		try {
			if (connection != null) {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					tripletList.add(new Triple(rs.getString("subject"), rs.getString("predicate"), rs.getString("object")));
				}
				rs.close();
				stmt.close();
				//connection.commit();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tripletList;
	}	
	
	/** Close the connection with the database instance */
	public void close() throws SQLException {
		connection.close();
	}
	
	/** Not implemented yet. Should return the Last Value/Result set that was generated within the database engine. */
	public String selectLastVal() {
		//TODO: Do implement "SELECT LASTVALUE();" functionality		
		String lastValue = "";
		return lastValue;
	}

	
}
