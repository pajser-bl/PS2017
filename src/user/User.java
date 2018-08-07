package user;
import java.time.localdatetime;

public class User {
	private static int userID = null;
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
		this.qualification = qualification;
	}
	
	public String getQualification( ) {
		return qualification;
	}
}
