package model.users;

import java.time.LocalDateTime;

public class Event {

	private int ID_event;
	private int ID_session;
	private LocalDateTime timeStamp;
	private String action;
	
	public Event(int iD_event, int iD_session, LocalDateTime timeStamp, String action) {
		super();
		ID_event = iD_event;
		ID_session = iD_session;
		this.timeStamp = timeStamp;
		this.action = action;
	}
	public Event( int iD_session, LocalDateTime timeStamp, String action) {
		super();
		ID_session = iD_session;
		this.timeStamp = timeStamp;
		this.action = action;
	}
	
	public Event(LocalDateTime timeStamp, String action) {
		this.timeStamp = timeStamp; 
		this.action = action;
	}
	public Event() {}
	
	@Override
	public String toString() {
		String s = "Vrijeme : " + timeStamp + "; Dogadjaj: " + action;
		return s;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public int getID_event() {
		return ID_event;
	}

	public void setID_event(int iD_event) {
		ID_event = iD_event;
	}

	public int getID_session() {
		return ID_session;
	}

	public void setID_session(int iD_sesion) {
		ID_session = iD_sesion;
	}
	
	
}
