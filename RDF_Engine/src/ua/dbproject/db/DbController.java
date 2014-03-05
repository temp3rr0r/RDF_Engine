package ua.dbproject.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbController {

	private Connection connection = null;
	private String connectionInfo = null;
	private String databaseSchemaName = null;
	private String databaseUserName = null;
	private String databasePassword = null;
	private String port = null;
	private String ip = null;
	private Statement stmt = null;
	
	public DbController() {
		this("127.0.0.1", "5433", "dbProject", "madks", "ma121284");
	}
	
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
	

	public ArrayList<Triplet> getTriplets(String query) {
		ArrayList<Triplet> tripletList = new ArrayList<Triplet>();
		try {
			if (connection != null) {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					tripletList.add(new Triplet(rs.getString("subject"), rs.getString("predicate"), rs.getString("object")));
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
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public String selectLastVal() {
		String lastValue = "";
		return lastValue;
	}

	
}
