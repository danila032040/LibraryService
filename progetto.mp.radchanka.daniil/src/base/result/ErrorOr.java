package base.result;

import java.util.function.Consumer;

public class ErrorOr<T> {

	private final OneOf2<T, Error> oneOf2Result;

	private ErrorOr(T result) {
		oneOf2Result = OneOf2.from0(result);
	}

	private ErrorOr(String errorMessage) {
		oneOf2Result = OneOf2.<T, Error>from1(Error.from(errorMessage));
	}

	public static <T> ErrorOr<T> fromErrorMessage(String errorMessage) {
		return new ErrorOr<T>(errorMessage);
	}

	public static <T> ErrorOr<T> fromResult(T result) {
		return new ErrorOr<T>(result);
	}

	public void match(
			Consumer<T> resultConsumer,
			Consumer<Error> errorConsumer) {
		oneOf2Result.match(resultConsumer, errorConsumer);
	}
}
