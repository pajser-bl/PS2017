package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import utility.TimeUtility;


public class Server {

	public final static String CONFIG_FILE="config.txt";
	public static String SERVER_IP_ADDRESS;
	public static int SERVER_PORT;
	public static int SERVER_CONNECTION_LIMIT;
	public static int SERVER_CONNECTION_COUNTER;
	public static boolean SERVER_ONLINE;
//	public static HashMap<User,Socket> onlineUsers;
	public static MapHandler mapHandler;
	
	public static void main(String args[]) {
		System.out.println("Server starting up...");
		setUpServer();
		try {
			ServerSocket serverSocket=new ServerSocket(SERVER_PORT);
			System.out.println("["+TimeUtility.getLDTNow()+"]Server is online, awaiting incoming user connections");
			while(SERVER_ONLINE&&serverLimitNotReached()) {
				Socket socket=serverSocket.accept();
				SERVER_CONNECTION_COUNTER++;
				ServerThread serverThread=new ServerThread(socket);
				serverThread.start();
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void setUpServer() {
		SERVER_IP_ADDRESS="127.0.0.1";
		SERVER_PORT=9000;
		SERVER_CONNECTION_LIMIT=100;
		SERVER_CONNECTION_COUNTER=0;
		SERVER_ONLINE=true;
//		onlineUsers=new HashMap<>();
	}
	public static boolean serverLimitNotReached() {
		return SERVER_CONNECTION_COUNTER<SERVER_CONNECTION_LIMIT;
	}

}
