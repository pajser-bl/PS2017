package model.interventions;

import java.time.LocalDateTime;

public class Report {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (ID_report != other.ID_report)
			return false;
		return true;
	}

	int ID_report;
	int ID_user;
	int ID_intervention;
	String remark;
	LocalDateTime closed_on;
	
	public Report(int iD_report,  int iD_intervention,int iD_user, String remark, LocalDateTime closed_on) {
		super();
		ID_report = iD_report;
		ID_user = iD_user;
		ID_intervention = iD_intervention;
		this.remark = remark;
		this.closed_on = closed_on;
	}

	public int getID_report() {
		return ID_report;
	}

	public void setID_report(int iD_report) {
		ID_report = iD_report;
	}

	public int getID_user() {
		return ID_user;
	}

	public void setID_user(int iD_user) {
		ID_user = iD_user;
	}

	public int getID_intervention() {
		return ID_intervention;
	}

	public void setID_intervention(int iD_intervention) {
		ID_intervention = iD_intervention;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getClosed_on() {
		return closed_on;
	}

	public void setClosed_on(LocalDateTime closed_on) {
		this.closed_on = closed_on;
	}
	

}
