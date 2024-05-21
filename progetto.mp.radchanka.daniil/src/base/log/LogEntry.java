package base.log;

import java.util.Optional;

import base.lazyLoading.LazyLoad;

public class LogEntry {
	private LogLevelType logLevel;
	private String timeStamp;
	private String originalMessage;
	private LazyLoad<String> compiledMessage;
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
		this.compiledMessage = new LazyLoad<String>(
				() -> String.format(originalMessage, arguments));
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
	public String getCompiledMessage() {
		return compiledMessage.getValue();
	}
}
