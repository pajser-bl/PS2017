package DAO;

import java.util.List;

import coordinates.InterventionCoordinate;

public interface InterventionCoordinateDAO{
    public InterventionCoordinate select(int ID_coordinate);
	public List<InterventionCoordinate> selectAll();
	int insert(InterventionCoordinate coordinate);
	public int update(InterventionCoordinate coordinate);
	public int delete(int ID_coordinate);
}
