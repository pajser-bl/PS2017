package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import utility.TimeUtility;

public class ServerThread extends Thread {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// login check i postaviti temp da bude user, za sad uvjek true
			boolean loginCheck = true;
			if (loginCheck) {
				System.out.println("[" + TimeUtility.getLDTNow() + "]User " + " joined.");
				
				Object a=(String) in.readObject();
				Gson g=new Gson();
				
				ArrayList<String> l=g.fromJson(a.toString(),TypeToken.get( new ArrayList<String>().getClass()).getType());
				for(String s:l)System.out.println(s);
				
				ArrayList<String> r=new ArrayList<>();
				r.add("A");
				r.add("B");
				
				out.writeObject(g.toJson(r));
			} else {
				socket.close();
				System.out.println("[" + TimeUtility.getLDTNow() + "]Unsuccessful attempt at login user:");
			}
		} catch (IOException |ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

}
