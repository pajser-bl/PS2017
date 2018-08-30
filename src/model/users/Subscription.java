package model.users;

import java.time.LocalDateTime;

public class Subscription {
	private int ID_subscription, ID_client;
	private LocalDateTime start_date, end_date;
	public int getID_subscription() {
		return ID_subscription;
	}
	public void setID_subscription(int iD_subscription) {
		ID_subscription = iD_subscription;
	}
	public int getID_client() {
		return ID_client;
	}
	public void setID_client(int iD_client) {
		ID_client = iD_client;
	}
	public LocalDateTime getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}
	public LocalDateTime getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}
	public Subscription(int iD_subscription, int iD_client, LocalDateTime start_date, LocalDateTime end_date) {
		super();
		ID_subscription = iD_subscription;
		ID_client = iD_client;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public Subscription(int iD_subscription, LocalDateTime start_date, LocalDateTime end_date) {
		super();
		ID_subscription = iD_subscription;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	
}
