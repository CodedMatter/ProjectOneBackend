package com.skillstorm;

import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.doas.EmployeeImpl;
import com.skillstorm.models.Employee;

public class Driver {
	
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeImpl();
		
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setLastName("John");
		employee.setWarehouseId(1);
		employee.setId(2);
//		System.out.println(dao.create(employee));
		dao.update(employee);
		System.out.println(dao.findAll());
		
		
	}
}
	