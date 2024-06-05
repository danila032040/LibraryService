package base.utils;

public interface Mapper<TFrom, TTo> {
    public TTo map(TFrom from);
}
