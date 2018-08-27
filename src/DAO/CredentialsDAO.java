package DAO;

import java.util.List;

import model.users.Credentials;

public interface CredentialsDAO {
	public Credentials select(int ID_credentials);
	public List<Credentials> selectAll();
	int insert(Credentials credentials);
	public int update(Credentials credentials);
	public int delete(int ID_credentials);
	public boolean checkUniqueUserame(String username);
}
