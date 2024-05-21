package base.log;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class LoggerImpl implements Logger {
	private Optional<LogScope> currentScopeLog;
	private GlobalLogConfiguration logConfiguration;
	private LogEntryPublisher logEntryPublisher;

	public LoggerImpl(GlobalLogConfiguration logConfiguration,
			LogEntryPublisher logEntryPublisher) {
		this.logConfiguration = logConfiguration;
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

		String timeStamp = new SimpleDateFormat(
				logConfiguration.getTimeStampFormat())
				.format(logConfiguration.getDateProvider().get());
		logEntryPublisher
				.publishLogEntry(
						new LogEntry(
								logLevel,
								timeStamp,
								message,
								currentScopeLog.map(LogScope::getName),
								args));

	}
	private boolean isLowerThanMinimalLogLevel(LogLevelType logLevel) {
		return this.logConfiguration
				.getMinimalLogLevel()
				.compareTo(logLevel) > 0;
	}

}
