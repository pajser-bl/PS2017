package DAO;

import java.util.List;

import model.users.Session;

public interface SessionDAO {
	public Session select(int sessionID);
	public List<Session> selectAll();
	int insert(Session session);
	public int update(Session session);
	public int delete(int sessionID);
}
