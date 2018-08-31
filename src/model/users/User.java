package model.users;
import java.time.LocalDate;

public class User {

	private int ID_user;
	private String name;
	private String surname;
	private LocalDate date_of_birth;
	private String type;
	private String qualification;
	
	public User(int iD_user, String name, String surname, LocalDate date_of_birth, String type, String qualification) {
		super();
		this.ID_user = iD_user;
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.setType(type);
		this.qualification = qualification;
	}
	public User( String name, String surname, LocalDate date_of_birth, String type, String qualification) {
		super();
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.setType(type);
		this.qualification = qualification;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Ime: " + name + "\nPrezime: " + surname;
		s += "\nKorisnicki ID: " + ID_user + "\nTip: " + type +  "\nStrucna sprema: " + qualification;
		return s;
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

	public String getQualification( ) {
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
	
}
