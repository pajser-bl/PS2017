package server;
import java.io.IOException;
import java.net.Socket;

import ostalo.TimeUtil;
import ostalo.User;

public class ServerThread extends Thread{
	private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket=socket;
	}
	
	
	
	@Override
	public void run() {
		//login check i postaviti temp da bude user, za sad uvjek true
		User user=new User("test");
		boolean loginCheck=true;
		if(loginCheck) {
			Server.onlineUsers.put(user,socket);
			System.out.println("["+TimeUtil.getCurrentTime()+"]User "+user.toString()+" joined.");
		}else {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("["+TimeUtil.getCurrentTime()+"]Unsuccessful attempt at login user:"+user.toString());
		}
		
	}
	
}
