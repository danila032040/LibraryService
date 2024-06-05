package base.utils;

public class Pair<T0, T1> {
    public static <T0, T1> Pair<T0, T1> of(T0 left, T1 right) {
        return new Pair<>(left, right);
    }
    
    private T0 t0;
    
    private T1 t1;
    
    private Pair(T0 left, T1 right) {
        this.t0 = left;
        this.t1 = right;
        
    }
    
    public T0 getLeft() {
        return t0;
    }
    
    public T1 getRight() {
        return t1;
    }
}
