package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import model.Vehicle;
import utility.DataSourceFactory;

public class MySQLVehicleDAO implements VehicleDAO{

	private static final String SQL_SELECT = "SELECT * FROM vehicle WHERE ID_vehicle=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM vehicle";
	private static final String SQL_INSERT = "INSERT INTO vehicle (ID_vehicle,registration,manufacturer,model, year) VALUES (null,?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE credentials SET registration=?, manufacturer=?, model=?, year=? WHERE ID_vehicle=?";
	private static final String SQL_DELETE = "DELETE FROM vehicle WHERE ID_vehicle=?";
	
	
	
	@Override
	public Vehicle select(int ID_vehicle) {
		Vehicle returnValue = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT);
			ps.setInt(1,ID_vehicle);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue = new Vehicle(rs.getInt("ID_vehicle"), rs.getString("registration"),
						rs.getString("manufacturer"), rs.getString("model"),Year.parse(rs.getString("year")));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public List<Vehicle> selectAll() {
		List<Vehicle> returnValue = new ArrayList<>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps = c.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next())
				returnValue.add(new Vehicle(rs.getInt("ID_vehicle"), rs.getString("registration"),
						rs.getString("manufacturer"), rs.getString("model"),Year.parse(rs.getString("year"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {rs.close();ps.close();	c.close();}catch(SQLException e) {}
		}
		return returnValue;
	}

	@Override
	public int insert(Vehicle vehicle) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, vehicle.getRegistration());
			ps.setObject(2, vehicle.getManufacturer());
			ps.setObject(3, vehicle.getModel());
			ps.setObject(4, vehicle.getYear());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int update(Vehicle vehicle) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_UPDATE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, vehicle.getRegistration());
			ps.setObject(2, vehicle.getManufacturer());
			ps.setObject(3, vehicle.getModel());
			ps.setObject(4, vehicle.getYear());
			ps.setObject(5, vehicle.getID_vehicle());
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

	@Override
	public int delete(int ID_vehicle) {
		int retVal=0;
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DataSourceFactory.getMySQLDataSource().getConnection();
			ps =c.prepareStatement(SQL_DELETE, Statement.NO_GENERATED_KEYS);
			ps.setObject(1, ID_vehicle);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {ps.close();c.close();}catch(SQLException e) {}
		}
		return retVal;
	}

}
