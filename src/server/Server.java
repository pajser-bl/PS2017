package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	public static ServerSocket serverSocket;
	public static Server server;
	public static ServerShutdownThread serverShutdownThread;

	public static void main(String args[]) {
		try {
			System.out.println("Server starting up...");
			server=new Server();
			serverShutdownThread = new ServerShutdownThread();
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println(
					"[" + TimeUtility.getStringTimeNow() + "]Server is online, awaiting incoming user connections");
			while (SERVER_ONLINE && serverLimitNotReached()) {
				Socket socket = serverSocket.accept();
				SERVER_CONNECTION_COUNTER++;
				ClientConnection connection = new ClientConnection(socket);
				connection.start();
				System.out.println("[" + TimeUtility.getStringTimeNow() + "]Connection enstablished");
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while working!");
			System.out.println("Shutting down server...");
			System.exit(1);
		}
	}

	public Server() {
		setUpServer();
		printWelcomeScreen();
		administrator_check();
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
		UserDAO userDAO = new MySQLUserDAO();
		if (!userDAO.administrator_exists()) {
			User admin = new User("temp", "temp", LocalDate.now(), "Administrator", "VSS", "", "admin",
					HashHandler.createHash("admin"));
			userDAO.insert(admin);
			System.out.println("Administrator is missing, creating temporary administrator account...");
		} else {
			System.out.println("Administrator is present.");
		}

	}

	public static boolean serverLimitNotReached() {
		return SERVER_CONNECTION_COUNTER < SERVER_CONNECTION_LIMIT;
	}
	
	public static void printWelcomeScreen() {
		System.out.println("        ____   ____   _   _  ____          ");
		System.out.println("       / ___| |  _ \\ | \\ | ||  _ \\         ");
		System.out.println("       \\___ \\ | |_) ||  \\| || |_) |        ");
		System.out.println("        ___) ||  __/ | |\\  ||  __/         ");
		System.out.println("       |____/ |_|    |_| \\_||_|            ");
		System.out.println("");
		System.out.println(" ____   _____  ____ __     __ _____  ____  ");
		System.out.println("/ ___| | ____||  _ \\\\ \\   / /| ____||  _ \\ ");
		System.out.println("\\___ \\ |  _|  | |_) |\\ \\ / / |  _|  | |_) |");
		System.out.println(" ___) || |___ |  _ <  \\ V /  | |___ |  _ < ");
		System.out.println("|____/ |_____||_| \\_\\  \\_/   |_____||_| \\_\\");
		System.out.println("");
		System.out.println("Application server for RoadRunner system.");
		System.out.println("------------------------------------------------");
	}
	
	public static class ServerShutdownThread extends Thread {
		String consoleInput;
		Scanner scan;

		public ServerShutdownThread() {
			scan = new Scanner(System.in);
			consoleInput = "";
			start();
		}

		public void run() {
			while (true) {
				switch (consoleInput = scan.nextLine()) {
				case "shutdown": {
						System.out.println("Server is shutting down...");
						System.out.println("Godbye.");
						System.exit(0);
				}
				case "time": {
					System.out.println("Server time: "+TimeUtility.localDateTimeToString(LocalDateTime.now()));
					break;
				}
				case "limit": {
					System.out.println("Server connection limit: "+SERVER_CONNECTION_LIMIT);
					break;
				}
				case "ip": {
					System.out.println("Server ip address: "+SERVER_IP_ADDRESS);
					break;
				}
				case "port": {
					System.out.println("Server port: "+SERVER_PORT);
					break;
				}
				case "help": {
					System.out.println("Awailable commands are:");
					System.out.println("   time - Get current server time.");
					System.out.println("   ip - Get server ip addres.");
					System.out.println("   port - Get server port.");
					System.out.println("   limit - Get server connection limit.");
					System.out.println("   shutdown - Shutdown server.");
					System.out.println("   help - Gets you this:)");
					System.out.println();
					break;
				}
				case "":{break;}
				default: {
					System.out.println("Unrecognised command. Check help for awailable commands.");
				}
				}
			}
		}
	}
}
