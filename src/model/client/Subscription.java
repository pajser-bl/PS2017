package model.client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription {
	private int ID_subscription, ID_client;
	private LocalDate start_date, end_date;

	public boolean isValid() {
		LocalDateTime now = LocalDateTime.now();
		return now.isBefore(end_date.atStartOfDay()) && now.isAfter(start_date.atStartOfDay());
	}

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

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public Subscription(int iD_subscription, int iD_client, LocalDate start_date, LocalDate end_date) {
		super();
		ID_subscription = iD_subscription;
		ID_client = iD_client;
		this.start_date = start_date;
		this.end_date = end_date;
	}

}
