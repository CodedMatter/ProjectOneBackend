package com.skillstorm.doas;

import java.util.List;

import com.skillstorm.models.Warehouse;

public interface WarehouseDAO {
	
	// create a warehouse
	public Warehouse create(Warehouse warehouseToCreate);
	
	// get all warehouses
	public List<Warehouse> findAll();
	
	// get warehouse by id
	public Warehouse findById(int id);
	
	// get warehouses by admin
	public List<Warehouse> findByAdminId(int id);
	
	// update warehouse
	public void update(Warehouse warehouse);
	
	// delete warehouse
	public void deleteById (int id);
	
	
}
