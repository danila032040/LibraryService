package base.converters.chain;

import base.converters.Converter;

public interface ConverterChainElement<TFrom, TTo>
		extends
			Converter<TFrom, TTo> {

	void setNext(ConverterChainElement<TFrom, TTo> converter);
	ConverterChainElement<TFrom, TTo> getNext();
}
