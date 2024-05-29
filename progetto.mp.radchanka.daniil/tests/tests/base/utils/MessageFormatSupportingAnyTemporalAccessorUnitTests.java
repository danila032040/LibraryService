package tests.base.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import base.utils.MessageFormatSupportingTimePackage;

public class MessageFormatSupportingAnyTemporalAccessorUnitTests {
	private static String getTimezoneOffset(TimeZone tz) {
		Calendar cal = GregorianCalendar.getInstance(tz);
		int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

		String offset = String
				.format(
						"%02d%02d",
						Math.abs(offsetInMillis / 3600000),
						Math.abs((offsetInMillis / 60000) % 60));
		offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

		return offset;
	}

	@Test
	public void format_WhenUsingDate_ShouldFormatCorrectly() {
		String pattern = "{0,date,yyyy-MM-dd HH:mm:ssZ}";
		GregorianCalendar calendar = new GregorianCalendar(
				2024,
				Calendar.MAY,
				01,
				1,
				0);
		calendar
				.setTimeZone(
						TimeZone.getTimeZone(ZoneId.of("America/New_York")));
		Date date = calendar.getTime();
		Object[] arguments = new Object[]{date};
		String expected = "2024-05-01 01:00:00-0400";
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);
		((SimpleDateFormat) messageFormatToTest.getFormats()[0])
				.setTimeZone(
						TimeZone.getTimeZone(ZoneId.of("America/New_York")));

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void format_WhenUsingInstant_ShouldFormatCorrectly() {
		String pattern = "{0,date,yyyy-MM-dd HH:mm:ssZ}";
		GregorianCalendar calendar = new GregorianCalendar(
				2024,
				Calendar.MAY,
				01,
				1,
				0);
		calendar
				.setTimeZone(
						TimeZone.getTimeZone(ZoneId.of("America/New_York")));
		Instant instant = calendar.getTime().toInstant();
		Object[] arguments = new Object[]{instant};
		String expected = "2024-05-01 01:00:00-0400";
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);
		((SimpleDateFormat) messageFormatToTest.getFormats()[0])
				.setTimeZone(
						TimeZone.getTimeZone(ZoneId.of("America/New_York")));

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void format_WhenUsingLocalDate_ShouldFormatCorrectly() {
		String pattern = "{0,date,yyyy-MM-dd}";
		LocalDate date = LocalDate.of(2024, 5, 01);
		Object[] arguments = new Object[]{date};
		String expected = "2024-05-01";
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void format_WhenUsingLocalDateTime_ShouldFormatCorrectly() {
		String pattern = "{0,date,yyyy-MM-dd HH:mm:ssZ}";
		LocalDateTime date = LocalDateTime.of(2024, 05, 01, 1, 0, 0);
		Object[] arguments = new Object[]{date};
		String expected = "2024-05-01 01:00:00"
				+ getTimezoneOffset(TimeZone.getDefault());
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void format_WhenUsingLocalTime_ShouldFormatCorrectly() {
		String pattern = "{0,time,HH:mm:ss}";
		LocalTime date = LocalTime.of(1, 0);
		Object[] arguments = new Object[]{date};
		String expected = "01:00:00";
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void format_WhenUsingOffsetDateTime_ShouldFormatCorrectly() {
		String pattern = "{0,date,yyyy-MM-dd HH:mm:ssZ}";
		OffsetDateTime date = OffsetDateTime
				.of(2024, 5, 1, 1, 0, 0, 0, ZoneOffset.ofHours(5));
		Object[] arguments = new Object[]{date};
		String expected = "2024-05-01 01:00:00+0500";
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);
		((SimpleDateFormat) messageFormatToTest.getFormats()[0])
				.setTimeZone(
						TimeZone
								.getTimeZone(
										ZoneId
												.of(
														ZoneOffset
																.ofHours(5)
																.getId())));

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void format_WhenUsingZonedDateTime_ShouldFormatCorrectly() {
		String pattern = "{0,date,yyyy-MM-dd HH:mm:ssZ}";
		ZonedDateTime date = ZonedDateTime
				.of(2024, 5, 1, 1, 0, 0, 0, ZoneId.of("America/New_York"));
		Object[] arguments = new Object[]{date};
		String expected = "2024-05-01 01:00:00-0400";
		Locale locale = Locale.getDefault();
		MessageFormatSupportingTimePackage messageFormatToTest = MessageFormatSupportingTimePackage
				.of(pattern, locale);
		((SimpleDateFormat) messageFormatToTest.getFormats()[0])
				.setTimeZone(
						TimeZone.getTimeZone(ZoneId.of("America/New_York")));

		String actual = messageFormatToTest.format(arguments);

		assertThat(actual).isEqualTo(expected);
	}
}
