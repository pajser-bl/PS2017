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
import model.users.Event;
import model.users.Session;
import model.users.Subscription;
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

import java.util.Scanner;

import DAO.ClientDAO;
import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.InterventionCoordinateDAO;
import DAO.InterventionDAO;

public class MySQLTest {
	public static void main(String[] args) {
		//skener za sledeci dio testa
		Scanner scan=new Scanner(System.in);
		
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

		
		
		
		
		
//		System.out.println("USER TEST");
//		System.out.println("test:user-insert:");
//		User u1 = new User(1, "A", "A", TimeUtility.stringToLocalDate("1990-10-01"), "Administrator", "vss");
//		User u2 = new User(2, "G", "G", TimeUtility.stringToLocalDate("1990-10-02"), "Supervizor", "vss");
//		scan.nextLine();
//		System.out.println(userDAO.insert(u1));
//		System.out.println(userDAO.insert(u2));
//		System.out.println("test:user-update:");
//		scan.nextLine();
//		System.out.println(userDAO.update(new User(1, "M", "M", TimeUtility.stringToLocalDate("1990-10-01"), "Administrator", "vss")));
//		System.out.println("test:user-delete:");
//		scan.nextLine();
//		System.out.println(userDAO.delete(1));
//		System.out.println("test:user-select:");
//		scan.nextLine();
//		System.out.println(userDAO.select(2));
		
//		System.out.println("CLIENT TEST");
//		System.out.println("test:client-insert:");
//		scan.nextLine();
//		System.out.println(clientDAO.insert(new Client(2,"Marko", "Markovic", "65656656565")));
//		System.out.println(clientDAO.insert(new Client(3, "M", "M", "2737747")));
//		System.out.println("test:client-update:");
//		scan.nextLine();
//		System.out.println(clientDAO.update(new Client(2,"Marko", "Markovic", "565757575757")));
//		System.out.println("test:client-delete:");
//		scan.nextLine();
//		System.out.println(clientDAO.delete(2));
//		System.out.println("test:client-select:");
//		scan.nextLine();
//		System.out.println(clientDAO.select(3));
		
//		System.out.println("SUBSCRIPTION TEST");
//		System.out.println("test:subscription-insert:");
//		scan.nextLine();
//		System.out.println(subDAO.insert(new Subscription(1,3,TimeUtility.stringToLocalDate("2000-12-31"),TimeUtility.stringToLocalDate("2001-12-31"))));
//		System.out.println(subDAO.insert(new Subscription(2,3,TimeUtility.stringToLocalDate("2001-12-31"),TimeUtility.stringToLocalDate("2002-12-31"))));
//		System.out.println("test:subscription-update:");
//		scan.nextLine();
//		System.out.println(subDAO.update(new Subscription(1,3,TimeUtility.stringToLocalDate("2000-11-31"),TimeUtility.stringToLocalDate("2001-11-31"))));
//		System.out.println("test:subscription-delete:");
//		scan.nextLine();
//		System.out.println(subDAO.delete(1));
//		System.out.println("test:subscription-select:");
//		scan.nextLine();
//		System.out.println(subDAO.select(2));
		
		
//		System.out.println("SESSION TEST");
//		System.out.println("test:session-insert:");
//		scan.nextLine();
//		int i;
//		System.out.println(sessionDAO.insert(new Session(1,2,TimeUtility.stringToLocalDateTime("2018-08-31 11:37:00"))));
//		System.out.println(i=sessionDAO.insert(new Session(2,2,TimeUtility.stringToLocalDateTime("2018-08-31 01:40:00"))));
//		System.out.println("test:session-update:");
//		scan.nextLine();
//		System.out.println(sessionDAO.update(new Session(i,2,TimeUtility.stringToLocalDateTime("2018-08-31 01:40:00"),TimeUtility.getLDTNow())));
//		System.out.println("test:session-delete:");
//		scan.nextLine();
//		System.out.println(sessionDAO.delete(i));
//		System.out.println("test:session-select:");
//		scan.nextLine();
//		System.out.println(sessionDAO.select(i-1));
//		
		
		
//		System.out.println("EVENT TEST");
//		System.out.println("test:event-insert:");
//		scan.nextLine();
//		System.out.println(eventDAO.insert(new Event(1,1,TimeUtility.stringToLocalDateTime("2018-08-31 03:37:00"),"A")));
//		System.out.println(eventDAO.insert(new Event(2,1,TimeUtility.stringToLocalDateTime("2018-08-31 01:37:00"),"b")));
//		System.out.println("test:event-update:");
//		scan.nextLine();
//		System.out.println(eventDAO.update(new Event(2,1,TimeUtility.stringToLocalDateTime("2018-08-31 01:37:00"),"C")));
//		System.out.println("test:event-delete:");
//		scan.nextLine();
//		System.out.println(eventDAO.delete(1));
//		System.out.println("test:event-select:");
//		scan.nextLine();
//		System.out.println(eventDAO.select(2));
		
		
		scan.close();
	}
}
