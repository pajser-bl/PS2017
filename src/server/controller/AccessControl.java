package server.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.SessionDAO;
import DAO.UserDAO;
import model.users.Credentials;
import model.users.Event;
import model.users.Session;
import model.users.User;
import server.ActiveUsersWatch;
import utility.HashHandler;

public class AccessControl {
	public static ArrayList<String> login(String username, String password,CredentialsDAO credentialsDAO,UserDAO userDAO,SessionDAO sessionDAO,EventDAO eventDAO) {
		boolean retVal = credentialsDAO.exists(username);
		boolean loginCheck = false;
		boolean alredyLoggedIn = false;
		ArrayList<String> reply = new ArrayList<>();
		if (retVal) {
			// postoje kredencijali sa datim username-om
			Credentials credentials = credentialsDAO.select(username);
			loginCheck = HashHandler.verifyPassword(password, credentials.getHash());
			alredyLoggedIn = ActiveUsersWatch.isAlredyLoggedIn(credentials.getID_user());
			if (loginCheck && !alredyLoggedIn) {
				// uspjesan login
				User user = userDAO.select(credentials.getID_user());
				
				reply.add("LOGIN OK");
				reply.add(String.valueOf(user.getID_user()));
				reply.add(user.getName());
				reply.add(user.getSurname());
				reply.add(user.getType());
				if(user.getType()=="Terenski radnik"|| user.getType()=="Operater") {
					int ID_session = sessionDAO.insert(new Session(user.getID_user(), LocalDateTime.now()));
					ActiveUsersWatch.addUserSession(user.getID_user(),ID_session);
					Event event=new Event(ID_session,LocalDateTime.now(),"Korisnik "+user.getName()+ " "+user.getSurname()+ " se prijavio na sistem.");
					eventDAO.insert(event);
				}
				ActiveUsersWatch.addActiveUser(user);
			} else {
				// neuspjesan login
				reply.add("LOGIN NOT OK");
			}
		} else {
			// ne postoje kredencijali sa username-om
			reply.add("LOGIN USERNAME NOT OK");
		}
		return reply;
	}
	public static ArrayList<String> logout(int user_ID,EventDAO eventDAO) {
		ArrayList<String> reply = new ArrayList<>();
		ActiveUsersWatch.removeActiveUser(user_ID);
		if(ActiveUsersWatch.userHasSession(user_ID)) {
			Event event=new Event(user_ID,LocalDateTime.now(),"Korisnik se odjavio.");
			eventDAO.insert(event);
			ActiveUsersWatch.removeUserSession(user_ID);
		}
		reply.add("LOGOUT OK");
		return reply;
	}
}
