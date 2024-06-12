package tests.base.log.mocks;

import base.log.LogLevelType;
import base.log.Logger;

public class LoggerMock implements Logger {
    private LogLevelType lastSpecifiedLogLevel;
    private String lastSpecifiedMessage;
    private Object[] lastSpecifiedArgs;
    
    public Object[] getLastSpecifiedArgs() {
        return lastSpecifiedArgs;
    }
    
    public LogLevelType getLastSpecifiedLogLevel() {
        return lastSpecifiedLogLevel;
    }
    
    public String getLastSpecifiedMessage() {
        return lastSpecifiedMessage;
    }
    
    @Override
    public void log(LogLevelType logLevel, String message, Object... args) {
        this.lastSpecifiedLogLevel = logLevel;
        this.lastSpecifiedMessage = message;
        this.lastSpecifiedArgs = args;
    }
}
