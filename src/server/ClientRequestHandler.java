package server;

import java.util.ArrayList;

import model.interventions.Intervention;
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
			System.out.println("Logout zatrazern-test");
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
		case "VIEW USER": {
		}
			break;
		case "VIEW USERS": {
			return clientControllerFacade.viewUsers(request.getRequest().get(0));
		}
		case "ACCESS MAP FIELD TECHNITIAN": {
		}
			break;
		case "ACCESS MAP OPERATOR": {
		}
			break;
		case "CHANGE STATE FIELD TECHNITIAN": {
		}
			break;
		case "VIEW STATE FIELD TECHNITIAN": {
		}
			break;
		case "VIEW STATES FIELD TECHNITIAN": {
		}
			break;
		case "VIEW ONLINE USERS": {
			return clientControllerFacade.viewOnlineUsers();
		}
		// case "VIEW USER SESSION":{
		// return clientControllerFacade.viewUserSession(request.getRequest().get(1));
		// }
		case "VIEW USER SESSIONS": {
		}
			break;

		case "VIEW CLIENT": {
			return clientControllerFacade.viewClient(Integer.parseInt(request.getRequest().get(0)));
		}
		case "VIEW CLIENTS": {
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
		}
		case "NEW INTERVENTION": {
			return clientControllerFacade.newIntervention(new Intervention(
					Integer.parseInt(request.getRequest().get(0)), Integer.parseInt(request.getRequest().get(1)),
					Integer.parseInt(request.getRequest().get(2)),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(3))));
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
		}
		case "UPDATE ROADREPORT": {
		}
		case "DELETE ROADREPORT": {
		}

		case "VIEW REPORT": {
			return clientControllerFacade.viewReport(Integer.parseInt((request.getRequest().get(0))));
		}
		case "VIEW REPORTS": {

		}
		case "NEW REPORT": {

		}
		case "INSERT REPORT": {
		}
		case "DELETE REPORT": {
		}
		}
		System.out.println("Unexisting function requested");
		return clientControllerFacade.unexistingRequest();
	}
}
