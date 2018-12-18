package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.EventDAO;
import DAO.InterventionDAO;
import model.interventions.Intervention;
import model.users.Event;
import server.ActiveUsersWatch;

public class InterventionControl {

	public static ArrayList<String> newIntervention(Intervention intervention, InterventionDAO interventionDAO, EventDAO eventDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
		int ID_session=ActiveUsersWatch.getUserSession(intervention.getID_user_opened());
		
		if (interventionDAO.insert(intervention) != 0) {
			event=new Event(intervention.getID_user_opened(),ID_session,LocalDateTime.now(),"Uspjesno otvorena nova intervencija ID: "+intervention.getID_intervention()+" .");
			reply.add("NEW INTERVENTION OK");
		} else {
			event=new Event(intervention.getID_user_opened(),ID_session,LocalDateTime.now(),"Otvaranje intervencije nije uspjelo.");
			reply.add("NEW INTERVENTION FAILED");
		}
		eventDAO.insert(event);
		return reply;
	}

	public static ArrayList<String> closeIntervention(int intervention_ID, int user_ID, LocalDateTime closed_on,
			String remark, boolean closed, InterventionDAO interventionDAO, EventDAO eventDAO) {
		ArrayList<String> reply = new ArrayList<>();
		Event event;
		int ID_session=ActiveUsersWatch.getUserSession(user_ID);
		if (interventionDAO.close(intervention_ID, remark, user_ID, closed_on, closed) != 0) {
			event=new Event(user_ID,ID_session,LocalDateTime.now(),"Uspjesno zatvorena intervencija ID: "+intervention_ID+" .");
			reply.add("CLOSE INTERVENTION OK");
		} else {
			event=new Event(user_ID,ID_session,LocalDateTime.now(),"Zatvaranje intervencije ID: "+intervention_ID+" nije uspjelo.");
			reply.add("CLOSE INTERVENTION FAILED");
		}
		eventDAO.insert(event);
		return reply;
	}

}
