package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.users.User;
import utility.DataSourceFactory;
import utility.TimeUtility;
import java.time.LocalDate;

public class MySQLUserDAO implements UserDAO{
	private static final String SQL_SELECT = "SELECT * FROM user WHERE ID_user=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM user";
	private static final String SQL_INSERT = "INSERT INTO user (ID_user, name, surname, date_of_birth, type, qualification) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE user SET name=?, surname=? , date_of_birth=? , type=? , qualification=?,   WHERE ID_user=?";
	private static final String SQL_DELETE = "DELETE FROM user WHERE ID_user=?";
	
	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
	
	@Override
	public User select(int userID) {
		User returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new User(rs.getInt("userID"),rs.getString("name"), rs.getString("surname"), 
						 TimeUtility.stringToLocalDate(rs.getString("date_of_birth")),rs.getString("type"), rs.getString("qualification") );
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public List<User> selectAll() {
		List<User> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new User(rs.getInt("userID"),rs.getString("name"), rs.getString("surname"), 
						 TimeUtility.stringToLocalDate(rs.getString("date_of_birth")),rs.getString("type"), rs.getString("qualification")));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public int insert(User user) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, user.getID_user());
			ps.setObject(2, user.getName());
			ps.setObject(3, user.getSurname());
			ps.setObject(4, user.getDate_of_birth());
			ps.setObject(5, user.getType());
			ps.setObject(6, user.getQualification());
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
	public int update(User user) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, user.getName());
			ps.setObject(2, user.getSurname());
			ps.setObject(3, user.getDate_of_birth());
			ps.setObject(4, user.getType());
			ps.setObject(5, user.getQualification());
			ps.setObject(6, user.getID_user());
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
	public int delete(int userID) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, userID);
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
