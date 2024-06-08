package base.result;

import java.util.Objects;

public class ErrorResult {
    public static ErrorResult from(String errorMessage) {
        return new ErrorResult(errorMessage);
    }
    
    private final String errorMessage;
    
    private ErrorResult(String errorMessage) {
        this.errorMessage = Objects.requireNonNull(errorMessage);
    }
    
    public String getMessage() {
        return errorMessage;
    }
}
