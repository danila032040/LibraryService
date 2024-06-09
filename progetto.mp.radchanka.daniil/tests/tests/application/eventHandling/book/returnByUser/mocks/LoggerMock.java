package tests.application.eventHandling.book.returnByUser.mocks;

import java.util.Optional;

import base.log.LogLevelType;
import base.log.Logger;

public class LoggerMock implements Logger {
    private Optional<String> lastErrorMessage = Optional.empty();
    
    public Optional<String> getLastErrorMessage() {
        return lastErrorMessage;
    }
    
    @Override
    public void log(LogLevelType logLevel, String message, Object... args) {
        if (logLevel == LogLevelType.Error)
            lastErrorMessage = Optional.of(String.format(message, args));
    }
}