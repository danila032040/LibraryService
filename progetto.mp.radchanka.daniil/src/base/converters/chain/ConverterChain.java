package base.converters.chain;

import java.util.function.Predicate;

import base.converters.Converter;

public class ConverterChain<TFrom, TTo>
        implements
        Converter<TFrom, TTo>,
        ConverterChainBuilder<TFrom, TTo>,
        ConverterChainStartWithBuilder<TFrom, TTo>,
        ConverterChainContinueWithBuilder<TFrom, TTo> {
    public static <TFrom, TTo> ConverterChainStartWithBuilder<TFrom, TTo> withBaseConverter(
            Converter<TFrom, TTo> converter) {
        return new ConverterChain<>(converter);
    }
    
    private ConverterChainElement<TFrom, TTo> firstChainConverterElement;
    
    private ConverterChainElement<TFrom, TTo> lastAddedChainConverterElement;
    
    private ConverterChain(Converter<TFrom, TTo> baseConverter) {
        this.firstChainConverterElement = ConverterChainElementImpl.from(baseConverter);
        this.lastAddedChainConverterElement = this.firstChainConverterElement;
    }
    
    @Override
    public Converter<TFrom, TTo> buildConverter() {
        return this;
    }
    
    @Override
    public ConverterChainContinueWithBuilder<TFrom, TTo> continueWith(ConverterChainElement<TFrom, TTo> converter) {
        converter.setNext(this.lastAddedChainConverterElement.getNext());
        this.lastAddedChainConverterElement.setNext(converter);
        this.lastAddedChainConverterElement = converter;
        return this;
    }
    
    @Override
    public ConverterChainContinueWithBuilder<TFrom, TTo> continueWith(
            Predicate<TFrom> canHandlePredicate,
            Converter<TFrom, TTo> converter) {
        return this.continueWith(ConverterChainElementImpl.from(canHandlePredicate, converter));
    }
    
    @Override
    public TTo convert(TFrom from) {
        return firstChainConverterElement.convert(from);
    }
    
    @Override
    public ConverterChainContinueWithBuilder<TFrom, TTo> startWith(ConverterChainElement<TFrom, TTo> converter) {
        ConverterChainElement<TFrom, TTo> next = this.lastAddedChainConverterElement;
        this.lastAddedChainConverterElement = converter;
        this.firstChainConverterElement = converter;
        converter.setNext(next);
        return this;
    }
    
    @Override
    public ConverterChainContinueWithBuilder<TFrom, TTo> startWith(
            Predicate<TFrom> canHandlePredicate,
            Converter<TFrom, TTo> converter) {
        return this.startWith(ConverterChainElementImpl.from(canHandlePredicate, converter));
    }
}
