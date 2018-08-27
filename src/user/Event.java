package user;

import java.time.LocalDateTime;

public class Event {
	private LocalDateTime time; 
	private User user;
	private String action;

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
}
