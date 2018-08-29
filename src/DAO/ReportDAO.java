package DAO;

import java.util.List;

import model.interventions.Report;

public interface ReportDAO {
	public Report select(int ID_report);
	public List<Report> selectAll();
	public int insert(Report report);
	public int update(Report report);
	public int delete(int ID_report);
}
