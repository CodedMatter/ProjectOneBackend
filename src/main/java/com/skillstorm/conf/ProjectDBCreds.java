package com.skillstorm.conf;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProjectDBCreds {
	
	private static ProjectDBCreds instance;
	private String url;
	private String username;
	private String password;
	
	
	/**
	 * Looks for the application.properties file and assign the url, username, and password
	 * 
	 */
	private ProjectDBCreds () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// read the properties (key/value) from the application.properties file
			try(InputStream input = ProjectDBCreds.class.getClassLoader()
					.getResourceAsStream("application.properties")) {
				// properties object
				Properties props = new Properties();
				props.load(input); 
				// Load in the file we opened 
				// grab the keys i want
				this.url = props.getProperty("db.url");
				this.username = props.getProperty("db.username");
				this.password = props.getProperty("db.password");
				

			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates and returns a connection to the database
	 * @return an object with url, username, password  for the project database
	 */
	public static ProjectDBCreds getInstance() {
		if(instance == null) instance = new ProjectDBCreds();
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
}
