package exceptions;

public class InvalidOption extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidOption(){
		super("\nInvalid option, please try again\n");
	}
}
