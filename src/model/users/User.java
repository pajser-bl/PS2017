package model.users;

import java.time.LocalDate;

public class User {

	private int ID_user;
	private String name;
	private String surname;
	private LocalDate date_of_birth;
	private String type;
	private String qualification;
	private String drivers_license;
	private String username;
	private String hash;

	
	public boolean equals(Object o) {
		if(!o.getClass().equals(this.getClass()))
			return false;
		if(((User)o).getID_user()==this.ID_user)
			return true;
		return false;
	}
	
	public User(int iD_user, String name, String surname, LocalDate date_of_birth, String type, String qualification, String drivers_license) {
		super();
		this.ID_user = iD_user;
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.setType(type);
		this.qualification = qualification;
		this.drivers_license = drivers_license;
	}

	public User(String name, String surname, LocalDate date_of_birth, String type, String qualification, String drivers_license) {
		super();
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.setType(type);
		this.qualification = qualification;
		this.drivers_license = drivers_license;
	}

	public User(int iD_user, String name, String surname, LocalDate date_of_birth, String type, String qualification,
			String drivers_license, String username, String hash) {
		super();
		ID_user = iD_user;
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.type = type;
		this.qualification = qualification;
		this.drivers_license = drivers_license;
		this.username = username;
		this.hash = hash;
	}
	
	public User( String name, String surname, LocalDate date_of_birth, String type, String qualification,
			String drivers_license, String username, String hash) {
		super();
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.type = type;
		this.qualification = qualification;
		this.drivers_license = drivers_license;
		this.username = username;
		this.hash = hash;
	}
	public User(int iD_user, String name, String surname, LocalDate date_of_birth, String type, String qualification,
			String drivers_license, String username) {
		super();
		ID_user = iD_user;
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.type = type;
		this.qualification = qualification;
		this.drivers_license = drivers_license;
		this.username = username;
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

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getQualification() {
		return qualification;
	}

	public void setID_user(int ID_user) {
		this.ID_user = ID_user;
	}

	public int getID_user() {
		return ID_user;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDrivers_license() {
		return drivers_license;
	}

	public void setDrivers_license(String drivers_license) {
		this.drivers_license = drivers_license;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
