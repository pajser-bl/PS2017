package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.EventDAO;
import DAO.UserDAO;
import model.users.Event;
import model.users.User;
import utility.HashHandler;
import utility.TimeUtility;

public class UserControl {

	public static ArrayList<String> changePassword(int ID_user, String password, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (userDAO.changePassword(ID_user, HashHandler.createHash(password)) != 0) {
			reply.add("UPDATE PASSWORD OK");
		} else {
			reply.add("UPDATE PASSWORD FAILED");
			reply.add("Neuspjesna promjena lozinke.");
		}
		return reply;
	}

	public static ArrayList<String> addUser(String name, String surname, String date_of_birth, String type,
			String qualification, String drivers_license, String username, String password, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (!userDAO.checkUniqueUserame(username)) {
			reply.add("ADD USER NOT OK");
			reply.add("Korisnicko ime vec zauzeto.");
			return reply;
		}
		User user = new User(name, surname, LocalDate.parse(date_of_birth), type, qualification, drivers_license,
				username, HashHandler.createHash(password));
		int id_user;
		if ((id_user = userDAO.insert(user)) != 0) {

			reply.add("ADD USER OK");
			reply.add("" + id_user);
			return reply;
		} else {
			reply.add("ADD USER NOT OK");
			reply.add("Greska!Novi korisnik nije kreiran.");
			return reply;
		}
	}

	public static ArrayList<String> updateUser(int ID_user, String name, String surname, String date_of_birth,
			String type, String qualification, String drivers_license, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		User user = new User(ID_user, name, surname, TimeUtility.stringToLocalDate(date_of_birth), type, qualification,
				drivers_license);
		if (userDAO.update(user) != 0) {
			reply.add("UPDATE USER OK");
		} else {
			reply.add("UPDATE USER FAILED");
			reply.add("Izmjena podataka korisnika nije uspjela.");
		}
		return reply;
	}

	public static ArrayList<String> deleteUser(int userID, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (ActiveUsersWatch.isAlredyLoggedIn(userID)) {
			reply.add("DELETE USER FAILED");
			reply.add("Korisnik je trenutno prijavljen na sistem.");
		} else if (userDAO.delete(userID) != 0) {
			reply.add("DELETE USER OK");
		} else {
			reply.add("DELETE USER FAILED");
			reply.add("Neuspjesno brisanje korisnika.");
		}
		return reply;
	}

	public static ArrayList<String> viewOnlineUsers(UserDAO userDAO) {
		return ActiveUsersWatch.getActiveUsers();
	}

	public static ArrayList<String> viewFieldTechnicians() {
		return ActiveUsersWatch.getOnlineFieldTechnicians();
	}

	public static ArrayList<String> viewAvailableFieldTechnicians() {
		return ActiveUsersWatch.getAvailableFieldTechnicians();
	}

	public static ArrayList<String> changeStateFieldTechnician(int user_ID, String state, EventDAO eventDAO) {
		ArrayList<String> reply = new ArrayList<>();
		String current_state = ActiveUsersWatch.getFieldTechnicianState(user_ID);
		if (current_state.equals("zauzet")) {
			reply.add("CHANGE STATE NOT OK");
			reply.add("Trenutno ste zauzeti.");
		} else {
			reply.add("CHANGE STATE OK");
			ActiveUsersWatch.changeFieldTechnicianState(user_ID, state);
			Event event = new Event(ActiveUsersWatch.getUserSession(user_ID), LocalDateTime.now(),
					"Korisnik je promjenio stanje u " + state);
			eventDAO.insert(event);
		}
		return reply;
	}

	public static ArrayList<String> viewFieldTechnicianState(int user_ID) {
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW FIELD TECHNICIAN STATE");
		reply.add(ActiveUsersWatch.getFieldTechnicianState(user_ID));
		return reply;
	}

	public static ArrayList<String> viewUser(int user_ID, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		try {
			reply.add("VIEW USER OK");
			User user = userDAO.select(user_ID);
			reply.add("" + user.getID_user());
			reply.add(user.getName());
			reply.add(user.getSurname());
			reply.add(user.getType());
			reply.add(TimeUtility.localDateToString(user.getDate_of_birth()));
			reply.add(user.getQualification());
			reply.add(user.getDrivers_license());
			reply.add(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			reply.add("VIEW USER NOT OK");
			reply.add("Greska kod pregleda korisnika.");
		}
		return reply;
	}

	public static ArrayList<String> viewUsers(String param, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		try {
			reply.add("VIEW USERS OK");
			ArrayList<User> users = (ArrayList<User>) userDAO.selectAll();
			reply.add("" + users.size());
			for (User u : users) {
				String userString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname();
				userString += ":" + u.getType() + ":" + u.getUsername();
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