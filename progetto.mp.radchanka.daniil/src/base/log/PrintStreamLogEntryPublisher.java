package base.log;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class PrintStreamLogEntryPublisher implements LogEntryPublisher {

	private final GlobalLogConfiguration logConfiguration;
	private final PrintStream printStream;

	public PrintStreamLogEntryPublisher(
			GlobalLogConfiguration globalLogConfiguration,
			PrintStream printStream) {
		this.logConfiguration = Objects.requireNonNull(globalLogConfiguration);
		this.printStream = Objects.requireNonNull(printStream);
	}

	@Override
	public void publishLogEntry(LogEntry logEntry) {
		Locale locale = logConfiguration.getLocale();
		String timeStamp = DateTimeFormatter
				.ofPattern(logConfiguration.getTimeStampFormat(), locale)
				.format(logEntry.getDateTime());
		String logLevelType = logEntry.getLogLevel().toShortString();
		String compiledMessage = logEntry.getCompiledMessage();;
		printStream
				.println(
						String
								.format(
										locale,
										"%s [%s]: %s",
										timeStamp,
										logLevelType,
										compiledMessage));
	}

}
