package server;

import java.util.ArrayList;

import DAO.CredentialsDAO;
import DAO.UserDAO;
import DAO.MySQL.MySQLCredentialsDAO;
import DAO.MySQL.MySQLUserDAO;
import model.users.Credentials;
import model.users.User;
import utility.HashHandler;

public class ClientControllerFacade {
	CredentialsDAO credentialsDAO;
	UserDAO userDAO;
	
	public ClientControllerFacade() {
		credentialsDAO=new MySQLCredentialsDAO();
		userDAO=new MySQLUserDAO();
	}
	public ArrayList<String> login(String username,String password) {
		boolean retVal=credentialsDAO.exists(username);
		boolean loginCheck=false;
		ArrayList<String> reply=new ArrayList<>();
		if(retVal) {
			//postoje kredencijali sa datim username-om 
			Credentials credentials=credentialsDAO.select(username);
			loginCheck=HashHandler.verifyPassword(password, credentials.getHash());
			if(loginCheck) {
				//uspjesan logine
				User user=userDAO.select(credentials.getID_user());
				reply.add("LOGIN OK");
				reply.add(String.valueOf(user.getID_user()));
				reply.add(user.getName());
				reply.add(user.getSurname());
				reply.add(user.getType());
			}else {
				// neuspjesan login
				reply.add("LOGIN PASSWORD NOT OK");
			}
		}else {
			//ne postoje kredencijali sa username-om
			reply.add("LOGIN USERNAME NOT OK");
		}
		return reply;
	}
	public ArrayList<String> logout() {
		ArrayList<String> reply=new ArrayList<>();
		reply.add("LOGIN OK");
		return reply;
	}
	
//	public void newCredentials() {}
//	public void viewCredentials(int userID){}

//	public void viewUsers(String param){}
//	public void addUser(User user){}
//	public void deleteUsere(int userID)


//	public void accessMapFieldTechnician(int intervention ID){}
//	public void accessMapOperator(){}
//	public void changeStateFieldTechnitian(String state){}
//	public void viewFieldTechnitianState(int userID){}
//	public void viewStatesFieldTechnitians(String param){}
//	public void viewOnlineUseres(){}
//	public void viewUserSession(int sesionId){}
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
