package base.log;

import java.util.Optional;

public class LogEntry {
	private LogLevelType logLevel;
	private String timeStamp;
	private String originalMessage;
	private Optional<String> scopeName;
	private Object[] arguments;

	public LogEntry(LogLevelType logLevel, String timeStamp,
			String originalMessage, Optional<String> scopeName,
			Object[] arguments) {
		this.timeStamp = timeStamp;
		this.logLevel = logLevel;
		this.originalMessage = originalMessage;
		this.scopeName = scopeName;
		this.arguments = arguments;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public LogLevelType getLogLevel() {
		return logLevel;
	}
	public String getOriginalMessage() {
		return originalMessage;
	}
	public Optional<String> getScopeName() {
		return scopeName;
	}
	public Object[] getArguments() {
		return arguments;
	}
	public String compileMessage() {
		return String.format(originalMessage, arguments);
	}
}
