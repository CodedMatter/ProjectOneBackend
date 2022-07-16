package com.skillstorm.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.conf.ProjectDBCreds;
import com.skillstorm.models.Company;

public class CompanyImpl implements CompanyDAO {

	@Override
	public Company create(Company company) {
		String sql = "Insert Into company (name,description) Values (?,?)";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getDescription());
			int rowsAffected = stmt.executeUpdate();
			
			if(rowsAffected != 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					Company newCompany = new Company();
					newCompany.setId(key);
					newCompany.setName(company.getName());
					newCompany.setDescription(company.getDescription());
					return newCompany;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company findById(int id) {
		String sql = "Select * From company Where company_id = " + id;
		
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

	@Override
	public Company findByName(String name) {
		String sql = "Select * From company Where name = ?";
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				return mapResultSet(result);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company update(Company company) {
		String sql = "UPDATE company "
				+ "SET name = ?, description = ? "
				+ "WHERE company_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getDescription());
			stmt.setInt(3, company.getId());
			stmt.executeUpdate();
			return company;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM company WHERE company_id = ?;";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Company mapResultSet(ResultSet result) throws SQLException {
		Company company = new Company();
		company.setId(result.getInt("company_id"));
		company.setName(result.getString("name"));
		company.setDescription(result.getString("description"));
		return company;
	}

	@Override
	public List<Company> findAll() {
		LinkedList<Company> companies = new LinkedList<>();
		String sql = "Select * From company";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				companies.add(mapResultSet(result));
			}
			return companies;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
