package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import server.Request;

public class Client {
	public static final String SERVER_IP_ADDRESS="127.0.0.1";
	public static final int PORT=9000;
	
	public static void main(String args[]) throws ClassNotFoundException {
		
		Socket socket;
		try {
			socket = new Socket(SERVER_IP_ADDRESS,PORT);
			ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
		
			ArrayList<Object> mes=new ArrayList<>();
			mes.add(new String("user"));
			mes.add(new String("password"));
			Request request=new Request("LOGIN", mes);
		
			output.writeObject(request);
			System.out.print(input.readObject().toString());
		
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	
	
}
