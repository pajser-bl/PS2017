package controller;

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
	public static ArrayList<String> login(String username, String password, CredentialsDAO credentialsDAO,
			UserDAO userDAO, SessionDAO sessionDAO, EventDAO eventDAO) {
		boolean retVal = credentialsDAO.exists(username);
		boolean loginCheck = false;
		boolean alredyLoggedIn = false;
		ArrayList<String> reply = new ArrayList<>();
		if (retVal) {
			// postoje kredencijali sa datim username-om
			Credentials credentials = credentialsDAO.select(username);
			loginCheck = HashHandler.verifyPassword(password, credentials.getHash());
			alredyLoggedIn = ActiveUsersWatch.isAlredyLoggedIn(credentials.getID_user());
			if (loginCheck) {
				if (!alredyLoggedIn) {
					// uspjesan login
					User user = userDAO.select(credentials.getID_user());
					reply.add("LOGIN OK");
					reply.add(String.valueOf(user.getID_user()));
					reply.add(user.getName());
					reply.add(user.getSurname());
					reply.add(user.getType());
					reply.add(username);
					System.out.println(user.getType());
					if (user.getType().equals("Terenski radnik") || user.getType().equals("Operater")) {
						int ID_session = sessionDAO.insert(new Session(user.getID_user(), LocalDateTime.now()));
						ActiveUsersWatch.addUserSession(user.getID_user(), ID_session);
						Event event = new Event(ID_session, LocalDateTime.now(),
								"Korisnik " + user.getName() + " " + user.getSurname() + " se prijavio na sistem.");
						eventDAO.insert(event);
					}
					ActiveUsersWatch.addActiveUser(user);
				} else {
					reply.add("LOGIN NOT OK");
					reply.add("Korisnik vec prijavljen.");
				}
			} else {
				// neuspjesan login
				reply.add("LOGIN NOT OK");
				reply.add("Neispravna lozinka.");
			}
		} else {
			// ne postoje kredencijali sa username-om
			reply.add("LOGIN USERNAME NOT OK");
			reply.add("Nepostojece korisnicko ime.");
		}
		return reply;
	}

	public static void logout(int user_ID, EventDAO eventDAO) {
		if (ActiveUsersWatch.userHasSession(user_ID)) {
			Event event=new Event(ActiveUsersWatch.getUserSession(user_ID),LocalDateTime.now(),"Korisnik se odjavio.");
			 eventDAO.insert(event);
			ActiveUsersWatch.removeUserSession(user_ID);
		}
		ActiveUsersWatch.removeActiveUser(user_ID);
	}
}
