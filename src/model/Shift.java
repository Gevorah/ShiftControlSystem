package model;

public class Shift {
	
	public final static String ATTENDED = "Attended";
	public final static String NO_ATTENDED = "No Attended";
	public final static String NO_USER = "No user";
	
	private String code;
	private String status;
	
	public Shift(String code) {
		this.code = code;
		this.status = "No attended.";
	}
	
	public String getCode() {
		return code;
	}
	
	public String getStatus() {
		return status;
	}
	
	
}
