package com.example.xin.calculatorsimple;

/**
 * Created by XL on 2016-02-22.
 */
public class Number extends Operand {

    private double value;

    public Number(String str){
        super(str);
        value = Double.parseDouble(str);
    }

    @Override
    public Operand Operation(Operand other, Operator op) {
        return null;
    }

    @Override
    public Operand Operation(Operator op) {
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
