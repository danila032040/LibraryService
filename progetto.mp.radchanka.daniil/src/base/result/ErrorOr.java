package base.result;

import java.util.Optional;

public class ErrorOr<T> extends OneOf2<T, String> {

	private ErrorOr(T result) {
		super(Optional.of(result), Optional.empty(), 0);
	}

	private ErrorOr(String errorMessage) {
		super(Optional.empty(), Optional.of(errorMessage), 1);
	}

	public static <T> ErrorOr<T> fromErrorMessage(String errorMessage) {
		return new ErrorOr<T>(errorMessage);
	}

	public static <T> ErrorOr<T> fromResult(T result) {
		return new ErrorOr<T>(result);
	}
}
