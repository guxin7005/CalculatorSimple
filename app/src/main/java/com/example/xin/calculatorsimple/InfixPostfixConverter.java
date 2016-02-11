package ca.google.calculator;

import java.util.*;

/**
 * Created by XL on 2016-02-09.
 */
public class InfixPostfixConverter {

    private Stack<String> opStack = new Stack<String>();
    private List<String> infixExpression = new ArrayList<String>();
    private List<String> postfixExpression = new ArrayList<String>();

    public InfixPostfixConverter(String rawExpression){
        setInfixExpression(rawExpression);
    }

    public InfixPostfixConverter(){
    }

    public void setInfixExpression(String rawExpression){
        StringTokenizer st = new StringTokenizer(rawExpression, "+-*/()", true);
        while(st.hasMoreTokens()){
            infixExpression.add(st.nextToken());
        }
    }

    public List<String> getPostfixExpression(){
        infixToPostfix();
        return postfixExpression;
    }

    private final int level1 = 0;//+-: level 0
    private final int level2 = 1;//*/: level 1
    private final int level3 = 3;//() level 3

    private int getOperatorLevel(String operator){
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

    private List<String> infixToPostfix(){
        return infixToPostfix(infixExpression);
    }

    private List<String> infixToPostfix(List<String> infixExpr){

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

    private void opStack(String operator){
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
