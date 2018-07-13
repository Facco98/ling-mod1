package calculator;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public interface BinaryOperator<T> extends Operator<T>{

    public T calculate( T op1, T op2 ) throws Exception;

    public default T calculate( T... ops ) throws Exception{

        if( ops.length != 2 )
            throw new WrongNumberArgsException("Expected 2; found: "+ops.length);
        return this.calculate(ops[0], ops[1]);

    }


}
