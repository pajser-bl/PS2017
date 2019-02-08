package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import DAO.ClientDAO;
import DAO.EventDAO;
import DAO.InterventionDAO;
import DAO.SessionDAO;
import DAO.SubscriptionDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import DAO.MySQL.MySQLClientDAO;
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
import model.Vehicle;
import model.client.Client;
import model.interventions.Intervention;

public class ClientControllerFacade {
	UserDAO userDAO;
	SessionDAO sessionDAO;
	EventDAO eventDAO;
	ClientDAO clientDAO;
	VehicleDAO vehicleDAO;
	InterventionDAO interventionDAO;
	SubscriptionDAO subscriptionDAO;

	public ClientControllerFacade() {
		userDAO = new MySQLUserDAO();
		sessionDAO = new MySQLSessionDAO();
		eventDAO = new MySQLEventDAO();
		clientDAO = new MySQLClientDAO();
		vehicleDAO = new MySQLVehicleDAO();
		interventionDAO = new MySQLInterventionDAO();
		subscriptionDAO = new MySQLSubscriptionDAO();

	}

	public ArrayList<String> login(String username, String password) throws Exception {
		return AccessControl.login(username, password, userDAO, sessionDAO, eventDAO);
	}

	public void logout(int user_ID) throws Exception {
		AccessControl.logout(user_ID, eventDAO, sessionDAO);
	}

	public void connectionLost(int ID_user) throws Exception {
		AccessControl.connectionLost(ID_user, eventDAO, sessionDAO);
	}

	public ArrayList<String> viewUser(int user_ID) {
		return UserControl.viewUser(user_ID, userDAO);
	}

	public ArrayList<String> viewUsers(String param) {
		return UserControl.viewUsers(param, userDAO);
	}

	public ArrayList<String> addUser(String name, String surname, String date_of_birth, String type,
			String qualification, String drivers_license, String username, String password) {
		return UserControl.addUser(name, surname, date_of_birth, type, qualification, drivers_license, username,
				password, userDAO);
	}

	public ArrayList<String> changePassword(int ID_user, String password) {
		return UserControl.changePassword(ID_user, password, userDAO);
	}

	public ArrayList<String> updateUser(int ID_user, String name, String surname, String date_of_birth, String type,
			String qualification, String drivers_license) {
		return UserControl.updateUser(ID_user, name, surname, date_of_birth, type, qualification, drivers_license,
				userDAO);
	}

	public ArrayList<String> deleteUser(int userID) {
		return UserControl.deleteUser(userID, userDAO);
	}

	public ArrayList<String> viewSessions() throws Exception {
		return SessionControl.viewSessions(sessionDAO, userDAO);
	}

	public ArrayList<String> viewUserSession(int ID_session) throws Exception {
		return SessionControl.viewUserSession(ID_session, sessionDAO, eventDAO);
	}

	public ArrayList<String> viewUserSessions(int ID_user) throws Exception {
		return SessionControl.viewUserSessions(ID_user, sessionDAO);
	}

	public ArrayList<String> viewClient(int clientID) throws Exception {
		return ClientControl.viewClient(clientID, clientDAO);
	}

	public ArrayList<String> newClient(String name, String surname, String phone_number) throws Exception {
		return ClientControl.newClient(name, surname, phone_number, clientDAO);
	}

	public ArrayList<String> updateClient(String client_ID, String name, String surname, String phone_number)
			throws Exception {
		return ClientControl.updateClient(client_ID, name, surname, phone_number, clientDAO);
	}

	public ArrayList<String> deleteClient(String client_ID) throws Exception {
		return ClientControl.deleteClient(client_ID, clientDAO);
	}

	public ArrayList<String> viewClients() throws Exception {
		return ClientControl.viewClients(clientDAO);
	}

	public ArrayList<String> viewSubscription(int subscription_ID) throws Exception {
		return SubscriptionControl.viewSubscription(subscription_ID, subscriptionDAO, clientDAO);
	}

	public ArrayList<String> viewSubscriptionsByUser(int client) throws Exception {
		return SubscriptionControl.viewSubscriptionsByUser(client, subscriptionDAO, clientDAO);
	}

	public ArrayList<String> viewSubscriptions() throws Exception {
		return SubscriptionControl.viewSubscriptions(subscriptionDAO, clientDAO);
	}

	public ArrayList<String> newSubscription(String client_ID, String start_date, String end_date) throws Exception {
		return SubscriptionControl.newSubscription(client_ID, start_date, end_date, subscriptionDAO);
	}

	public ArrayList<String> updateSubscription(String subscription_ID, String client_ID, String start_date,
			String end_date) throws Exception {
		return SubscriptionControl.updateSubscription(subscription_ID, client_ID, start_date, end_date,
				subscriptionDAO);
	}

	public ArrayList<String> deleteSubscription(String subscription_ID) throws Exception {
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

	// public void accessMapFieldTechnician(int intervention ID){}
	// public void accessMapOperator(){}
	public ArrayList<String> viewOnlineUsers() {
		return UserControl.viewOnlineUsers(userDAO);
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
		reply.add(string + " je nepostojeci zahtjev.");
		return reply;
	}

}
