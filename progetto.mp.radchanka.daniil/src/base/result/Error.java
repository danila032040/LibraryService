package base.result;

import java.util.Objects;

public class Error {
	public static Error from(String errorMessage) {
		return new Error(errorMessage);
	}

	private final String errorMessage;

	private Error(String errorMessage) {
		this.errorMessage = Objects.requireNonNull(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
