package model;

public class Shift {
	
	public final static String ATTENDED = "Attended";
	public final static String NO_ATTENDED = "No Attended";
	public final static String NO_USER = "No user";
	
	private String code;
	private User user;
	private String status;
	
	public Shift(String code, User user) {
		this.code = code;
		this.status = "No attended.";
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

	public void setStatus(String status) {
		this.status = status;
	}
}
