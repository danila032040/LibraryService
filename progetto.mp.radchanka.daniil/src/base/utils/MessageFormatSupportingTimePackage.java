package base.utils;

import java.text.Format;
import java.text.MessageFormat;
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
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import base.utils.converters.Converter;
import base.utils.converters.chain.ConverterChain;

public class MessageFormatSupportingTimePackage extends Formatter {
	private MessageFormat messageFormat;
	private Converter<Object, Object> objectToDateIfItIsFromTimePackageConverter;

	private MessageFormatSupportingTimePackage(MessageFormat messageFormat) {
		this.messageFormat = messageFormat;
		this.objectToDateIfItIsFromTimePackageConverter = ConverterChain
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
						ZonedDateTime.class::isInstance,
						(obj) -> Date.from(((ZonedDateTime) obj).toInstant()))
				.continueWith(
						OffsetDateTime.class::isInstance,
						(obj) -> Date.from(((OffsetDateTime) obj).toInstant()))
				.continueWith(
						TemporalAccessor.class::isInstance,
						(obj) -> Date
								.from(Instant.from((TemporalAccessor) obj)))
				.buildConverter();
	}

	public static MessageFormatSupportingTimePackage of(
			MessageFormat messageFormat) {
		return new MessageFormatSupportingTimePackage(messageFormat);
	}

	public static MessageFormatSupportingTimePackage of(
			String pattern,
			Locale locale) {
		return of(new MessageFormat(pattern, locale));
	}

	public String format(Object... arguments) {
		return messageFormat
				.format(
						Arrays
								.stream(arguments)
								.map(this::convertToDateIfItIsFromTimePackage)
								.toArray());
	}

	public Format[] getFormats() {
		return messageFormat.getFormats();
	}

	private Object convertToDateIfItIsFromTimePackage(Object obj) {
		return objectToDateIfItIsFromTimePackageConverter.convert(obj);
	}

	@Override
	public String format(LogRecord record) {
		return messageFormat.format(record);
	}
}
