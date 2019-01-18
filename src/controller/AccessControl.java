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
import utility.HashHandler;
import utility.TimeUtility;

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
					if (user.getType().toLowerCase().equals("terenski radnik") || user.getType().toLowerCase().equals("operater")) {
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
					System.out.println("[" + TimeUtility.getStringTimeNow()
							+ "]Unsuccessful login(user alredy logged in) request from " + username + " .");
				}
			} else {
				// neuspjesan login
				reply.add("LOGIN NOT OK");
				reply.add("Neispravna lozinka.");
				System.out.println("[" + TimeUtility.getStringTimeNow()
						+ "]Unsuccessful login(wrong password) request from " + username + " .");
			}
		} else {
			// ne postoje kredencijali sa username-om
			reply.add("LOGIN USERNAME NOT OK");
			reply.add("Nepostojece korisnicko ime.");
			System.out.println("[" + TimeUtility.getStringTimeNow()
					+ "]Unsuccessful login(unexisting user) request from " + username + " .");
		}
		return reply;
	}

	public static void logout(int user_ID, EventDAO eventDAO, CredentialsDAO credentialsDAO, SessionDAO sessionDAO) {
		if (ActiveUsersWatch.userHasSession(user_ID)) {
			int sessionID = ActiveUsersWatch.getUserSession(user_ID);
			Event event = new Event(sessionID, LocalDateTime.now(), "Korisnik se odjavio.");
			eventDAO.insert(event);
			Session session = sessionDAO.select(sessionID);
			session.setEnd(LocalDateTime.now());
			sessionDAO.update(session);
			ActiveUsersWatch.removeUserSession(user_ID);
		}
		ActiveUsersWatch.removeActiveUser(user_ID);
	}

	public static void connectionLost(int iD_user, EventDAO eventDAO,SessionDAO sessionDAO) {
		if (ActiveUsersWatch.userHasSession(iD_user)) {
			int iD_session=ActiveUsersWatch.getUserSession(iD_user);
			Event event = new Event(iD_session, LocalDateTime.now(), "Konekcija izgubljena.");
			eventDAO.insert(event);
			Session session = sessionDAO.select(iD_session);
			session.setEnd(LocalDateTime.now());
			sessionDAO.update(session);
			ActiveUsersWatch.removeUserSession(iD_user);
		}
		ActiveUsersWatch.removeActiveUser(iD_user);
	}
}
