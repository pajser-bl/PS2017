package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCommunicationController {
	
	ObjectInputStream input;
	ObjectOutputStream output;
	ClientRequestHandler clientRequestHandler;
	
	public ClientCommunicationController(Socket sock){
		try {
			input=new ObjectInputStream(sock.getInputStream());
			output=new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object receive() {
		return null;
	}
	public void send(Object object) {}
	


}
