package model;

public class Vehicle {
	private int ID_vehicle;
	private String registration;
	private String manufacturer;
	private String model;
	private int year;

	public Vehicle() {
	}

	public Vehicle(String registration, String model, String manufacturer, int year) {
		super();
		this.registration = registration;
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
	}

	public Vehicle(int iD_vehicle, String registration, String model, String manufacturer, int year) {
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

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRegistration() {
		return this.registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

}
