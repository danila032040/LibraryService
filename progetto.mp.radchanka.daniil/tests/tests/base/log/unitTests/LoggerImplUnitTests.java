package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Locale;

import org.junit.Test;

import base.log.GlobalLogConfiguration;
import base.log.LogEntry;
import base.log.LogLevelType;
import base.log.Logger;
import base.log.LoggerImpl;
import tests.base.log.mocks.LogEntryPublisherMock;

public class LoggerImplUnitTests {

	@Test
	public void log_ShouldPublishCorrectLogEntry() {
		LocalDateTime expectedDateTime = LocalDateTime.of(2024, 1, 1, 1, 0, 0);
		String expectedOriginalMessage = "Test {0,number}";
		Object[] expectedArguments = new Object[]{2};
		String expectedCompiledMessage = "Test 2";
		LogLevelType expectedLogLevelType = LogLevelType.Information;
		GlobalLogConfiguration logConfiguration = new GlobalLogConfiguration();
		LogEntryPublisherMock logEntryPublisher = new LogEntryPublisherMock();
		logConfiguration.setMinimalLogLevel(LogLevelType.Information);
		logConfiguration.setLocalDateTimeProvider(() -> expectedDateTime);
		logConfiguration.setLocale(Locale.US);
		Logger loggerToTest = new LoggerImpl(
				logConfiguration,
				logEntryPublisher);
		loggerToTest
				.log(
						expectedLogLevelType,
						expectedOriginalMessage,
						expectedArguments);

		LogEntry actual = logEntryPublisher.getLastPublishedLogEntry();

		assertThat(actual.getLogLevel()).isEqualTo(expectedLogLevelType);
		assertThat(actual.getDateTime()).isEqualTo(expectedDateTime);
		assertThat(actual.getOriginalMessage())
				.isSameAs(expectedOriginalMessage);
		assertThat(actual.getArguments()).isSameAs(expectedArguments);
		assertThat(actual.getCompiledMessage())
				.isEqualTo(expectedCompiledMessage);
	}

	@Test
	public void log_WithLogLevelTypeLowerThanMinimumLogLevelType_ShouldNotPublishAnyLogEntry() {
		GlobalLogConfiguration logConfiguration = new GlobalLogConfiguration();
		LogEntryPublisherMock logEntryPublisher = new LogEntryPublisherMock();
		logConfiguration.setMinimalLogLevel(LogLevelType.Fatal);
		Logger loggerToTest = new LoggerImpl(
				logConfiguration,
				logEntryPublisher);
		loggerToTest.logInformation("");

		LogEntry actual = logEntryPublisher.getLastPublishedLogEntry();

		assertThat(actual).isNull();
	}
}
