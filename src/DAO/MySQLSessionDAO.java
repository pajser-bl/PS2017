package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utility.TimeUtility;
import java.util.ArrayList;
import java.util.List;

import model.users.Session;
import utility.DataSourceFactory;

public class MySQLSessionDAO implements SessionDAO {
	private static final String SQL_SELECT = "SELECT * FROM session WHERE ID_session=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM session";
	private static final String SQL_INSERT = "INSERT INTO session (ID_session, ID_user, start, end)  VALUES (null,?,?,null)";
	private static final String SQL_UPDATE = "UPDATE session SET ID_user=?, start=?, end=?  WHERE ID_session = ?";
	private static final String SQL_DELETE = "DELETE FROM session WHERE ID_session=?";
	
	
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
				returnValue = new Session(rs.getInt("ID_session"), rs.getInt("ID_user"), 
						TimeUtility.stringToLocalDateTime(rs.getString("start")), 
						TimeUtility.stringToLocalDateTime(rs.getString("end")));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();c.close();}catch(SQLException e) {}
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
				returnValue.add(new Session(rs.getInt("ID_session"), rs.getInt("ID_user"), 
						TimeUtility.stringToLocalDateTime(rs.getString("start")), 
						TimeUtility.stringToLocalDateTime(rs.getString("end"))));
		} catch (SQLException e) {
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
		ResultSet rs=null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, session.getUserID());
			ps.setObject(2, session.getStart());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next())
				retVal=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();c.close();}catch(SQLException e) {}
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
			ps.setObject(1, session.getUserID());
			ps.setObject(2, session.getStart());
			ps.setObject(3, session.getEnd());
			ps.setObject(4, session.getSessionID());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
	
	@Override
	public int delete(int ID_session) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_session);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
}
