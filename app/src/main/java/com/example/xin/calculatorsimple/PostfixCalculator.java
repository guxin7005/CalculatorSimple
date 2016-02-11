package ca.google.calculator;
import java.util.*;

/**
 * Created by XL on 2016-02-05.
 */
public class PostfixCalculator {

    //private String postfixExpression;
    private Stack<String> stack = new Stack<String>();

    public String evaluate(List<String> postfixExpression){
        /**
         * Evaluate postfix expression
         * @param List<String> postfixExpression
         * @return String result
         */

        for(String I:postfixExpression){
            if(isNumber(I)){
                stack.push(I);
            }
            else if(isOperator(I)){
                calculate(I);
            }
        }
        return stack.pop();
    }

    private void calculate(String operator){
        double d2 = Float.valueOf(stack.pop());
        double d1 = Float.valueOf(stack.pop());
        double res = 0;

        if("+".equals(operator)){res = plus(d1,d2);}
        else if("-".equals(operator)){res = minus(d1,d2);}
        else if("*".equals(operator)){res = multiply(d1,d2);}
        else if("/".equals(operator)){res = divide(d1,d2);}

        stack.push(Double.toString(res));
    }

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
