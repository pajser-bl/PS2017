package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.ClientDAO;
import DAO.EventDAO;
import DAO.InterventionDAO;
import DAO.RoadReportDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import model.Vehicle;
import model.interventions.Intervention;
import model.users.Client;
import model.users.Event;
import model.users.User;
import utility.TimeUtility;

public class InterventionControl {

	public static ArrayList<String> newIntervention(Intervention intervention, Client client, Vehicle vehicle,
			int fieldTechnician_ID, InterventionDAO interventionDAO, EventDAO eventDAO, ClientDAO clientDAO,
			VehicleDAO vehicleDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
		int ID_intervention;
		int ID_session = ActiveUsersWatch.getUserSession(intervention.getID_user_opened());
		int client_ID = clientDAO.exist(client);
		int vehicle_ID = vehicleDAO.exist(vehicle);
		if (client_ID != 0) {
			intervention.setID_client(client_ID);
		} else {
			intervention.setID_client(clientDAO.insert(client));
		}
		if (vehicle_ID != 0) {
			intervention.setID_client(vehicle_ID);
		} else {
			intervention.setID_vehicle(vehicleDAO.insert(vehicle));
		}
		if ((ID_intervention = interventionDAO.insert(intervention)) != 0) {
//dodaj event za tehnicara
			event = new Event(ID_session, LocalDateTime.now(),
					"Uspjesno otvorena nova intervencija ID: " + intervention.getID_intervention() + " .");
			reply.add("NEW INTERVENTION OK");
		} else {
			event = new Event(intervention.getID_user_opened(), ID_session, LocalDateTime.now(),
					"Otvaranje intervencije nije uspjelo.");
			reply.add("NEW INTERVENTION FAILED");
			reply.add("Otvaranje intervencije nije uspjelo.");
		}
		eventDAO.insert(event);
		return reply;
	}

	public static ArrayList<String> closeIntervention(int intervention_ID, int user_ID, LocalDateTime closed_on,
			String remark, boolean closed, InterventionDAO interventionDAO, EventDAO eventDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
		int ID_session = ActiveUsersWatch.getUserSession(user_ID);
		if (interventionDAO.close(intervention_ID, remark, user_ID, closed_on, closed) != 0) {
			event = new Event(user_ID, ID_session, LocalDateTime.now(),
					"Uspjesno zatvorena intervencija ID: " + intervention_ID + " .");
			reply.add("CLOSE INTERVENTION OK");
		} else {
			event = new Event(user_ID, ID_session, LocalDateTime.now(),
					"Zatvaranje intervencije ID: " + intervention_ID + " nije uspjelo.");
			reply.add("CLOSE INTERVENTION FAILED");
		}
		eventDAO.insert(event);
		return reply;
	}

	public static ArrayList<String> viewInterventions(InterventionDAO interventionDAO, UserDAO userDAO,
			ClientDAO clientDAO, RoadReportDAO roadReportDAO) {
		ArrayList<String> reply = new ArrayList<>();
		ArrayList<Intervention> interventions = (ArrayList<Intervention>) interventionDAO.selectAll();
		String clientFullName;
		String openedOperater;
		String closedOperater = "";
		String tempString;
		String fieldTechnician = "";
		reply.add("VIEW INTERVENTIONS OK");
		reply.add("" + interventions.size());
		for (Intervention i : interventions) {
			clientFullName = clientDAO.select(i.getID_client()).getName() + " "
					+ clientDAO.select(i.getID_client()).getSurname();
			openedOperater = userDAO.select(i.getID_user_opened()).getName() + " "
					+ userDAO.select(i.getID_user_opened()).getSurname();
			if (i.getID_user_closed() != 0)
				closedOperater = userDAO.select(i.getID_user_closed()).getName() + " "
						+ userDAO.select(i.getID_user_closed()).getSurname();
			fieldTechnician = userDAO.select(roadReportDAO.select(i.getID_intervention()).getID_user()).getName() + " "
					+ userDAO.select(roadReportDAO.select(i.getID_intervention()).getID_user()).getSurname();
			// ovo je sto se sve salje a aplikatovno sa strane klijenta se bira u obziru da
			// li je supervizor ili operater
			tempString = i.getID_intervention() + ":" + clientFullName + ":" + openedOperater + ":" + i.getOpened_on()
					+ ":" + fieldTechnician + ":" + closedOperater + ":" + i.getClosed_on() + ":" + i.isClosed();
			reply.add(tempString);
		}
		return reply;
	}

	public static ArrayList<String> viewOpenedInterventions(InterventionDAO interventionDAO, UserDAO userDAO,
			ClientDAO clientDAO, RoadReportDAO roadReportDAO) {
		ArrayList<String> reply = new ArrayList<>();
		ArrayList<Intervention> interventions = (ArrayList<Intervention>) interventionDAO.selectAllOpen();
		String clientFullName;
		String openedOperater;
		String tempString;
		String fieldTechnician = "";
		reply.add("VIEW OPENED INTERVENTIONS OK");
		reply.add("" + interventions.size());
		for (Intervention i : interventions) {
			clientFullName = clientDAO.select(i.getID_client()).getName() + " "
					+ clientDAO.select(i.getID_client()).getSurname();
			openedOperater = userDAO.select(i.getID_user_opened()).getName() + " "
					+ userDAO.select(i.getID_user_opened()).getSurname();
			fieldTechnician = userDAO.select(i.getID_field_technician()).getName() + " "
					+ userDAO.select(i.getID_field_technician()).getSurname();
			tempString = i.getID_intervention() + ":" + clientFullName + ":" + openedOperater + ":"
					+ TimeUtility.localDateTimeToString(i.getOpened_on()).replace(":", ";") + ":" + fieldTechnician;
			reply.add(tempString);
		}
		return reply;
	}

	public static ArrayList<String> viewIntervention(int ID_intervention, InterventionDAO interventionDAO,
			UserDAO userDAO, ClientDAO clientDAO, RoadReportDAO roadReportDAO, VehicleDAO vehicleDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Intervention intervention = interventionDAO.select(ID_intervention);
		reply.add("VIEW INTERVENTION OK");
		reply.add("" + intervention.getID_intervention());
		String client = clientDAO.select(intervention.getID_client()).getName() + " "
				+ clientDAO.select(intervention.getID_client()).getSurname();
		reply.add("" + intervention.getID_vehicle());
		reply.add("" + intervention.getID_user_opened());
		reply.add("" + intervention.getID_user_closed());
		reply.add(TimeUtility.localDateTimeToString(intervention.getOpened_on()));
		reply.add(TimeUtility.localDateTimeToString(intervention.getClosed_on()));
		reply.add(intervention.getRemark());
		if (intervention.isClosed())
			reply.add("CLOSED");
		else
			reply.add("OPEN");
		return reply;
	}

	public static ArrayList<String> checkFieldTechnicianIntervention(int user_ID, InterventionDAO interventionDAO,UserDAO userDAO,ClientDAO clientDAO,VehicleDAO vehicleDAO) {
		ArrayList<String> reply = new ArrayList<>();
		int ID_intervention=interventionDAO.getInterventionByFieldTechnician(user_ID);
		if (ID_intervention != 0) {
			Intervention intervention = interventionDAO.select(ID_intervention);
			User operator=userDAO.select(intervention.getID_user_opened());
			Client client=clientDAO.select(intervention.getID_client());
			Vehicle vehicle=vehicleDAO.select(intervention.getID_vehicle());
			reply.add("CHECK FIELD TECHNICIAN INTERVENTION OK");
			//intervention INFO, client INFO, vehicleINFO
			reply.add("" + ID_intervention);
			
			
		} else {
			reply.add("CHECK FIELD TECHNICIAN INTERVENTION NOT OK");
			reply.add("Terenski radnik nema zaduzenja.");
		}
		return reply;

	}

}
