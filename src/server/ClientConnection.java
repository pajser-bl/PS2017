package server;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnection extends Thread{
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
	}
	
	public void sendReply(Request request){
		output.write("Odgovor");
	}
} 