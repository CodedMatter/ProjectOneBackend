package com.skillstorm.doas;

import java.util.List;

import com.skillstorm.models.Administrator;

public interface AdministratorDAO {
	// create 
	public Administrator create(Administrator administrator);
	
	// find all
	public List<Administrator> findAll();
	
	// find by id
	public Administrator findById(int id);
	
	// update
	public Administrator update(Administrator administrator);
	
	// delete by id
	public void delete(int id);
	
}
