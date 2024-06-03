package base.converters.chain;

import base.converters.Converter;

public interface ConverterChainBuilder<TFrom, TTo> {
	public Converter<TFrom, TTo> buildConverter();
}
