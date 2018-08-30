package DAO;

import java.util.List;

import model.users.Subscription;

public interface SubscriptionDAO {
	public Subscription select(int userID);
	public List<Subscription> selectAll();
	int insert(Subscription subscription);
	public int update(Subscription subscription);
	public int delete(int ID_subscription);
}
