package base.log;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.function.Supplier;

public class LogConfiguration {
	private Supplier<LocalDateTime> localDateTimeProvider;
	private Locale locale;
	private LogLevelType minimalLogLevel;
	private String timeStampFormat;

	public Supplier<LocalDateTime> getLocalDateTimeProvider() {
		return localDateTimeProvider;
	}

	public Locale getLocale() {
		return locale;
	}

	public LogLevelType getMinimalLogLevel() {
		return minimalLogLevel;
	}

	public String getTimeStampFormat() {
		return timeStampFormat;
	}

	public void setLocalDateTimeProvider(
			Supplier<LocalDateTime> localDateTimeProvider) {
		this.localDateTimeProvider = localDateTimeProvider;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setMinimalLogLevel(LogLevelType minimalLogLevel) {
		this.minimalLogLevel = minimalLogLevel;
	}

	public void setTimeStampFormat(String timeStampFormat) {
		this.timeStampFormat = timeStampFormat;
	}
}
