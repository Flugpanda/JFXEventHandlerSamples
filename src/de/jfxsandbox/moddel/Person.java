package de.jfxsandbox.moddel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A very basic model class for a person
 * 
 * @author Bastian Bräunel
 *
 */
public class Person {

	private StringProperty firstName;
	private StringProperty lastName;
	
	/**
	 * Constructor for creating a person object with the given Name
	 * 
	 * @param fistName	the first name
	 * @param lastName	the last name
	 */
	public Person(String fistName, String lastName) {
		this.firstName = new SimpleStringProperty(fistName);
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastName() {
		return this.lastName;
	}
}
