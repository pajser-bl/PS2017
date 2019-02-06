package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import DAO.UserDAO;
import DAO.MySQL.MySQLUserDAO;
import model.users.User;
import utility.HashHandler;
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
		administrator_check();
		try {
			ServerSocket serverSocket=new ServerSocket(SERVER_PORT);
			System.out.println("[" +TimeUtility.getStringTimeNow() + "]Server is online, awaiting incoming user connections");
			while (SERVER_ONLINE && serverLimitNotReached()) {
				Socket socket = serverSocket.accept();
				SERVER_CONNECTION_COUNTER++;
				ClientConnection connection = new ClientConnection(socket);
				connection.start();
				System.out.println("[" +TimeUtility.getStringTimeNow() + "]Connection enstablished");
			}
			serverSocket.close();
			System.out.println("Godbye.");
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
			SERVER_ONLINE = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void administrator_check() {
		System.out.println("Checking database for administrator account.");
		UserDAO userDAO=new MySQLUserDAO();
		if(!userDAO.administrator_exists()) {
			User admin=new User("temp", "temp", LocalDate.now(), "Administrator", "VSS", "", "admin", HashHandler.createHash("admin"));
			userDAO.insert(admin);
			System.out.println("Administrator is missing, creating temporary administrator account...");
		}else {
			System.out.println("Administrator is present.");
		}
		
	}
	
	public static boolean serverLimitNotReached() {
		return SERVER_CONNECTION_COUNTER < SERVER_CONNECTION_LIMIT;
	}
}
