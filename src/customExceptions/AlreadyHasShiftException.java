package customExceptions;

@SuppressWarnings("serial")
public class AlreadyHasShiftException extends Exception{
	
	private String id;
	private String shiftReport;
	
	public AlreadyHasShiftException(String id, String shiftReport) {
		super(String.format("The User Already Has a Shift Assigned. Document Number:%s%s",id,shiftReport));
		this.id = id;
		this.shiftReport = shiftReport;
	}

	public String getId() {
		return id;
	}

	public String getShiftReport() {
		return shiftReport;
	}
	
}
