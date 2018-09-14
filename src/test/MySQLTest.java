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
		//skener za sledeci dio testa
		Scanner scan=new Scanner(System.in);
		
		UserDAO userDAO = new MySQLUserDAO();
		ClientDAO clientDAO = new MySQLClientDAO();
		SubscriptionDAO subDAO = new MySQLSubscriptionDAO();
		CredentialsDAO credentialsDAO = new MySQLCredentialsDAO();
		EventDAO eventDAO = new MySQLEventDAO();
		SessionDAO sessionDAO = new MySQLSessionDAO();
		InterventionCoordinateDAO intervenitonCoordinateDAO = new MySQLInterventionCoordinateDAO();
		InterventionDAO interventionDAO = new MySQLInterventionDAO();
		ReportDAO reportDAO = new MySQLReportDAO();
		RoadReportDAO road_reportDAO = new MySQLRoadReportDAO();
		VehicleDAO vehicleDAO = new MySQLVehicleDAO();

//		Credentials credentials = new Credentials(1, 1, "a", HashHandler.createHash("a"));
//		credentialsDAO.insert(credentials);

		System.out.println(credentialsDAO.checkUniqueUserame("a"));
		System.out.println(credentialsDAO.checkUniqueUserame("b"));
		
//		System.out.println(credentialsDAO.exists("a"));

		
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
		
//		System.out.println("CREDENTIALS TEST");
//		System.out.println("test:credentials-insert:");
//		scan.nextLine();
//		User u1 = new User("Ime1", "Prezime1", TimeUtility.stringToLocalDate("1991-11-01"), "Administrator", "vss");
//		User u2 = new User("Ime2", "Prezime2", TimeUtility.stringToLocalDate("1992-12-02"), "Supervizor", "vss");
//		int i1=userDAO.insert(u1);
//		int i2=userDAO.insert(u2);
//		String pass1="sifra1";
//		String pass2="sifra2";
//		System.out.println(credentialsDAO.insert(new Credentials(i1,i1,"admin",HashHandler.createHash(pass1))));
//		System.out.println(credentialsDAO.insert(new Credentials(i2,i2,"super",HashHandler.createHash(pass2))));
//		System.out.println("test:credentials-update:");
//		scan.nextLine();
//		System.out.println(credentialsDAO.update(new Credentials(i1,i1,"admin1",HashHandler.createHash(pass1))));
//		System.out.println("test:credentials-delete:");
//		scan.nextLine();
//		System.out.println(credentialsDAO.delete(4));
//		System.out.println("test:credentials-select:");
//		scan.nextLine();
//		System.out.println(HashHandler.verifyPassword(pass1, credentialsDAO.select(3).getHash()));
		
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
		

//		System.out.println("VEHICLE TEST");
//		System.out.println("test:vehicle-insert:");
//		scan.nextLine();
//		System.out.println(vehicleDAO.insert(new Vehicle("AAA-AAA","Audi","R8",(short)2010)));
//		System.out.println(vehicleDAO.insert(new Vehicle("AAA-SSS","Mazda","R7",(short)1994)));
//		System.out.println("test:vehicle-update:");
//		scan.nextLine();
//		System.out.println(vehicleDAO.update(new Vehicle(3,"AAA-AAA","Mitsubishi","Lan EVO10",(short)2012)));
//		System.out.println("test:vehicle-delete:");
//		scan.nextLine();
//		System.out.println(vehicleDAO.delete(4));
//		System.out.println("test:vehicle-select:");
//		scan.nextLine();
//		System.out.println(vehicleDAO.select(2));
		
//		System.out.println("INTERVENTION TEST");
//		System.out.println("test:intervention-insert:");
//		scan.nextLine();
//		Client c1=new Client("Marko", "Markovic", "65656656565");
//		Vehicle v1=new Vehicle("AAA-AAA","Mitsubishi","Lan EVO10",(short)2012);
//		User user=new User(1, "A", "A", TimeUtility.stringToLocalDate("1990-10-01"), "operater", "vss");
//		int clientInt=clientDAO.insert(c1);
//		int vehicleInt=vehicleDAO.insert(v1);
//		int userInt=userDAO.insert(user);
//		Intervention interv1=new Intervention(clientInt, vehicleInt, userInt, TimeUtility.getLDTNow());
//		System.out.println(interventionDAO.insert(interv1));
//		System.out.println("test:vehicle-update:");
//		scan.nextLine();
//		System.out.println(interventionDAO.update(new Intervention(2,clientInt, vehicleInt, userInt,userInt, interv1.getOpened_on(),TimeUtility.getLDTNow(),"sranje",true)));
//		System.out.println("test:vehicle-delete:");
//		scan.nextLine();
//		System.out.println(interventionDAO.delete(3));
//		System.out.println("test:vehicle-select:");
//		scan.nextLine();
//		System.out.println(interventionDAO.select(4));

//		System.out.println("COORDINATE TEST");
//		System.out.println("test:intervenitonCoordinate-insert:");
//		scan.nextLine();
//		System.out.println(intervenitonCoordinateDAO.insert(new InterventionCoordinate(2,2,3.1,2.1111)));
//		System.out.println("test:intervenitonCoordinate-update:");
//		scan.nextLine();
//		System.out.println(intervenitonCoordinateDAO.update(new InterventionCoordinate(2,2,3.111111111111,2.1111)));
//		System.out.println("test:intervenitonCoordinate-select:");
//		scan.nextLine();
//		System.out.println(intervenitonCoordinateDAO.select(2));
//		System.out.println("test:intervenitonCoordinate-delete:");
//		scan.nextLine();
//		System.out.println(intervenitonCoordinateDAO.delete(2));
		
		
//		System.out.println("ROAD_REPORT TEST");
//		System.out.println("test:road_report-insert:");
//		scan.nextLine();
//		User user=new User(1, "A", "A", TimeUtility.stringToLocalDate("1990-10-01"), "terenskiRR", "vss");
//		int userInt=userDAO.insert(user);
//		System.out.println(road_reportDAO.insert(new RoadReport(2,2, userInt, "guma osla", TimeUtility.getLDTNow(), null)));
//		System.out.println("test:road_report-update:");
//		scan.nextLine();
//		System.out.println(road_reportDAO.update(new RoadReport(2,2, userInt, "guma osla", TimeUtility.getLDTNow(), "oslo sve")));
//		System.out.println("test:road_report-select:");
//		scan.nextLine();
//		System.out.println(road_reportDAO.select(2));
//		System.out.println("test:road_report-delete:");
//		scan.nextLine();
//		System.out.println(road_reportDAO.delete(2));
		
//		System.out.println("REPORT TEST");
//		System.out.println("test:report-insert:");
//		scan.nextLine();
//		System.out.println(reportDAO.insert(new Report(2, 2, 4, "test", TimeUtility.getLDTNow())));
//		System.out.println("test:report-update:");
//		scan.nextLine();
//		System.out.println(reportDAO.update(new Report(2, 2, 4, "test1111111", TimeUtility.getLDTNow())));
//		System.out.println("test:report-select:");
//		scan.nextLine();
//		System.out.println(reportDAO.select(2));
//		System.out.println("test:report-delete:");
//		scan.nextLine();
//		System.out.println(reportDAO.delete(2));
		
		scan.close();
	}
}
