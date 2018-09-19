package server;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.SessionDAO;
import DAO.UserDAO;
import DAO.MySQL.MySQLCredentialsDAO;
import DAO.MySQL.MySQLEventDAO;
import DAO.MySQL.MySQLSessionDAO;
import DAO.MySQL.MySQLUserDAO;
import model.users.Credentials;
import model.users.Event;
import model.users.Session;
import model.users.User;
import utility.HashHandler;
import utility.TimeUtility;

public class ClientControllerFacade {
	CredentialsDAO credentialsDAO;
	UserDAO userDAO;
	SessionDAO sessionDAO;
	EventDAO eventDAO;

	public ClientControllerFacade() {
		credentialsDAO = new MySQLCredentialsDAO();
		userDAO = new MySQLUserDAO();
		sessionDAO = new MySQLSessionDAO();
		eventDAO = new MySQLEventDAO();
	}

	public ArrayList<String> login(String username, String password) {
		boolean retVal = credentialsDAO.exists(username);
		boolean loginCheck = false;
		ArrayList<String> reply = new ArrayList<>();
		if (retVal) {
			// postoje kredencijali sa datim username-om
			Credentials credentials = credentialsDAO.select(username);
			loginCheck = HashHandler.verifyPassword(password, credentials.getHash());
			if (loginCheck) {
				// uspjesan logine
				User user = userDAO.select(credentials.getID_user());
				int ID_session = sessionDAO.insert(new Session(user.getID_user(), TimeUtility.getLDTNow()));

				reply.add("LOGIN OK");
				reply.add(String.valueOf(user.getID_user()));
				reply.add(user.getName());
				reply.add(user.getSurname());
				reply.add(user.getType());
				reply.add(String.valueOf(ID_session));

			} else {
				// neuspjesan login
				reply.add("LOGIN PASSWORD NOT OK");
			}
		} else {
			// ne postoje kredencijali sa username-om
			reply.add("LOGIN USERNAME NOT OK");
		}
		return reply;
	}

	public ArrayList<String> logout() {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("LOGOUT OK");
		return reply;
	}

	public ArrayList<String> newCredentials(int ID_user, String username, String password) {
		ArrayList<String> replay = new ArrayList<>();
		if (credentialsDAO.checkUniqueUserame(username)) {
			if (credentialsDAO
					.insert(new Credentials(ID_user, ID_user, username, HashHandler.createHash(password))) != 0) {
				replay.add("NEW CREDENTIALS OK");
			} else {
				replay.add("NEW CREDENTIALS FAILED");
			}
		} else {
			replay.add("USERNAME TAKEN");
		}
		return replay;

	}

	public ArrayList<String> updatePassword(int ID_user, String username, String password) {
		ArrayList<String> reply = new ArrayList<>();
		if (credentialsDAO.update(new Credentials(ID_user, ID_user, username, HashHandler.createHash(password))) != 0) {
			reply.add("UPDATE PASSWORD OK");
		} else {
			reply.add("UPDATE PASSWORD FAILED");
		}
		return reply;
	}

	public ArrayList<String> deleteCredentials(int ID_user) {
		ArrayList<String> reply = new ArrayList<>();
		if (credentialsDAO.delete(ID_user) != 0) {
			reply.add("DELETE CREDENTIALS OK");
		} else {
			reply.add("DELETE CREDENTIALS FAILED");
		}
		return reply;
	}

//	public void viewUsers(String param){}
	public ArrayList<String> addUser(String name, String surname, String date_of_birth, String type,
			String qualification) {
		ArrayList<String> reply = new ArrayList<>();
		User user = new User(name, surname, TimeUtility.stringToLocalDate(date_of_birth), type, qualification);
		if (userDAO.insert(user) != 0) {
			reply.add("ADD USER OK");
		} else {
			reply.add("ADD USER FAILED");
		}
		return reply;
	}

	public ArrayList<String> updateUser(int ID_user, String name, String surname, String date_of_birth, String type,
			String qualification) {
		ArrayList<String> reply = new ArrayList<>();
		User user = new User(ID_user, name, surname, TimeUtility.stringToLocalDate(date_of_birth), type, qualification);
		if (userDAO.update(user) != 0) {
			reply.add("UPDATE USER OK");
		} else {
			reply.add("UPDATE USER FAILED");
		}
		return reply;
	}

	public ArrayList<String> deleteUser(int userID) {
		ArrayList<String> reply = new ArrayList<>();
		if ((credentialsDAO.delete(userID) != 0) && (userDAO.delete(userID) != 0)) {
			reply.add("DELETE USER OK");
		} else {
			reply.add("DELETE USER FAILED");
		}
		return reply;
	}

//	public void accessMapFieldTechnician(int intervention ID){}
//	public void accessMapOperator(){}
//	public void changeStateFieldTechnitian(String state){}
//	public void viewFieldTechnitianState(int userID){}
//	public void viewStatesFieldTechnitians(String param){}
//	public void viewOnlineUseres(){}
	public ArrayList<String> viewUserSession(int ID_session) {
		// ID_session,ID_user,start,end
		ArrayList<String> reply = new ArrayList<>();
		Session session = sessionDAO.select(ID_session);
		int ID_user = session.getUserID();
		LocalDateTime start = session.getStart();
		LocalDateTime end = session.getEnd();
		// reply.add();
		for (Event e : eventDAO.selectBySession(ID_session))
			reply.add(e.toString());
		return reply;
	}
//	public void viewUserSessions(String param){}

//	public void viewClient(int clientID){}
//	public void viewClients(String param){}
//	public void newClient(Client client){}
//	public void deleteClient(Client client){}

//	public void newSubscription(Subscripption subscription){}
//	public void deleteSubscription(int subscriptionID){}
//	public void viewSubscription(int subscriptionID){}
//	public void viewSubscriptions(String param){}

//	public void newIntervention(Intervention intervention){}
//	public void closeIntervention(Intervention intervention){}
//	public void viewIntervention(int interventionID){}
//	public void viewInterventions(String param){}

//	public void newFieldReport(FieldReport fieldReport){}

//	public void newReport(Report report){}
//	public void viewReport(int reportID){}
//	public void viewReports(String param){}

}
