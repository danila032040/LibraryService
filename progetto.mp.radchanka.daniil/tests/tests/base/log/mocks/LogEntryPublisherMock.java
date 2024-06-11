package tests.base.log.mocks;

import base.log.LogEntry;
import base.log.LogEntryPublisher;

public class LogEntryPublisherMock implements LogEntryPublisher {
    private LogEntry lastPublishedLogEntry;
    
    public LogEntry getLastPublishedLogEntry() {
        return lastPublishedLogEntry;
    }
    
    @Override
    public void publish(LogEntry logEntry) {
        this.lastPublishedLogEntry = logEntry;
    }
    
}
