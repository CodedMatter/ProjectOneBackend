package com.skillstorm;

import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.doas.EmployeeImpl;
import com.skillstorm.doas.ItemDAO;
import com.skillstorm.doas.ItemImpl;
import com.skillstorm.models.Employee;
import com.skillstorm.models.Item;

public class Driver {
	
	public static void main(String[] args) {
		ItemDAO dao = new ItemImpl();
		Item newItem = new Item();
		newItem.setName("Shoes");
		newItem.setDescription("");
		newItem.setId(2);
		
//		System.out.println(dao.create(newItem));
//		System.out.println(dao.findById(2));
//		newItem.setDescription("What are those!");
		System.out.println(dao.update(newItem));
//		System.out.println(dao.findByName("Shoes"));
//		dao.delete(newItem.getId());
//		System.out.println(dao.findAll());

		
		
	}
}
	