package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

	Socket socket;
	BufferedReader input;
	PrintWriter output;
	
	public Client(String host, int port) {
		try {
		socket = new Socket(host, port);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		}
		catch(Exception e) {
			System.out.println("Veza nije uspostavljena !");
		}
	}
	
	public String sendRequest(String request) {
		output.write(request);
		try {
			return input.readLine();
		}
		catch(Exception e) {
			System.out.println("Greska kod odogvora");
			return "Nema odgovora";
		}
	}
}
