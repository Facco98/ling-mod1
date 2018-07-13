package calculator;

public interface Operator<T> {

    public T calculate( T... ops ) throws Exception;

}
