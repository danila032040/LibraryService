package base.utils;

public class Pair<T0, T1> {
	public static <T0, T1> Pair<T0, T1> of(T0 t0, T1 t1) {
		return new Pair<T0, T1>(t0, t1);
	}

	private T0 t0;

	private T1 t1;
	private Pair(T0 t0, T1 t1) {
		this.t0 = t0;
		this.t1 = t1;

	}

	public T0 getT0() {
		return t0;
	}

	public T1 getT1() {
		return t1;
	}
}
