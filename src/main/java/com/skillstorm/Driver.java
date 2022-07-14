package com.skillstorm;

import java.util.List;

import com.skillstorm.doas.AdministrationImpl;
import com.skillstorm.doas.AdministratorDAO;
import com.skillstorm.models.Administrator;

public class Driver {
	
	public static void main(String[] args) {
		AdministratorDAO dao = new AdministrationImpl();
		List<Administrator> admins = dao.findAll();
		System.out.println(admins);
	}
}
	