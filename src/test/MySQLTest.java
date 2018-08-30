package test;

import DAO.MySQLUserDAO;
import DAO.MySQLVehicleDAO;
import DAO.ReportDAO;
import DAO.RoadReportDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import client.Client;
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
		

		userDAO.insert(new User("A", "A", 1, TimeUtility.stringToLocalDate("1999-12-10") , "vss"));
		userDAO.select(1);
		System.out.println(userDAO);
		
		
		clientDAO.insert(new Client(2,"Marko", "Markovic", "65656656565"));
		clientDAO.insert(new Client(3, "M", "M", "2737747"));
		clientDAO.update(new Client(2,"Marko", "Markovic", "565757575757"));
		
		
		
		
		

		user.insert(new User("A", "A", 1, new LocalDate(1990, 11, 1), "vss"));

		
		
		
		
		
		
		
		
		
		
		
		
	}
}
