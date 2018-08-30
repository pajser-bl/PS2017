package test;

import DAO.MySQLUserDAO;
import DAO.MySQLVehicleDAO;
import DAO.ReportDAO;
import DAO.RoadReportDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import model.users.Client;
import model.users.User;
import utility.TimeUtility;
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
		UserDAO userDAO = new MySQLUserDAO();
		ClientDAO clientDAO = new MySQLClientDAO();
		SubscriptionDAO subDAO = new MySQLSubscriptionDAO();
		CredentialsDAO credDAO = new MySQLCredentialsDAO();
		EventDAO eventDAO = new MySQLEventDAO();
		SessionDAO sessionDAO = new MySQLSessionDAO();
		InterventionCoordinateDAO coordinateDAO = new MySQLInterventionCoordinateDAO();
		InterventionDAO interventionDAO = new MySQLInterventionDAO();
		ReportDAO reportDAO = new MySQLReportDAO();
		RoadReportDAO roadReportDAO = new MySQLRoadReportDAO();
		VehicleDAO vehicleDAO = new MySQLVehicleDAO();
		
		User u1=new User(1,"A", "A", TimeUtility.stringToLocalDate("1990-10-1"), "Administrator","vss");
		userDAO.insert(u1);
		
		
		
		clientDAO.insert(new Client(2,"Marko", "Markovic", "65656656565"));
		clientDAO.insert(new Client(3, "M", "M", "2737747"));
		clientDAO.update(new Client(2,"Marko", "Markovic", "565757575757"));
		for(Client c : clientDAO.selectAll()) {
			System.out.println(c.toString());
		}
		clientDAO.delete(2);
		
		subDAO.insert(new Subsription(5, 2, TimeUtility.stringToLocalDate("2010-12-4")), TimeUtility.stringToLocalDate("2013-5-6"));
		
		
		
		
		
		
		
		
		
	}
}
