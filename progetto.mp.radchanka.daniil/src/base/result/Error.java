package base.result;

public class Error {
	public static Error from(String errorMessage) {
		return new Error(errorMessage);
	}

	private final String errorMessage;

	private Error(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
