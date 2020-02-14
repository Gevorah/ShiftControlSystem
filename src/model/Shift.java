package model;

public class Shift {
	
	public final static String ATTENDED = "Attended";
	public final static String NO_ATTENDED = "No Attended";
	public final static String NO_USER = "No user";
	
	private String code;
	private User user;
	private String status;
	
	public Shift(String code) {
		this.code = code;
		this.status = NO_ATTENDED;
		this.user = null;
	}
	
	@Override
	public String toString() {
		return String.format("Shift [%s - %s]",code,status);
	}

	public String getCode() {
		return code;
	}
	
	public String getStatus() {
		return status;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
