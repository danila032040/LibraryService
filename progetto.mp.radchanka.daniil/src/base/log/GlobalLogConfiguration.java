package base.log;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

public class GlobalLogConfiguration {
	private LogLevelType minimalLogLevel;
	private String timeStampFormat;
	private Supplier<LocalDateTime> localDateTimeProvider;
	private Locale locale;

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

	public Supplier<LocalDateTime> getLocalDateTimeProvider() {
		return localDateTimeProvider;
	}

	public void setLocalDateTimeProvider(
			Supplier<LocalDateTime> localDateTimeProvider) {
		this.localDateTimeProvider = localDateTimeProvider;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
