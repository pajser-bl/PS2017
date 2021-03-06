package DAO;

import java.util.List;

import model.users.User;

public interface UserDAO {
	public User select(int userID);
	public List<User> selectAll();
	int insert(User user);
	public int update(User user);
	public int delete(int userID);
	public boolean administrator_exists();
	public boolean checkUniqueUserame(String username);
	public User select(String username);
	public int changePassword(int ID_credentials,String hashedPassword);	
}
