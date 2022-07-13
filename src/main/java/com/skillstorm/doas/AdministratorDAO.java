package com.skillstorm.doas;

import com.skillstorm.models.Administrator;

public interface AdministratorDAO {
	// create 
	public Administrator create(Administrator administrator);
	
	// update
	public Administrator update(Administrator administrator);
	
	// delete by id
	public void delete(int id);
	

}
