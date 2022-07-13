package com.skillstorm.doas;

import java.util.List;

import com.skillstorm.models.Employee;

public interface EmployeeDAO {
	// create employee
	public Employee create(Employee employee);
	
	// find by first name
	public Employee findByFirstName(String firstName);
	
	// find by last name
	public Employee findByLastName(String lastName);

	// find by id
	public Employee findByid(int employeeId);

	// find by warehouse
	public List<Employee> findByWarehouseId(int warehouseId);
	
	// update
	public Employee update(Employee employee);
	
	// delete by id
	public void delete(int employeeId);
}
