package controller;

import java.util.ArrayList;

import DAO.CredentialsDAO;
import DAO.UserDAO;
import model.users.Credentials;
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
		if (credentialsDAO.checkUniqueUserame(username)) {
			reply.add("ADD USER NOT OK");
			reply.add("Korisnicko ime vec zauzeto.");
			return reply;
		}
		User user = new User(name, surname, TimeUtility.stringToLocalDate(date_of_birth), type, qualification);
		int id_user;
		if ((id_user = userDAO.insert(user)) != 0) {
			Credentials credentials = new Credentials(id_user, id_user, username, HashHandler.createHash(password));
			if (credentialsDAO.insert(credentials) != 0) {
				reply.add("ADD USER OK");
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
		}
		return reply;
	}

	public static ArrayList<String> deleteUser(int userID, UserDAO userDAO, CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if ((credentialsDAO.delete(userID) != 0) && (userDAO.delete(userID) != 0)) {
			reply.add("DELETE USER OK");
		} else {
			reply.add("DELETE USER FAILED");
		}
		return reply;
	}

	public static ArrayList<String> viewOnlineUsers() {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW ONLINE USERS");
		reply.addAll(ActiveUsersWatch.getActiveUsers());
		return reply;
	}

	public static ArrayList<String> viewStatesFieldTechnitians() {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW FIELD TECHNITIANS STATE");
		reply.addAll(ActiveUsersWatch.getOnlineFieldTechnitians());
		return reply;
	}

	public static ArrayList<String> viewAvailableFieldTechnitians() {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW AVAILABLE FIELD TECHNITIANS");
		reply.addAll(ActiveUsersWatch.getAvailableFieldTechnitians());
		return reply;
	}

	public static ArrayList<String> changeStateFieldTechnitian(int user_ID, String state) {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("CHANGE FIELD TECHNITIAN STATE");
		ActiveUsersWatch.changeFieldTechnitianState(user_ID, state);
		return reply;
	}

	public static ArrayList<String> viewFieldTechnitianState(int user_ID) {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW FIELD TECHNITIAN STATE");
		reply.add(ActiveUsersWatch.getFieldTechnitianState(user_ID));
		return reply;
	}
}
