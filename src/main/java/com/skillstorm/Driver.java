package com.skillstorm;

import com.skillstorm.doas.AdministratorDAO;
import com.skillstorm.doas.AdministratorImpl;
import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.doas.EmployeeImpl;
import com.skillstorm.doas.ItemDAO;
import com.skillstorm.doas.ItemImpl;
import com.skillstorm.doas.LoginImpl;
import com.skillstorm.doas.LoginDAO;
import com.skillstorm.models.Administrator;
import com.skillstorm.models.Employee;
import com.skillstorm.models.Item;
import com.skillstorm.models.Login;

public class Driver {
	
	public static void main(String[] args) {
		AdministratorDAO dao = new AdministratorImpl();
		System.out.println(dao.findById(7));
		
	}
}
	