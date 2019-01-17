package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.CredentialsDAO;
import DAO.EventDAO;
import DAO.UserDAO;
import model.users.Credentials;
import model.users.Event;
import model.users.User;
import server.ActiveUsersWatch;
import utility.HashHandler;
import utility.TimeUtility;

public class UserControl {

	public static ArrayList<String> newCredentials(int ID_user, String username, String password,
			CredentialsDAO credentialsDAO) {
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

	public static ArrayList<String> updatePassword(int ID_user, String username, String password,
			CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (credentialsDAO.update(new Credentials(ID_user, ID_user, username, HashHandler.createHash(password))) != 0) {
			reply.add("UPDATE PASSWORD OK");
		} else {
			reply.add("UPDATE PASSWORD FAILED");
		}
		return reply;
	}

	public static ArrayList<String> deleteCredentials(int ID_user, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (credentialsDAO.delete(ID_user) != 0) {
			reply.add("DELETE CREDENTIALS OK");
		} else {
			reply.add("DELETE CREDENTIALS FAILED");
		}
		return reply;
	}

	public static ArrayList<String> addUser(String name, String surname, String date_of_birth, String type,
			String qualification, String username, String password, UserDAO userDAO, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (!credentialsDAO.checkUniqueUserame(username)) {
			reply.add("ADD USER NOT OK");
			reply.add("Korisnicko ime vec zauzeto.");
			return reply;
		}
		//User user = new User(name, surname, TimeUtility.stringToLocalDate(date_of_birth), type, qualification);
		User user = new User(name, surname, LocalDate.parse(date_of_birth), type, qualification);
		//System.out.println(TimeUtility.stringToLocalDate(date_of_birth).toString());
		int id_user;
		if ((id_user = userDAO.insert(user)) != 0) {
			Credentials credentials = new Credentials(id_user, id_user, username, HashHandler.createHash(password));
			if (credentialsDAO.insert(credentials) != 0) {
				reply.add("ADD USER OK");
				reply.add(""+id_user);
				return reply;
			}
		}
		reply.add("ADD USER NOT OK");
		reply.add("Greska!Novi korisnik nije kreiran.");
		return reply;
	}

	public static ArrayList<String> updateUser(int ID_user, String name, String surname, String date_of_birth,
			String type, String qualification, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		User user = new User(ID_user, name, surname, TimeUtility.stringToLocalDate(date_of_birth), type, qualification);
		if (userDAO.update(user) != 0) {
			reply.add("UPDATE USER OK");
		} else {
			reply.add("UPDATE USER FAILED");
			reply.add("Izmjena podataka korisnika nije uspjela.");
		}
		return reply;
	}

	public static ArrayList<String> changePassword(int userID, String password, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		String hashedPassword = HashHandler.createHash(password);
		if (credentialsDAO.changePassword(userID, hashedPassword) != 0) {
			reply.add("CHANGE PASSWORD OK");
		} else {
			reply.add("CHANGE PASSWORD FAILED");
			reply.add("Promjena lozinke nije moguca.");
		}
		return reply;
	}

	public static ArrayList<String> deleteUser(int userID, UserDAO userDAO, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (ActiveUsersWatch.isAlredyLoggedIn(userID)) {
			reply.add("DELETE USER FAILED");
			reply.add("Korisnik je trenutno prijavljen na sistem.");
		} else if ((credentialsDAO.delete(userID) != 0) && (userDAO.delete(userID) != 0)) {
			reply.add("DELETE USER OK");
		} else {
			reply.add("DELETE USER FAILED");
			reply.add("Neuspjesno brisanje korisnika.");
		}
		return reply;
	}

	public static ArrayList<String> viewOnlineUsers(CredentialsDAO credentialsDAO) {
		return ActiveUsersWatch.getActiveUsers(credentialsDAO);
	}

	public static ArrayList<String> viewFieldTechnitians() {
		return ActiveUsersWatch.getOnlineFieldTechnitians();
	}

	public static ArrayList<String> viewAvailableFieldTechnitians() {
		return ActiveUsersWatch.getAvailableFieldTechnitians();
		}

	public static ArrayList<String> changeStateFieldTechnitian(int user_ID, String state,EventDAO eventDAO) {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("CHANGE FIELD TECHNITIAN STATE");
		ActiveUsersWatch.changeFieldTechnitianState(user_ID, state);
		Event event=new Event(ActiveUsersWatch.getUserSession(user_ID),LocalDateTime.now(),"Korisnik je promjenio stanje u "+state);
		eventDAO.insert(event);
		return reply;
	}

	public static ArrayList<String> viewFieldTechnitianState(int user_ID) {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW FIELD TECHNITIAN STATE");
		reply.add(ActiveUsersWatch.getFieldTechnitianState(user_ID));
		return reply;
	}

	public static ArrayList<String> viewUser(int user_ID, UserDAO userDAO, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		try {
			reply.add("VIEW USER OK");
			User user = userDAO.select(user_ID);
			Credentials credentials = credentialsDAO.select(user_ID);
			reply.add("" + user.getID_user());
			reply.add(user.getName());
			reply.add(user.getSurname());
			reply.add(user.getType());
			reply.add(TimeUtility.localDateToString(user.getDate_of_birth()));
			reply.add(user.getQualification());
			reply.add(credentials.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			reply.add("VIEW USER NOT OK");
			reply.add("Greska kod pregleda korisnika.");
		}
		return reply;
	}

	public static ArrayList<String> viewUsers(String param, UserDAO userDAO, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		try {
			reply.add("VIEW USERS OK");
			ArrayList<User> users = (ArrayList<User>) userDAO.selectAll();
			ArrayList<Credentials> credentials = (ArrayList<Credentials>) credentialsDAO.selectAll();
			reply.add("" + users.size());
			for (User u : users) {
				String userString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname();
				userString += ":" + u.getType() + ":"
						+ credentials
								.get(credentials.indexOf(new Credentials(u.getID_user(), u.getID_user(), null, null)))
								.getUsername();
				reply.add(userString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reply.add("VIEW USERS NOT OK.");
			reply.add("Greska u koracima.");
		}
		return reply;
	}
}