package base.log;

import java.io.PrintStream;

public class PrintStreamLogEntryPublisher implements LogEntryPublisher {
	
	private PrintStream printStream;
	public PrintStreamLogEntryPublisher(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void publishLogEntry(LogEntry logEntry) {
		String timeStamp = logEntry.getTimeStamp();
		String logLevelType = logEntry.getLogLevel().toShortString();
		String compiledMessage = logEntry.getCompiledMessage();
		String scopeNameWithCompiledMessage = logEntry
				.getScopeName()
				.map(scopeName -> scopeName + " " + compiledMessage)
				.orElse(compiledMessage);
		printStream.println(
						String
								.format(
										"%s [%s]: %s",
										timeStamp,
										logLevelType,
										scopeNameWithCompiledMessage));
	}

}
