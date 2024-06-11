package base.result;

import java.util.Optional;
import java.util.function.Consumer;

public class ErrorOr<T> {
    
    private final OneOf2<T, ErrorResult> oneOf2Result;
    
    private ErrorOr(T result) {
        oneOf2Result = OneOf2.from0(result);
    }
    
    private ErrorOr(String errorMessage) {
        oneOf2Result = OneOf2.<T, ErrorResult>from1(ErrorResult.from(errorMessage));
    }
    
    private ErrorOr(ErrorResult errorResult) {
        oneOf2Result = OneOf2.<T, ErrorResult>from1(errorResult);
    }
    
    public static <T> ErrorOr<T> fromErrorResult(ErrorResult errorResult) {
        return new ErrorOr<>(errorResult);
    }
    
    public static <T> ErrorOr<T> fromErrorMessage(String errorMessage) {
        return new ErrorOr<>(errorMessage);
    }
    
    public static <T> ErrorOr<T> fromResult(T result) {
        return new ErrorOr<>(result);
    }
    
    public void match(Consumer<T> resultConsumer, Consumer<ErrorResult> errorConsumer) {
        oneOf2Result.match(resultConsumer, errorConsumer);
    }
    
    public boolean isError() {
        return oneOf2Result.isT1();
    }
    
    public Optional<T> getResult() {
        return oneOf2Result.getT0();
    }
    
    public Optional<ErrorResult> getError() {
        return oneOf2Result.getT1();
    }
}
