package base.log;

import java.text.MessageFormat;
import java.util.Optional;

public class LoggerImpl implements Logger {
	private Optional<LogScope> currentScopeLog;
	private GlobalLogConfiguration logConfiguration;
	private LogEntryPublisher logEntryPublisher;

	public LoggerImpl(GlobalLogConfiguration globalLogConfiguration, LogEntryPublisher logEntryPublisher) {
		this.logConfiguration = globalLogConfiguration;
		this.logEntryPublisher = logEntryPublisher;
	}

	public LogScope beginScope(String scopeName) throws Exception {
		if (currentScopeLog.isPresent())
			currentScopeLog.get().close();
		currentScopeLog = Optional.of(new LogScope(this, scopeName));
		return currentScopeLog.get();
	}

	public void closeScope() throws Exception {
		if (currentScopeLog.isPresent())
			currentScopeLog.get().close();
	}

	public void log(LogLevelType logLevel, String message, Object... args) {
		if (isLowerThanMinimalLogLevel(logLevel))
			return;
		logEntryPublisher.publishLogEntry(new LogEntry(logLevel, logConfiguration.getDateProvider().get(),
				currentScopeLog.map(LogScope::getName), message, args, (originalMessage, arguments) -> MessageFormat
						.format(originalMessage, arguments, logConfiguration.getLocale())));

	}

	private boolean isLowerThanMinimalLogLevel(LogLevelType logLevel) {
		return this.logConfiguration.getMinimalLogLevel().compareTo(logLevel) > 0;
	}

}
