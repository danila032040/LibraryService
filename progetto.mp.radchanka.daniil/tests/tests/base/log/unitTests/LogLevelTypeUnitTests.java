package tests.base.log.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import base.log.LogLevelType;

public class LogLevelTypeUnitTests {
	@Test
	public void toShortString_WhenInformationLogLevelTypeIsUsed_ShouldBeINF() {
		String expected = "INF";

		String actual = LogLevelType.Information.toShortString();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void toShortString_WhenDebugLogLevelTypeIsUsed_ShouldBeDBG() {
		String expected = "DBG";

		String actual = LogLevelType.Debug.toShortString();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void toShortString_WhenWarningLogLevelTypeIsUsed_ShouldBeWRN() {
		String expected = "WARN";

		String actual = LogLevelType.Warning.toShortString();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void toShortString_WhenErrorLogLevelTypeIsUsed_ShouldBeERR() {
		String expected = "ERR";

		String actual = LogLevelType.Error.toShortString();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void toShortString_WhenFatalLogLevelTypeIsUsed_ShouldBeFTL() {
		String expected = "FTL";

		String actual = LogLevelType.Fatal.toShortString();

		assertThat(actual).isEqualTo(expected);
	}
}
