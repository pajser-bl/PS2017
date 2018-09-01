package DAO.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.ReportDAO;
import model.interventions.Report;
import utility.DataSourceFactory;
import utility.TimeUtility;

public class MySQLReportDAO implements ReportDAO{

	private static final String SQL_SELECT = "SELECT * FROM report WHERE ID_report=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM report";
	private static final String SQL_INSERT = "INSERT INTO report (ID_report,ID_user,ID_intervention, remark,closed_on) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE report SET ID_user=?,ID_intervention=?, remark=?,closed_on=? WHERE ID_report=?";
	private static final String SQL_DELETE = "DELETE FROM report WHERE ID_report=?";
	
	
	@Override
	public Report select(int ID_report) {
		Report returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, ID_report);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Report(rs.getInt("ID_report"), rs.getInt("ID_user"),rs.getInt("ID_intervention"), rs.getString("remark"),TimeUtility.stringToLocalDateTime(rs.getString("closed_on")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {rs.close();ps.close();c.close();} catch (SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public List<Report> selectAll() {
		List<Report> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Report(rs.getInt("ID_report"), rs.getInt("ID_user"),rs.getInt("ID_intervention"), rs.getString("remark"),TimeUtility.stringToLocalDateTime(rs.getString("closed_on"))));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();ps.close();c.close();} catch (SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public int insert(Report report) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, report.getID_report());//ID_report = ID_intervention!!!
			ps.setObject(2, report.getID_user());
			ps.setObject(3, report.getID_intervention());
			ps.setObject(4, report.getRemark());
			ps.setObject(5, report.getClosed_on());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int update(Report report) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, report.getID_user());
			ps.setObject(2, report.getID_intervention());
			ps.setObject(3, report.getRemark());
			ps.setObject(4, report.getClosed_on());
			ps.setObject(5, report.getID_report());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_report) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_report);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}
	
}
