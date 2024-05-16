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
		String logLevelType = toShortStringLogLevelType(logEntry.getLogLevel());
		String compiledMessage = logEntry.compileMessage();
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

	private String toShortStringLogLevelType(LogLevelType logLevel) {
		switch (logLevel) {
			case Information :
				return "INF";
			case Debug :
				return "DBG";
			case Warning :
				return "WARN";
			case Error :
				return "ERR";
			case Fatal :
				return "FTL";
			default :
				throw new IllegalArgumentException();
		}
	}

}
