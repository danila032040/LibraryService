package base.utils;

import java.text.Format;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import base.utils.converters.Converter;
import base.utils.converters.ConverterChain;

public class MessageFormatSupportingAnyTemporalAccessor {
	private MessageFormat messageFormat;
	private Converter<Object, Object> objectToDateConverter;

	private MessageFormatSupportingAnyTemporalAccessor(
			MessageFormat messageFormat) {
		this.messageFormat = messageFormat;
		this.objectToDateConverter = ConverterChain
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

	public static MessageFormatSupportingAnyTemporalAccessor of(
			MessageFormat messageFormat) {
		return new MessageFormatSupportingAnyTemporalAccessor(messageFormat);
	}
	public static MessageFormatSupportingAnyTemporalAccessor of(
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
		return objectToDateConverter.convert(obj);
	}
}
