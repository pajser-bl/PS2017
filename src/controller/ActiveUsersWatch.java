package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import DAO.CredentialsDAO;
import model.users.User;

public class ActiveUsersWatch {
	public static ArrayList<User> activeUsersList = new ArrayList<>();
	public static Map<User, String> roadAssistentStates = new HashMap<User, String>();
	public static Map<Integer, Integer> activeUsersSession = new HashMap<Integer, Integer>();
	//public static Map<Integer, Integer> fieldTechniciansInterventions=new HashMap<Integer,Integer>();

	
//	public static void assignIntervention(int fieldTechnician_ID, int intervention_ID) {
//		changeFieldTechnicianState(fieldTechnician_ID, "zauzet");
//		fieldTechniciansInterventions.put(fieldTechnician_ID,intervention_ID);
//	}
//	public static void deassignjIntervention(int fieldTechnician_ID) {
//		changeFieldTechnicianState(fieldTechnician_ID, "neaktivan");
//		fieldTechniciansInterventions.remove(fieldTechnician_ID);
//	}
//	public static int getFieldTechnicianByIntervention(int id_intervention) {
//		for(Integer id:fieldTechniciansInterventions.keySet()){
//			if(fieldTechniciansInterventions.get(id).equals(id_intervention))
//				return id;
//		}
//		return 0;
//	}
//	public static ArrayList<String> checkFieldTechnicianAssignement(int fieldTechnician_ID) {
//		ArrayList<String> reply=new ArrayList<String>();
//		if(fieldTechniciansInterventions.containsKey(fieldTechnician_ID)) {
//			reply.add("CHECK FIELD TECHNICIAN ASSIGNMENT OK");
//			reply.add(""+fieldTechniciansInterventions.get(fieldTechnician_ID));
//		}else {
//			reply.add("CHECK FIELD TECHNICIAN ASSIGNMENT OK");
//			reply.add("0");
//		}
//		return reply;
//	}
	
	public static void addActiveUser(User user) {
		activeUsersList.add(user);
		if (user.getType().toLowerCase().equals("terenski radnik")) {
			roadAssistentStates.put(user, "neaktivan");
		}
	}

	public static boolean isAlredyLoggedIn(int user_ID) {
		for (User user : activeUsersList)
			if (user.getID_user() == user_ID)
				return true;
		return false;
	}

	public static void removeActiveUser(int user_ID) {
		for (Iterator<User> userIterator = activeUsersList.iterator(); userIterator.hasNext();) {
			User u = userIterator.next();
			if (u.getID_user() == user_ID) {
				if (u.getType().equals("Terenski Radnik"))
					roadAssistentStates.remove(u);
				userIterator.remove();
			}
		}
	}

	public static String getFieldTechnicianState(int user_ID) {
		for (User u : roadAssistentStates.keySet()) {
			if (user_ID == u.getID_user()) {
				String tempStateString = roadAssistentStates.get(u);
				return u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":" + tempStateString;
			}
		}
		return null;
	}

	public static void changeFieldTechnicianState(int user_ID, String state) {
		for (User u : roadAssistentStates.keySet()) {
			if (u.getID_user() == user_ID)
				roadAssistentStates.replace(u, state);
		}
	}

	// vrate sve korisnike koji su povezani na server
	public static ArrayList<String> getActiveUsers(CredentialsDAO credentialsDAO) {
		ArrayList<String> reply = new ArrayList<>();
		String tempUserString;
		reply.add("VIEW ACTIVE USERS OK");
		reply.add("" + activeUsersList.size());
		for (User u : activeUsersList) {
			tempUserString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":" + u.getType();
			reply.add(tempUserString);
		}
		return reply;
	}

	// promjeni ine funkcije ako je drugaciji naziv tipa korisnika
	public static ArrayList<String> getOnlineFieldTechnicians() {
		ArrayList<String> reply = new ArrayList<>();
		String tempRoadAssistentString;
		String tempStateString;
		reply.add("VIEW FIELD TECHNICIANS OK");
		reply.add("" + roadAssistentStates.size());
		for (User u : roadAssistentStates.keySet()) {
			tempStateString = roadAssistentStates.get(u);
			tempRoadAssistentString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":" + tempStateString;
			reply.add(tempRoadAssistentString);
		}
		return reply;
	}

	// ovo je za terenske radnike
	public static ArrayList<String> getAvailableFieldTechnicians() {
		ArrayList<String> reply = new ArrayList<>();
		String tempRoadAssistentString;
		String tempStateString;
		int count=0;
		reply.add("VIEW AVAILABLE FIELD TECHNICIANS OK");
		for (User u : roadAssistentStates.keySet()) {
			tempStateString = roadAssistentStates.get(u);
			if (tempStateString.equals("aktivan")) {
				tempRoadAssistentString = u.getID_user() + ":" + u.getName() + ":" + u.getSurname() + ":"
						+ tempStateString;
				reply.add(tempRoadAssistentString);
				count++;
			}
		}
		reply.add(1, ""+count);
		return reply;
	}

	public static void addUserSession(int ID_user, int ID_session) {
		activeUsersSession.put(ID_user, ID_session);
	}

	public static void removeUserSession(int ID_user) {
		activeUsersSession.remove(ID_user);
	}

	public static boolean userHasSession(int ID_user) {
		return activeUsersSession.containsKey(ID_user);
	}

	public static int getUserSession(int ID_user) {
		return activeUsersSession.get(ID_user);
	}
	
}