package com.skillstorm.doas;

import com.skillstorm.models.Login;

public interface LoginDAO {
	
	// create profile
	public Login create(Login loginProfile);
	
	// get profile by username and password
	public Login get(String username, String password);
	
	// delete profile by admin id
	public void delete(int adminId);
}
