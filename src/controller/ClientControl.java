package controller;

import java.util.ArrayList;

import DAO.ClientDAO;
import model.users.Client;

public class ClientControl {

	
	
	public static ArrayList<String> newClient(String name, String surname, String phone_number, ClientDAO clientDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Client client = new Client(name, surname, phone_number);

		if (clientDAO.insert(client) != 0) {
			reply.add("NEW CLIENT OK");
		} else {
			reply.add("NEW CLIENT FAILED");
		}
		return reply;
	}
	
	public static ArrayList<String> updateClient(String client_ID, String name, String surname, String phone_number, ClientDAO clientDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Client client = new Client(Integer.parseInt(client_ID), name, surname, phone_number);
		if (clientDAO.update(client) != 0) {
			reply.add("UPDATE CLIENT OK");
		} else {
			reply.add("UPDATE CLIENT FAILED");
		}
		return reply;
	}
	public static ArrayList<String> deleteClient(String client_ID, ClientDAO clientDAO) {
		ArrayList<String> reply = new ArrayList<>();
		if (clientDAO.delete(Integer.parseInt(client_ID)) != 0) {
			reply.add("DELETE CLIENT OK");
		} else {
			reply.add("DELETE CLIENT FAILED");
		}
		return reply;
	}

}
