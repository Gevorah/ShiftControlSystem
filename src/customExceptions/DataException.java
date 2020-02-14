package customExceptions;

@SuppressWarnings("serial")
public class DataException extends IllegalArgumentException{
	
	public DataException() {
		super("The data was not input.");
	}
}
