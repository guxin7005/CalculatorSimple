package com.example.xin.calculatorsimple;
import java.util.*;

/**
 * Created by XL on 2016-02-09.
 */

/** Comment by Xin Gu 2016-02-10
 *
 */
public class InfixPostfixConverter {

    private Stack<Elements> opStack = new Stack<Elements>();
    private List<Elements> infixExpression = new ArrayList<Elements>();
    private List<Elements> postfixExpression = new ArrayList<Elements>();

    public InfixPostfixConverter(List<Elements> inputExpression){
        setInfixExpression(inputExpression);
    }

    public InfixPostfixConverter(){
    }

    public void setInfixExpression(List<Elements> inputExpression){
            infixExpression = inputExpression;
    }

    public List<Elements> getPostfixExpression(){
        infixToPostfix();
        return postfixExpression;
    }

    private final int level1 = 0;//+-: level 0
    private final int level2 = 1;//*/: level 1
    private final int level3 = 3;//() level 3    /Maybe we do not define a level 3.

    private int getOperatorLevel(String operator){
        char oper = operator.charAt(0);

        switch(oper){
            case '+':
            case '-':
                return level1;
            case '*':
            case '/':
            case '%':
                return level2;
            case '^':
                return level3;
        }

        if("+".equals(operator)||"-".equals(operator)){
            return level1;
        }
        if("*".equals(operator)||"/".equals(operator)){
            return level2;
        }
        if("(".equals(operator)||")".equals(operator)){
            return level3;
        }

        return -1;
    }

    private List<Elements> infixToPostfix(){
        return infixToPostfix(infixExpression);
    }

    private List<Elements> infixToPostfix(List<Elements> infixExpr){

        for(String A : infixExpr){
            if(isNumber(A))
                postfixExpression.add(A);
            else if(isOperator(A))
                opStack(A);
            else
                System.out.println("undefined operator!");
        }

        while(!opStack.empty()){
            postfixExpression.add(opStack.pop());
        }

        return postfixExpression;
    }

    private void opStack(Operator operator){
        //Push operator into the stack if it is empty
        if(opStack.empty()){
            opStack.push(operator);
            return;
        }

        //Push operator into the stack if it is a left bracket
        if("(".equals(operator)){
            opStack.push(operator);
            return;
        }

        //Pop all elements in the stack to the output if meet the right bracket
        if(")".equals(operator)){
            String tmp = "";
            while(!"(".equals(tmp=opStack.pop())){
                postfixExpression.add(tmp);
            }
            return;
        }

        //Push any operator into the stack if the previous operator is left bracket
        if("(".equals(opStack.peek())){
            opStack.push(operator);
            return;
        }

        if(comparePriority(operator, opStack.peek())){
            opStack.push(operator);
            return;
        }
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
        return operator.matches("[\\+\\-\\*\\/\\(\\)]");
    }

    private boolean comparePriority(String op1,String op2){
        return getOperatorLevel(op1) > getOperatorLevel(op2);
    }
}
