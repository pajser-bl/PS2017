package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import DAO.ClientDAO;
import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.InterventionDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import DAO.MySQL.MySQLClientDAO;
import DAO.MySQL.MySQLCredentialsDAO;
import DAO.MySQL.MySQLEventDAO;
import DAO.MySQL.MySQLInterventionDAO;
import DAO.MySQL.MySQLSessionDAO;
import DAO.MySQL.MySQLSubscriptionDAO;
import DAO.MySQL.MySQLUserDAO;
import DAO.MySQL.MySQLVehicleDAO;
import controller.AccessControl;
import controller.ClientControl;
import controller.InterventionControl;
import controller.SessionControl;
import controller.SubscriptionControl;
import controller.UserControl;
import model.users.Client;
import model.Vehicle;
import model.interventions.Intervention;

public class ClientControllerFacade {
	CredentialsDAO credentialsDAO;
	UserDAO userDAO;
	SessionDAO sessionDAO;
	EventDAO eventDAO;
	ClientDAO clientDAO;
	VehicleDAO vehicleDAO;
	InterventionDAO interventionDAO;
	SubscriptionDAO subscriptionDAO;

	public ClientControllerFacade() {
		credentialsDAO = new MySQLCredentialsDAO();
		userDAO = new MySQLUserDAO();
		sessionDAO = new MySQLSessionDAO();
		eventDAO = new MySQLEventDAO();
		clientDAO = new MySQLClientDAO();
		vehicleDAO = new MySQLVehicleDAO();
		interventionDAO = new MySQLInterventionDAO();
		subscriptionDAO = new MySQLSubscriptionDAO();

	}

	public ArrayList<String> login(String username, String password) {
		return AccessControl.login(username, password, credentialsDAO, userDAO, sessionDAO, eventDAO);
	}

	public void logout(int user_ID) {
		AccessControl.logout(user_ID, eventDAO, credentialsDAO, sessionDAO);
	}

	public void connectionLost(int ID_user) {
		AccessControl.connectionLost(ID_user, eventDAO, sessionDAO);
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

	public ArrayList<String> viewUser(int user_ID) {
		return UserControl.viewUser(user_ID, userDAO, credentialsDAO);
	}

	public ArrayList<String> viewUsers(String param) {
		return UserControl.viewUsers(param, userDAO, credentialsDAO);
	}

	public ArrayList<String> addUser(String name, String surname, String date_of_birth, String type,
			String qualification, String username, String password) {
		return UserControl.addUser(name, surname, date_of_birth, type, qualification, username, password, userDAO,
				credentialsDAO);
	}

	public ArrayList<String> changePassword(int ID_user, String password) {
		return UserControl.changePassword(ID_user, password, credentialsDAO);
	}

	public ArrayList<String> updateUser(int ID_user, String name, String surname, String date_of_birth, String type,
			String qualification) {
		return UserControl.updateUser(ID_user, name, surname, date_of_birth, type, qualification, userDAO);
	}

	public ArrayList<String> deleteUser(int userID) {
		return UserControl.deleteUser(userID, userDAO, credentialsDAO);
	}

	public ArrayList<String> viewUserSession(int ID_session) {
		return SessionControl.viewUserSession(ID_session, sessionDAO, eventDAO);
	}

	public ArrayList<String> viewUserSessions(int ID_user) {
		return SessionControl.viewUserSessions(ID_user, sessionDAO);
	}

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
		return SubscriptionControl.updateSubscription(subscription_ID, client_ID, start_date, end_date,
				subscriptionDAO);
	}

	public ArrayList<String> deleteSubscription(String subscription_ID) {
		return SubscriptionControl.deleteSubscription(subscription_ID, subscriptionDAO);
	}

	public ArrayList<String> newIntervention(Intervention intervention, Client client, Vehicle vehicle)
			throws Exception {
		return InterventionControl.newIntervention(intervention, client, vehicle, interventionDAO, eventDAO, clientDAO,
				vehicleDAO);
	}

	public ArrayList<String> newRoadReport(int intervention_ID, String assistance, LocalDateTime time_of_assistance,
			String remark) throws Exception {
		return InterventionControl.newRoadReport(intervention_ID, assistance, time_of_assistance, remark,
				interventionDAO, eventDAO);
	}

	public ArrayList<String> closeIntervention(int intervention_ID, int operater_ID, LocalDateTime closed_on,
			String remark) throws Exception {
		return InterventionControl.closeIntervention(intervention_ID, operater_ID, closed_on, remark, interventionDAO,
				eventDAO);
	}

	public ArrayList<String> newReport(int intervention_ID, int supervisor_ID, String remark, LocalDateTime report_made)
			throws Exception {
		return InterventionControl.newReport(intervention_ID, supervisor_ID, remark, report_made, interventionDAO);
	}

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

	// public ArrayList<String> viewReport(int report_ID) {
	// return ReportControl.viewReport(report_ID,interventionDAO, roadReportDAO,
	// reportDAO, clientDAO, userDAO, vehicleDAO);
	// }
	//
	// public ArrayList<String> newReport(int ID_report, int ID_intervention, int
	// ID_user, String remark, LocalDateTime closed_on) {
	// return
	// ReportControl.newReport(ID_report,ID_intervention,ID_user,remark,closed_on,reportDAO);
	// }

	// public void viewReports(String param){}

	// public void accessMapFieldTechnician(int intervention ID){}
	// public void accessMapOperator(){}
	public ArrayList<String> viewOnlineUsers() {
		return UserControl.viewOnlineUsers(credentialsDAO);
	}

	public ArrayList<String> viewFieldTechnicians() {
		return UserControl.viewFieldTechnicians();
	}

	public ArrayList<String> viewAvailableFieldTechnicians() {
		return UserControl.viewAvailableFieldTechnicians();
	}

	public ArrayList<String> changeStateFieldTechnician(int user_ID, String state) {
		return UserControl.changeStateFieldTechnician(user_ID, state, eventDAO);
	}

	public ArrayList<String> viewFieldTechnicianState(int user_ID) {
		return UserControl.viewFieldTechnicianState(user_ID);
	}

	public ArrayList<String> checkFieldTechnicianIntervention(int user_ID) {
		return InterventionControl.checkFieldTechnicianIntervention(user_ID, interventionDAO, clientDAO, vehicleDAO,
				userDAO);
	}

	public ArrayList<String> viewSubscriptions() {
		return SubscriptionControl.viewSubscriptions(subscriptionDAO); // treba testirati
	}

	public ArrayList<String> viewClients() {
		return ClientControl.viewClients(clientDAO);

	}

	public ArrayList<String> viewOpenedInterventions() throws Exception {
		return InterventionControl.viewOpenedInterventions(interventionDAO, userDAO, clientDAO);
	}

	public ArrayList<String> viewOpenIntervention(int ID_intervention) throws Exception {
		return InterventionControl.viewIntervention(ID_intervention, interventionDAO, userDAO, clientDAO, vehicleDAO);
	}

	public ArrayList<String> viewFieldReportIntervention(int ID_intervention) throws Exception {
		return InterventionControl.viewIntervention(ID_intervention, interventionDAO, userDAO, clientDAO, vehicleDAO);
	}

	public ArrayList<String> viewClosedIntervention(int ID_intervention) throws Exception {
		return InterventionControl.viewIntervention(ID_intervention, interventionDAO, userDAO, clientDAO, vehicleDAO);
	}

	public ArrayList<String> viewReport(int ID_intervention) throws Exception {
		return InterventionControl.viewIntervention(ID_intervention, interventionDAO, userDAO, clientDAO, vehicleDAO);
	}

	public ArrayList<String> unexistingRequest(String string) {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("UNEXISTING FUNCTION REQUEST");
		reply.add(string);
		return reply;
	}
}
