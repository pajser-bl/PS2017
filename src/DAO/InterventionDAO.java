package DAO;

import java.util.List;

import model.interventions.Intervention;

public interface InterventionDAO {
	
	public Intervention select(int ID_intervention);
	public List<Intervention> selectAll();
	public int insert(Intervention intervention);
	public int update(Intervention intev);
	public int delete(int ID_intervention);
}
