package base.log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class PrintStreamLogEntryPublisher implements LogEntryPublisher {

	private final GlobalLogConfiguration logConfiguration;
	private final PrintStream printStream;

	public PrintStreamLogEntryPublisher(GlobalLogConfiguration globalLogConfiguration, PrintStream printStream) {
		this.logConfiguration = globalLogConfiguration;
		this.printStream = printStream;
	}

	@Override
	public void publishLogEntry(LogEntry logEntry) {
		String timeStamp = new SimpleDateFormat(logConfiguration.getTimeStampFormat()).format(logEntry.getDateTime());
		String logLevelType = logEntry.getLogLevel().toShortString();
		String compiledMessage = logEntry.getCompiledMessage();
		String scopeNameWithCompiledMessage = logEntry.getScopeName()
				.map(scopeName -> scopeName + " " + compiledMessage).orElse(compiledMessage);
		printStream.println(String.format("%s [%s]: %s", timeStamp, logLevelType, scopeNameWithCompiledMessage));
	}

}
