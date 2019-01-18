package DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.interventions.Intervention;

public interface InterventionDAO {
	
	public Intervention select(int ID_intervention);
	public List<Intervention> selectAll();
	public int insert(Intervention intervention);
	public int update(Intervention intervention);
	public int delete(int ID_intervention);
	public int close(int ID_intervention,String remark,int ID_closed,LocalDateTime closed_on,boolean closed);
	public List<Intervention> selectAllOpen();
}
