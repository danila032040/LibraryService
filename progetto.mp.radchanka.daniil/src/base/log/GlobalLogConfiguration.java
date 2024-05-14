package base.log;

import java.util.Date;
import java.util.function.Supplier;

public class GlobalLogConfiguration {
	private LogLevelType minimalLogLevel;
	private String timeStampFormat;
	private Supplier<Date> dateProvider;

	public LogLevelType getMinimalLogLevel() {
		return minimalLogLevel;
	}

	public void setMinimalLogLevel(LogLevelType minimalLogLevel) {
		this.minimalLogLevel = minimalLogLevel;
	}

	public String getTimeStampFormat() {
		return timeStampFormat;
	}

	public void setTimeStampFormat(String timeStampFormat) {
		this.timeStampFormat = timeStampFormat;
	}

	public Supplier<Date> getDateProvider() {
		return dateProvider;
	}

	public void setDateProvider(Supplier<Date> dateProvider) {
		this.dateProvider = dateProvider;
	}

}
