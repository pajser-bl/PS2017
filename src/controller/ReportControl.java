package controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import DAO.ReportDAO;
import DAO.RoadReportDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import DAO.ClientDAO;
import DAO.InterventionDAO;
import model.Vehicle;
import model.interventions.Intervention;
import model.interventions.Report;
import model.interventions.RoadReport;
import model.users.Client;
import model.users.User;
import utility.TimeUtility;
public class ReportControl {

	public static ArrayList<String> newReport(int ID_report,int ID_intervention,int ID_user,String remark,LocalDateTime closed_on,ReportDAO reportDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (reportDAO.insert(new Report(ID_report, ID_intervention, ID_user, remark, closed_on)) != 0) {
			reply.add("NEW ROADREPORT OK");
		} else {
			reply.add("NEW ROADREPORT FAILED");
		}
		return reply;
	}
	// vrijeme poziva klijenta vrijeme pristizanja terenskog izvjestaja 	podaci o intervenciji	otvorio vrijeme otvaranja
	// zatvorio vrijeme zatvaranja podaci o klijentu ime prezime broj telefona podaci o vozilu registarski broj proizvadjac i godina porizvodnje
	// napomena 
	public static ArrayList<String> viewReport(int report_ID, InterventionDAO interventionDAO,RoadReportDAO roadReportDAO,ReportDAO reportDAO,ClientDAO clientDAO,UserDAO userDAO,VehicleDAO vehicleDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Intervention i=interventionDAO.select(report_ID);
		RoadReport rR=roadReportDAO.select(report_ID);
		Report r = reportDAO.select(report_ID);
		User opener=userDAO.select(i.getID_user_opened());
		User closer=userDAO.select(i.getID_user_opened());
		User roadTechnitian=userDAO.select(rR.getID_user());
		User supervisor=userDAO.select(r.getID_user());
		Vehicle vehicle=vehicleDAO.select(i.getID_vehicle());
		Client client=clientDAO.select(i.getID_client());
		
		reply.add(""+r.getID_report());
		reply.add(""+r.getID_user());
		reply.add(""+r.getID_intervention());
		reply.add(r.getRemark());
		reply.add(TimeUtility.localDateTimeToString(r.getClosed_on()));

		return reply;
	}
	// datum otvaranja klijent ko je otvorio 
	//ko je terenski radnik, ko je napravio report
	public static ArrayList<String> viewReports(InterventionDAO interventionDAO,RoadReportDAO roadReportDAO,ReportDAO reportDAO,ClientDAO clientDAO,UserDAO userDAO) {
		ArrayList<String>reply = new ArrayList<>();
		ArrayList<Intervention> interventions= (ArrayList<Intervention>) interventionDAO.selectAll();
		ArrayList<RoadReport> roadReports= (ArrayList<RoadReport>) roadReportDAO.selectAll();
		ArrayList<Report> reports = (ArrayList<Report>) reportDAO.selectAll();
		reply.add("VIEW REPORTS");
		reply.add(""+reports.size());
		for(Report r : reports) {
			int ID_report= r.getID_report();
			Intervention i=interventions.get(interventions.indexOf(new Intervention(ID_report,ID_report, ID_report, ID_report, null,null,"", false)));
			RoadReport rR=roadReports.get(roadReports.indexOf(new RoadReport(ID_report,ID_report,ID_report,null,null,null)));
			Client client = clientDAO.select(i.getID_client());
			User operater=userDAO.select(i.getID_user_closed());
			User terenskiRadnik=userDAO.select(rR.getID_user());
			User supervizor=userDAO.select(r.getID_user());
			String clientString =client.getName()+" "+client.getSurname();
			String operaterString=operater.getName()+" "+operater.getSurname();
			String terenskiRadnikString=terenskiRadnik.getName()+" "+terenskiRadnik.getSurname();
			String supervizorString=supervizor.getName()+" "+supervizor.getSurname();
			String tempString=ID_report+":"+clientString+":"+i.getOpened_on()+":"+operaterString+":"+terenskiRadnikString+":"+supervizorString;
			reply.add(tempString);
		}
		return reply;
	}

	
}
