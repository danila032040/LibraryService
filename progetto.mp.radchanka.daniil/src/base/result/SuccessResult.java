package base.result;

import java.util.Objects;

public class SuccessResult {
    public static SuccessResult from(String successMessage) {
        return new SuccessResult(successMessage);
    }
    
    private final String successMessage;
    
    private SuccessResult(String successMessage) {
        this.successMessage = Objects.requireNonNull(successMessage);
    }
    
    public String getSuccessMessage() {
        return successMessage;
    }
}
