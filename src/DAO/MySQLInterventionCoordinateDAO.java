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
import model.InterventionCoordinate;
import utility.DataSourceFactory;

public class MySQLInterventionCoordinateDAO implements InterventionCoordinateDAO {

	private static final String SQL_SELECT = "SELECT FROM Coordinate WHERE ID_coordinate=?";
	private static final String SQL_SELECT_ALL_OPEN = "SELECT * FROM Coordinate c,Intervention i WHERE c.ID_intervention=i.ID_intervention AND i.closed=false";
	private static final String SQL_INSERT = "INSERT INTO Coordinate (ID_coordinate,ID_intervention, latitude, longitude) VALUES (null,null, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE Coordinate SET latitude=?, longitude=? WHERE ID_coordinate=?";
	private static final String SQL_DELETE = "DELETE FROM Coordinate WHERE ID_coordinate=?";

	private final static Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

	public InterventionCoordinate select(int ID_coordinate) {
		InterventionCoordinate returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1, ID_coordinate);
			rs = ps.executeQuery(SQL_SELECT);
			returnValue = new InterventionCoordinate(rs.getInt("ID_coordinate"), rs.getInt("ID_intervention"),
					rs.getDouble("longitude"), rs.getDouble("latitude"));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	public List<InterventionCoordinate> selectAll() {
		List<InterventionCoordinate> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL_OPEN);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new InterventionCoordinate(rs.getInt("ID_coordinate"), rs.getInt("ID_intervention"),
						rs.getDouble("longitude"), rs.getDouble("latitude")));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}
	
	public int insert(InterventionCoordinate coordinate) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, coordinate.getLatitude());
			ps.setObject(2, coordinate.getLongitude());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	public int update(InterventionCoordinate coordinate) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(0, coordinate.getID_coordinate());//ID_intervention = ID_coordinate = ID_report = ID_road_report
			ps.setObject(1, coordinate.getID_intervention());
			ps.setObject(2, coordinate.getLatitude());
			ps.setObject(3, coordinate.getLongitude());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {;ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	public int delete(int ID_coordinate) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(0, ID_coordinate);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, this.getClass() + " exception:", e);
			e.printStackTrace();
		}finally {
			try {;ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

}
