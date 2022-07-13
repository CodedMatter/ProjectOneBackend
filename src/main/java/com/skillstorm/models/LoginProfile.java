package com.skillstorm.models;

public class LoginProfile {
	private int id;
	private String username;
	private String password;
	private int administratorId;
	
	public LoginProfile() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdministratorId() {
		return administratorId;
	}
	public void setAdministratorId(int administratorId) {
		this.administratorId = administratorId;
	}

	@Override
	public String toString() {
		return "LoginProfile [id=" + id + ", username=" + username + ", password=" + password + ", administratorId="
				+ administratorId + "]";
	}
	
	
	
}
