package exception;

public class FileNameTooLongException extends Exception {

	public FileNameTooLongException() {
		super();
	}
	
	public FileNameTooLongException(String message) {
		super(message);
	}
	
}
