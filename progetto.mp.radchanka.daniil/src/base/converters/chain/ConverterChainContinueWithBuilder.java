package base.converters.chain;

import java.util.function.Predicate;

import base.converters.Converter;

public interface ConverterChainContinueWithBuilder<TFrom, TTo> extends ConverterChainBuilder<TFrom, TTo> {
    public ConverterChainContinueWithBuilder<TFrom, TTo> continueWith(ConverterChainElement<TFrom, TTo> converter);
    
    public ConverterChainContinueWithBuilder<TFrom, TTo> continueWith(
            Predicate<TFrom> canHandlePredicate,
            Converter<TFrom, TTo> converter);
}
