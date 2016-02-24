package com.example.xin.calculatorsimple;

/**
 * Created by xiaoqian-niu on 2016-02-16.
 */
public abstract class Operand extends Element {
    private int dimensions;
    private String symbol;

    public Operand(String str){
    }

    public abstract Operand Operation(Operand other, Operator op);

    public abstract Operand Operation(Operator op);

    public abstract String toString();
}
