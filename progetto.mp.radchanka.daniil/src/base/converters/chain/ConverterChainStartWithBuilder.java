package base.converters.chain;

import java.util.function.Predicate;

import base.converters.Converter;

public interface ConverterChainStartWithBuilder<TFrom, TTo> extends ConverterChainBuilder<TFrom, TTo> {
    ConverterChainContinueWithBuilder<TFrom, TTo> startWith(ConverterChainElement<TFrom, TTo> converter);
    
    ConverterChainContinueWithBuilder<TFrom, TTo> startWith(
            Predicate<TFrom> canHandlePredicate,
            Converter<TFrom, TTo> converter);
}
