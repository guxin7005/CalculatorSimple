package com.example.xin.calculatorsimple;

/**
 * Created by XL on 2016-02-22.
 */
public class Number extends Operand {

    private double value;

    public Number(String str){
        super(str);

        super.isOperator = false;

        value = Double.parseDouble(str);
    }

    public Number(double val){

        super(String.valueOf(val));

        super.isOperator = false;

        value = val;
    }

    public double getValue(){
        return value;
    }

    @Override
    public Operand Operation(Operand other, Operator op) {

        System.out.println("val= " + value);
        System.out.println("other= " + other);
        System.out.println("op= " + op);

        double res = 0.0;

        if (op.isLeft_assoiative()) {
            System.out.println(op.getSymbol());
            System.out.println("+".charAt(0) == op.getSymbol());
            if ("+".charAt(0) == op.getSymbol()) {
                res = plus(value, ((Number) other).getValue());
            } else if ("-".charAt(0) == op.getSymbol()) {
                res = minus(value, ((Number) other).getValue());
            } else if ("*".charAt(0) == op.getSymbol()) {
                res = multiply(value, ((Number) other).getValue());
            } else if ("/".charAt(0) == op.getSymbol()) {
                res = divide(value, ((Number) other).getValue());
            }

        }
        else {

            if ("^".charAt(0) == op.getSymbol()) {}

        }

        return new Number(res);
    }

    @Override
    public Operand Operation(Operator op) {
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


    private static double plus(double a, double b){
        return a + b;
    }

    private static double minus(double a, double b){
        return a - b;
    }

    private static double multiply(double a, double b){
        return a * b;
    }

    private static double divide(double a, double b){
        return a / b;
    }

}
