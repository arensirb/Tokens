package no.brisner.minetime;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class MySQL {
	
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	DatabaseMetaData dbm;
	Statement stmt;
	
	private String hostname = "localhost";
	private String portnmbr = "3306";
	private String username = "minecraft";
	private String password = "";
	private String database = "minecraft";
	
	public MySQL(String hostname,
			 String portnmbr,
			 String database,
			 String username,
			 String password) {
	this.hostname = hostname;
	this.portnmbr = portnmbr;
	this.database = database;
	this.username = username;
	this.password = password;
}

	public Boolean initialize() {		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Check that server's Java has MySQL support.
			return true;
	    } catch (ClassNotFoundException e) {
	    	return false;
	    }
	}
	public void open() {
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctutorial","root","root");
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			conn.close();
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	public void prepare(String sql) {
		
	}
	public void query(PreparedStatement ps) {
		
	}
	public void next() {
		try {
			rs.next();
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	public void getColumn(String columnLabel) {
		try {
			rs.getString(columnLabel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void query(String sql) {
		
	}
	
	public Boolean checkTable(String table) {
		try {
			dbm = conn.getMetaData();
			rs = dbm.getTables(null, null, "votifier", null);
			if(rs.next()) {
				// Table exists
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
