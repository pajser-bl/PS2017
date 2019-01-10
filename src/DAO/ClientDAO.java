package DAO;

import java.util.List;

import model.users.Client;

public interface ClientDAO {
	public Client select(int userID);
	public List<Client> selectAll();
	int insert(Client client);
	public int update(Client user);
	public int delete(int ID_client);
	public int exist(Client client);
}
