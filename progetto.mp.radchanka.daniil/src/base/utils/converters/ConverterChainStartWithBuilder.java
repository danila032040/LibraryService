package base.utils.converters;

import java.util.function.Predicate;

public interface ConverterChainStartWithBuilder<TFrom, TTo> extends ConverterChainBuilder<TFrom, TTo>{
	ConverterChainContinueWithBuilder<TFrom, TTo> startWith(
			ConverterChainElement<TFrom, TTo> converter);

	ConverterChainContinueWithBuilder<TFrom, TTo> startWith(
			Predicate<TFrom> canHandlePredicate,
			Converter<TFrom, TTo> converter);
}
