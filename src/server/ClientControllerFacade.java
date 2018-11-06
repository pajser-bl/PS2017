package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import DAO.ClientDAO;
import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.InterventionDAO;
import DAO.ReportDAO;
import DAO.RoadReportDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.MySQL.MySQLClientDAO;
import DAO.MySQL.MySQLCredentialsDAO;
import DAO.MySQL.MySQLEventDAO;
import DAO.MySQL.MySQLInterventionDAO;
import DAO.MySQL.MySQLReportDAO;
import DAO.MySQL.MySQLRoadReportDAO;
import DAO.MySQL.MySQLSessionDAO;
import DAO.MySQL.MySQLSubscriptionDAO;
import DAO.MySQL.MySQLUserDAO;
import model.users.Client;
import model.interventions.Intervention;
import model.interventions.Report;
import model.interventions.RoadReport;
import model.users.Credentials;
import model.users.Event;
import model.users.Session;
import model.users.Subscription;
import model.users.User;
import server.controller.AccessControl;
import server.controller.ClientControl;
import server.controller.SubscriptionControl;
import server.controller.UserControl;
import utility.HashHandler;
import utility.TimeUtility;

public class ClientControllerFacade {
	CredentialsDAO credentialsDAO;
	UserDAO userDAO;
	SessionDAO sessionDAO;
	EventDAO eventDAO;
	ClientDAO clientDAO;
	InterventionDAO interventionDAO;
	SubscriptionDAO subscriptionDAO;
	RoadReportDAO roadReportDAO;
	ReportDAO reportDAO;

	public ClientControllerFacade() {
		credentialsDAO = new MySQLCredentialsDAO();
		userDAO = new MySQLUserDAO();
		sessionDAO = new MySQLSessionDAO();
		eventDAO = new MySQLEventDAO();
		clientDAO = new MySQLClientDAO();
		interventionDAO = new MySQLInterventionDAO();
		subscriptionDAO = new MySQLSubscriptionDAO();
		roadReportDAO = new MySQLRoadReportDAO();
		reportDAO = new MySQLReportDAO();
	}

	public ArrayList<String> login(String username, String password) {
		return AccessControl.login(username, password, credentialsDAO, userDAO, sessionDAO, eventDAO);
	}

	public ArrayList<String> logout(int user_ID) {
		return AccessControl.logout(user_ID, eventDAO);
	}

	public ArrayList<String> newCredentials(int ID_user, String username, String password) {
		return UserControl.newCredentials(ID_user, username, password, credentialsDAO);
	}

	public ArrayList<String> updatePassword(int ID_user, String username, String password) {
		return UserControl.updatePassword(ID_user, username, password, credentialsDAO);
	}

	public ArrayList<String> deleteCredentials(int ID_user) {
		return UserControl.deleteCredentials(ID_user, credentialsDAO);
	}

	// public void viewUsers(String param){}

	public ArrayList<String> addUser(String name, String surname, String date_of_birth, String type,
			String qualification) {
		return UserControl.addUser(name, surname, date_of_birth, type, qualification, userDAO);
	}

	public ArrayList<String> updateUser(int ID_user, String name, String surname, String date_of_birth, String type,
			String qualification) {
		return UserControl.updateUser(ID_user, name, surname, date_of_birth, type, qualification, userDAO);
	}

	public ArrayList<String> deleteUser(int userID) {
		return UserControl.deleteUser(userID, userDAO, credentialsDAO);
	}

	public ArrayList<String> viewUserSession(int ID_session) {
		// ID_session,ID_user,start,end
		ArrayList<String> reply = new ArrayList<>();
		Session session = sessionDAO.select(ID_session);
		int ID_user = session.getUserID();
		// LocalDateTime start = session.getStart();
		// LocalDateTime end = session.getEnd();
		// reply.add();
		for (Event e : eventDAO.selectBySession(ID_session))
			reply.add(e.toString());
		return reply;
	}
	// public void viewUserSessions(String param){}

	public ArrayList<String> viewClient(int clientID) {

		ArrayList<String> reply = new ArrayList<>();
		Client client = clientDAO.select(clientID);

		if (client != null) {
			reply.add("VIEW USER OK");
			reply.add("" + client.getID_client());
			reply.add(client.getName());
			reply.add(client.getSurname());
			reply.add(client.getPhone_number());
		} else {
			reply.add("VIEW USER FAILED");
		}
		return reply;

	}

	public ArrayList<String> newClient(String name, String surname, String phone_number) {
		return ClientControl.newClient(name, surname, phone_number, clientDAO);
	}

	public ArrayList<String> updateClient(String client_ID, String name, String surname, String phone_number) {
		return ClientControl.updateClient(client_ID, name, surname, phone_number, clientDAO);
	}

	public ArrayList<String> deleteClient(String client_ID) {
		return ClientControl.deleteClient(client_ID, clientDAO);
	}

	// public void viewClients(String param){}

	public ArrayList<String> viewSubscription(int subscription_ID) {
		return SubscriptionControl.viewSubscription(subscription_ID, subscriptionDAO);
	}
	// public void viewSubscriptions(String param){}

	public ArrayList<String> newSubscription(String client_ID, String start_date, String end_date) {
		return SubscriptionControl.newSubscription(client_ID, start_date, end_date, subscriptionDAO);
	}

	public ArrayList<String> updateSubscription(String subscription_ID, String client_ID, String start_date,
			String end_date) {
		return SubscriptionControl.updateSubscription(subscription_ID, client_ID, start_date, end_date, subscriptionDAO);
	}

	public ArrayList<String> deleteSubscription(String subscription_ID) {
		return SubscriptionControl.deleteSubscription(subscription_ID, subscriptionDAO);
	}

	public ArrayList<String> newIntervention(Intervention intervention) {
		// new Intervention(int iD_client, int iD_vehicle,
		// int iD_user_opened, LocalDateTime opened_on)
		ArrayList<String> reply = new ArrayList<>();
		if (interventionDAO.insert(intervention) != 0) {
			reply.add("NEW INTERVENTION OK");
		} else {
			reply.add("NEW INTERVENTION FAILED");
		}
		return reply;
	}

	public ArrayList<String> viewIntervention(int ID_intervention) {
		ArrayList<String> reply = new ArrayList<>();
		Intervention intervention = interventionDAO.select(ID_intervention);
		// if(interventionDAO.exists(ID_intervention)) {
		// reply.add("VIEW INTERVENTION OK");
		reply.add("" + intervention.getID_intervention());
		reply.add("" + intervention.getID_client());
		reply.add("" + intervention.getID_vehicle());
		reply.add("" + intervention.getID_user_opened());
		reply.add("" + intervention.getID_user_closed());
		reply.add(TimeUtility.localDateTimeToString(intervention.getOpened_on()));
		reply.add(TimeUtility.localDateTimeToString(intervention.getClosed_on()));
		reply.add(intervention.getRemark());
		if (intervention.isClosed())
			reply.add("CLOSED");
		else
			reply.add("OPEN");
		// }else{
		// reply.add("VIEW INTERVENTION FAILED");
		// }
		return reply;
	}
	// public void viewInterventions(String param){}

	public ArrayList<String> updateIntervention(Intervention intervention) {
		ArrayList<String> reply = new ArrayList<>();
		if (interventionDAO.update(intervention) != 0) {
			reply.add("UPDATE INTERVENTION OK");
		} else {
			reply.add("UPDATE INTERVENTION FAILED");
		}
		return reply;
	}

	public ArrayList<String> deleteIntervention(String intervention_ID) {
		ArrayList<String> reply = new ArrayList<>();
		if (interventionDAO.delete(Integer.parseInt(intervention_ID)) != 0) {
			reply.add("DELETE INTERVENTION OK");
		} else {
			reply.add("DELETE INTERVENTION FAILED");
		}
		return reply;
	}

	public ArrayList<String> closeIntervention(int intervention_ID, int user_ID, LocalDateTime closed_on, String remark,
			boolean closed) {
		ArrayList<String> reply = new ArrayList<>();
		if (interventionDAO.close(intervention_ID, remark, user_ID, closed_on, closed) != 0) {
			reply.add("CLOSE INTERVENTION OK");
		} else {
			reply.add("CLOSE INTERVENTION FAILED");
		}
		return reply;
	}

	public ArrayList<String> newRoadReport(int fieldreport_ID, int intervention_ID, int user_ID, String assistance,
			LocalDateTime time, String remark) {
		ArrayList<String> reply = new ArrayList<>();

		if (roadReportDAO
				.insert(new RoadReport(fieldreport_ID, intervention_ID, user_ID, assistance, time, remark)) != 0) {
			reply.add("NEW ROADREPORT OK");
		} else {
			reply.add("NEW ROADREPORT FAILED");
		}
		return reply;
	}

	public ArrayList<String> viewReport(int report_ID) {
		ArrayList<String> reply = new ArrayList<>();
		Intervention intervention = interventionDAO.select(report_ID);
		RoadReport roadReport = roadReportDAO.select(report_ID);
		Report report = reportDAO.select(report_ID);

		return reply;
	}

	public void newReport(int iD_report, int iD_intervention, int iD_user, String remark, LocalDateTime closed_on) {
		ArrayList<String> reply = new ArrayList<>();
		if (reportDAO.insert(new Report(iD_report, iD_intervention, iD_user, remark, closed_on)) != 0) {
			reply.add("NEW ROADREPORT OK");
		} else {
			reply.add("NEW ROADREPORT FAILED");
		}
	}

	// public void viewReports(String param){}

	// public void accessMapFieldTechnician(int intervention ID){}
	// public void accessMapOperator(){}
	public ArrayList<String> viewOnlineUsers() {
		return UserControl.viewOnlineUsers();
	}

	// public void viewStatesFieldTechnitians(String param){}
	public ArrayList<String> viewStatesFieldTechnitians() {
		return UserControl.viewStatesFieldTechnitians();
	}

	public ArrayList<String> viewAvailableFieldTechnitians() {
		return UserControl.viewAvailableFieldTechnitians();
	}

	public ArrayList<String> changeStateFieldTechnitian(int user_ID, String state) {
		return UserControl.changeStateFieldTechnitian(user_ID, state);
	}

	public ArrayList<String> viewFieldTechnitianState(int user_ID) {
		return UserControl.viewFieldTechnitianState(user_ID);
	}

	public ArrayList<String> unexistingRequest() {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("UNEXISTING FUNCTION REQUEST");
		return reply;
	}

}
