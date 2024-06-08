package tests.application.pipelineBehaviours.mocks;

import java.util.ArrayList;
import java.util.List;

import base.log.LogLevelType;
import base.log.Logger;

public class LoggerMock implements Logger {
    private final List<LogLevelType> spesifiedLogLevels = new ArrayList<>();
    private final List<String> specifiedMessages = new ArrayList<>();
    private final List<Object[]> specifiedArgumentsList = new ArrayList<>();
    
    @Override
    public void log(LogLevelType logLevel, String message, Object... args) {
        this.spesifiedLogLevels.add(logLevel);
        this.specifiedMessages.add(message);
        this.specifiedArgumentsList.add(args);
    }
    
}
