package com.example.xin.calculatorsimple;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by XL on 2016-02-22.
 *
 * March 16, 2016 Xin Gu/Hongbo Niu
 * Issue 00006:   Modify function isNumber()
 *                Check input is numerical;
 *
 */

public class InputExpressionParser {



    public List<Element> parse(String rawExpression){
        return classify(tokenizer(rawExpression));
    }

    public List<Element> parse(ArrayList<String> rawExpression){
        return classify(rawExpression);
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
                //System.out.println("add new Number  " + I);
            }
            else if(isOperator(I)){
                infixExpression.add(new Operator(I));
                //System.out.println("add new Operator " + I);
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
        //return num.matches("\\d+");
        //return (num.matches("(\\d*)\\.\\d+") || num.matches("\\d+"));
        //
        /* Issue 00006 change Start */
        try{
            Double.parseDouble(num);
        }catch (NumberFormatException e){
            return false;
        }

        return true;
        /* Issue 00006 change End */

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
