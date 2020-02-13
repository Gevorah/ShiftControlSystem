package customExceptions;

@SuppressWarnings("serial")
public class ExistException extends Exception{
	
	private String id;
	
	public ExistException(String id) {
		super("The Document Number Already Exist in the List. Id:"+id);
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
}
