package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.TimeUtility;
import java.util.ArrayList;
import java.util.List;

import model.users.Session;
import utility.DataSourceFactory;

public class MySQLSessionDAO implements SessionDAO {
	private static final String SQL_SELECT = "SELECT * FROM session WHERE sessionID =?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM session";
	private static final String SQL_INSERT = "INSERT INTO session (sessionID, userID, eventList, start, end)  VALUES (?,?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE session SET userID=?, eventList=?, start=?, end=? , WHERE sessionID = ?";
	private static final String SQL_DELETE = "DELETE FROM session WHERE sessionID=?";
	
	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
	
	@Override
	public Session select(int sessionID) {
		Session returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1,sessionID);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Session(rs.getInt("sessionID"), rs.getInt("userID"), 
						TimeUtility.stringToLocalDateTime(rs.getString("start")), 
						TimeUtility.stringToLocalDateTime(rs.getString("end")));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
//		}finally {
//			//stry {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public List<Session> selectAll() {
		List<Session> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Session(rs.getInt("sessionID"), rs.getInt("userID"), 
						TimeUtility.stringToLocalDateTime(rs.getString("start")), 
						TimeUtility.stringToLocalDateTime(rs.getString("end"))));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public int insert(Session session) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, session.getSessionID());
			ps.setObject(2, session.getUserID());
			ps.setObject(3, session.getStart());
			ps.setObject(4, session.getEnd());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
	
	@Override
	public int update(Session session) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, session.getSessionID());
			ps.setObject(2, session.getUserID());
			ps.setObject(3, session.getStart());
			ps.setObject(4, session.getEnd());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
	
	@Override
	public int delete(int sessionID) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, sessionID);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
}
