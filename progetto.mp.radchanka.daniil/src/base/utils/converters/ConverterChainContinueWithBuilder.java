package base.utils.converters;

import java.util.function.Predicate;

public interface ConverterChainContinueWithBuilder<TFrom, TTo>
		extends
			ConverterChainBuilder<TFrom, TTo> {
	public ConverterChainContinueWithBuilder<TFrom, TTo> continueWith(
			ConverterChainElement<TFrom, TTo> converter);

	public ConverterChainContinueWithBuilder<TFrom, TTo> continueWith(
			Predicate<TFrom> canHandlePredicate,
			Converter<TFrom, TTo> converter);
}
