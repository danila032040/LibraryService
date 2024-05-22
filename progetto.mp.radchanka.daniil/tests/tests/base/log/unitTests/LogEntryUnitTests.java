package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;

import base.log.LogEntry;
import base.log.LogLevelType;

public class LogEntryUnitTests {
	@Test
	public void getCompiledMessage_ShouldApplyMessageFormatToTheOriginalMessageUsingSpecifiedArguments() {
		String originalMessage = "{0}, {1}, {2, number, #.##}, {3,date, short}";
		Object[] arguments = new Object[] { 5, "String Test", 5.12345, new Date(2024, 01, 01) };
		LogEntry logEntry = new LogEntry(LogLevelType.Information, "TimeStampAsString", Optional.empty(),
				originalMessage, arguments);
		String expected = "5, String test, 5,12, "
				+ MessageFormat.format("{0, date, short}", new Date(2024, 01, 01));

		String actual = logEntry.getCompiledMessage();

		assertThat(actual).isEqualTo(expected);
	}
}
