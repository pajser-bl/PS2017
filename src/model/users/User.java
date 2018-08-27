package model.users;
import java.time.LocalDateTime;

public class User {
	private static int userID;
	private LocalDateTime employmentDate;
	private String qualification;
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}
	
	public void setEmploymentDate(LocalDateTime employmentDate) {
		this.employmentDate = employmentDate;
	}
	
	public LocalDateTime getEmploymentDate() {
		return employmentDate;
	}
	
	public void setQualifications(String qualifications) {
		this.qualification = qualifications;
	}
	
	public String getQualification( ) {
		return qualification;
	}
}
