package base.utils.converters.chain;

import base.utils.converters.Converter;

public interface ConverterChainElement<TFrom, TTo>
		extends
			Converter<TFrom, TTo> {

	void setNext(ConverterChainElement<TFrom, TTo> converter);
	ConverterChainElement<TFrom, TTo> getNext();
}
