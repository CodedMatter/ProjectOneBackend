package com.skillstorm.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.skillstorm.conf.ProjectDBCreds;
import com.skillstorm.models.Item;

public class ItemImpl implements ItemDAO {

	@Override
	public Item create(Item itemToCreate) {
		String sql = "Insert Into item (name,description) Values (?, ?);";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, itemToCreate.getName());
			stmt.setString(2, itemToCreate.getDescription());
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected != 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				
				if(keys.next()) {
					int key = keys.getInt(1);
					Item newItem = new Item();
					newItem.setId(key);
					newItem.setName(itemToCreate.getName());
					newItem.setDescription(itemToCreate.getDescription());
					return newItem;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Item findById(int itemId) {
		String sql = "Select * From item Where item_id = " + itemId;
		
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
	public List<Item> findByName(String itemName) {
		LinkedList<Item> items = new LinkedList<>();
		String sql = "Select * From item Where name Like ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+itemName+"%");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				items.add(mapResultSet(result));
			}
			return items;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Item update(Item itemToUpdate) {
		String sql = "Update item "
				+ "Set name = ?, description = ? "
				+ "Where item_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, itemToUpdate.getName());
			stmt.setString(2, itemToUpdate.getDescription());
			stmt.setInt(3, itemToUpdate.getId());
			stmt.executeUpdate();
			return itemToUpdate;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		String sql = "Delete From item Where item_id = ?";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	private Item mapResultSet(ResultSet result) throws SQLException {
		Item item = new Item();
		item.setId(result.getInt("item_id"));
		item.setName(result.getString("name"));
		item.setDescription(result.getString("description"));
		return item;
	}

	@Override
	public List<Item> findAll() {
		LinkedList<Item> items = new LinkedList<>();
		String sql = "Select * From item";
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				items.add(mapResultSet(result));
			}
			return items;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int amountOfItemsInWarehouse(int id) {
		String sql = "Select sum(amount) As total From items_in_warehouse Where warehouse_id = " + id;
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if(result.next()) {
				return result.getInt("total");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Item> findItemsInWarehouse(int id) {
		LinkedList<Item> items = new LinkedList<>();
		String sql = "Select item.item_id, item.name, item.description From item "
				+ "Left Join items_in_warehouse on item.item_id = items_in_warehouse.item_id "
				+ "Where warehouse_id = " + id;
		
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				items.add(mapResultSet(result));
			}
			System.out.println("called");
			return items;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int amountOfSpecificItemInWarehouse(int warehouseId, int itemId) {
		String sql = "Select amount As total From items_in_warehouse Where warehouse_id = " + warehouseId +
				" And item_id = " + itemId;
				
		try(Connection conn = ProjectDBCreds.getInstance().getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if(result.next()) {
				return result.getInt("total");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
