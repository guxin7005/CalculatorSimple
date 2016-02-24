package com.example.xin.calculatorsimple;

import java.util.*;

/**
 * Created by XL on 2016-02-22.
 */
public class InputExpressionParser {



    public List<Element> parse(String rawExpression){
        return classify(tokenizer(rawExpression));
    }

    private List<String> tokenizer(String rawExpression){
        /**
         * Split the raw string
         * @param String
         * @return ArrayList<String>
         */

        StringTokenizer st = new StringTokenizer(rawExpression, "+-*/()", true);

        List<String> splitExp = new ArrayList<String>(); //Store strings which have already been split.

        while(st.hasMoreTokens()){
            splitExp.add(st.nextToken());
        }

        return splitExp;
    }

    private List<Element> classify(List<String> splitExp){
        /**
         * Classify the string list
         * @param String
         * @return ArrayList<String>
         */

        List<Element> infixExpression = new ArrayList<Element>();

        for(String I:splitExp){
            if(isNumber(I)){
                infixExpression.add(new Number(I));
            }
            else if(isOperator(I)){
                infixExpression.add(new Operator(I));
            }
        }
        return infixExpression;
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
        return operator.matches("[\\+\\-\\*\\/\\(\\)\\%]");
    }
}
