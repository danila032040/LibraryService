package base.result;

import java.util.Objects;

public class Success {
	public static Success from(String successMessage) {
		return new Success(successMessage);
	}
	private final String successMessage;

	private Success(String successMessage) {
		this.successMessage = Objects.requireNonNull(successMessage);
	}

	public String getSuccessMessage() {
		return successMessage;
	}
}
