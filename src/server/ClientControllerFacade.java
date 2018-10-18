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
import DAO.MySQL.MySQLClientDAO;
import DAO.MySQL.MySQLCredentialsDAO;
import DAO.MySQL.MySQLEventDAO;
import DAO.MySQL.MySQLInterventionDAO;
import DAO.MySQL.MySQLSessionDAO;
import DAO.MySQL.MySQLSubscriptionDAO;
import DAO.MySQL.MySQLUserDAO;
import model.users.Client;
import model.interventions.Intervention;
import model.users.Credentials;
import model.users.Event;
import model.users.Session;
import model.users.Subscription;
import model.users.User;
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


	public ClientControllerFacade() {
		credentialsDAO = new MySQLCredentialsDAO();
		userDAO = new MySQLUserDAO();
		sessionDAO = new MySQLSessionDAO();
		eventDAO = new MySQLEventDAO();
		clientDAO = new MySQLClientDAO();
		interventionDAO=new MySQLInterventionDAO();
		subscriptionDAO = new MySQLSubscriptionDAO();
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
				int ID_session = sessionDAO.insert(new Session(user.getID_user(), LocalDateTime.now()));

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

	// public void viewUsers(String param){}
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

	// public void accessMapFieldTechnician(int intervention ID){}
	// public void accessMapOperator(){}
	// public void changeStateFieldTechnitian(String state){}
	// public void viewFieldTechnitianState(int userID){}
	// public void viewStatesFieldTechnitians(String param){}
	// public void viewOnlineUseres(){}
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
	// public void viewUserSessions(String param){}

	public ArrayList<String> viewClient(int clientID) {
		
		ArrayList<String> reply = new ArrayList<>();
		Client client = clientDAO.select(clientID);
		
		if(client != null) {
		reply.add("VIEW USER OK");
		reply.add(""+client.getID_client());
		reply.add(client.getName());
		reply.add(client.getSurname());
		reply.add(client.getPhone_number());
		} else {
			reply.add("VIEW USER FAILED");
		}
		 return reply;
		
	}

	public ArrayList<String> newClient(String name, String surname, String phone_number) {
		ArrayList<String> reply = new ArrayList<>();
		Client client = new Client(name, surname, phone_number);
		
		if(clientDAO.insert(client) != 0) {
		reply.add("NEW CLIENT OK");
		} else {
			reply.add("NEW CLIENT FAILED");
		}
		 return reply;
	}

	public ArrayList<String> updateClient(String client_ID, String name, String surname, String phone_number) {
		ArrayList<String> reply = new ArrayList<>();
		Client client = new Client(Integer.parseInt(client_ID), name, surname, phone_number);
		if (clientDAO.update(client) != 0) {
			reply.add("UPDATE CLIENT OK");
		} else {
			reply.add("UPDATE CLIENT FAILED");
		}
		return reply;
	
	}

	public ArrayList<String> deleteClient(String client_ID) {
		ArrayList<String> reply = new ArrayList<>();
		if (clientDAO.delete(Integer.parseInt(client_ID)) != 0) {
			reply.add("DELETE CLIENT OK");
		} else {
			reply.add("DELETE CLIENT FAILED");
		}
		return reply;
	}

	// public void viewClients(String param){}


	// public void newSubscription(Subscripption subscription){}
	// public void deleteSubscription(int subscriptionID){}
	
	 public void viewSubscription(int subscription_ID) {
		 ArrayList<String> reply = new ArrayList<>();
			Subscription subscription = subscriptionDAO.select(subscription_ID);
			
			if(subscription != null) {
			reply.add("VIEW SUBSCRIPTION OK");
			reply.add(""+subscription.getID_subscription());
			reply.add(subscription.getID_client());
			reply.add(subscription.getStart_date());
			reply.add(subscription.getEnd_date());
			} else {
				reply.add("VIEW subscription FAILED");
			}
			 return reply;
	 }
	// public void viewSubscriptions(String param){}

	 public ArrayList<String> newIntervention(Intervention intervention){
		 ArrayList<String> reply = new ArrayList<>();
		 if(interventionDAO.insert(intervention)!=0) {
			 reply.add("NEW INTERVENTION OK");
		 }else {
			 reply.add("NEW INTERVENTION FAILED");
		 }
		 return reply;
	 }

	public ArrayList<String> viewIntervention(int ID_intervention) {
		ArrayList<String> reply = new ArrayList<>();
		Intervention intervention = interventionDAO.select(ID_intervention);
//		if(interventionDAO.exists(ID_intervention)) {
		//reply.add("VIEW INTERVENTION OK");
		reply.add(""+intervention.getID_intervention());
		reply.add(""+intervention.getID_client());
		reply.add(""+intervention.getID_vehicle());
		reply.add(""+intervention.getID_user_opened());
		reply.add(""+intervention.getID_user_closed());
		reply.add(TimeUtility.localDateTimeToString(intervention.getOpened_on()));
		reply.add(TimeUtility.localDateTimeToString(intervention.getClosed_on()));
		reply.add(intervention.getRemark());
		if(intervention.isClosed())
			reply.add("CLOSED");
		else 
			reply.add("OPEN");
//		}else{
//			reply.add("VIEW INTERVENTION FAILED");
//		}
		return reply;
	}
	// public void closeIntervention(Intervention intervention){}
	// public void viewInterventions(String param){}

	// public void newFieldReport(FieldReport fieldReport){}

	// public void newReport(Report report){}
	// public void viewReport(int reportID){}
	// public void viewReports(String param){}

}
