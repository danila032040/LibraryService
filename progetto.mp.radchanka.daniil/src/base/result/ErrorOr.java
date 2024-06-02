package base.result;

import java.util.Optional;

public class ErrorOr<T> extends OneOf2<T, Error> {

	private ErrorOr(T result) {
		super(Optional.of(result), Optional.empty());
	}

	private ErrorOr(String errorMessage) {
		super(Optional.empty(), Optional.of(Error.from(errorMessage)));
	}

	public static <T> ErrorOr<T> fromErrorMessage(String errorMessage) {
		return new ErrorOr<T>(errorMessage);
	}

	public static <T> ErrorOr<T> fromResult(T result) {
		return new ErrorOr<T>(result);
	}
}
