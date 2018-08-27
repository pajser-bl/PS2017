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

import model.Credentials;
import utility.DataSourceFactory;

public class MySQLCredentialsDAO implements CredentialsDAO{

	private static final String SQL_SELECT = "SELECT * FROM credentials WHERE ID_credentials=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM credentials";
	private static final String SQL_INSERT = "INSERT INTO credentials (ID_credentials,ID_user, username, password, salt) VALUES (?,?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE credentials SET username=?, password=? ,salt=? WHERE ID_credentials=?";
	private static final String SQL_DELETE = "DELETE FROM credentials WHERE ID_credentials=?";
	
	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

	@Override
	public Credentials select(int ID_credentials) {
		Credentials returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1,ID_credentials);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Credentials(rs.getInt("ID_credentials"), rs.getInt("ID_user"),
						rs.getString("username"), rs.getString("password"),rs.getString("salt"));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public List<Credentials> selectAll() {
		List<Credentials> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Credentials(rs.getInt("ID_credentials"), rs.getInt("ID_user"),
						rs.getString("username"), rs.getString("password"),rs.getString("salt")));
			c.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public int insert(Credentials credentials) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, credentials.getID_user());
			ps.setObject(2, credentials.getID_user());
			ps.setObject(3, credentials.getUsername());
			ps.setObject(4, credentials.getPassword());
			ps.setObject(5, credentials.getSalt());
			retVal = ps.executeUpdate();
			c.close();
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int update(Credentials credentials) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, credentials.getUsername());
			ps.setObject(2, credentials.getPassword());
			ps.setObject(3, credentials.getSalt());
			ps.setObject(4, credentials.getID_user());
			retVal = ps.executeUpdate();
			c.close();
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_credentials) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_credentials);
			retVal = ps.executeUpdate();
			c.close();
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public boolean checkUniqueUserame(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
