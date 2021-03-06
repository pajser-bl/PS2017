package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.ClientDAO;
import model.client.Client;
import utility.DataSourceFactory;

public class MySQLClientDAO implements ClientDAO {
	private static final String SQL_SELECT = "SELECT * FROM client WHERE ID_client=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM client";
	private static final String SQL_INSERT = "INSERT INTO client (ID_client, name, surname, phone_number) VALUES (null,?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE client SET name=?, surname=? , phone_number=?  WHERE ID_client=?";
	private static final String SQL_DELETE = "DELETE FROM client WHERE ID_client=?";
	private static final String SQL_EXIST = "SELECT * FROM client where name=? and surname=? and phone_number=?";
	
	@Override
	public int exist(Client client) {
		int returnValue=0;
		Connection c=null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_EXIST);
			ps.setString(1, client.getName());
			ps.setString(2, client.getSurname());
			ps.setString(3, client.getPhone_number());
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = rs.getInt("ID_client");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();} catch(SQLException e) {}
		}
		return returnValue;
	}
	
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
				returnValue = new Client(rs.getInt("ID_client"), rs.getString("name"), rs.getString("surname"), rs.getString("phone_number"));
		} catch (SQLException e) {
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
				returnValue.add(new Client(rs.getInt("ID_client"), rs.getString("name"), rs.getString("surname"), rs.getString("phone_number")));
		} catch (SQLException e) {
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
		ResultSet rs=null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, client.getName());
			ps.setObject(2, client.getSurname());
			ps.setObject(3, client.getPhone_number());
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
	public int update(Client client) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, client.getName());
			ps.setObject(2, client.getSurname());
			ps.setObject(3, client.getPhone_number());
			ps.setObject(4, client.getID_client());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	
}
