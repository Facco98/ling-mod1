package calculator;

public abstract class Calculator {

    public static Double dispatch( int opCode, double op1, double op2 ) throws DividingByZeroException {

        Double res = null;

        switch(opCode){

            case 0:
                res = add(op1, op2);
                break;
            case 1:
                res = minus(op1, op2);
                break;
            case 2:
                res = mul(op1, op2);
                break;
            case 3:
                res = div(op1, op2);
                break;
        }
        return res;

    }

    public static Double add( double op1, double op2){

        return op1 + op2;

    }

    public static Double minus( double op1, double op2 ){

        return op1 - op2;

    }

    public static Double mul( double op1, double op2 ){

        return op1*op2;

    }

    public static Double div( double op1, double op2 ) throws DividingByZeroException {

        if( op2 == 0 )
            throw new DividingByZeroException();
        return op1/op2;

    }

}
