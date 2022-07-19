package com.skillstorm.doas;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.skillstorm.conf.ProjectDBCreds;
import com.skillstorm.models.LoginProfile;

public class LoginImpl implements LoginProfileDAO {

	@Override
	public LoginProfile create(LoginProfile loginProfile) {
		String sql = "Insert Into login (username, password, administrator_id) "
				+ "Values (?, ?, ?)";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, loginProfile.getUsername());
			stmt.setString(2, loginProfile.getPassword());
			stmt.setInt(3, loginProfile.getAdministratorId());
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected != 0) {
				return loginProfile;
			}
		}
		catch(SQLException e) {
			e.printStackTrace()	;
		}
		return null;
	}

	@Override
	public LoginProfile get(String username, String password) {
		String sql = "Select * From login Where username = ? And password = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				LoginProfile profile = new LoginProfile();
				profile.setUsername(result.getString("username"));
				profile.setPassword(result.getString("password"));
				profile.setAdministratorId(result.getInt("administrator_id"));
				return profile;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int adminId) {
		String sql = "DELETE FROM login WHERE administrator_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, adminId);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
