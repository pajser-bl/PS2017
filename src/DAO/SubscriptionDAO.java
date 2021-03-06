package DAO;

import java.util.List;

import model.client.Subscription;

public interface SubscriptionDAO {
	public Subscription select(int userID);
	public List<Subscription> selectAll();
	public List<Subscription> selectAllByClient(int ID_client);
	int insert(Subscription subscription);
	public int update(Subscription subscription);
	public int delete(int ID_subscription);
}
