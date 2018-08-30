package model.users;
import java.time.LocalDateTime;

public class User {

	private String name;
	private String surname;
	private int userID;
	private LocalDateTime employment_date;
	private LocalDateTime date_of_birth;
	private String qualification;
	
	
	
	@Override
	public String toString() {
		String s = "";
		s += "Ime: " + name + "\nPrezime: " + surname;
		s += "\nKorisnicki ID: " + userID + "\nDatum zaposlenja: " + employment_date.toString()
		+ "\nStrucna sprema: " + qualification;
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

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}
	
	public void setEmploymentDate(LocalDateTime employment_date) {
		this.employment_date = employment_date;
	}
	
	public LocalDateTime getEmployment_date() {
		return employment_date;
	}

	public LocalDateTime getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDateTime date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public User(String name, String surname, int userID, LocalDateTime employment_date, LocalDateTime date_of_birth,
			String qualification) {
		super();
		this.name = name;
		this.surname = surname;
		this.userID = userID;
		this.employment_date = employment_date;
		this.date_of_birth = date_of_birth;
		this.qualification = qualification;
	}

	
	
}
