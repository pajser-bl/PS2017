package model.interventions;

import java.time.LocalDateTime;

public class RoadReport {
	int ID_road_report;
	int ID_user;
	int ID_intervention;
	String assistance;
	LocalDateTime time_of_assistance;
	String remark;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoadReport other = (RoadReport) obj;
		if (ID_road_report != other.ID_road_report)
			return false;
		return true;
	}
	
	public RoadReport(int iD_road_report,  int iD_intervention,int iD_user, String assistance,
			LocalDateTime time_of_assistance, String remark) {
		super();
		ID_road_report = iD_road_report;
		ID_user = iD_user;
		ID_intervention = iD_intervention;
		this.assistance = assistance;
		this.time_of_assistance = time_of_assistance;
		this.remark = remark;
	}
	public int getID_road_report() {
		return ID_road_report;
	}
	public void setID_road_report(int iD_road_report) {
		ID_road_report = iD_road_report;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
