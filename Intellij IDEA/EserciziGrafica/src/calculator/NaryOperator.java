package calculator;

public interface NaryOperator<T> extends Operator<T> {

    public T calculate( T... ops ) throws Exception;

}
