package DAO;

import java.util.List;
import model.interventions.RoadReport;

public interface RoadReportDAO{
	public RoadReport select(int ID_road_report);
	public List<RoadReport> selectAll();
	public int insert(RoadReport roadReport);
	public int update(RoadReport roadReport);
	public int delete(int ID_road_report);
}
