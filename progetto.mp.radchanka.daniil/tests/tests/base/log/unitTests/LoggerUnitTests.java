package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import base.log.LogLevelType;
import tests.base.log.mocks.LoggerMock;

public class LoggerUnitTests {

	@Test
	public void logInformation_ShouldSpecifyInformationLogLevelType() {
		LoggerMock logger = new LoggerMock();
		LogLevelType expected = LogLevelType.Information;

		logger.logInformation(null);
		LogLevelType actual = logger.getLastSpecifiedLogLevel();

		assertThat(actual).isEqualTo(expected);
	}
	@Test
	public void logDebug_ShouldSpecifyDebugLogLevelType() {
		LoggerMock logger = new LoggerMock();
		LogLevelType expected = LogLevelType.Debug;

		logger.logDebug(null);
		LogLevelType actual = logger.getLastSpecifiedLogLevel();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void logWarning_ShouldSpecifyWarningLogLevelType() {
		LoggerMock logger = new LoggerMock();
		LogLevelType expected = LogLevelType.Warning;

		logger.logWarning(null);
		LogLevelType actual = logger.getLastSpecifiedLogLevel();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void logError_ShouldSpecifyErrorLogLevelType() {
		LoggerMock logger = new LoggerMock();
		LogLevelType expected = LogLevelType.Error;

		logger.logError(null);
		LogLevelType actual = logger.getLastSpecifiedLogLevel();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void logFatal_ShouldSpecifyFatalLogLevelType() {
		LoggerMock logger = new LoggerMock();
		LogLevelType expected = LogLevelType.Fatal;

		logger.logFatal(null);
		LogLevelType actual = logger.getLastSpecifiedLogLevel();

		assertThat(actual).isEqualTo(expected);
	}
}
