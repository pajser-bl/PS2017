package model;

import java.time.Year;

public class Vehicle {
	private int ID_vehicle;
	private String registration;
	private String manufacturer;
	private String model;
	private Year year;
	
	public Vehicle() {}
	
	public Vehicle(String registration, String manufacturer, String model, Year year) {
		super();
		this.registration = registration;
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
	}


	public Vehicle(int iD_vehicle, String registration, String manufacturer, String model, Year year) {
		super();
		ID_vehicle = iD_vehicle;
		this.registration = registration;
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
	}


	public int getID_vehicle() {
		return ID_vehicle;
	}
	public void setID_vehicle(int iD_vehicle) {
		ID_vehicle = iD_vehicle;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	
	
}
