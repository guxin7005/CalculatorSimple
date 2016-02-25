package com.example.xin.calculatorsimple;
import java.util.*;

/**
 * Created by XL on 2016-02-05.
 */
public class PostfixCalculator {

    //private String postfixExpression;
    private Stack<Element> stack = new Stack<Element>();

    public String evaluate(List<Element> postfixExpression){
        /**
         * Evaluate postfix expression
         * @param List<String> postfixExpression
         * @return String result
         */

        System.out.println("Input postfix exp:" + postfixExpression);

        for(Element I:postfixExpression){
            if(I instanceof Operand){
                System.out.println("push Operand " + I + " into the stack");
                stack.push(I);
            }
            else if(I instanceof Operator){
                System.out.println("meet Operator " + I);
                calculate((Operator)I);
            }
        }
        return stack.pop().toString();
    }

    private void calculate(Operator operator){
        Operand d2 = (Operand)stack.pop();
        Operand d1 = (Operand)stack.pop();

        System.out.println("d1= " + d1);
        System.out.println("d2= " + d2);
        System.out.println("d1= " + d2);

        Operand res = d1.Operation(d2, operator);

        System.out.println("res= " + res);

        stack.push(res);
    }


    //Deprecated functions
    private boolean isNumber(String num){
        /**
         * Check the input string is a number
         * @param String
         * @return true: is a number, false: is not a number
         */
        return num.matches("\\d+");
    }

    private boolean isOperator(String operator){
        /**
         * Check the input string is a operator
         * @param String
         * @return true: is a operator, false: is not a operator
         */
        return operator.matches("[\\+\\-\\*\\/]");
    }


}
