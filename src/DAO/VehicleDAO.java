package DAO;

import java.util.List;

import model.Vehicle;

public interface VehicleDAO {
	public Vehicle select(int ID_vehicle);
	public List<Vehicle> selectAll();
	int insert(Vehicle vehicle);
	public int update(Vehicle vehicle);
	public int delete(int ID_vehicle);
}
