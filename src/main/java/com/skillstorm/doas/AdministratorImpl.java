package com.skillstorm.doas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.conf.ProjectDBCreds;
import com.skillstorm.models.Administrator;

public class AdministratorImpl implements AdministratorDAO {

	@Override
	public Administrator create(Administrator administrator) {
		String sql = "Insert Into administrator(first_name,last_name) Values (?,?)";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, administrator.getFirstName());
			stmt.setString(2, administrator.getLastName());
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					Administrator newAdmin = new Administrator();
					newAdmin.setId(key);
					newAdmin.setFirstName(administrator.getFirstName());
					newAdmin.setLastName(administrator.getLastName());
					return newAdmin;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Administrator update(Administrator administrator) {
		String sql = "UPDATE administrator "
				+ "SET first_name = ?, last_name = ? "
				+ "WHERE administrator_id = ?";
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, administrator.getFirstName());
			stmt.setString(2, administrator.getLastName());
			stmt.setInt(3, administrator.getId());
			stmt.executeUpdate();
			return administrator;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM administrator WHERE administrator_id = ?;";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

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

	@Override
	public Administrator findById(int id) {
		String sql = "Select * From administrator Where administrator_id = " + id;
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if(result.next()) {
				return mapResultSet(result);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
