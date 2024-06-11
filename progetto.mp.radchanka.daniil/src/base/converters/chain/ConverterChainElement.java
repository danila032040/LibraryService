package base.converters.chain;

import java.util.Objects;
import java.util.function.Predicate;

import base.converters.Converter;

public class ConverterChainElement<TFrom, TTo> implements Converter<TFrom, TTo> {
    
    public static <TFrom, TTo> ConverterChainElement<TFrom, TTo> from(Converter<TFrom, TTo> converter) {
        return new ConverterChainElement<>((from) -> true, converter);
    }
    
    public static <TFrom, TTo> ConverterChainElement<TFrom, TTo> from(
            Predicate<TFrom> canHandlePredicate,
            Converter<TFrom, TTo> converter) {
        return new ConverterChainElement<>(canHandlePredicate, converter);
    }
    
    private final Predicate<TFrom> canHandlePredicate;
    
    private final Converter<TFrom, TTo> converter;
    
    private ConverterChainElement<TFrom, TTo> nextConverterElement;
    
    private ConverterChainElement(Predicate<TFrom> canHandlePredicate, Converter<TFrom, TTo> converter) {
        this.canHandlePredicate = Objects.requireNonNull(canHandlePredicate);
        this.converter = Objects.requireNonNull(converter);
    }
    
    @Override
    public TTo convert(TFrom from) {
        if (!canHandlePredicate.test(from)) {
            return nextConverterElement.convert(from);
        }
        return converter.convert(from);
    }
    
    public ConverterChainElement<TFrom, TTo> getNext() {
        return this.nextConverterElement;
    }
    
    public void setNext(ConverterChainElement<TFrom, TTo> converterChainElement) {
        this.nextConverterElement = Objects.requireNonNull(converterChainElement);
    }
}
