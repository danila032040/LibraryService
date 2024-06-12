package base.log;

public interface Logger {
    public void log(LogLevelType logLevel, String message, Object... args);
    
    public default void logDebug(String message, Object... args) {
        log(LogLevelType.Debug, message, args);
    }
    
    public default void logError(String message, Object... args) {
        log(LogLevelType.Error, message, args);
    }
    
    public default void logFatal(String message, Object... args) {
        log(LogLevelType.Fatal, message, args);
    }
    
    public default void logInformation(String message, Object... args) {
        log(LogLevelType.Information, message, args);
    }
    
    public default void logWarning(String message, Object... args) {
        log(LogLevelType.Warning, message, args);
    }
}
