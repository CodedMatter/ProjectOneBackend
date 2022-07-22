package com.skillstorm.doas;

import java.util.List;

import com.skillstorm.models.Item;

public interface ItemDAO {
	// create an item
	public Item create(Item itemToCreate);
	
	public List<Item> findAll();
	
	//  find item by id
	public Item findById(int itemId);

	// find item by name
	public List<Item> findByName(String itemName);
	
	// update item details
	public Item update(Item itemToUpdate);
	
	// delete item by id
	public void delete(int id);
	
	// get total amount of items in warehouse by id
	public int amountOfItemsInWarehouse(int id);
	
	// get all the items in a warehouse by id
	public List<Item> findItemsInWarehouse(int id);
	
	// gets the amount of an item that is in a warehouse
	public int amountOfSpecificItemInWarehouse(int warehouseId, int itemId);
}
