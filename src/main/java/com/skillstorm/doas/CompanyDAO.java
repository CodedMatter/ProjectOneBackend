package com.skillstorm.doas;

import com.skillstorm.models.Company;

public interface CompanyDAO {
	// create 
	public Company create (Company company);
	
	// find by id
	public Company findById(int id);
	
	// find by name
	public Company findByName(String name);
	
	// update
	public Company update(Company company);
	
	// delete by id
	public void delete(int id);
}
