package com.skillstorm.models;

public class Administrator {
	private int id;
	private String firstName;
	private String lastName;
	
	public Administrator () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
