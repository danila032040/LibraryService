package base.utils.converters.chain;

import base.utils.converters.Converter;

public interface ConverterChainBuilder<TFrom, TTo> {
	public Converter<TFrom, TTo> buildConverter();
}
