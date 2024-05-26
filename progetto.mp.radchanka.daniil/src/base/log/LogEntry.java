package base.log;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BiFunction;

import base.lazyLoading.LazyLoad;

public class LogEntry {
	private LogLevelType logLevel;
	private LocalDateTime dateTime;
	private String originalMessage;
	private LazyLoad<String> compiledMessage;
	private Optional<String> scopeName;
	private Object[] arguments;

	public LogEntry(LogLevelType logLevel, LocalDateTime dateTime,
			Optional<String> scopeName, String originalMessage,
			Object[] arguments, CompiledMessageBuilder compiledMessageBuilder) {
		this.dateTime = dateTime;
		this.logLevel = logLevel;
		this.scopeName = scopeName;
		this.originalMessage = originalMessage;
		this.arguments = arguments;
		this.compiledMessage = new LazyLoad<String>(
				() -> compiledMessageBuilder
						.compileMessage(originalMessage, arguments));
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
