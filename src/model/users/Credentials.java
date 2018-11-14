package model.users;


public class Credentials {
	private int ID_credentials;
	private int ID_user;
	private String username;
	private String hash;
		
	public Credentials(int iD_credentials, int iD_user, String username, String hash) {
		super();
		ID_credentials = iD_credentials;
		ID_user = iD_user;
		this.username = username;
		this.hash = hash;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		public int getID_user() {
		return ID_user;
	}
	public void setID_user(int iD_user) {
		ID_user = iD_user;
	}
	public int getID_credentials() {
		return ID_credentials;
	}
	public void setID_credentials(int iD_credentials) {
		ID_credentials = iD_credentials;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public boolean equals(Object o) {
		if(!o.getClass().equals(this.getClass()))
			return false;
		if(((Credentials)o).ID_credentials==this.ID_credentials)
			return true;
		return false;
	}
}
