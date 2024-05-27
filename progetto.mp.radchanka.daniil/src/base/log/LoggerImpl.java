package base.log;

import base.utils.MessageFormatSupportingTimePackage;

public class LoggerImpl implements Logger {
	private GlobalLogConfiguration logConfiguration;
	private LogEntryPublisher logEntryPublisher;

	public LoggerImpl(GlobalLogConfiguration globalLogConfiguration,
			LogEntryPublisher logEntryPublisher) {
		this.logConfiguration = globalLogConfiguration;
		this.logEntryPublisher = logEntryPublisher;
	}

	public void log(LogLevelType logLevel, String message, Object... args) {
		if (isLowerThanMinimalLogLevel(logLevel))
			return;
		logEntryPublisher
				.publishLogEntry(
						new LogEntry(
								logLevel,
								logConfiguration
										.getLocalDateTimeProvider()
										.get(),
								message,
								args,
								(
										originalMessage,
										arguments) -> MessageFormatSupportingTimePackage
												.of(
														originalMessage,
														logConfiguration
																.getLocale())
												.format(arguments)));

	}

	private boolean isLowerThanMinimalLogLevel(LogLevelType logLevel) {
		return this.logConfiguration
				.getMinimalLogLevel()
				.compareTo(logLevel) > 0;
	}

}
