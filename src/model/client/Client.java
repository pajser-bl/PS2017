package model.client;

public class Client {
	private int ID_client;
	private String name, surname;
	private String phone_number;
	
	
	public int getID_client() {
		return ID_client;
	}
	public void setID_client(int iD_client) {
		ID_client = iD_client;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Client(int iD_client, String name, String surname, String phone_number) {
		super();
		ID_client = iD_client;
		this.name = name;
		this.surname = surname;
		this.phone_number = phone_number;
	}
	public Client( String name, String surname, String phone_number) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone_number = phone_number;
	}
	

}
