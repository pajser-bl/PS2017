package server;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import client.Request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnection extends Thread {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	public ClientConnection (Socket socket) {
		try {
			this.socket = socket;
			}
			catch(Exception e) {
				System.out.println("Veza nije uspostavljena !");
			}
		}
	
	public void run() {
		try {
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		}
		catch(Exception e) {
			System.out.println("Otvaranje input/outpu streama nije uspjelo !");
		}
		try {
			Request request = new Gson().fromJson(input.readLine(), new TypeToken<ArrayList<String>>(){}.getType());
			sendReply(new Gson().toJson(request.getRequest()));
		}
		catch(Exception e) {
			System.out.println("Porslo nesto ali ne valja");
		}
	}
	
	public void sendReply(String reply){
		output.println(reply);
	}
} 