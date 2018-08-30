package model.users;

import java.time.LocalDateTime;

public class Session {

	private int sessionID;
	private int userID;
	private LocalDateTime start, end;

	public Session(int userID) {
		this.userID = userID;
	}
	
	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public Session(int sessionID, int userID, LocalDateTime start, LocalDateTime end) {
		super();
		this.sessionID = sessionID;
		this.userID = userID;
		this.start = start;
		this.end = end;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}


	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Session(int userID, LocalDateTime start, LocalDateTime end) {
		super();
		this.userID = userID;
		this.start = start;
		this.end = end;
	}

}
