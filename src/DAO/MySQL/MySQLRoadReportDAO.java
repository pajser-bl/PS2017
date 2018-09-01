package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.RoadReportDAO;
import model.interventions.RoadReport;
import utility.DataSourceFactory;
import utility.TimeUtility;


public class MySQLRoadReportDAO implements RoadReportDAO{
	
	private static final String SQL_SELECT = "SELECT * FROM road_report WHERE ID_road_report=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM road_report";
	private static final String SQL_INSERT = "INSERT INTO road_report (ID_road_report,ID_user,ID_intervention,assistance,time_of_assistance, remark) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE road_report SET ID_user=?,ID_intervention=?,assistance=?,time_of_assistance=?, remark=? WHERE ID_road_report=?";
	private static final String SQL_DELETE = "DELETE FROM road_report WHERE ID_road_report=?";
	
	
	@Override
	public RoadReport select(int ID_road_report) {
		RoadReport returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, ID_road_report);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new RoadReport(rs.getInt("ID_road_report"), rs.getInt("ID_user"),rs.getInt("ID_intervention"),rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")), rs.getString("remark"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {rs.close();ps.close();c.close();} catch (SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public List<RoadReport> selectAll() {
		List<RoadReport> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new RoadReport(rs.getInt("ID_road_report"), rs.getInt("ID_user"),rs.getInt("ID_intervention"),rs.getString("assistance"),
						TimeUtility.stringToLocalDateTime(rs.getString("time_of_assistance")), rs.getString("remark")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();ps.close();c.close();} catch (SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public int insert(RoadReport roadReport) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, roadReport.getID_road_report());//ID_road_report = ID_intervention!!!
			ps.setObject(2, roadReport.getID_user());
			ps.setObject(3, roadReport.getID_intervention());
			ps.setObject(4, roadReport.getAssistance());
			ps.setObject(5, roadReport.getTime_of_assistance());
			ps.setObject(6, roadReport.getRemark());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int update(RoadReport roadReport) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, roadReport.getID_user());
			ps.setObject(2, roadReport.getID_intervention());
			ps.setObject(3, roadReport.getAssistance());
			ps.setObject(4, roadReport.getTime_of_assistance());
			ps.setObject(5, roadReport.getRemark());
			ps.setObject(6, roadReport.getID_road_report());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_road_report) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_road_report);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	
	
}
