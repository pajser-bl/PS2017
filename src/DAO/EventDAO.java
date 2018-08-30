package DAO;

import java.util.List;

import model.users.Event;


public interface EventDAO {
	public Event select(int ID_event);
	public List<Event> selectBySession(int ID_session);
	public int insert(Event event);
	public int update(Event event);
	public int delete(int ID_event);
}
