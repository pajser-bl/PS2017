package controller;

import java.util.ArrayList;
import java.util.List;

import DAO.EventDAO;
import DAO.SessionDAO;
import model.users.Event;
import model.users.Session;
import utility.TimeUtility;

public class SessionControl {

	public static ArrayList<String> viewUserSession(int ID_session, SessionDAO sessionDAO, EventDAO eventDAO) {
		// ID_session,ID_user,start,end
		ArrayList<String> reply = new ArrayList<>();
		Session session = sessionDAO.select(ID_session);
		int ID_user = session.getUserID();
		String start = TimeUtility.localDateTimeToString(session.getStart());
		String end = TimeUtility.localDateTimeToString(session.getEnd());
		reply.add(""+ID_session);
		reply.add(""+ID_user);
		reply.add(start);
		reply.add(end);
		ArrayList<Event> eventList=(ArrayList<Event>) eventDAO.selectBySession(ID_session);
		reply.add(""+eventList.size());
		for (Event e : eventList)
			reply.add(e.toString());
		return reply;
	}

	public static ArrayList<String> viewUserSessions(int ID_user, SessionDAO sessionDAO) { // treba testiratiS
		ArrayList<String> reply = new ArrayList<>();
		List<Session> sessions = sessionDAO.viewUserSessions(ID_user);
		for(Session s :sessions) {
			int ID_session= s.getSessionID();
			String start = TimeUtility.localDateTimeToString(s.getStart());
			String end = TimeUtility.localDateTimeToString(s.getEnd());
			reply.add(""+ID_session);
			reply.add(""+ID_user);
			reply.add(start);
			reply.add(end);
			
		}
		return reply;
	}

}
