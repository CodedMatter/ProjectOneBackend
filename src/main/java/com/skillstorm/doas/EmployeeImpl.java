package com.skillstorm.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.LinkLoopException;

import com.skillstorm.conf.ProjectDBCreds;
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
	public List<Employee> findByFirstName(String firstName) {
		LinkedList<Employee> employees = new LinkedList<>();
		String sql = "Select * From employee Where first_name Like ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+firstName+"%");
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				employees.add(mapResultSet(result));
			}
			return employees;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> findByLastName(String lastName) {
		LinkedList<Employee> employees = new LinkedList<>();
		String sql = "Select * From employee Where last_name Like ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+lastName+"%");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				employees.add(mapResultSet(result));
			}
			return employees;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee findByid(int employeeId) {
		String sql = "Select * From employee Where employee_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				return mapResultSet(result);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> findByWarehouseId(int warehouseId) {
		LinkedList<Employee> employees = new LinkedList<>();
		String sql = "Select * From employee Where warehouse_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, warehouseId);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				employees.add(mapResultSet(result));
			}
			return employees;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee update(Employee employee) {
		String sql = "Update employee "
				+ "Set first_name = ?, last_name = ?, warehouse_id = ? "
				+ "Where employee_id = ?";
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setInt(3, employee.getWarehouseId());
			stmt.setInt(4, employee.getId());
			stmt.executeUpdate();
			return employee;
		}
		catch (SQLException e) {
			
		}
		return null;
	}

	@Override
	public void delete(int employeeId) {
		String sql = "Delete From employee Where employee_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	private Employee mapResultSet(ResultSet result) throws SQLException {
		Employee employee = new Employee();
		employee.setId(result.getInt("employee_id"));
		employee.setFirstName(result.getString("first_name"));
		employee.setLastName(result.getString("last_name"));
		employee.setWarehouseId(result.getInt("warehouse_id"));
		return employee;
		
	}

	@Override
	public List<Employee> findAll() {
		LinkedList<Employee> employees = new LinkedList<>();
		String sql = "Select * From employee";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				employees.add(mapResultSet(result));
			}
			return employees;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
