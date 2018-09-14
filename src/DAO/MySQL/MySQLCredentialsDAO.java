package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.CredentialsDAO;
import model.users.Credentials;
import utility.DataSourceFactory;

public class MySQLCredentialsDAO implements CredentialsDAO{

	private static final String SQL_SELECT = "SELECT * FROM credentials WHERE ID_credentials=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM credentials";
	private static final String SQL_INSERT = "INSERT INTO credentials (ID_credentials,ID_user, username, hash) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE credentials SET username=?, hash=? WHERE ID_credentials=?";
	private static final String SQL_DELETE = "DELETE FROM credentials WHERE ID_credentials=?";
	
	private static final String SQL_UNIQUE_USERNAME = "SELECT EXISTS(SELECT 1 FROM credentials WHERE username=? limit 1)AS is_unique;";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM credentials WHERE username=?";
	private static final String SQL_EXISTS = "SELECT EXISTS(SELECT 1 FROM credentials WHERE username=? limit 1)AS _exists";
//	private static final String SQL_EXISTS = "SELECT count(*) as count FROM credentials WHERE username =?";
	
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
						rs.getString("username"), rs.getString("hash"));
		} catch (SQLException e) {
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
						rs.getString("username"), rs.getString("hash")));
		} catch (SQLException e) {
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
			ps.setObject(1, credentials.getID_credentials());//ID_user=ID_credentials
			ps.setObject(2, credentials.getID_user());
			ps.setObject(3, credentials.getUsername());
			ps.setObject(4, credentials.getHash());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
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
			ps.setObject(2, credentials.getHash());
			ps.setObject(3, credentials.getID_user());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
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
			returnValue=rs.getInt("is_unique")!=0;
			return returnValue;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public Credentials select(String username) {
		Credentials returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_BY_USERNAME);
			ps.setString(1,username);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Credentials(rs.getInt("ID_credentials"), rs.getInt("ID_user"),
						rs.getString("username"), rs.getString("hash"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public boolean exists(String username) {
		boolean returnValue;
		Connection c=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_EXISTS);
			ps.setString(1,username);
			rs=ps.executeQuery();
			if(rs.next()) {
				returnValue=rs.getInt("_exists")==1;
			}else {
				returnValue=false;
				}
			return returnValue;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{rs.close();ps.close();c.close();}catch(SQLException e){}
		}
		return false; //blablablablaaaa
	}

}
