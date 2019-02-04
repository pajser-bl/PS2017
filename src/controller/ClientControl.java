package controller;

import java.util.ArrayList;
import java.util.List;

import DAO.ClientDAO;
import model.client.Client;

public class ClientControl {

	public static ArrayList<String> newClient(String name, String surname, String phone_number, ClientDAO clientDAO)
			throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Client client = new Client(name, surname, phone_number);
		if (clientDAO.exist(client) != 0) {
			reply.add("NEW CLIENT FAILED");
			reply.add("Klijent vec postoji.");
		} else if (clientDAO.insert(client) != 0) {
			reply.add("NEW CLIENT OK");
		} else {
			reply.add("NEW CLIENT FAILED");
			reply.add("Dodavanje klijenta nije uspjelo.");
		}
		return reply;
	}

	public static ArrayList<String> updateClient(String client_ID, String name, String surname, String phone_number,
			ClientDAO clientDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Client client = new Client(Integer.parseInt(client_ID), name, surname, phone_number);
		if (clientDAO.update(client) != 0) {
			reply.add("UPDATE CLIENT OK");
		} else {
			reply.add("UPDATE CLIENT FAILED");
			reply.add("Prepravljanje klijenta nije uspjelo.");
		}
		return reply;
	}

	public static ArrayList<String> deleteClient(String client_ID, ClientDAO clientDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		if (clientDAO.delete(Integer.parseInt(client_ID)) != 0) {
			reply.add("DELETE CLIENT OK");
		} else {
			reply.add("DELETE CLIENT FAILED");
			reply.add("Brisanje klijenta nije uspjelo.");
		}
		return reply;
	}

	public static ArrayList<String> viewClients(ClientDAO clientDAO) throws Exception {
		List<Client> clients = clientDAO.selectAll();
		ArrayList<String> reply = new ArrayList<>();
		reply.add("VIEW CLIENTS OK");
		reply.add("" + clients.size());
		for (Client c : clients) {
			int ID_client = c.getID_client();
			String name = c.getName();
			String surname = c.getSurname();
			String phone_number = c.getPhone_number();
			reply.add("" + ID_client + ":" + name + ":" + surname + ":" + phone_number);
		}
		return reply;
	}

	public static ArrayList<String> viewClient(int clientID, ClientDAO clientDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Client client = clientDAO.select(clientID);
		if (client != null) {
			reply.add("VIEW USER OK");
			reply.add("" + client.getID_client());
			reply.add(client.getName());
			reply.add(client.getSurname());
			reply.add(client.getPhone_number());
		} else {
			reply.add("VIEW USER FAILED");
		}
		return reply;
	}

}
