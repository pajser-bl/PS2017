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
import java.time.LocalDateTime;

import model.users.Client;
import utility.DataSourceFactory;
import utility.TimeUtility;

public class MySQLClientDAO implements ClientDAO {
	private static final String SQL_SELECT = "SELECT * FROM user WHERE ID_client=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM client";
	private static final String SQL_INSERT = "INSERT INTO client (ID_client, name, surname, phone_number) VALUES (?,?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE user SET name=?, surname=? , phone_number=?  WHERE ID_client=?";
	private static final String SQL_DELETE = "DELETE FROM client WHERE ID_client=?";
	
	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
	
	@Override
	public Client select(int ID_client) {
		Client returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, ID_client);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Client(rs.getInt("ID_client"), rs.getString("name"), rs.getString("surname"), rs.getInt("phone_number"));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();} catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public List<Client> selectAll() {
		List<Client> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Client(rs.getInt("ID_client"), rs.getString("name"), rs.getString("surname"), rs.getInt("phone_number")));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public int insert(Client client) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, client.getID_client());
			ps.setObject(2, client.getName());
			ps.setObject(3, client.getSurname());
			ps.setObject(4, client.getPhone_number());
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
	public int update(Client client) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, client.getID_client());
			ps.setObject(2, client.getName());
			ps.setObject(3, client.getSurname());
			ps.setObject(4, client.getPhone_number());
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
	public int delete(int ID_client) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_client);
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
