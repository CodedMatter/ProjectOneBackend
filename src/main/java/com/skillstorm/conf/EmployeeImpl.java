package com.skillstorm.conf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.models.Employee;

public class EmployeeImpl implements EmployeeDAO {

	@Override
	public Employee create(Employee employee) {
		String sql = "Insert Into employee (first_name, last_name, warehouse_id) "
				+ " Values (?, ?, ?)";
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setInt(3, employee.getWarehouseId());
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					Employee newEmployee = new Employee();
					newEmployee.setId(key);
					newEmployee.setFirstName(employee.getFirstName());
					newEmployee.setLastName(employee.getLastName());
					newEmployee.setWarehouseId(employee.getWarehouseId());
					return newEmployee;
				}
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByid(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByWarehouseId(int warehouseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee update(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int employeeId) {
		// TODO Auto-generated method stub

	}
	
	private Employee mapResultSet(ResultSet result) throws SQLException {
		Employee employee = new Employee();
		employee.setId(result.getInt("employee_id"));
		employee.setFirstName(result.getString("first_name"));
		employee.setLastName(result.getString("last_name"));
		employee.setWarehouseId(result.getInt("warehouse_id"));
		return employee;
		
	}

}
