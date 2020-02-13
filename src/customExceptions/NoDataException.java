package customExceptions;

@SuppressWarnings("serial")
public class NoDataException extends Exception{
	
	public NoDataException() {
		super("The data was not input.");
	}
}
