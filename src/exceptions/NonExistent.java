package exceptions;

public class NonExistent extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NonExistent(){
		super("\nThe data entered does not match any ");
	}
}
