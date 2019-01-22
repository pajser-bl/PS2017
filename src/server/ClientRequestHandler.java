package server;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Vehicle;
import model.interventions.Intervention;
import model.users.Client;
import server.Request;
import utility.TimeUtility;

public class ClientRequestHandler {
	static ClientControllerFacade clientControllerFacade = new ClientControllerFacade();

	public static ArrayList<String> handle(Request request) throws Exception {
		switch (request.getRequestType()) {
		case "LOGIN": {
			return clientControllerFacade.login(request.getRequest().get(0), request.getRequest().get(1));
		}
		case "LOGOUT": {
			clientControllerFacade.logout(Integer.parseInt(request.getRequest().get(0)));
			return null;
		}
		case "CONNECTION LOST": {
			clientControllerFacade.connectionLost(Integer.parseInt(request.getRequest().get(0)));
			return null;
		}
		case "NEW CREDENTIALS": {
			return clientControllerFacade.newCredentials(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1), request.getRequest().get(2));
		}
		case "UPDATE PASSWORD": {
			return clientControllerFacade.updatePassword(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1), request.getRequest().get(2));
		}
		case "DELETE CREDENTIALS": {
			return clientControllerFacade.deleteCredentials(Integer.parseInt(request.getRequest().get(0)));
		}
		case "NEW USER": {
			return clientControllerFacade.addUser(request.getRequest().get(0), request.getRequest().get(1),
					request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4),
					request.getRequest().get(5), request.getRequest().get(6));
		}
		case "UPDATE USER": {
			return clientControllerFacade.updateUser(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1), request.getRequest().get(2), request.getRequest().get(3),
					request.getRequest().get(4), request.getRequest().get(5));
		}
		case "DELETE USER": {
			return clientControllerFacade.deleteUser(Integer.parseInt(request.getRequest().get(0)));
		}
		case "CHANGE PASSWORD": {
			return clientControllerFacade.changePassword(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1));
		}
		case "VIEW USER": {
			return clientControllerFacade.viewUser(Integer.parseInt(request.getRequest().get(0)));
		}
		case "VIEW USERS": {
			return clientControllerFacade.viewUsers(request.getRequest().get(0));
		}
		case "VIEW ACTIVE USERS": {
			return clientControllerFacade.viewOnlineUsers();
		}
		case "ACCESS MAP FIELD TECHNICIAN": {
		}
			break;
		case "ACCESS MAP OPERATOR": {
		}
			break;
		case "CHANGE STATE FIELD TECHNICIAN": {
			return clientControllerFacade.changeStateFieldTechnician(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1));
		}
		case "VIEW FIELD TECHNICIAN STATE ": {
			return clientControllerFacade.viewFieldTechnicianState(Integer.parseInt(request.getRequest().get(0)));
		}
		// pregledaj stanja svih onlajn terenskih radnika
		case "VIEW FIELD TECHNICIANS": {
			return clientControllerFacade.viewFieldTechnicians();
		}
		case "VIEW AVAILABLE FIELD TECHNICIANS": {
			return clientControllerFacade.viewAvailableFieldTechnicians();
		}
		case "VIEW ONLINE USERS": {
			return clientControllerFacade.viewOnlineUsers();
		}
		case "VIEW USER SESSION": {// terenski radnik ili operater poziva ili posebno supervizor
			return clientControllerFacade.viewUserSession(Integer.parseInt(request.getRequest().get(0)));
		}
		case "VIEW USER SESSIONS": {// supervizor zove
			return clientControllerFacade.viewUserSessions(Integer.parseInt((request.getRequest().get(0))));
		}
		case "VIEW CLIENT": {
			return clientControllerFacade.viewClient(Integer.parseInt(request.getRequest().get(0)));
		}
		case "VIEW CLIENTS": {
			return clientControllerFacade.viewClients();
		}
		case "NEW CLIENT": {
			return clientControllerFacade.newClient(request.getRequest().get(0), request.getRequest().get(1),
					request.getRequest().get(2));
		}
		case "UPDATE CLIENT": {
			return clientControllerFacade.updateClient(request.getRequest().get(0), request.getRequest().get(1),
					request.getRequest().get(2), request.getRequest().get(3));
		}
		case "DELETE CLIENT": {
			return clientControllerFacade.deleteClient(request.getRequest().get(0));
		}

		case "VIEW SUBSCRIPTION": {
			return clientControllerFacade.viewSubscription(Integer.parseInt(request.getRequest().get(0)));
		}
		case "VIEW SUBSCRIPTIONS": {
			return clientControllerFacade.viewSubscriptions();
		}
		case "NEW SUBSCRIPTION": {
			return clientControllerFacade.newSubscription(request.getRequest().get(0), request.getRequest().get(1),
					request.getRequest().get(2));
		}
		case "DELETE SUBSCRIPTION": {
			return clientControllerFacade.deleteSubscription(request.getRequest().get(0));
		}
		case "UPDATE SUBSCRIPTION": {
			return clientControllerFacade.updateSubscription(request.getRequest().get(0), request.getRequest().get(1),
					request.getRequest().get(2), request.getRequest().get(3));
		}

//		case "VIEW INTERVENTION": {
//			return clientControllerFacade.viewIntervention(Integer.parseInt(request.getRequest().get(0)));
//		}
//
//		case "VIEW OPENED INTERVENTIONS": {
//			return clientControllerFacade.viewOpenedInterventions();
//		}
//
//		case "VIEW INTERVENTIONS": {
//			return clientControllerFacade.viewInterventions();
//		}
//		case "VIEW ROADREPORT": {
//			return clientControllerFacade.viewRoadReport(request.getRequest().get(0));
//		}
//		case "VIEW REPORT": {
//			return clientControllerFacade.viewReport(Integer.parseInt((request.getRequest().get(0))));
//		}
//		case "VIEW REPORTS": {
//			return clientControllerFacade.viewReports();
//		}
		case "NEW INTERVENTION": {
			// opened_ID,timestamp,client_name,client_surname,client_phonenumber,registration,model,manufacturer,year,field_technician_ID
			// new Intervention(int iD_user_opened, int iD_field_technician, LocalDateTime
			// opened_on,String state)
			return clientControllerFacade.newIntervention(
					new Intervention(Integer.parseInt(request.getRequest().get(0)),
							Integer.parseInt(request.getRequest().get(9)),
							TimeUtility.stringToLocalDateTime(request.getRequest().get(1)), "otvorena"),
					new Client(request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4)),
					new Vehicle(request.getRequest().get(5), request.getRequest().get(6), request.getRequest().get(7),
							Integer.parseInt(request.getRequest().get(8))));
		}
//		case "NEW  ROADREPORT": {
//			return clientControllerFacade.newRoadReport(Integer.parseInt(request.getRequest().get(0)),
//					Integer.parseInt(request.getRequest().get(0)), Integer.parseInt(request.getRequest().get(2)),
//					request.getRequest().get(3), TimeUtility.stringToLocalDateTime(request.getRequest().get(4)),
//					request.getRequest().get(5));
//		}
//		case "CLOSE INTERVENTION": {
//			return clientControllerFacade.closeIntervention(Integer.parseInt(request.getRequest().get(0)),
//					Integer.parseInt(request.getRequest().get(1)),
//					TimeUtility.stringToLocalDateTime(request.getRequest().get(3)), request.getRequest().get(4),
//					Boolean.parseBoolean(request.getRequest().get(5)));
//		}
//		case "NEW REPORT": {
//			return clientControllerFacade.newReport(Integer.parseInt(request.getRequest().get(0)),
//					Integer.parseInt(request.getRequest().get(1)), Integer.parseInt(request.getRequest().get(2)),
//					request.getRequest().get(3), TimeUtility.stringToLocalDateTime(request.getRequest().get(4)));
//		}
//		case "DELETE INTERVENTION": {
//			return clientControllerFacade.deleteIntervention(request.getRequest().get(0));
//		}

		case "CHECK FIELD TECHNICIAN INTERVENTION": {
			return clientControllerFacade
					.checkFieldTechnicianIntervention(Integer.parseInt(request.getRequest().get(0)));
		}
		}
		return clientControllerFacade.unexistingRequest(request.getRequestType());
	}
}
