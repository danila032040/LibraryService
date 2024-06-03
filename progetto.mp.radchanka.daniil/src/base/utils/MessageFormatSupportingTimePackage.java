package base.utils;

import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.IntStream;

import base.converters.Converter;
import base.converters.chain.ConverterChain;

public class MessageFormatSupportingTimePackage extends Format {
	private static final long serialVersionUID = 1L;
	public static MessageFormatSupportingTimePackage of(
			MessageFormat messageFormat) {
		return new MessageFormatSupportingTimePackage(messageFormat);
	}
	public static MessageFormatSupportingTimePackage of(
			String pattern,
			Locale locale) {
		return of(new MessageFormat(pattern, locale));
	}

	private MessageFormat messageFormat;

	private Converter<Object, Object> objectToDateIfItIsFromTimePackageOtherwiseToTheSameObjectConverter;

	private MessageFormatSupportingTimePackage(MessageFormat messageFormat) {
		this.messageFormat = messageFormat;
		this.objectToDateIfItIsFromTimePackageOtherwiseToTheSameObjectConverter = ConverterChain
				.withBaseConverter(obj -> obj)
				.startWith(
						LocalDate.class::isInstance,
						(obj) -> Date
								.from(
										((LocalDate) obj)
												.atStartOfDay(
														ZoneId.systemDefault())
												.toInstant()))
				.continueWith(
						LocalTime.class::isInstance,
						(obj) -> Date
								.from(
										((LocalTime) obj)
												.atDate(LocalDate.EPOCH)
												.atZone(ZoneId.systemDefault())
												.toInstant()))
				.continueWith(
						LocalDateTime.class::isInstance,
						(obj) -> Date
								.from(
										((LocalDateTime) obj)
												.atZone(ZoneId.systemDefault())
												.toInstant()))
				.continueWith(
						TemporalAccessor.class::isInstance,
						(obj) -> Date
								.from(Instant.from((TemporalAccessor) obj)))
				.buildConverter();
	}

	public String format(Object... arguments) {
		adjustTimeZoneOfFormatsUsingArguments(arguments);
		return messageFormat
				.format(
						Arrays
								.stream(arguments)
								.map(this::convertToDateIfItIsFromTimePackage)
								.toArray());
	}
	@Override
	public StringBuffer format(
			Object obj,
			StringBuffer toAppendTo,
			FieldPosition pos) {
		return messageFormat.format(obj, toAppendTo, pos);
	}

	public Format[] getFormats() {
		return messageFormat.getFormats();
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		return messageFormat.parseObject(source, pos);
	}

	private void adjustTimeZoneIfArgumentContainsTimeZone(
			SimpleDateFormat simpleDateTimeFormat,
			Object argument) {
		if (argument instanceof ZonedDateTime) {
			ZonedDateTime zonedDateTime = (ZonedDateTime) argument;
			simpleDateTimeFormat
					.setTimeZone(TimeZone.getTimeZone(zonedDateTime.getZone()));
		}
		if (argument instanceof OffsetDateTime) {
			OffsetDateTime offsetDateTime = (OffsetDateTime) argument;
			simpleDateTimeFormat
					.setTimeZone(
							TimeZone
									.getTimeZone(
											ZoneId
													.of(
															offsetDateTime
																	.getOffset()
																	.getId())));
		}
	}

	private void adjustTimeZoneOfFormatsUsingArguments(Object... arguments) {
		Format[] formats = messageFormat.getFormats();
		IntStream
				.range(0, formats.length)
				.filter(index -> formats[index] instanceof SimpleDateFormat)
				.boxed()
				.map(
						index -> Pair
								.of(
										(SimpleDateFormat) formats[index],
										arguments[index]))
				.forEach(
						pair -> adjustTimeZoneIfArgumentContainsTimeZone(
								pair.getT0(),
								pair.getT1()));
	}

	private Object convertToDateIfItIsFromTimePackage(Object obj) {
		return objectToDateIfItIsFromTimePackageOtherwiseToTheSameObjectConverter
				.convert(obj);
	}
}
