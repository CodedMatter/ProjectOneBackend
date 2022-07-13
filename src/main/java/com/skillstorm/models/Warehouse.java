package com.skillstorm.models;

public class Warehouse {
	private int id;
	private String name;
	private int capacity;
	private int companyId;
	private int numOfEmployees;
	
	public Warehouse() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getNumOfEmployees() {
		return numOfEmployees;
	}

	public void setNumOfEmployees(int numOfEmployees) {
		this.numOfEmployees = numOfEmployees;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", name=" + name + ", capacity=" + capacity + ", companyId=" + companyId
				+ ", numOfEmployees=" + numOfEmployees + "]";
	}
	
	
}
