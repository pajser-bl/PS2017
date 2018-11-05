package test;

import DAO.ReportDAO;
import DAO.RoadReportDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import DAO.MySQL.MySQLClientDAO;
import DAO.MySQL.MySQLCredentialsDAO;
import DAO.MySQL.MySQLEventDAO;
import DAO.MySQL.MySQLInterventionCoordinateDAO;
import DAO.MySQL.MySQLInterventionDAO;
import DAO.MySQL.MySQLReportDAO;
import DAO.MySQL.MySQLRoadReportDAO;
import DAO.MySQL.MySQLSessionDAO;
import DAO.MySQL.MySQLSubscriptionDAO;
import DAO.MySQL.MySQLUserDAO;
import DAO.MySQL.MySQLVehicleDAO;
import model.Vehicle;
import model.coordinates.InterventionCoordinate;
import model.interventions.Intervention;
import model.interventions.Report;
import model.interventions.RoadReport;
import model.users.Client;
import model.users.Credentials;
import model.users.Event;
import model.users.Session;
import model.users.Subscription;
import model.users.User;
import utility.HashHandler;
import utility.TimeUtility;

import java.time.Year;
import java.util.Scanner;

import DAO.ClientDAO;
import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.InterventionCoordinateDAO;
import DAO.InterventionDAO;

@SuppressWarnings("unused")
public class MySQLTest {
	public static void main(String[] args) {
		UserDAO userDAO=new MySQLUserDAO();
		CredentialsDAO credentialsDAO=new MySQLCredentialsDAO();
		
		String admin_username="admin";
		String admin_password="admin";
		
		//User user=new User("a","a",TimeUtility.stringToLocalDate("1990-09-09"),"administrator","vss");
//		int id_admin=userDAO.insert(user);
		Credentials credentials= new Credentials(2,2,"admin",HashHandler.createHash("admin"));
		credentialsDAO.update(credentials);

	}
}
