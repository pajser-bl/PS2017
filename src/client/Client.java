package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

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
	
	public ArrayList<String> sendRequest(Request request) {
		output.println(new Gson().toJson(request));
		try {
			return new Gson().fromJson(input.readLine(), new TypeToken<ArrayList<String>>(){}.getType());
		}
		catch(Exception e) {
			System.out.println("Greska kod odogvora");
			return null;
		}
	}
	
	public static void main(String[] args) {
		String s1 = "Prvi String"; String s2 = "Drugi String"; String s3 = "Treci String"; String s4 = "ZAHTJEV";
		ArrayList<String> podaci = new ArrayList<String>();
		podaci.add(s1); podaci.add(s2); podaci.add(s3);
		Request request = new Request(s4, podaci);
		String testjson = new Gson().toJson(request.getRequest());
		System.out.println(testjson);
		ArrayList<String> reply = new Gson().fromJson(testjson, new TypeToken<ArrayList<String>>(){}.getType());
		for(int i = 0 ;i < reply.size(); i++)
			System.out.print(reply.get(i) + " ;");
		/*Request test = new Gson().fromJson(testjson, new TypeToken<Request>(){}.getType());
		System.out.println(test.toString());*/
		
		//Client client = new Client("192.168.1.13", 9000);
		//System.out.println(client.sendRequest(request));
	}
}
