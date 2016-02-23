package com.example.xin.calculatorsimple;

/* Author Xin Gu, Feb 4th, 2016 */
/* Issue 00003 by Xin Gu, Feb 6, 2016 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String strInput;
    TextView expressionTextView;
    TextView resultTextView;
    String currentItem;
    ArrayList<String> inputItems;
    boolean isItemUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strInput = "";
        currentItem = "0";
        isItemUpdated = false;

        inputItems=new ArrayList<String>();


        expressionTextView = (TextView) findViewById(R.id.expressionTextView);
        expressionTextView.setGravity(Gravity.CENTER_VERTICAL);
        expressionTextView.setHint("The input expression will show here.");

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setGravity(Gravity.CENTER_VERTICAL);
        resultTextView.setText(currentItem);
        isItemUpdated = false;


        updateItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void updateExpression() {
        expressionTextView.setText(strInput);
    }

    public void updateItem(){
        resultTextView.setText(currentItem);
        isItemUpdated = true;
    }

    //* remove the last character of the input */
    public void btnClearOnClick(View view) {
        if(isItemUpdated) {
            currentItem = currentItem.substring(0, currentItem.length() - 1);
            if(currentItem.isEmpty()) {
                currentItem = "0";
                isItemUpdated = false;
            }
            resultTextView.setText(currentItem);
        }

        else if(strInput.length()>0){
            char tail = strInput.charAt(strInput.length() - 1);

            if(Operator.isOperator(tail)){
                strInput = strInput.substring(0, strInput.length() - 1);
            }
            else if(tail==')'){
                int leftParenthesisIndex = strInput.lastIndexOf('(');
                strInput = strInput.substring(0, leftParenthesisIndex);
            }
            else{
                Pattern pattern = Pattern.compile(".*(\\+|-|\\*|/|\\^)");
                Matcher matcher = pattern.matcher(strInput);
                if(matcher.find()){
                    strInput = strInput.substring(0, matcher.end());
                }else{
                    strInput="";
                }
            }
            removeFromItems();
            updateExpression();
        }
    }

    public void removeFromItems(){
        if(inputItems.isEmpty()) return;
        inputItems.remove(inputItems.size() - 1);
    }

    public void processCurrentItem(){

    }

    public void btnToggleSignOnClick(View v){
        if(currentItem.charAt(0)!='-'){
            currentItem='-'+currentItem;
        }else{
            currentItem=currentItem.substring(1, currentItem.length());
        }

        updateItem();
    }

    public void btnPointOnClick(View v){
        if(!currentItem.contains(".")) {
            currentItem += ".";
            updateItem();
        }
    }

    public void btn0OnClick(View v) {
        isItemUpdated = true;

        if(currentItem.equals("0") || currentItem.equals("-0")) return;

        currentItem+="0";
        updateItem();
    }

    public void btn1OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="1";
        else if(currentItem.equals("-0"))
            currentItem="-1";
        else
            currentItem+="1";
        updateItem();
    }

    public void btn2OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="2";
        else if(currentItem.equals("-0"))
            currentItem="-2";
        else
            currentItem += "2";
        updateItem();
    }

    public void btn3OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="3";
        else if(currentItem.equals("-0"))
            currentItem="-3";
        else
            currentItem += "3";
        updateItem();
    }

    public void btn4OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="4";
        else if(currentItem.equals("-0"))
            currentItem="-4";
        else
            currentItem += "4";
        updateItem();
    }

    public void btn5OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="5";
        else if(currentItem.equals("-0"))
            currentItem="-5";
        else
            currentItem += "5";
        updateItem();
    }

    public void btn6OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="6";
        else if(currentItem.equals("-0"))
            currentItem="-6";
        else
            currentItem += "6";
        updateItem();
    }

    public void btn7OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="7";
        else if(currentItem.equals("-0"))
            currentItem="-7";
        else
            currentItem += "7";
        updateItem();
    }

    public void btn8OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="8";
        else if(currentItem.equals("-0"))
            currentItem="-8";
        else
            currentItem += "8";
        updateItem();
    }

    public void btn9OnClick(View v) {
        if(currentItem.equals("0"))
            currentItem="9";
        else if(currentItem.equals("-0"))
            currentItem="-9";
        else
            currentItem += "9";
        updateItem();
    }

    public void btnPlusOnClick(View v) {
        if(!isItemUpdated){
            if(strInput.isEmpty()) return;
            strInput = strInput.substring(0, strInput.length() - 1);
            removeFromItems();
            strInput+="+";
        }
        else {
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += "+";
            strInput += currentItem;

            /* reset the item for next input or result */
            currentItem = "0";
            isItemUpdated = false;
            resultTextView.setText(currentItem);
        }

        inputItems.add("+");
        updateExpression();
    }

    public void btnSubtractOnClick(View v){
        if(!isItemUpdated){
            if(strInput.isEmpty()) return;
            strInput = strInput.substring(0, strInput.length() - 1);
            removeFromItems();
            strInput+="-";
        }
        else {
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += "-";
            strInput += currentItem;

            /* reset the item for next input or result */
            currentItem = "0";
            isItemUpdated = false;
            resultTextView.setText(currentItem);
        }

        inputItems.add("-");
        updateExpression();
    }

    public void btnMultiplyOnClick(View v) {
        if(!isItemUpdated){
            if(strInput.isEmpty()) return;
            strInput = strInput.substring(0, strInput.length() - 1);
            removeFromItems();
            strInput+="*";
        }
        else {
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += "*";
            strInput += currentItem;

            /* reset the item for next input or result */
            currentItem = "0";
            isItemUpdated = false;
            resultTextView.setText(currentItem);
        }

        inputItems.add("*");
        updateExpression();
    }

    public void btnDivideOnClick(View v){
        if(!isItemUpdated){
            if(strInput.isEmpty()) return;
            strInput = strInput.substring(0, strInput.length() - 1);
            removeFromItems();
            strInput+="/";
        }
        else {
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += "/";
            strInput += currentItem;

            /* reset the item for next input or result */
            currentItem = "0";
            isItemUpdated = false;
            resultTextView.setText(currentItem);
        }

        inputItems.add("/");
        updateExpression();
    }

    public void btnPercentOnClick(View v){
        if(isItemUpdated){
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            double currentValue = Double.parseDouble(currentItem)/100.0;
            currentItem = Double.toString(currentValue);

            updateItem();
        }
    }

    public void btnExpoOnClick(View v){
        if(!isItemUpdated){
            if(strInput.isEmpty()) return;
            strInput = strInput.substring(0, strInput.length() - 1);
            removeFromItems();
            strInput+="^";
        }
        else {
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += "^";
            strInput += currentItem;

            /* reset the item for next input or result */
            currentItem = "0";
            isItemUpdated = false;
            resultTextView.setText(currentItem);
        }

        inputItems.add("^");
        updateExpression();
    }

    public void btnCalcOnClick(View v){
        if(!isItemUpdated){
            if(strInput.isEmpty()) return;
            strInput = strInput.substring(0, strInput.length() - 1);
            removeFromItems();
            strInput+="=";
        }
        else {
            if(currentItem.equals("-0")) {
                currentItem = "0";
            }

            if(currentItem.endsWith(".")){
                currentItem += "0";
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += "=";
            strInput += currentItem;

            /* reset the item for next input or result */
            currentItem = "0";
            isItemUpdated = false;
        }

        updateExpression();

        String result = calculate(inputItems);
        resultTextView.setText(result);
    }


    public String calculate(ArrayList<String> strInput){
        return "aaa";
    }
}
