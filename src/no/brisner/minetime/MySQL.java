package no.brisner.minetime;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Andreas Brisner aka arensirb
 *
 */

public class MySQL {
	
	protected enum Statements {
		SELECT, INSERT, UPDATE, DELETE, DO, REPLACE, LOAD, HANDLER, CALL, // Data manipulation statements
		CREATE, ALTER, DROP, TRUNCATE, RENAME  // Data definition statements
	}
	
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	DatabaseMetaData dbm;
	Statement stmt;
	
	private String hostname = "localhost";
	private String port = "3306";
	private String username = "minecraft";
	private String password = "";
	private String database = "minecraft";
	
	/**
	 *
	 * @param hostname Host of database server
	 * @param portnmbr Port of database server
	 * @param database Database on server
	 * @param username Username for database server
	 * @param password Password for database server
	 */
	public MySQL(String hostname,
			 String portnmbr,
			 String database,
			 String username,
			 String password) {
	this.hostname = hostname;
	this.port = portnmbr;
	this.database = database;
	this.username = username;
	this.password = password;
}

	public boolean initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Check that server's Java has MySQL support.
			return true;
	    } catch (ClassNotFoundException e) {
	    	return false;
	    }
	}
	/**
	 * 
	 * @return Attempt to open a new connection.
	 * 
	 */
	public boolean open() {
		if(initialize()) {
			try {
				String url = String.format("jdbc:mysql://%s:%s/%s",hostname,port,database);
				Minetime.log.info("URL IS: " + url);
				Minetime.log.info("Username: "+ username);
				Minetime.log.info("Password: "+ password);
			
				conn = DriverManager.getConnection(url,username,password);
				return true;
			} catch ( SQLException e) {
				e.printStackTrace();
				return false;
			}
		}	
		return false;
	}
	/**
	 * 
	 * @return Attempt to close an open connection
	 */
	public boolean close() {
		try {
			conn.close();
			return true;
		} catch ( SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public void prepare(String sql) {
		try {
			ps = conn.prepareStatement(sql);
		} catch ( SQLException e) {
			e.printStackTrace();
		}
				
	}
	public void setString(int index, String string) {
		try {
			ps.setString(index, string);
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	public void setInt(int index, int a) {
		try {
			ps.setInt(index, a);
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void query() {
		try{
			rs = ps.executeQuery();
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	public int queryUpdate() {
		try {
			return ps.executeUpdate();
		} catch ( SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public boolean query(String sql) {
		Minetime.log.info("String: " + sql);
		try {
			stmt = conn.createStatement();
			switch (this.getStatement(sql)) {
		    case SELECT:
		    	Minetime.log.info("String: " + sql);
			    rs = stmt.executeQuery(sql);
			    return true;
		    
		    default:
		    	stmt.executeUpdate(sql);
		    	return true;
	    }
		} catch ( SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean next() {
		try {
			rs.next();
			return true;
		} catch ( SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void getColumn(String columnLabel) {
		try {
			rs.getString(columnLabel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	public void getArray(String columnLabel) {
		try{
			rs.getArray(columnLabel);
		} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	public void getInt(String columnLabel) {
		try {
			rs.getInt(columnLabel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkTable(String table) {
		try {
			dbm = conn.getMetaData();
			rs = dbm.getTables(null, null, table, null);
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
	protected Statements getStatement(String query) {
		String trimmedQuery = query.trim();
		if (trimmedQuery.substring(0,6).equalsIgnoreCase("SELECT"))
			return Statements.SELECT;
		else if (trimmedQuery.substring(0,6).equalsIgnoreCase("INSERT"))
			return Statements.INSERT;
		else if (trimmedQuery.substring(0,6).equalsIgnoreCase("UPDATE"))
			return Statements.UPDATE;
		else if (trimmedQuery.substring(0,6).equalsIgnoreCase("DELETE"))
			return Statements.DELETE;
		else if (trimmedQuery.substring(0,6).equalsIgnoreCase("CREATE"))
			return Statements.CREATE;
		else if (trimmedQuery.substring(0,5).equalsIgnoreCase("ALTER"))
			return Statements.ALTER;
		else if (trimmedQuery.substring(0,4).equalsIgnoreCase("DROP"))
			return Statements.DROP;
		else if (trimmedQuery.substring(0,8).equalsIgnoreCase("TRUNCATE"))
			return Statements.TRUNCATE;
		else if (trimmedQuery.substring(0,6).equalsIgnoreCase("RENAME"))
			return Statements.RENAME;
		else if (trimmedQuery.substring(0,2).equalsIgnoreCase("DO"))
			return Statements.DO;
		else if (trimmedQuery.substring(0,7).equalsIgnoreCase("REPLACE"))
			return Statements.REPLACE;
		else if (trimmedQuery.substring(0,4).equalsIgnoreCase("LOAD"))
			return Statements.LOAD;
		else if (trimmedQuery.substring(0,7).equalsIgnoreCase("HANDLER"))
			return Statements.HANDLER;
		else if (trimmedQuery.substring(0,4).equalsIgnoreCase("CALL"))
			return Statements.CALL;
		else
			return Statements.SELECT;
	}
}
