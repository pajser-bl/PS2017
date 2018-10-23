package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

//import javax.net.ServerSocketFactory;
//import javax.net.ssl.SSLServerSocketFactory;
import utility.TimeUtility;

public class Server {

	public final static String CONFIG_FILE = "config.txt";
	public static String SERVER_IP_ADDRESS;
	public static int SERVER_PORT;
	public static int SERVER_CONNECTION_LIMIT;
	public static int SERVER_CONNECTION_COUNTER;
	public static boolean SERVER_ONLINE;
	public static MapHandler mapHandler;

	public static void main(String args[]) {
		System.out.println("Server starting up...");
		setUpServer();
		try {
			//ServerSocketFactory sslSocketFactory = SSLServerSocketFactory.getDefault();
			//ServerSocket serverSocket = sslSocketFactory.createServerSocket(SERVER_PORT);
			ServerSocket serverSocket=new ServerSocket(SERVER_PORT);
			System.out.println("[" +TimeUtility.getStringTimeNow() + "]Server is online, awaiting incoming user connections");
			while (SERVER_ONLINE && serverLimitNotReached()) {
				Socket socket = serverSocket.accept();
				SERVER_CONNECTION_COUNTER++;
				ClientConnection connection = new ClientConnection(socket);
				connection.start();
				System.out.println("Connection enstablished");
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setUpServer() {
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("server.conf");
			props.load(in);
			in.close();

			SERVER_IP_ADDRESS = props.getProperty("server.ip_address");
			SERVER_PORT = Integer.parseInt(props.getProperty("server.port"));
			SERVER_CONNECTION_LIMIT = Integer.parseInt(props.getProperty("server.max_connections"));
			SERVER_CONNECTION_COUNTER = 0;
//		onlineUsers = new HashMap<>();
			SERVER_ONLINE = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean serverLimitNotReached() {
		return SERVER_CONNECTION_COUNTER < SERVER_CONNECTION_LIMIT;
	}
}
