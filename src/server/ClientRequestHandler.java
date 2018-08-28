package server;

public class ClientRequestHandler {
	ClientControllerFacade clientControllerFacade;
	
	public void handle(Request request) {
		switch(request.getRequestType()) {
			case "LOGIN":{}break;
			case "LOGOUT":{}break;
			
			case "NEW CREDENTIALS":{}break;
			case "DELETE CREDENTIALS":{}break;
			case "VIEW CREDENTIALS":{}break;
			
			case "NEW USER":{}break;
			case "DELETE USER":{}break;
			case "VIEW USER":{}break;
			case "VIEW USERS":{}break;
			
			case "ACCESS MAP FIELD TECHNITIAN":{}break;
			case "ACCESS MAP OPERATOR":{}break;
			case "CHANGE STATE FIELD TECHNITIAN":{}break;
			case "VIEW STATE FIELD TECHNITIAN":{}break;
			case "VIEW STATES FIELD TECHNITIAN":{}break;
			case "VIEW ONLINE USERS":{}break;
			case "VIEW USER SESSION":{}break;
			case "VIEW USER SESSIONS":{}break;
			
			case "VIEW CLIENT":{}break;
			case "VIEW CLIENTS":{}break;
			case "NEW CLIENT":{}break;
			case "DELETE CLIENT":{}break;
			
			case "VIEW SUBSCRIPTION":{}break;
			case "VIEW SUBSCRIPTIONS":{}break;
			case "NEW SUBSCRIPTION":{}break;
			case "DELETE SUBSCRIPTION":{}break;
			
			case "VIEW INTERVENTION":{}break;
			case "VIEW INTERVENTIONS":{}break;
			case "NEW INTERVENTION":{}break;
			case "DELETE INTERVENTION":{}break;
			
			case "NEW FIELD REPORT":{}break;
			
			case "VIEW REPORT":{}break;
			case "VIEW REPORTS":{}break;
			case "NEW REPORT":{}break;
			
			default:{}break;
		}
		
	}
}
