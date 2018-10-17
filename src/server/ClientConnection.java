package server;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import server.Request;

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
	private int ID_user;
	private int ID_session;
	private boolean logoutCheck = false;
	ArrayList<String> reply;

	public ClientConnection(Socket socket) {
		try {
			this.socket = socket;
		} catch (Exception e) {
			System.out.println("Veza nije uspostavljena !");
		}
	}

	public void run() {
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			reply = new ArrayList<>();
		} catch (Exception e) {
			System.out.println("Otvaranje input/outpu streama nije uspjelo !");
		}

		while (!logoutCheck) {
			try {
				if (input.ready()) {
					Request request = new Gson().fromJson(input.readLine(), new TypeToken<Request>() {
					}.getType());
					// JA BIH BEZ OVOGA TJ DA SERVERA ZABOLE KO JE KLIJENT
					reply.clear();
					reply.addAll(ClientRequestHandler.handle(request));
					if (request.getRequestType().equals("LOGIN")) {
						ID_user = Integer.parseInt(reply.get(1));
						ID_session = Integer.parseInt(reply.get(5));
					}
					if (request.getRequestType().equals("LOGOUT")) {
						logoutCheck = true;
					}
					sendReply(new Gson().toJson(reply));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Porslo nesto ali ne valja");
			}
		}

	}

	public void sendReply(String reply) {
		output.println(reply);
	}
}