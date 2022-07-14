package com.skillstorm.doas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.conf.ProjectDBCreds;
import com.skillstorm.models.Administrator;

public class AdministrationImpl implements AdministratorDAO {

	@Override
	public Administrator create(Administrator administrator) {
		
		return null;
	}

	@Override
	public Administrator update(Administrator administrator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Administrator> findAll() {		
		
		LinkedList<Administrator> administrators = new LinkedList<>();
		String sql = "SELECT * FROM administrator";
		
		// connection will auto close in the event of failure
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			
			// Create a statement using the connection object 
			Statement stmt = conn.createStatement();
			
			// executing the query  returns a ResultSet which contains all of the values returned
			ResultSet result = stmt.executeQuery(sql);
			
			// next() returns a boolean on whether the iterator is done yet
			// you need to advance the cursor with it so that you can parse all of the results
			while (result.next()) {
				Administrator administrator = mapResultSet(result);
				administrators.add(administrator);
			}
			
			return administrators;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Administrator mapResultSet(ResultSet result) throws SQLException {
		Administrator administrator = new Administrator();
		administrator.setId(result.getInt("administrator_id"));
		administrator.setFirstName(result.getString("first_name"));
		administrator.setLastName(result.getString("last_name"));
		return administrator;
		
	}

}
