package controller;

import java.util.ArrayList;
import java.util.List;

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

	public static ArrayList<String> viewClients(ClientDAO clientDAO) {
		List<Client> clients = clientDAO.selectAll(); 
		ArrayList<String> reply=new ArrayList<>();
		for(Client c:clients) {
			int ID_client = c.getID_client();
			String name = c.getName();
			String surname= c.getSurname();
			String phone_number=c.getPhone_number();
			reply.add(""+ID_client);
			reply.add(name);
			reply.add(surname);
			reply.add(phone_number);
		}
		return null;
	}

}
