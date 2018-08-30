package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.interventions.Intervention;
import utility.DataSourceFactory;
import utility.TimeUtility;

public class MySQLInterventionDAO implements InterventionDAO {

	private static final String SQL_SELECT = "SELECT * FROM intervention WHERE ID_intervention=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM intervention";
	private static final String SQL_INSERT = "INSERT INTO intervention (ID_intervention,ID_client,ID_vehicle,ID_user_opened,ID_user_closed,opened_on,closed_on,closed, remark) VALUES (null,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE intervention SET ID_client=?,ID_vehicle=?,ID_user_opened=?,ID_user_closed=?,opened_on=?,closed_on=?,closed=?,remark=? WHERE ID_intervention=?";
	private static final String SQL_DELETE = "DELETE FROM intervention WHERE ID_intervention=?";
	private static final String SQL_CLOSE = "UPDATE intervention SET ID_user_closed=?,closed_on=?,closed=?,remark=? WHERE ID_intervention=?";

	@Override
	public Intervention select(int ID_intervention) {
		Intervention returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, ID_intervention);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Intervention(rs.getInt("ID_intervention"), rs.getInt("ID_client"),
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark"),
						rs.getBoolean("closed"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {rs.close();ps.close();c.close();} catch (SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public List<Intervention> selectAll() {
		List<Intervention> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Intervention(rs.getInt("ID_intervention"), rs.getInt("ID_client"),
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark"),
						rs.getBoolean("closed")));
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
	public int insert(Intervention intervention) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, intervention.getID_client());
			ps.setObject(2, intervention.getID_vehicle());
			ps.setObject(3, intervention.getID_user_opened());
			ps.setObject(4, intervention.getID_user_closed());
			ps.setObject(5, intervention.getOpened_on());
			ps.setObject(6, intervention.getClosed_on());
			ps.setObject(7, intervention.getRemark());
			ps.setObject(8, intervention.isClosed());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int update(Intervention intervention) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, intervention.getID_client());
			ps.setObject(2, intervention.getID_vehicle());
			ps.setObject(3, intervention.getID_user_opened());
			ps.setObject(4, intervention.getID_user_closed());
			ps.setObject(5, intervention.getOpened_on());
			ps.setObject(6, intervention.getClosed_on());
			ps.setObject(7, intervention.getRemark());
			ps.setObject(8, intervention.isClosed());
			ps.setObject(9, intervention.getID_intervention());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_intervention) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_intervention);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int close(int ID_intervention, String remark, int ID_closed, LocalDateTime closed_on, boolean closed) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_CLOSE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_closed);
			ps.setObject(2, closed_on);
			ps.setObject(3, closed);
			ps.setObject(4, remark);
			ps.setObject(5, ID_intervention);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

}
