package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import model.users.Event;
import utility.DataSourceFactory;

public class MySQLEventDAO implements EventDAO {
	
	private static final String SQL_SELECT = "SELECT * FROM event WHERE ID_event=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM event WHERE ID_session=?";
	private static final String SQL_INSERT = "INSERT INTO event (ID_event,ID_session, time, action) VALUES (null,?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE event SET action=?, session_ID=? ,timeStamp=? WHERE ID_event=?";
	private static final String SQL_DELETE = "DELETE FROM event WHERE ID_event=?";
	
	@Override
	public Event select(int ID_event) {
		Event returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1,ID_event);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Event(rs.getInt("ID_event"), rs.getInt("ID_session"),
						LocalDateTime.parse(rs.getString("time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), rs.getString("action"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public List<Event> selectBySession(int ID_session) {
		List<Event> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			ps.setInt(1,ID_session);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Event(rs.getInt("ID_event"), rs.getInt("ID_session"),
						LocalDateTime.parse(rs.getString("time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), rs.getString("action")));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;

	}

	@Override
	public int insert(Event event) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, event.getID_session());
			ps.setObject(2, event.getTimeStamp());
			ps.setObject(3, event.getAction());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int update(Event  event) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, event.getID_event());
			ps.setObject(2, event.getID_session());
			ps.setObject(3, event.getTimeStamp());
			ps.setObject(4, event.getAction());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_event) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_event);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
}
