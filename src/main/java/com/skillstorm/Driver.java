package com.skillstorm;

import java.util.List;

import com.skillstorm.conf.EmployeeImpl;
import com.skillstorm.doas.AdministrationImpl;
import com.skillstorm.doas.AdministratorDAO;
import com.skillstorm.doas.CompanyDAO;
import com.skillstorm.doas.CompanyImpl;
import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.doas.WarehouseDAO;
import com.skillstorm.doas.WarehouseImpl;
import com.skillstorm.models.Administrator;
import com.skillstorm.models.Company;
import com.skillstorm.models.Employee;
import com.skillstorm.models.Warehouse;

public class Driver {
	
	public static void main(String[] args) {
//		EmployeeDAO dao = new EmployeeImpl();
//		Employee employee = new Employee();
//		employee.setFirstName("Brian");
//		employee.setLastName("Rodriguez");
//		employee.setWarehouseId(1);
//		System.out.println(dao.create(employee));
		
		WarehouseDAO dao = new WarehouseImpl();
//		Warehouse newWarehouse2 = new Warehouse();
//		newWarehouse2.setName("Warehouse 2");
//		newWarehouse2.setCapacity(200);
//		newWarehouse2.setCompanyId(1);
//		newWarehouse2.setNumOfEmployees(1000);
//		newWarehouse2.setAdministratorId(1);
//		dao.create(newWarehouse2);
//		Warehouse newWarehouse3 = new Warehouse();
//		newWarehouse3.setName("Warehouse 3");
//		newWarehouse3.setCapacity(100);
//		newWarehouse3.setCompanyId(1);
//		newWarehouse3.setNumOfEmployees(100);
//		newWarehouse3.setAdministratorId(1);
//		dao.create(newWarehouse3);

		System.out.println(dao.findByAdminId(1));
	}
}
	