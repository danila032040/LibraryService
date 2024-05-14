package base.log;

public class LogEntryPublisherDecorator implements LogEntryPublisher {

	private LogEntryPublisher wrappeeLogEntryPublisher;

	public LogEntryPublisherDecorator(
			LogEntryPublisher wrappeeLogEntryPublisher) {
		super();
		this.wrappeeLogEntryPublisher = wrappeeLogEntryPublisher;
	}

	@Override
	public void publishLogEntry(LogEntry logEntry) {
		wrappeeLogEntryPublisher.publishLogEntry(logEntry);
	}

}
