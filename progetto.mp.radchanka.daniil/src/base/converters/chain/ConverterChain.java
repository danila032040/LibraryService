package base.converters.chain;

import java.util.Optional;
import java.util.function.Predicate;

import base.converters.Converter;

public class ConverterChain<TFrom, TTo> implements Converter<TFrom, TTo> {
    public static <TFrom, TTo> ConverterChain<TFrom, TTo> forConverter(Converter<TFrom, TTo> converter) {
        return new ConverterChain<>(converter);
    }
    
    private ConverterChainElement<TFrom, TTo> firstChainConverterElement;
    
    private Optional<ConverterChainElement<TFrom, TTo>> lastAddedChainConverterElement;
    
    private ConverterChain(Converter<TFrom, TTo> baseConverter) {
        this.firstChainConverterElement = ConverterChainElement.from(baseConverter);
        this.lastAddedChainConverterElement = Optional.empty();
    }
    
    public ConverterChain<TFrom, TTo> addConverterChainElement(ConverterChainElement<TFrom, TTo> converter) {
        if (this.lastAddedChainConverterElement.isEmpty())
            return startWith(converter);
        converter.setNext(this.lastAddedChainConverterElement.orElseThrow().getNext());
        this.lastAddedChainConverterElement.orElseThrow().setNext(converter);
        this.lastAddedChainConverterElement = Optional.of(converter);
        return this;
    }
    
    public ConverterChain<TFrom, TTo> addConverterChainElement(
            Predicate<TFrom> canHandlePredicate,
            Converter<TFrom, TTo> converter) {
        return this.addConverterChainElement(ConverterChainElement.from(canHandlePredicate, converter));
    }
    
    @Override
    public TTo convert(TFrom from) {
        return firstChainConverterElement.convert(from);
    }
    
    private ConverterChain<TFrom, TTo> startWith(ConverterChainElement<TFrom, TTo> converter) {
        ConverterChainElement<TFrom, TTo> next = this.firstChainConverterElement;
        this.lastAddedChainConverterElement = Optional.of(converter);
        this.firstChainConverterElement = converter;
        converter.setNext(next);
        return this;
    }
}
