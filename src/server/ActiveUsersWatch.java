package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.users.User;

public class ActiveUsersWatch {
	public static ArrayList<User> activeUsersList = new ArrayList<>();
	public static Map<User, String> roadAssistentStates = new HashMap<User, String>();

	public static void addActiveUser(User user) {
		activeUsersList.add(user);
		if (user.getType().toLowerCase().equals("terenski radnik")) {
			roadAssistentStates.put(user, "zauzet");
		}
	}
	
	public static boolean isAlredyLoggedIn(int user_ID) {
		for(User user:activeUsersList)
			if(user.getID_user()==user_ID)
				return true;
		return false;
	}
	
	public static void removeActiveUser(int user_ID) {
		for (User u : activeUsersList) {
			if (u.getID_user() == user_ID) {
				if (u.getType().equals("terenski radnik"))
					roadAssistentStates.remove(u);
				activeUsersList.remove(u);
			}
		}
	}

	public static String getFieldTechnitianState(int user_ID) {
		for (User u : roadAssistentStates.keySet()) {
			if (user_ID == u.getID_user()) {
				String tempStateString = roadAssistentStates.get(u);
				return u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":" + tempStateString;
			}
		}
		return null;
	}

	public static void changeFieldTechnitianState(int user_ID, String state) {
		for (User u : roadAssistentStates.keySet()) {
			if (u.getID_user() == user_ID)
				roadAssistentStates.replace(u, state);
		}
	}

	// vrate sve korisnike koji su povezani na server
	public static ArrayList<String> getActiveUsers() {
		ArrayList<String> reply = new ArrayList<>();
		String tempUserString;
		for (User u : activeUsersList) {
			tempUserString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":" + u.getType();
			reply.add(tempUserString);
		}
		return reply;
	}

	// promjeni ine funkcije ako je drugaciji naziv tipa korisnika
	public static ArrayList<String> getOnlineFieldTechnitians() {
		ArrayList<String> reply = new ArrayList<>();
		String tempRoadAssistentString;
		String tempStateString;
		for (User u : roadAssistentStates.keySet()) {
			tempStateString = roadAssistentStates.get(u);
			tempRoadAssistentString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":" + tempStateString;
			reply.add(tempRoadAssistentString);
		}
		return reply;
	}

	// ovo je za terenske radnike

	public static ArrayList<String> getAvailableFieldTechnitians() {
		ArrayList<String> reply = new ArrayList<>();
		String tempRoadAssistentString;
		String tempStateString;
		for (User u : roadAssistentStates.keySet()) {
			tempStateString = roadAssistentStates.get(u);
			if (tempStateString.equals("available")) {
				tempRoadAssistentString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":"
						+ tempStateString;
				reply.add(tempRoadAssistentString);
			}
		}
		return reply;
	}
}
