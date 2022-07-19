package com.skillstorm;

import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.doas.EmployeeImpl;
import com.skillstorm.doas.ItemDAO;
import com.skillstorm.doas.ItemImpl;
import com.skillstorm.doas.LoginImpl;
import com.skillstorm.doas.LoginProfileDAO;
import com.skillstorm.models.Employee;
import com.skillstorm.models.Item;
import com.skillstorm.models.LoginProfile;

public class Driver {
	
	public static void main(String[] args) {
		LoginProfileDAO dao = new LoginImpl();
		LoginProfile profile = new LoginProfile();
		profile.setAdministratorId(1);
		profile.setUsername("admin");
		profile.setPassword("pass");
		System.out.println(dao.create(profile));
		System.out.println(dao.get("admin","pass"));
		
	}
}
	