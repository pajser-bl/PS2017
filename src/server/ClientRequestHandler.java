package server;

import java.util.ArrayList;
import model.Vehicle;
import model.client.Client;
import model.interventions.Intervention;
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
		/**
		 * String name, String surname, String date_of_birth, String type,
		 * String qualification, String drivers_license, String username,String password
		 */
		case "NEW USER": {
			return clientControllerFacade.addUser(request.getRequest().get(0), request.getRequest().get(1),
					request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4),
					request.getRequest().get(5), request.getRequest().get(6),request.getRequest().get(7));
		}
		case "UPDATE USER": {
			return clientControllerFacade.updateUser(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1), request.getRequest().get(2), request.getRequest().get(3),
					request.getRequest().get(4), request.getRequest().get(5),request.getRequest().get(6));
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

		case "VIEW OPEN INTERVENTION": {
			return clientControllerFacade.viewOpenIntervention(Integer.parseInt(request.getRequest().get(0)));
		}

		case "VIEW FIELD REPORT INTERVENTION": {
			return clientControllerFacade.viewFieldReportIntervention(Integer.parseInt(request.getRequest().get(0)));
		}

		case "VIEW CLOSED INTERVENTION": {
			return clientControllerFacade.viewClosedIntervention(Integer.parseInt(request.getRequest().get(0)));
		}

		case "VIEW REPORT": {
			return clientControllerFacade.viewReport(Integer.parseInt(request.getRequest().get(0)));
		}

		case "VIEW OPENED INTERVENTIONS": {
			return clientControllerFacade.viewOpenedInterventions();
		}

		case "NEW INTERVENTION": {
			/**
			 * 
			 * opened_ID,timestamp,
			 * client_name,client_surname,client_phonenumber,
			 * registration,model,manufacturer,year,
			 * field_technician_ID
			 * 
			 * new Intervention(int iD_user_opened, int iD_field_technician, LocalDateTime
			 * opened_on,String state)
			 * 
			 */
			return clientControllerFacade.newIntervention(
					new Intervention(Integer.parseInt(request.getRequest().get(0)),
							Integer.parseInt(request.getRequest().get(9)),
							TimeUtility.stringToLocalDateTime(request.getRequest().get(1)), "otvorena"),
					new Client(request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4)),
					new Vehicle(request.getRequest().get(5), request.getRequest().get(6), request.getRequest().get(7),
							Integer.parseInt(request.getRequest().get(8))));
		}
		case "NEW ROAD REPORT": {
			// intervention_ID,assistance,time_of_assistance,remark_field_technician
			return clientControllerFacade.newRoadReport(Integer.parseInt(request.getRequest().get(0)),
					request.getRequest().get(1), TimeUtility.stringToLocalDateTime(request.getRequest().get(2)),
					request.getRequest().get(3));
		}
		case "CLOSE INTERVENTION": {
			// intervention_ID,iD_operater,closed_on,remark
			return clientControllerFacade.closeIntervention(Integer.parseInt(request.getRequest().get(0)),
					Integer.parseInt(request.getRequest().get(1)),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(2)), request.getRequest().get(3));
		}

		case "NEW REPORT": {
			// ID_supervisor,remark_supervisor,report_made
			return clientControllerFacade.newReport(Integer.parseInt(request.getRequest().get(0)),
					Integer.parseInt(request.getRequest().get(1)), request.getRequest().get(2),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(3)));
		}

		case "CHECK FIELD TECHNICIAN INTERVENTION": {
			return clientControllerFacade
					.checkFieldTechnicianIntervention(Integer.parseInt(request.getRequest().get(0)));
		}
		}
		return clientControllerFacade.unexistingRequest(request.getRequestType());
	}
}
