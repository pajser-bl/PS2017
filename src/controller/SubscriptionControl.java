package controller;

import java.util.ArrayList;
import java.util.List;
import DAO.ClientDAO;
import DAO.SubscriptionDAO;
import model.client.Client;
import model.client.Subscription;
import utility.TimeUtility;

public class SubscriptionControl {

	public static ArrayList<String> viewSubscription(int subscription_ID, SubscriptionDAO subscriptionDAO,
			ClientDAO clientDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Subscription subscription = subscriptionDAO.select(subscription_ID);

		if (subscription != null) {
			reply.add("VIEW SUBSCRIPTION OK");
			reply.add("" + subscription.getID_subscription());
			reply.add("" + subscription.getID_client());
			Client client = clientDAO.select(subscription.getID_client());
			reply.add(client.getName() + " " + client.getSurname());
			reply.add(TimeUtility.localDateToString(subscription.getStart_date()));
			reply.add(TimeUtility.localDateToString(subscription.getEnd_date()));
			reply.add("" + subscription.isValid());
		} else {
			reply.add("VIEW SUBSCRIPTION NOT OK");
		}
		return reply;
	}

	public static ArrayList<String> newSubscription(String client_ID, String start_date, String end_date,
			SubscriptionDAO subscriptionDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Subscription subscription = new Subscription(Integer.parseInt(client_ID), Integer.parseInt(client_ID),
				TimeUtility.stringToLocalDate(start_date), TimeUtility.stringToLocalDate(end_date));
		if (subscriptionDAO.insert(subscription) != 0) {
			reply.add("NEW SUBSCRIPTION OK");
		} else {
			reply.add("NEW SUBSCRIPTION NOT OK");
		}
		return reply;
	}

	public static ArrayList<String> updateSubscription(String subscription_ID, String client_ID, String start_date,
			String end_date, SubscriptionDAO subscriptionDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Subscription subscription = new Subscription(Integer.parseInt(subscription_ID), Integer.parseInt(client_ID),
				TimeUtility.stringToLocalDate(start_date), TimeUtility.stringToLocalDate(end_date));
		if (subscriptionDAO.update(subscription) != 0) {
			reply.add("UPDATE SUBSCRIPTION OK");
		} else {
			reply.add("UPDATE SUBSCRIPTION NOT OK");
		}
		return reply;
	}

	public static ArrayList<String> deleteSubscription(String subscription_ID, SubscriptionDAO subscriptionDAO)
			throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		if (subscriptionDAO.delete(Integer.parseInt(subscription_ID)) != 0) {
			reply.add("DELETE SUBSCRIPTION OK");
		} else {
			reply.add("DELETE SUBSCRIPTION NOT OK");
		}
		return reply;
	}

	public static ArrayList<String> viewSubscriptions(SubscriptionDAO subscriptionDAO, ClientDAO clientDAO)
			throws Exception {
		List<Subscription> subscriptions = subscriptionDAO.selectAll();
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW SUBSCRIPTIONS OK");
		reply.add("" + subscriptions.size());
		Client client = null;
		for (Subscription s : subscriptions) {
			int ID_subscription = s.getID_subscription();
			int ID_client = s.getID_client();
			client = clientDAO.select(ID_client);
			String clientString = client.getName() + " " + client.getSurname();
			String start_date = TimeUtility.localDateToString(s.getStart_date());
			String end_date = TimeUtility.localDateToString(s.getEnd_date());
			String tempString = "" + ID_subscription + ":" + ID_client + ":" + clientString + ":";
			tempString += start_date.replace(":", ";") + ":" + end_date.replace(":", ";") + ":" + s.isValid();
			reply.add(tempString);
		}
		return reply;
	}

	public static ArrayList<String> viewSubscriptionsByUser(int client_ID, SubscriptionDAO subscriptionDAO,
			ClientDAO clientDAO) throws Exception {
		List<Subscription> subscriptions = subscriptionDAO.selectAllByClient(client_ID);
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW SUBSCRIPTIONS BY CLIENT OK");
		Client client = clientDAO.select(client_ID);
		reply.add(client.getName() + " " + client.getSurname());
		reply.add("" + subscriptions.size());
		for (Subscription s : subscriptions) {
			int ID_subscription = s.getID_subscription();
			String start_date = TimeUtility.localDateToString(s.getStart_date());
			String end_date = TimeUtility.localDateToString(s.getEnd_date());
			String tempString = "" + ID_subscription + ":" + start_date.replace(":", ";");
			tempString += ":" + end_date.replace(":", ";") + ":" + s.isValid();
			reply.add(tempString);
		}
		return reply;
	}

}
