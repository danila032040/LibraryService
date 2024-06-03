package base.converters;

public interface Converter<TFrom, TTo> {
	public TTo convert(TFrom from);
}
