
package com.example.xin.calculatorsimple;

import java.util.Stack;

import java.util.*;

/**
 * Created by XL on 2016-02-09.
 */

/** Comment by Xin Gu 2016-02-10
 *
 */
public class InfixPostfixConverter {

    private Stack<Operator> opStack = new Stack<Operator>();
    private List<Element> infixExpression = new ArrayList<Element>();
    private List<Element> postfixExpression = new ArrayList<Element>();

    public InfixPostfixConverter(List<Element> inputExpression){
        setInfixExpression(inputExpression);
    }

    public InfixPostfixConverter(){
    }

    public void setInfixExpression(List<Element> inputExpression){
            infixExpression = inputExpression;
    }

    public List<Element> getPostfixExpression(){
        infixToPostfix();
        return postfixExpression;
    }

    /*
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
    */

    private List<Element> infixToPostfix(){
        return infixToPostfix(infixExpression);
    }

    private List<Element> infixToPostfix(List<Element> infixExpr){

        for(Element A : infixExpr){
            System.out.println("opStack " + opStack);
            System.out.println("postfixExpression " + postfixExpression);
            if(A instanceof Operand){
                System.out.println("meet Operand " + A);
                postfixExpression.add(A);
            }
            else if(A instanceof Operator) {
                System.out.println("meet Operator " + A);
                opStack((Operator) A);
            }
            else
                System.out.println("undefined operator!");
        }

        System.out.println("finish! opStack " + opStack);
        System.out.println(!opStack.empty());

        System.out.println(postfixExpression);

        while(!opStack.empty()){
            System.out.println("opStack " + opStack);
            Operator tmp = opStack.pop();
            System.out.println(tmp);
            postfixExpression.add(tmp);
        }

        System.out.println(postfixExpression);
        System.out.println(!opStack.empty());


        return postfixExpression;
    }

    private void opStack(Operator operator){
        //Push operator into the stack if it is empty

        System.out.println("input op " + operator);

        if(opStack.empty()){
            opStack.push(operator);
            return;
        }

        //Push operator into the stack if it is a left bracket
        if("(".charAt(0) == operator.getSymbol()){
            opStack.push(operator);
            return;
        }

        //Pop all elements in the stack to the output if meet the right bracket
        if(")".charAt(0) == operator.getSymbol()){
            Operator tmp;
            while(!("(".charAt(0) == ( (tmp=((Operator)opStack.pop())) .getSymbol() )) )
            {
                postfixExpression.add(tmp);
            }
            return;
        }

        //Push any operator into the stack if the previous operator is left bracket
        if("(".charAt(0) == (opStack.peek()).getSymbol()){
            opStack.push(operator);
            return;
        }

        System.out.println("comparePriority " + operator);
        if(comparePriority(operator, opStack.peek())){
            opStack.push(operator);
            return;
        }

        if(!comparePriority(operator, opStack.peek())){
            postfixExpression.add(opStack.pop());
            opStack(operator);
        }

        System.out.println("opStack " + opStack);
    }

    private boolean comparePriority(Operator op1,Operator op2){
        return op1.getLevel() > op2.getLevel();
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
        return operator.matches("[\\+\\-\\*\\/\\(\\)]");
    }

}
