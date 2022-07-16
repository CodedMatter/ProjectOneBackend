package com.skillstorm.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import com.skillstorm.conf.ProjectDBCreds;
import com.skillstorm.models.Warehouse;

public class WarehouseImpl implements WarehouseDAO {

	@Override
	public Warehouse create(Warehouse warehouseToCreate) {
		String sql = "Insert Into warehouse (name, item_capacity, company_id, number_of_employees, administrator_id) "
				+ "Values(?, ?, ?, ?, ?)";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, warehouseToCreate.getName());
			stmt.setInt(2, warehouseToCreate.getCapacity());
			stmt.setInt(3, warehouseToCreate.getCompanyId());
			stmt.setInt(4, warehouseToCreate.getNumOfEmployees());
			stmt.setInt(5, warehouseToCreate.getAdministratorId());
			int rowsEffected = stmt.executeUpdate();
			if(rowsEffected != 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next()) {
					int key = keys.getInt(1);
					Warehouse newWarehouse = new Warehouse();
					newWarehouse.setId(key);
					newWarehouse.setName(warehouseToCreate.getName());
					newWarehouse.setCapacity(warehouseToCreate.getCapacity());
					newWarehouse.setCompanyId(warehouseToCreate.getCompanyId());
					newWarehouse.setNumOfEmployees(warehouseToCreate.getNumOfEmployees());
					newWarehouse.setAdministratorId(warehouseToCreate.getAdministratorId());
					return newWarehouse;
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Warehouse> findAll() {
		LinkedList<Warehouse> warehouses = new LinkedList<>();
		String sql = "Select * From warehouse";
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				warehouses.add(mapResultSet(result));
			}
			return warehouses;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Warehouse findById(int id) {
		String sql = "Select * From warehouse Where warehouse_id = " + id;
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
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
	public List<Warehouse> findByAdminId(int id) {
		LinkedList<Warehouse> warehouses = new LinkedList<>();
		String sql = "Select * From warehouse Where administrator_id = " + id;
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				warehouses.add(mapResultSet(result));
			}
			return warehouses;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Warehouse warehouse) {
		String sql = "UPDATE warehouse "
				+ "SET name = ?, item_capacity = ?, company_id = ?, "
				+ "number_of_employees = ?,  administrator_id = ? "
				+ "WHERE warehouse_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, warehouse.getName());
			stmt.setInt(2, warehouse.getCapacity());
			stmt.setInt(3, warehouse.getCompanyId());
			stmt.setInt(4, warehouse.getNumOfEmployees());
			stmt.setInt(5, warehouse.getAdministratorId());
			stmt.setInt(6, warehouse.getId());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(int id) {
		String sql = "Delete From warehouse Where warehouse_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	private Warehouse mapResultSet(ResultSet result) throws SQLException {
		Warehouse warehouse = new Warehouse();
		warehouse.setId(result.getInt("warehouse_id"));
		warehouse.setName(result.getString("name"));
		warehouse.setCapacity(result.getInt("item_capacity"));
		warehouse.setCompanyId(result.getInt("company_id"));
		warehouse.setNumOfEmployees(result.getInt("number_of_employees"));
		warehouse.setAdministratorId(result.getInt("administrator_id"));
		return warehouse;
	}

}
