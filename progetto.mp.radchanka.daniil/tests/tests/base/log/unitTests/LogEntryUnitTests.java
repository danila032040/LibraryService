package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;
import java.util.Optional;

import org.junit.Test;

import base.log.LogEntry;
import base.log.LogLevelType;
import tests.base.log.mocks.CompiledMessageBuilderMock;

public class LogEntryUnitTests {
	@Test
	public void getCompiledMessage_ShouldUseSpecifiedCompiledMessageBuilderWithOriginalMessageAndArguments() {
		String originalMessage = "";
		Object[] arguments = new Object[]{};
		String expectedCompiledMessage = "Generated compiled message";
		CompiledMessageBuilderMock compiledMessageProvider = new CompiledMessageBuilderMock(
				(a, b) -> expectedCompiledMessage);
		LogEntry logEntry = new LogEntry(
				LogLevelType.Information,
				LocalDateTime.of(2024, 01, 01, 0, 0),
				Optional.empty(),
				originalMessage,
				arguments,
				compiledMessageProvider);

		String actualCompiledMessage = logEntry.getCompiledMessage();
		String passedOriginalMessage = compiledMessageProvider
				.getLastSpecifiedOriginalMessage();
		Object[] passedArguments = compiledMessageProvider
				.getLastSpecifiedArguments();

		assertThat(actualCompiledMessage).isSameAs(expectedCompiledMessage);
		assertThat(passedOriginalMessage).isSameAs(originalMessage);
		assertThat(passedArguments).isSameAs(arguments);
	}
}
