package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import DAO.ClientDAO;
import DAO.EventDAO;
import DAO.InterventionDAO;
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
			InterventionDAO interventionDAO, EventDAO eventDAO, ClientDAO clientDAO, VehicleDAO vehicleDAO)
			throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
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
		if ((interventionDAO.insert(intervention)) != 0) {
			// dodaj event za tehnicara
			event = new Event(ActiveUsersWatch.getUserSession(intervention.getID_user_opened()), LocalDateTime.now(),
					"Uspjesno otvorena nova intervencija ID: " + intervention.getID_intervention() + " .");
			eventDAO.insert(event);
			event = new Event(ActiveUsersWatch.getUserSession(intervention.getID_field_technician()),
					LocalDateTime.now(),
					"Dodjeljena nova intervencija ID: " + intervention.getID_intervention() + " .");
			eventDAO.insert(event);

			reply.add("NEW INTERVENTION OK");
		} else {
			event = new Event(intervention.getID_user_opened(),
					ActiveUsersWatch.getUserSession(intervention.getID_user_opened()), LocalDateTime.now(),
					"Otvaranje intervencije nije uspjelo.");
			eventDAO.insert(event);
			reply.add("NEW INTERVENTION NOT OK");
			reply.add("Otvaranje intervencije nije uspjelo.");
		}
		return reply;
	}

	public static ArrayList<String> newRoadReport(int intervention_ID, String assistance,
			LocalDateTime time_of_assistance, String remark, InterventionDAO interventionDAO, EventDAO eventDAO)
			throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
		Intervention intervention = interventionDAO.select(intervention_ID);
		intervention.setAssistance(assistance);
		intervention.setTime_of_assistance(time_of_assistance);
		intervention.setRemark_field_technician(remark);
		intervention.setState("terenski izvjestaj");
		if (interventionDAO.update(intervention) != 0) {
			event = new Event(ActiveUsersWatch.getUserSession(intervention.getID_user_opened()), LocalDateTime.now(),
					"Pristigao terenski izvjestaj za intervenciju ID: " + intervention.getID_intervention() + " .");
			eventDAO.insert(event);
			event = new Event(ActiveUsersWatch.getUserSession(intervention.getID_field_technician()),
					LocalDateTime.now(), "Napravlen i poslat terenski izvjestaj za intervenciju ID: "
							+ intervention.getID_intervention() + " .");
			eventDAO.insert(event);

			reply.add("NEW ROAD REPORT OK");
		} else {
			event = new Event(intervention.getID_user_opened(),
					ActiveUsersWatch.getUserSession(intervention.getID_field_technician()), LocalDateTime.now(),
					"Neuspjesno kreiranje terenskog izvjestaja.");
			eventDAO.insert(event);
			reply.add("NEW ROAD REPORT NOT OK");
			reply.add("Slanje terenskog izvjestaja nije uspjelo.");
		}
		return reply;
	}

	public static ArrayList<String> closeIntervention(int intervention_ID, int operater_ID, LocalDateTime closed_on,
			String remark, InterventionDAO interventionDAO, EventDAO eventDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
		Intervention intervention = interventionDAO.select(intervention_ID);
		intervention.setID_user_closed(operater_ID);
		intervention.setClosed_on(closed_on);
		intervention.setRemark_operator(remark);
		if (interventionDAO.update(intervention) != 0) {
			event = new Event(operater_ID, ActiveUsersWatch.getUserSession(operater_ID), LocalDateTime.now(),
					"Uspjesno zatvorena intervencija ID: " + intervention_ID + " .");
			reply.add("CLOSE INTERVENTION OK");
			eventDAO.insert(event);
		} else {
			event = new Event(operater_ID, ActiveUsersWatch.getUserSession(operater_ID), LocalDateTime.now(),
					"Zatvaranje intervencije ID: " + intervention_ID + " nije uspjelo.");
			reply.add("CLOSE INTERVENTION  NOT OK");
			reply.add("Zatvaranje intervencije nije uspjelo.");
			eventDAO.insert(event);
		}
		return reply;
	}
	public static ArrayList<String> newReport(int intervention_ID, int supervisor_ID, String remark,
			LocalDateTime report_made, InterventionDAO interventionDAO)throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Intervention intervention = interventionDAO.select(intervention_ID);
		intervention.setID_supervisor(supervisor_ID);
		intervention.setReport_made(report_made);
		intervention.setRemark_supervisor(remark);
		if (interventionDAO.update(intervention) != 0) {
			reply.add("NEW REPORT OK");
		} else {
			reply.add("NEW REPORT NOT OK");
			reply.add("Zatvaranje intervencije nije uspjelo.");
		}
		return reply;
	}
	public static ArrayList<String> viewOpenedInterventions(InterventionDAO interventionDAO, UserDAO userDAO,
			ClientDAO clientDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		ArrayList<Intervention> interventions = (ArrayList<Intervention>) interventionDAO.selectAllOpen();
		User operator_opened;
		User fieldTechnician;
		Client client;
		String tempString;
		reply.add("VIEW OPENED INTERVENTIONS OK");
		reply.add("" + interventions.size());
		for (Intervention i : interventions) {
			client = clientDAO.select(i.getID_client());
			operator_opened = userDAO.select(i.getID_user_opened());
			fieldTechnician = userDAO.select(i.getID_field_technician());
			tempString = i.getID_intervention() + ":" + client.getName() + " " + client.getSurname();
			tempString += ":" + operator_opened.getName() + " " + operator_opened.getSurname() + ":";
			tempString += TimeUtility.localDateTimeToString(i.getOpened_on()).replace(":", ";");
			tempString += ":" + fieldTechnician.getName() + " " + fieldTechnician.getSurname();
			tempString += ":" + i.getState();
			reply.add(tempString);
		}
		return reply;
	}

	public static ArrayList<String> viewIntervention(int ID_intervention, InterventionDAO interventionDAO,
			UserDAO userDAO, ClientDAO clientDAO, VehicleDAO vehicleDAO) throws Exception {
		ArrayList<String> reply = new ArrayList<>();
		Intervention intervention = interventionDAO.select(ID_intervention);
		Client client = clientDAO.select(intervention.getID_client());
		Vehicle vehicle = vehicleDAO.select(intervention.getID_vehicle());
		User opened = userDAO.select(intervention.getID_user_opened());
		User fieldTechnician = userDAO.select(intervention.getID_field_technician());
		String state=intervention.getState();
		
		reply.add("VIEW INTERVENTION OK");
		reply.add("" + intervention.getID_intervention());
		reply.add(client.getName() + " " + client.getSurname());
		reply.add(client.getPhone_number());
		reply.add(vehicle.getRegistration());
		reply.add(vehicle.getModel());
		reply.add(vehicle.getManufacturer());
		reply.add("" + vehicle.getYear());
		reply.add(opened.getName() + " " + opened.getSurname());
		reply.add(fieldTechnician.getName() + " " + fieldTechnician.getSurname());
		reply.add(TimeUtility.localDateTimeToString(intervention.getOpened_on()));
		reply.add(intervention.getState());

		if (state.equals("terenski izvjestaj") || state.equals("zatvorena") || state.equals("izvjestaj")) {
			reply.add(intervention.getAssistance());
			reply.add(TimeUtility.localDateTimeToString(intervention.getTime_of_assistance()));
			reply.add(intervention.getRemark_field_technician());
		}
		if (state.equals("zatvorena") || state.equals("izvjestaj")) {
			User closed = userDAO.select(intervention.getID_user_closed());
			reply.add(closed.getName() + " " + closed.getSurname());
			reply.add(TimeUtility.localDateTimeToString(intervention.getClosed_on()));
			reply.add(intervention.getRemark_operator());
		}
		if (state.equals("izvjestaj")) {
			User supervisor = userDAO.select(intervention.getID_supervisor());
			reply.add(supervisor.getName() + " " + supervisor.getSurname());
			reply.add(intervention.getRemark_supervisor());
			reply.add(TimeUtility.localDateTimeToString(intervention.getReport_made()));
		}
		return reply;
	}

	public static ArrayList<String> checkFieldTechnicianIntervention(int user_ID, InterventionDAO interventionDAO,
			ClientDAO clientDAO, VehicleDAO vehicleDAO, UserDAO userDAO) {
		ArrayList<String> reply = new ArrayList<>();
		int ID_intervention = interventionDAO.getInterventionByFieldTechnician(user_ID);
		if (ID_intervention != 0) {
			Intervention intervention = interventionDAO.select(ID_intervention);
			User opened = userDAO.select(intervention.getID_user_opened());
			User fieldTechnician=userDAO.select(user_ID);
			Client client = clientDAO.select(intervention.getID_client());
			Vehicle vehicle = vehicleDAO.select(intervention.getID_vehicle());
			reply.add("CHECK FIELD TECHNICIAN INTERVENTION OK");
			reply.add("" + intervention.getID_intervention());
			reply.add(client.getName() + " " + client.getSurname());
			reply.add(client.getPhone_number());
			reply.add(vehicle.getRegistration());
			reply.add(vehicle.getModel());
			reply.add(vehicle.getManufacturer());
			reply.add("" + vehicle.getYear());
			reply.add(opened.getName() + " " + opened.getSurname());
			reply.add(fieldTechnician.getName() + " " + fieldTechnician.getSurname());
			reply.add(TimeUtility.localDateTimeToString(intervention.getOpened_on()));
			reply.add(intervention.getState());
		} else {
			reply.add("CHECK FIELD TECHNICIAN INTERVENTION NOT OK");
			reply.add("Terenski radnik nema zaduzenja.");
		}
		return reply;
	}

	

}
