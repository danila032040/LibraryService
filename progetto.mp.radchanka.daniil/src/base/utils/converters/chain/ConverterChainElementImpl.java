package base.utils.converters.chain;

import java.util.Objects;
import java.util.function.Predicate;

import base.utils.converters.Converter;

public class ConverterChainElementImpl<TFrom, TTo>
		implements
			Converter<TFrom, TTo>,
			ConverterChainElement<TFrom, TTo> {

	private Predicate<TFrom> canHandlePredicate;
	private Converter<TFrom, TTo> converter;
	private ConverterChainElement<TFrom, TTo> nextConverter;

	private ConverterChainElementImpl(Predicate<TFrom> canHandlePredicate,
			Converter<TFrom, TTo> converter) {
		this.canHandlePredicate = Objects.requireNonNull(canHandlePredicate);
		this.converter = Objects.requireNonNull(converter);
	}

	public static <TFrom, TTo> ConverterChainElementImpl<TFrom, TTo> from(
			Predicate<TFrom> canHandlePredicate,
			Converter<TFrom, TTo> converter) {
		return new ConverterChainElementImpl<TFrom, TTo>(
				canHandlePredicate,
				converter);
	}

	public static <TFrom, TTo> ConverterChainElementImpl<TFrom, TTo> from(
			Converter<TFrom, TTo> converter) {
		return new ConverterChainElementImpl<TFrom, TTo>(
				(from) -> true,
				converter);
	}

	@Override
	public TTo convert(TFrom from) {
		if (!canHandlePredicate.test(from))
			return nextConverter.convert(from);
		return converter.convert(from);
	}

	@Override
	public void setNext(ConverterChainElement<TFrom, TTo> converter) {
		this.nextConverter = Objects.requireNonNull(converter);
	}

	@Override
	public ConverterChainElement<TFrom, TTo> getNext() {
		return this.nextConverter;
	}
}
