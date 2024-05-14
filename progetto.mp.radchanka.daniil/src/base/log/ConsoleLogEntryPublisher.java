package base.log;

public class ConsoleLogEntryPublisher implements LogEntryPublisher {

	@Override
	public void publishLogEntry(LogEntry logEntry) {
		String timeStamp = logEntry.getTimeStamp();
		String logLevelType = toShortStringLogLevelType(logEntry.getLogLevel());
		String compiledMessage = logEntry.compileMessage();
		String scopeNameWithCompiledMessage = logEntry
				.getScopeName()
				.map(scopeName -> scopeName + " " + compiledMessage)
				.orElse(compiledMessage);
		System.out
				.println(
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
