package coordinates;

public class InterventionCoordinate extends Coordinate {
	private int ID_intervention;
	private int ID_coordinate;
	
	public InterventionCoordinate() {
		super();
	}
	
	public InterventionCoordinate(double latitude,double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	public InterventionCoordinate(int iD_coordinate, int ID_intervention, double latitude, double longitude) {
		super(latitude, longitude);
		this.setID_intervention(ID_intervention);
		this.setID_coordinate(iD_coordinate);
	}

	public int getID_intervention() {
		return this.ID_intervention;
	}

	public void setID_intervention(int iD_intervention) {
		this.ID_intervention = iD_intervention;
	}

	public int getID_coordinate() {
		return this.ID_coordinate;
	}

	public void setID_coordinate(int iD_coordinate) {
		this.ID_coordinate = iD_coordinate;
	}
}
