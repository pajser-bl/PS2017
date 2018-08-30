package model.interventions;

import java.time.LocalDateTime;

public class Intervention {
	
    int ID_intervention;
	int ID_client;
	int ID_vehicle;
	int ID_user_opened;
	int ID_user_closed;
	LocalDateTime opened_on;
	LocalDateTime closed_on;
	boolean closed;
	String remark;
	
	
	public Intervention(int iD_client, int iD_vehicle, int iD_user_opened, int iD_user_closed, LocalDateTime opened_on,
			LocalDateTime closed_on, String remark, boolean closed) {
		super();
		ID_client = iD_client;
		ID_vehicle = iD_vehicle;
		ID_user_opened = iD_user_opened;
		this.opened_on = opened_on;
		this.closed = closed;
		this.remark = remark;
	}

	public Intervention(int iD_intervention, int iD_client, int iD_vehicle, int iD_user_opened, int iD_user_closed,
			LocalDateTime opened_on, LocalDateTime closed_on, String remark, boolean closed) {
		super();
		ID_intervention = iD_intervention;
		ID_client = iD_client;
		ID_vehicle = iD_vehicle;
		ID_user_opened = iD_user_opened;
		ID_user_closed = iD_user_closed;
		this.opened_on = opened_on;
		this.closed_on = closed_on;
		this.closed = closed;
		this.remark = remark;
	}
	
	public int getID_intervention() {
		return ID_intervention;
	}
	public void setID_intervention(int iD_intervention) {
		ID_intervention = iD_intervention;
	}
	public int getID_client() {
		return ID_client;
	}
	public void setID_client(int iD_client) {
		ID_client = iD_client;
	}
	public int getID_vehicle() {
		return ID_vehicle;
	}
	public void setID_vehicle(int iD_vehicle) {
		ID_vehicle = iD_vehicle;
	}
	public int getID_user_opened() {
		return ID_user_opened;
	}
	public void setID_user_opened(int iD_user_opened) {
		ID_user_opened = iD_user_opened;
	}
	public int getID_user_closed() {
		return ID_user_closed;
	}
	public void setID_user_closed(int iD_user_closed) {
		ID_user_closed = iD_user_closed;
	}
	public LocalDateTime getOpened_on() {
		return opened_on;
	}
	public void setOpened_on(LocalDateTime opened_on) {
		this.opened_on = opened_on;
	}
	public LocalDateTime getClosed_on() {
		return closed_on;
	}
	public void setClosed_on(LocalDateTime closed_on) {
		this.closed_on = closed_on;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
