package server;

import java.util.ArrayList;

import server.Request;

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
			return clientControllerFacade.updateUser(Integer.parseInt(request.getRequest().get(0)), request.getRequest().get(1),
					request.getRequest().get(2), request.getRequest().get(3), request.getRequest().get(4),request.getRequest().get(5));
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
//			case "VIEW USER SESSION":{
//				return clientControllerFacade.viewUserSession(request.getRequest().get(1));
//			}
		case "VIEW USER SESSIONS": {
		}
			break;

		case "VIEW CLIENT": {
		}
			break;
		case "VIEW CLIENTS": {
		}
			break;
		case "NEW CLIENT": {
		}
			break;
		case "DELETE CLIENT": {
		}
			break;

		case "VIEW SUBSCRIPTION": {
		}
			break;
		case "VIEW SUBSCRIPTIONS": {
		}
			break;
		case "NEW SUBSCRIPTION": {
		}
			break;
		case "DELETE SUBSCRIPTION": {
		}
			break;

		case "VIEW INTERVENTION": {
		}
			break;
		case "VIEW INTERVENTIONS": {
		}
			break;
		case "NEW INTERVENTION": {
		}
			break;
		case "DELETE INTERVENTION": {
		}
			break;

		case "NEW FIELD REPORT": {
		}
			break;

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
