package com.skillstorm.doas;

import java.util.List;

import com.skillstorm.models.Item;

public interface ItemDAO {
	// create an item
	public Item create(Item itemToCreate);
	
	//  find item by id
	public Item findById(int itemId);

	// find item by name
	public Item findByName(String itemName);

	
	// find all items in a warehouse
	public List<Item> findByWarehouseId(int warehouseId);
	
	// update item details
	public Item update(Item itemToUpdate);
	
	// delete item by id
	public void delete(int id);
}
