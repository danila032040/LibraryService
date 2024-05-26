package base.utils.converters;

public interface ConverterChainBuilder<TFrom, TTo> {
	public Converter<TFrom, TTo> buildConverter();
}
