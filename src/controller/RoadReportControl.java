package controller;
import java.util.ArrayList;
import DAO.RoadReportDAO;
import model.interventions.RoadReport;
import utility.TimeUtility;
public class RoadReportControl {

	public static ArrayList<String> deleteRoadReport(String ID_road_report,RoadReportDAO roadReportDAO) { // treba testirati
		ArrayList<String> reply = new ArrayList<>();
		if (roadReportDAO.delete(Integer.parseInt(ID_road_report)) != 0) {
			reply.add("DELETE ROAD REPORT OK");
		} else {
			reply.add("DELETE ROAD REPORT FAILED");
		}
		return reply;
	}

	public static ArrayList<String> viewRoadReport(String ID_road_report, RoadReportDAO roadReportDAO) { // treba testirati
		ArrayList<String> reply=new ArrayList<>();
		RoadReport r= roadReportDAO.select(Integer.parseInt(ID_road_report));
		reply.add(""+r.getID_road_report());
		reply.add(""+r.getID_user());
		reply.add(""+r.getID_intervention());
		reply.add(r.getAssistance());
		reply.add(TimeUtility.localDateTimeToString(r.getTime_of_assistance()));
		reply.add(r.getRemark());
		return reply;	
		}
	

}
