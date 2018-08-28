package server;

public class Request {
	private String requestType;
	private Object argument;

	public Request(String requestType,Object argument) {
		this.requestType=requestType;
		this.argument=argument;
	}
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Object getArgument() {
		return argument;
	}

	public void setArguments(Object argument) {
		this.argument = argument;
	}

}
