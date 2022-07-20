package com.skillstorm.models;

public class Login {
	private int administratorId;
	private String username;
	private String password;
	
	public Login() {
		
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
		return "Login [administratorId=" + administratorId + ", username=" + username + ", password=" + password
				+ "]";
	}

	
}
