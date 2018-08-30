package test;

import DAO.MySQLUserDAO;
import DAO.MySQLVehicleDAO;
import DAO.ReportDAO;
import DAO.RoadReportDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import DAO.MySQLClientDAO;
import DAO.MySQLCredentialsDAO;
import DAO.MySQLEventDAO;
import DAO.MySQLInterventionCoordinateDAO;
import DAO.MySQLInterventionDAO;
import DAO.MySQLReportDAO;
import DAO.MySQLRoadReportDAO;
import DAO.MySQLSessionDAO;
import DAO.MySQLSubscriptionDAO;

import java.time.LocalDate;

import DAO.ClientDAO;
import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.InterventionCoordinateDAO;
import DAO.InterventionDAO;

public class MySQLTest {
	public static void main(String [] args) {
		UserDAO user = new MySQLUserDAO();
		ClientDAO client = new MySQLClientDAO();
		SubscriptionDAO sub = new MySQLSubscriptionDAO();
		CredentialsDAO cred = new MySQLCredentialsDAO();
		EventDAO event = new MySQLEventDAO();
		SessionDAO session = new MySQLSessionDAO();
		InterventionCoordinateDAO coordinate = new MySQLInterventionCoordinateDAO();
		InterventionDAO intervention = new MySQLInterventionDAO();
		ReportDAO report = new MySQLReportDAO();
		RoadReportDAO roadReport = new MySQLRoadReportDAO();
		VehicleDAO vehicle = new MySQLVehicleDAO();
		
		user.insert(new User("A", "A", 1, new LocalDate(1990, 11, 1), "vss");
		
		
		
		
		
		
		
		
		
		
		
		
	}
}