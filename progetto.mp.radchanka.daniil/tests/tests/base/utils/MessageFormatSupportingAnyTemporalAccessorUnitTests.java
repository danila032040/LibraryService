package tests.base.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import base.utils.MessageFormatSupportingAnyTemporalAccessor;

public class MessageFormatSupportingAnyTemporalAccessorUnitTests {
	@Test
	public void format_ShouldFormatCorrectly() {
		String pattern = "{0}, {1}, {2,number,#.##}, {3,date,yyyy-MM-dd}, {4,date,yyyy-MM-dd HH:mm:ss}, {5,date,yyyy-MM-dd HH:mm:ssZ}, {6,date,yyyy-MM-dd HH:mm:ss}, {7,date,yyyy-MM-dd HH:mm:ssZ}";
		Object[] arguments = new Object[]{1, "Test", 3.54123,
				LocalDate.of(2024, 05, 01),
				LocalDateTime.of(2024, 05, 01, 1, 0),
				ZonedDateTime
						.of(
								2024,
								05,
								01,
								7,
								0,
								0,
								0,
								ZoneId.of("America/New_York")),
				new GregorianCalendar(2024, Calendar.MAY, 01, 1, 0)
						.getTime()
						.toInstant(),
				OffsetDateTime
						.of(2024, 05, 01, 7, 0, 0, 0, ZoneOffset.ofHours(5))};
		MessageFormatSupportingAnyTemporalAccessor messageFormatToTest = MessageFormatSupportingAnyTemporalAccessor
				.of(pattern, Locale.US);
		((SimpleDateFormat) messageFormatToTest.getFormats()[5])
				.setTimeZone(
						TimeZone.getTimeZone(ZoneId.of("America/New_York")));
		((SimpleDateFormat) messageFormatToTest.getFormats()[7])
				.setTimeZone(
						TimeZone
								.getTimeZone(
										ZoneId
												.of(
														ZoneOffset
																.ofHours(5)
																.getId())));
		String expected = "1, Test, 3.54, 2024-05-01, 2024-05-01 01:00:00, 2024-05-01 07:00:00-0400, 2024-05-01 01:00:00, 2024-05-01 07:00:00+0500";

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}
}
