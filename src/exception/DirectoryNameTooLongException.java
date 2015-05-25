package exception;

public class DirectoryNameTooLongException extends Exception {

	public DirectoryNameTooLongException() {
		super();
	}
	
	public DirectoryNameTooLongException(String message) {
		super(message);
	}
	
}
