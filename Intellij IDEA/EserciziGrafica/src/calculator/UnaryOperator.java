package calculator;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public interface UnaryOperator<T> extends Operator<T> {

    public T calculate( T op1 ) throws Exception;

    public default T calculate( T... ops ) throws Exception{

        if( ops.length != 1 )
            throw new WrongNumberArgsException("Expected 1; found " + ops.length);
        return this.calculate(ops[0]);

    }

}
