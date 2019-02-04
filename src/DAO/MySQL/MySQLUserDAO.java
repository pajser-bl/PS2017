package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import model.users.User;
import utility.DataSourceFactory;
import utility.TimeUtility;

public class MySQLUserDAO implements UserDAO {
	private static final String SQL_SELECT = "SELECT * FROM user WHERE ID_user=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM user";
	private static final String SQL_INSERT = "INSERT INTO user (ID_user, name, surname, date_of_birth, type, qualification, drivers_license, username, hash) VALUES (null,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE user SET name=?, surname=? , date_of_birth=? , type=? , qualification=? , drivers_license=?   WHERE ID_user=?";
	private static final String SQL_DELETE = "DELETE FROM user WHERE ID_user=?";
	
	private static final String SQL_ADMINISTRATOR_EXISTS = "SELECT EXISTS(SELECT 1 FROM user WHERE type=? limit 1)AS _administrator_exists";
	private static final String SQL_UNIQUE_USERNAME = "SELECT EXISTS(SELECT 1 FROM user WHERE username=? limit 1)AS _exists";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM user WHERE username=?";
	private static final String SQL_CHANGE_PASSWORD = "UPDATE user SET hash=? WHERE ID_user=?";

	
	
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
				returnValue = new User(rs.getInt("ID_user"), rs.getString("name"), rs.getString("surname"),
						TimeUtility.stringToLocalDate(rs.getString("date_of_birth")), rs.getString("type"),
						rs.getString("qualification"), rs.getString("drivers_license"), rs.getString("username"), rs.getString("hash"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
			}
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
				returnValue.add(new User(rs.getInt("ID_user"), rs.getString("name"), rs.getString("surname"),
						TimeUtility.stringToLocalDate(rs.getString("date_of_birth")), rs.getString("type"),
						rs.getString("qualification"), rs.getString("drivers_license"), rs.getString("username"), rs.getString("hash")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
			}
		}
		return returnValue;
	}

	@Override
	public int insert(User user) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, user.getName());
			ps.setObject(2, user.getSurname());
			ps.setObject(3, user.getDate_of_birth());
			ps.setObject(4, user.getType());
			ps.setObject(5, user.getQualification());
			ps.setObject(6, user.getDrivers_license());
			ps.setObject(7, user.getUsername());
			ps.setObject(8, user.getHash());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
				retVal = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
			}
		}
		return retVal;
	}

	@Override
	public int update(User user) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, user.getName());
			ps.setObject(2, user.getSurname());
			ps.setObject(3, user.getDate_of_birth());
			ps.setObject(4, user.getType());
			ps.setObject(5, user.getQualification());
			ps.setObject(6, user.getID_user());
			ps.setObject(7, user.getDrivers_license());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
			}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_user) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_user);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
			}
		}
		return retVal;
	}

	@Override
	public boolean administrator_exists() {
		boolean returnValue = false;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_ADMINISTRATOR_EXISTS);
			ps.setString(1, "Administrator");
			rs = ps.executeQuery();
			if (rs.next()) {
				returnValue = rs.getInt("_administrator_exists") != 0;
			} else {
				returnValue = false;
			}
			return returnValue;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				c.close();
			} catch (SQLException e) {
			}
		}
		return returnValue;
	}

	@Override
	public boolean checkUniqueUserame(String username) {
		boolean returnValue = false;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_UNIQUE_USERNAME);
			ps.setString(1,username);
			rs = ps.executeQuery();
			if(rs.next()) {
				returnValue=rs.getInt("_exists")==0;
			}else {
				returnValue=false;
			}
			return returnValue;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public User select(String username) {
		User returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_BY_USERNAME);
			ps.setString(1,username);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new User(rs.getInt("ID_user"), rs.getString("name"), rs.getString("surname"),
						TimeUtility.stringToLocalDate(rs.getString("date_of_birth")), rs.getString("type"),
						rs.getString("qualification"), rs.getString("drivers_license"), rs.getString("username"), rs.getString("hash"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public int changePassword(int ID_user, String hashedPassword) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_CHANGE_PASSWORD, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, hashedPassword);
			ps.setObject(2, ID_user);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
}
