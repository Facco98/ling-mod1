package calculator;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public enum Operation{

    ADD("Plus", ( a, b ) -> a+b ) ,
    SUB("Minus", ( a, b ) -> a-b),
    MUL("Multiply", ( a, b ) -> a*b),
    DIV("Divide", ( a, b )  ->{

        if( b == 0 )
            throw new DividingByZeroException();
        return a/b;

    }),
    LOG("Log", ( a ) -> Math.log(a)),
    LOGB("Log_b", ( a,b ) ->Math.log(a)/Math.log(b) ),
    LOG10("Log_10", ( a ) -> Math.log(a)/Math.log(10)),
    TRIPLE_SUM("Triple SUm", 3, (a) -> a[0] + a[1] + a[2]);

    private String toPrint;
    private Operator<Double> operator;
    private int nOperands;

    Operation( String toPrint, BinaryOperator<Double> operator ){

        this.toPrint = toPrint;
        this.operator = operator;
        this.nOperands = 2;

    }

    Operation( String toPrint, UnaryOperator<Double> operator ){

        this.operator = operator;
        this.toPrint = toPrint;
        this.nOperands = 1;
    }

    Operation( String toPrint, int n, NaryOperator<Double> operator){

        this.toPrint = toPrint;
        this.nOperands = n;
        this.operator = operator;

    }

    public Double calculate(Double... ops) throws Exception {

        return this.operator.calculate(ops);

    }

    public String toString(){

        return this.toPrint;

    }

    public int getnOperands(){

        return this.nOperands;

    }

    public static int getMaxNOperands(){

        int n = 0;
        for( Operation i : Operation.values() ){

            if( i.getnOperands() > n )
                n = i.getnOperands();

        }
        return n;

    }
}
