package base.log;

import java.time.LocalDateTime;

import base.lazyLoading.LazyLoad;

public class LogEntry {
    private final Object[] arguments;
    private final LazyLoad<String> compiledMessage;
    private final LocalDateTime dateTime;
    private final LogLevelType logLevel;
    private final String originalMessage;
    
    public LogEntry(
            LogLevelType logLevel,
            LocalDateTime dateTime,
            String originalMessage,
            Object[] arguments,
            CompiledMessageBuilder compiledMessageBuilder) {
        this.dateTime = dateTime;
        this.logLevel = logLevel;
        this.originalMessage = originalMessage;
        this.arguments = arguments;
        this.compiledMessage = new LazyLoad<>(() -> compiledMessageBuilder.compileMessage(originalMessage, arguments));
    }
    
    public Object[] getArguments() {
        return arguments;
    }
    
    public String getCompiledMessage() {
        return compiledMessage.getValue();
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public LogLevelType getLogLevel() {
        return logLevel;
    }
    
    public String getOriginalMessage() {
        return originalMessage;
    }
}
