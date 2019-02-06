package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.InterventionDAO;
import model.interventions.Intervention;
import utility.DataSourceFactory;
import utility.TimeUtility;

public class MySQLInterventionDAO implements InterventionDAO {

	private static final String SQL_SELECT = "SELECT * FROM intervention WHERE ID_intervention=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM intervention";
	private static final String SQL_SELECT_ALL_OPENED = "SELECT * FROM intervention where state=\"otvorena\"";
	private static final String SQL_SELECT_ALL_CLOSED = "SELECT * FROM intervention where state=\"zatvorena\"";
	private static final String SQL_SELECT_ALL_REPORTS = "SELECT * FROM intervention where state=\"izvjestaj\"";
	private static final String SQL_INSERT = "INSERT INTO intervention (ID_intervention,ID_client,ID_vehicle,ID_user_opened,ID_field_technician,opened_on,state,assistance,time_of_assistance,remark_field_technician,ID_user_closed,closed_on,remark_operator,ID_supervisor, remark_supervisor,report_made) VALUES (null,?,?,?,?,?,?,null,null,null,null,null,null,null,null,null)";
	private static final String SQL_UPDATE = "UPDATE intervention SET ID_client = ?, ID_vehicle = ?,ID_user_opened = ?,ID_field_technician = ?, opened_on = ?, state = ?, assistance = ?, time_of_assistance = ?, remark_field_technician = ?, ID_user_closed = ?, closed_on = ?, remark_operator = ?, ID_supervisor = ?,  remark_supervisor = ?, report_made = ? WHERE ID_intervention=?";
	private static final String SQL_DELETE = "DELETE FROM intervention WHERE ID_intervention=?";
	private static final String SQL_GET_INTERVENTION_BY_FIELD_TECHNICIAN = "SELECT ID_intervention FROM intervention where ID_field_technician=? and state=\"otvorena\"";

	@Override
	public int getInterventionByFieldTechnician(int user_ID) {
		int returnValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_GET_INTERVENTION_BY_FIELD_TECHNICIAN);
			ps.setInt(1, user_ID);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = rs.getInt("ID_intervention");
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
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_field_technician"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")), rs.getString("state"),
						rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")),
						rs.getString("remark_field_technician"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark_operator"),
						rs.getInt("ID_supervisor"), rs.getString("remark_supervisor"),
						TimeUtility.stringToLocalDateTime(rs.getString("report_made")));
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
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_field_technician"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")), rs.getString("state"),
						rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")),
						rs.getString("remark_field_technician"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark_operator"),
						rs.getInt("ID_supervisor"), rs.getString("remark_supervisor"),
						TimeUtility.stringToLocalDateTime(rs.getString("report_made"))));
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
	public List<Intervention> selectAllOpen() {
		List<Intervention> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL_OPENED);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Intervention(rs.getInt("ID_intervention"), rs.getInt("ID_client"),
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_field_technician"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")), rs.getString("state"),
						rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")),
						rs.getString("remark_field_technician"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark_operator"),
						rs.getInt("ID_supervisor"), rs.getString("remark_supervisor"),
						TimeUtility.stringToLocalDateTime(rs.getString("report_made"))));
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
	public List<Intervention> selectAllClosed() {
		List<Intervention> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL_CLOSED);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Intervention(rs.getInt("ID_intervention"), rs.getInt("ID_client"),
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_field_technician"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")), rs.getString("state"),
						rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")),
						rs.getString("remark_field_technician"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark_operator"),
						rs.getInt("ID_supervisor"), rs.getString("remark_supervisor"),
						TimeUtility.stringToLocalDateTime(rs.getString("report_made"))));
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
	public List<Intervention> selectAllReports() {
		List<Intervention> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL_REPORTS);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Intervention(rs.getInt("ID_intervention"), rs.getInt("ID_client"),
						rs.getInt("ID_vehicle"), rs.getInt("ID_user_opened"), rs.getInt("ID_field_technician"),
						TimeUtility.stringToLocalDateTime(rs.getString("opened_on")), rs.getString("state"),
						rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")),
						rs.getString("remark_field_technician"), rs.getInt("ID_user_closed"),
						TimeUtility.stringToLocalDateTime(rs.getString("closed_on")), rs.getString("remark_operator"),
						rs.getInt("ID_supervisor"), rs.getString("remark_supervisor"),
						TimeUtility.stringToLocalDateTime(rs.getString("report_made"))));
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
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, intervention.getID_client());
			ps.setObject(2, intervention.getID_vehicle());
			ps.setObject(3, intervention.getID_user_opened());
			ps.setObject(4, intervention.getID_field_technician());
			ps.setObject(5, intervention.getOpened_on());
			ps.setObject(6, intervention.getState());
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
	public int update(Intervention intervention) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, intervention.getID_client());
			ps.setObject(2, intervention.getID_vehicle());
			ps.setObject(3, intervention.getID_user_opened());
			ps.setObject(4, intervention.getID_field_technician());
			ps.setObject(5, intervention.getOpened_on());
			ps.setObject(6, intervention.getState());
			ps.setObject(7, intervention.getAssistance());
			ps.setObject(8, intervention.getTime_of_assistance());
			ps.setObject(9, intervention.getRemark_field_technician());
			ps.setObject(10, intervention.getID_user_closed());
			ps.setObject(11, intervention.getClosed_on());
			ps.setObject(12, intervention.getRemark_operator());
			ps.setObject(13, intervention.getID_supervisor());
			ps.setObject(14, intervention.getRemark_supervisor());
			ps.setObject(15, intervention.getReport_made());
			ps.setObject(16, intervention.getID_intervention());
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
	public int delete(int ID_intervention) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_intervention);
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

}
