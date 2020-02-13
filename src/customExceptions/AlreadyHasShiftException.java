package customExceptions;

@SuppressWarnings("serial")
public class AlreadyHasShiftException extends Exception{
	
	private String id;
	
	public AlreadyHasShiftException(String id) {
		super("The User Already Has a Shift Assigned. Id:"+id);
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
