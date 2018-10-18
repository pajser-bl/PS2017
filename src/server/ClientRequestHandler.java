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
			return clientControllerFacade.logout();
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
					request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4));
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
		}
			break;

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
		}
			break;
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
			break;
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
			break;
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
					Integer.parseInt(request.getRequest().get(0)),Integer.parseInt(request.getRequest().get(2)),request.getRequest().get(3),
					TimeUtility.stringToLocalDateTime(request.getRequest().get(4)), 
					request.getRequest().get(5));
		}

		case "VIEW REPORT": {
		}
			break;
		case "VIEW REPORTS": {
		}
			break;
		case "NEW REPORT": {
		}
			break;

		default: {
		}
			break;
		}
		return null;

	}
}
