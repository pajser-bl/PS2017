package controller;

import java.util.ArrayList;
import java.util.List;

import DAO.SubscriptionDAO;
import model.users.Subscription;
import utility.TimeUtility;

public class SubscriptionControl {
	
	
	public static ArrayList<String> viewSubscription(int subscription_ID, SubscriptionDAO subscriptionDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Subscription subscription = subscriptionDAO.select(subscription_ID);

		if (subscription != null) {
			reply.add("VIEW SUBSCRIPTION OK");
			reply.add("" + subscription.getID_subscription());
			reply.add("" + subscription.getID_client());
			reply.add(TimeUtility.localDateToString(subscription.getStart_date()));
			reply.add(TimeUtility.localDateToString(subscription.getEnd_date()));
		} else {
			reply.add("VIEW subscription FAILED");
		}
		return reply;
	}
	public static ArrayList<String> newSubscription(String client_ID, String start_date, String end_date, SubscriptionDAO subscriptionDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Subscription subscription = new Subscription(Integer.parseInt(client_ID), Integer.parseInt(client_ID),
				TimeUtility.stringToLocalDate(start_date), TimeUtility.stringToLocalDate(end_date));
		if (subscriptionDAO.insert(subscription) != 0) {
			reply.add("NEW SUBSCRIPTION OK");
		} else {
			reply.add("NEW SUBSCRIPTION FAILED");
		}
		return reply;
	}
	public static ArrayList<String> updateSubscription(String subscription_ID, String client_ID, String start_date,
			String end_date, SubscriptionDAO subscriptionDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Subscription subscription = new Subscription(Integer.parseInt(subscription_ID), Integer.parseInt(client_ID),
				TimeUtility.stringToLocalDate(start_date), TimeUtility.stringToLocalDate(end_date));
		if (subscriptionDAO.update(subscription) != 0) {
			reply.add("UPDATE SUBSCRIPTION OK");
		} else {
			reply.add("UPDATE SUBSCRIPTION FAILED");
		}
		return reply;
	}
	public static ArrayList<String> deleteSubscription(String subscription_ID, SubscriptionDAO subscriptionDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (subscriptionDAO.delete(Integer.parseInt(subscription_ID)) != 0) {
			reply.add("DELETE SUBSCRIPTION OK");
		} else {
			reply.add("DELETE SUBSCRIPTION FAILED");
		}
		return reply;
	}
	public static ArrayList<String> viewSubscriptions(SubscriptionDAO subscriptionDAO) { // treba testirati
		List<Subscription> subscriptions =subscriptionDAO.selectAll();
		ArrayList<String> reply= new ArrayList<>();
		for(Subscription s: subscriptions) {
			int ID_subscription=s.getID_subscription();
			int ID_client=s.getID_client();
			String start_date=TimeUtility.localDateToString(s.getStart_date());
			String end_date=TimeUtility.localDateToString(s.getEnd_date());
			reply.add(""+ID_subscription);
			reply.add(""+ID_client);
			reply.add(start_date);
			reply.add(end_date);
		}
		return reply;
	}
}
