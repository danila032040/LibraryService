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

import base.utils.converters.Converter;
import base.utils.converters.chain.ConverterChain;

public class MessageFormatSupportingTimePackage extends Format {
	/**
	 * 
	 */
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

	public String format(Object... arguments) {
		Format[] formats = messageFormat.getFormats();
		for (int i = 0; i < formats.length; ++i) {
			if (formats[i] instanceof SimpleDateFormat) {
				SimpleDateFormat simpleDateFormat = (SimpleDateFormat) formats[i];
				if (arguments[i] instanceof ZonedDateTime) {
					ZonedDateTime zonedDateTime = (ZonedDateTime) arguments[i];
					simpleDateFormat
							.setTimeZone(
									TimeZone
											.getTimeZone(
													zonedDateTime.getZone()));
				}
				if (arguments[i] instanceof OffsetDateTime) {
					OffsetDateTime offsetDateTime = (OffsetDateTime) arguments[i];
					simpleDateFormat
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
		}
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

	private Object convertToDateIfItIsFromTimePackage(Object obj) {
		return objectToDateIfItIsFromTimePackageOtherwiseToTheSameObjectConverter
				.convert(obj);
	}
}
