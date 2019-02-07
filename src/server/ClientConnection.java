package server;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import server.Request;
import utility.TimeUtility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnection extends Thread {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private int ID_user;
	private String username;
	private String userName;
	private String userSurname;
	private String userType;
	private boolean logoutCheck = false;
	ArrayList<String> reply;
	

	public ClientConnection(Socket socket) {
		try {
			this.socket = socket;
		} catch (Exception e) {
			System.out.println("Connection not established!");
		}
	}

	public void run() {
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			reply = new ArrayList<>();
		} catch (IOException e) {
			System.out.println("Unsuccessfull opening of input/output stream!");
			e.printStackTrace();
		}

		while (!logoutCheck) {
			try {
				if(this.output.checkError()&&!logoutCheck) {
					System.out.println("["+TimeUtility.getStringTimeNow()+"]Connection lost to "+this.username+"("+this.userName+" "+this.userSurname+")["+this.userType+"].");
					ArrayList<String> temp=new ArrayList<String>();temp.add(""+ID_user);
					ClientRequestHandler.handle(new Request("CONNECTION LOST",temp));
				}
				if (input.ready()) {
					Request request = new Gson().fromJson(input.readLine(), new TypeToken<Request>() {
					}.getType());
					reply.clear();
					if (request.getRequestType().equals("LOGOUT")) {
						logoutCheck = true;
						ClientRequestHandler.handle(request);
						System.out.println("["+TimeUtility.getStringTimeNow()+"]User "+this.username+" ("+this.userName+" "+this.userSurname+")["+this.userType+"] has logged out.");
						this.socket.close();
					} else if (request.getRequestType().equals("LOGIN")) {
						reply.addAll(ClientRequestHandler.handle(request));
						if(reply.get(0).equals("LOGIN OK")) {
							this.ID_user=Integer.parseInt(reply.get(1));
							this.userName=reply.get(2);
							this.userSurname=reply.get(3);
							this.userType=reply.get(4);
							this.username=reply.get(5);
							System.out.println("[" + TimeUtility.getStringTimeNow() + "]User " + this.username
							+ " (" + this.userName + " " + this.userSurname + ")["+this.userType+"] has successfully logged in.");
						}else {
							logoutCheck=true;
						}
						sendReply(new Gson().toJson(reply));
					} else {
						reply.addAll(ClientRequestHandler.handle(request));
						if(reply.get(0).equals("UNEXISTING FUNCTION REQUEST"))
							System.out.println("Unexisting function requested by "+this.username+" ("+this.userName+" "+this.userSurname+")["+this.userType+"], request: "+reply.get(1));	
						sendReply(new Gson().toJson(reply));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception happened durig communication with user: "+this.username+" ("+this.userName+" "+this.userSurname+")["+this.userType+"].");
				reply.clear();
				reply.add("INTERNAL SERVER ERROR");
				reply.add("Doslo je do interne serverske greske.");
				sendReply(new Gson().toJson(reply));
			}
		}
	}

	public void sendReply(String reply) {
		output.println(reply);
	}
}