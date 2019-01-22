package model.interventions;

import java.time.LocalDateTime;

public class Intervention {

	private int ID_intervention;
	private int ID_client;
	private int ID_vehicle;
	private int ID_user_opened;
	private int ID_field_technician;
	private LocalDateTime opened_on;
	private String state;
	
	private String assistance;
	private LocalDateTime time_of_assistance;
	private String remark_field_technician;
	
	private int ID_user_closed;
	private LocalDateTime closed_on;
	private String remark_operator;
	
	private int ID_supervisor;
	private String remark_supervisor;
	private LocalDateTime report_made;
	

	public Intervention(int iD_user_opened, int iD_field_technician,
			LocalDateTime opened_on,String state) {
		super();
		this.ID_field_technician = iD_field_technician;
		this.ID_user_opened = iD_user_opened;
		this.opened_on = opened_on;
		this.state=state;
	}	

	

	public Intervention(int iD_intervention, int iD_client, int iD_vehicle, int iD_user_opened, int iD_field_technician,
			LocalDateTime opened_on, String state, String assistance, LocalDateTime time_of_assistance,
			String remark_field_technician, int iD_user_closed, LocalDateTime closed_on, String remark_operator,
			int iD_supervisor, String remark_supervisor, LocalDateTime report_made) {
		super();
		ID_intervention = iD_intervention;
		ID_client = iD_client;
		ID_vehicle = iD_vehicle;
		ID_user_opened = iD_user_opened;
		ID_field_technician = iD_field_technician;
		this.opened_on = opened_on;
		this.state = state;
		this.assistance = assistance;
		this.time_of_assistance = time_of_assistance;
		this.remark_field_technician = remark_field_technician;
		ID_user_closed = iD_user_closed;
		this.closed_on = closed_on;
		this.remark_operator = remark_operator;
		ID_supervisor = iD_supervisor;
		this.remark_supervisor = remark_supervisor;
		this.report_made = report_made;
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

	public int getID_field_technician() {
		return ID_field_technician;
	}

	public void setID_field_technician(int iD_field_technician) {
		ID_field_technician = iD_field_technician;
	}

	public LocalDateTime getOpened_on() {
		return opened_on;
	}

	public void setOpened_on(LocalDateTime opened_on) {
		this.opened_on = opened_on;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAssistance() {
		return assistance;
	}

	public void setAssistance(String assistance) {
		this.assistance = assistance;
	}

	public LocalDateTime getTime_of_assistance() {
		return time_of_assistance;
	}

	public void setTime_of_assistance(LocalDateTime time_of_assistance) {
		this.time_of_assistance = time_of_assistance;
	}

	public String getRemark_field_technician() {
		return remark_field_technician;
	}

	public void setRemark_field_technician(String remark_field_technician) {
		this.remark_field_technician = remark_field_technician;
	}

	public LocalDateTime getClosed_on() {
		return closed_on;
	}

	public void setClosed_on(LocalDateTime closed_on) {
		this.closed_on = closed_on;
	}

	public String getRemark_operator() {
		return remark_operator;
	}

	public void setRemark_operator(String remark_operator) {
		this.remark_operator = remark_operator;
	}

	public int getID_supervisor() {
		return ID_supervisor;
	}

	public void setID_supervisor(int iD_supervisor) {
		ID_supervisor = iD_supervisor;
	}

	public String getRemark_supervisor() {
		return remark_supervisor;
	}

	public void setRemark_supervisor(String remark_supervisor) {
		this.remark_supervisor = remark_supervisor;
	}

	public LocalDateTime getReport_made() {
		return report_made;
	}

	public void setReport_made(LocalDateTime report_made) {
		this.report_made = report_made;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervention other = (Intervention) obj;
		if (ID_intervention != other.ID_intervention)
			return false;
		return true;
	}
}
