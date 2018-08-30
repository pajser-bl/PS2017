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
import utility.DataSourceFactory;
import utility.TimeUtility;
import model.users.Subscription;

public class MySQLSubscriptionDAO implements SubscriptionDAO {
	private static final String SQL_SELECT = "SELECT * FROM subscription WHERE ID_subscription=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM subscription";
	private static final String SQL_INSERT = "INSERT INTO subscription (ID_subscription, ID_client, start_date, end_date) VALUES (?,?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE subscription SET ID_client=?, start_date=? , end_date=?  WHERE ID_subscription=?";
	private static final String SQL_DELETE = "DELETE FROM subscription WHERE ID_subscription=?";
	
	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
	
	@Override
	public Subscription select(int ID_subscription) {
		Subscription returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, ID_subscription);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Subscription(rs.getInt("ID_subscription"), rs.getInt("ID_client"),
						TimeUtility.stringToLocalDate(rs.getString("start_date")),
						TimeUtility.stringToLocalDate( rs.getString("end_date")));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();} catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public List<Subscription> selectAll() {
		List<Subscription> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Subscription(rs.getInt("ID_subscription"), rs.getInt("ID_client"),
						TimeUtility.stringToLocalDate(rs.getString("start_date")),
						TimeUtility.stringToLocalDate( rs.getString("end_date"))));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	@Override
	public int insert(Subscription subscription) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, subscription.getID_subscription());
			ps.setObject(2, subscription.getID_client());
			ps.setObject(3, subscription.getStart_date());
			ps.setObject(4, subscription.getEnd_date());
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
	public int update(Subscription subscription) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, subscription.getID_subscription());
			ps.setObject(2, subscription.getID_client());
			ps.setObject(3, subscription.getStart_date());
			ps.setObject(4, subscription.getEnd_date());
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
	public int delete(int ID_subscription) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_subscription);
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
