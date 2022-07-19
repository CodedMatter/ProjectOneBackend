package com.skillstorm.doas;

import com.skillstorm.models.LoginProfile;

public interface LoginProfileDAO {
	
	// create profile
	public LoginProfile create(LoginProfile loginProfile);
	
	// get profile by username and password
	public LoginProfile get(String username, String password);
	
	// delete profile by admin id
	public void delete(int adminId);
}
