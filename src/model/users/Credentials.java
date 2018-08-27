package model.users;


public class Credentials {
	private int ID_credentials;
	private int ID_user;
	private String username;
	private String password;
	private String salt;
	
	public Credentials() {}
	public Credentials(int ID_credentials,int ID_user,String username,String password,String salt) {
		this.ID_credentials=ID_credentials;
		this.ID_user=ID_user;
		this.username=username;
		this.password=password;
		this.salt=salt;
	}
	public Credentials(String username,String password,String salt) {
		this.username=username;
		this.password=password;
		this.salt=salt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_credentials;
		result = prime * result + ID_user;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		if (ID_credentials != other.ID_credentials)
			return false;
		if (ID_user != other.ID_user)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	
	
	
}
