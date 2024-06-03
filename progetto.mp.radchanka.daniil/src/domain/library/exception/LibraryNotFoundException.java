package domain.library.exception;

public class LibraryNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public LibraryNotFoundException(String message) {
		super(message);
	}
}
