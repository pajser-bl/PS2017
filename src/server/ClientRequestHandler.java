package server;

import java.util.ArrayList;

import model.Vehicle;
import model.interventions.Intervention;
import model.users.Client;
import server.Request;
import utility.TimeUtility;

public class ClientRequestHandler {
	static ClientControllerFacade clientControllerFacade = new ClientControllerFacade();

	public static ArrayList<String> handle(Request request) {
		switch (request.getRequestType()) {
		case "LOGIN": {
			return clientControllerFacade.login(request.getRequest().get(0), request.getRequest().get(1));
		}
		case "LOGOUT": {
			clientControllerFacade.logout(Integer.parseInt(request.getRequest().get(0)));
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
		case "ACCESS MAP FIELD TECHNITIAN": {
		}
			break;
		case "ACCESS MAP OPERATOR": {
		}
			break;
		case "CHANGE STATE FIELD TECHNITIAN": {
			return clientControllerFacade.changeStateFieldTechnitian(Integer.parseInt(request.getRequest().get(1)),
					request.getRequest().get(2));
		}
		case "VIEW FIELD TECHNITIAN STATE ": {
			return clientControllerFacade.viewFieldTechnitianState(Integer.parseInt(request.getRequest().get(0)));
		}
		// pregledaj stanja svih onlajn terenskih radnika
		case "VIEW FIELD TECHNITIANS": {
			return clientControllerFacade.viewFieldTechnitians();
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

		case "VIEW INTERVENTION": {
			return clientControllerFacade.viewIntervention(Integer.parseInt(request.getRequest().get(0)));
		}
		case "VIEW INTERVENTIONS": {
			return clientControllerFacade.viewInterventions();
		}

		case "NEW INTERVENTION": {
			// opened_ID,timestamp,client_name,client_surname,client_phonenumber,registration,model,manufacturer,year
			return clientControllerFacade.newIntervention(
					new Intervention(0,0,Integer.parseInt(request.getRequest().get(0)),
							TimeUtility.stringToLocalDateTime(request.getRequest().get(1))),
					new Client(request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4)),
					new Vehicle(request.getRequest().get(5), request.getRequest().get(6),request.getRequest().get(7),Integer.parseInt(request.getRequest().get(8))));
		}
		case "UPDATE INTERVENTION": {
			return clientControllerFacade.updateIntervention(new Intervention(
					Integer.parseInt(request.getRequest().get(0)), Integer.parseInt(request.getRequest().get(1)),
					Integer.parseInt(request.getRequest().get(2)), Integer.parseInt(request.getRequest().get(3)),
					Integer.parseInt(request.getRequest().get(4)),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(5)),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(6)), request.getRequest().get(7),
					Boolean.parseBoolean(request.getRequest().get(8))));
		}
		case "DELETE INTERVENTION": {
			return clientControllerFacade.deleteIntervention(request.getRequest().get(0));
		}

		case "CLOSE INTERVENTION": {
			return clientControllerFacade.closeIntervention(Integer.parseInt(request.getRequest().get(0)),
					Integer.parseInt(request.getRequest().get(1)),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(3)), request.getRequest().get(4),
					Boolean.parseBoolean(request.getRequest().get(5)));
		}
		case "NEW  ROADREPORT": {
			return clientControllerFacade.newRoadReport(Integer.parseInt(request.getRequest().get(0)),
					Integer.parseInt(request.getRequest().get(0)), Integer.parseInt(request.getRequest().get(2)),
					request.getRequest().get(3), TimeUtility.stringToLocalDateTime(request.getRequest().get(4)),
					request.getRequest().get(5));
		}
		case "VIEW ROADREPORT": {
			return clientControllerFacade.viewRoadReport(request.getRequest().get(0));
		}
		case "DELETE ROADREPORT": {
			return clientControllerFacade.deleteRoadReport(request.getRequest().get(0)); // treba testirati
		}
		case "VIEW REPORT": {
			return clientControllerFacade.viewReport(Integer.parseInt((request.getRequest().get(0))));
		}
		case "VIEW REPORTS": {
			return clientControllerFacade.viewReports();
		}
		case "NEW REPORT": {
			return clientControllerFacade.newReport(Integer.parseInt(request.getRequest().get(0)),
					Integer.parseInt(request.getRequest().get(1)), Integer.parseInt(request.getRequest().get(2)),
					request.getRequest().get(3), TimeUtility.stringToLocalDateTime(request.getRequest().get(4)));
		}
		}
		System.out.println("Unexisting function requested");
		return clientControllerFacade.unexistingRequest(request.getRequestType());
	}
}
