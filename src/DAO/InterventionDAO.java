package DAO;

import java.util.List;

import model.interventions.Intervention;

public interface InterventionDAO {
	
	public Intervention select(int ID_intervention);
	public List<Intervention> selectAll();
	public List<Intervention> selectAllOpen();
	public List<Intervention> selectAllClosed();
	public List<Intervention> selectAllReports();
	public int insert(Intervention intervention);
	public int update(Intervention intervention);
	public int delete(int ID_intervention);
	public int getInterventionByFieldTechnician(int user_ID);
	public int newRoadReport(Intervention intervention);
	public int closeIntervention(Intervention intervention);
	public int newReport(Intervention intervention);
}
