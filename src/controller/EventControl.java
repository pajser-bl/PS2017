package controller;

import DAO.EventDAO;
import model.users.Event;

public class EventControl {

	public static void addEvent(Event event, EventDAO eventDAO) {
		eventDAO.insert(event);
	}
	
	
}
