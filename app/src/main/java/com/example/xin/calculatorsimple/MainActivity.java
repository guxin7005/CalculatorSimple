package com.example.xin.calculatorsimple;

/* Author Xin Gu, Feb 4th, 2016 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**/
/*1.  Issue 00001: Hongbo Niu, Feb 28, 2016
*     The key '%' does not work after calculation
*
*     1.1. assign result = "" when clear all the info for new calculation;
*     1.2. add login in the function btnPercentOnClick();
* */

/*2. Issue 00005: Hongbo Niu, Feb 28, 2016
*    The key '+/-' does not work
*
*    2.1 add login in the function btnToggleSignOnClick();
*    2.2 modify the function processCurrentItem() to reset result;
* */

/*2.  Issue 00002: Guxin, Feb 28, 2016
*     use result
* */

public class MainActivity extends AppCompatActivity {

    TextView expressionTextView;
    TextView resultTextView;
    Button btnClear;

    String currentItem;
    String strInput;
    String result;
    ArrayList<String> inputItems;

    boolean isItemUpdated;

    /*........*/
    boolean isTest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initialize the members*/
        strInput = "";
        currentItem = "0";
        result = "";
        inputItems = new ArrayList<String>();

        expressionTextView = (TextView) findViewById(R.id.expressionTextView);
        expressionTextView.setGravity(Gravity.CENTER_VERTICAL);
        expressionTextView.setHint("The input expression will show here.");

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setGravity(Gravity.CENTER_VERTICAL);
        resultTextView.setText(currentItem);
        isItemUpdated = false;

        resultTextView.setText(currentItem);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnLongClickListener(new Button.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                longClick();
                return true;
            }
        });

        resultTextView.setOnLongClickListener(
                new TextView.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v) {
                        ResultTextViewLongClick();
                        return false;
                    }
                }
        );
    }

        private void ResultTextViewLongClick(){
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.copy_popup, null, false),100,100, true);

            pw.showAtLocation(this.findViewById(R.id.root), Gravity.CENTER, 0, 0);
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

    public void updateItem() {

        if (isTest) {
            String temp = currentItem + "       " + calculate(inputItems);
            resultTextView.setText(temp);
        } else {
            resultTextView.setText(currentItem);
        }
    }

    public void longClick() {
        /*clear current Item*/
        isItemUpdated = false;
        currentItem = "0";

        /*clear input expression*/
        inputItems.clear();
        strInput = "";                          /*Issue 00001*/
        result = "";

        updateItem();
        expressionTextView.setText(strInput);
    }

    //* remove the last character of the input */
    public void btnClearOnClick(View view) {
        if (isItemUpdated) {
            currentItem = currentItem.substring(0, currentItem.length() - 1);
            if (currentItem.isEmpty()) {
                currentItem = "0";
                isItemUpdated = false;
            }
            updateItem();
        } else if (!strInput.isEmpty()) {
            if (inputItems.size() == 1) {
                strInput = "";
                inputItems.clear();
                updateExpression();
                updateItem();
                return;
            }

            char tail = strInput.charAt(strInput.length() - 1);

            if (Operator.isOperator(tail)) {
                strInput = strInput.substring(0, strInput.length() - 1);
                removeFromItems();
            } else if (tail == ')') {
                int leftParenthesisIndex = strInput.lastIndexOf('(');
                strInput = strInput.substring(0, leftParenthesisIndex);
                removeFromItems();
            } else if (tail == '=') {
                strInput = "";                      /*Issue 00001*/
                inputItems.clear();
                result="";
            } else {
                Pattern pattern = Pattern.compile(".*(\\+|-|\\*|/|\\^)");
                Matcher matcher = pattern.matcher(strInput);
                if (matcher.find()) {
                    strInput = strInput.substring(0, matcher.end());
                    removeFromItems();
                } else {
                    strInput = "";                  /*Issue 00001*/
                    inputItems.clear();
                    result="";
                    /***kj;kjljyyiyi*/
                }
            }
            updateExpression();
            updateItem();           //here is for test
        }
    }

    private void resetCurrentItem() {
        currentItem = "0";
        isItemUpdated = false;
        updateItem();
    }

    public String removeFromItems() {
        if (inputItems.isEmpty()) return "";
        String ret = inputItems.get(inputItems.size() - 1);
        inputItems.remove(inputItems.size() - 1);
        return ret;
    }
/* dddd */

    public void btnToggleSignOnClick(View v) {

        if(isItemUpdated || result.isEmpty()){          /*Issue 00005*/
            isItemUpdated = true;
            if (currentItem.charAt(0) != '-') {
                currentItem = '-' + currentItem;
            } else {
                currentItem = currentItem.substring(1, currentItem.length());
            }
            updateItem();
        /* Issue 00005 Start */
        }else{
            if(result.startsWith("-")){
                result = result.substring(1, result.length());
            }else{
                result = "-" + result;
            }
            currentItem = result;
            updateItem();
        }
        /* Issue 00005 End */
    }

    public void btnPointOnClick(View v) {
        if (!currentItem.contains(".")) {
            currentItem += ".";
            isItemUpdated = true;
            updateItem();
        }
    }

    public void btn0OnClick(View v) {
        isItemUpdated = true;

        if (currentItem.equals("0") || currentItem.equals("-0")) return;

        currentItem += "0";
        updateItem();
    }

    private void btNumberOnClick(String num) {
        if (strInput.endsWith("=")) {
            strInput = "";                              /* Issue 00001 */
            expressionTextView.setText(strInput);
            inputItems.clear();
            result="";
        }

        if (currentItem.equals("0"))
            currentItem = num;
        else if (currentItem.equals("-0"))
            currentItem = "-" + num;
        else
            currentItem += num;

        isItemUpdated = true;
        updateItem();
    }

    public void btn1OnClick(View v) {
        btNumberOnClick("1");
    }

    public void btn2OnClick(View v) {
        btNumberOnClick("2");
    }

    public void btn3OnClick(View v) {
        btNumberOnClick("3");
    }

    public void btn4OnClick(View v) {
        btNumberOnClick("4");
    }

    public void btn5OnClick(View v) {
        btNumberOnClick("5");
    }

    public void btn6OnClick(View v) {
        btNumberOnClick("6");
    }

    public void btn7OnClick(View v) {
        btNumberOnClick("7");
    }

    public void btn8OnClick(View v) {
        btNumberOnClick("8");
    }

    public void btn9OnClick(View v) {
        btNumberOnClick("9");
    }

    public void processCurrentItem(String symbol) {
        if (!isItemUpdated) {
            if (strInput.isEmpty()) return;
            /*if strInput ends with numbers*/
            char end = strInput.charAt(strInput.length() - 1);
            switch (end) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    strInput = strInput.substring(0, strInput.length() - 1);
                    removeFromItems();
                    break;
                case '=':
                    /*continue to calculate using the current result*/
                    strInput = result;
                    resetCurrentItem();
                    inputItems.clear();
                    inputItems.add(result);
                    result = "";                  /*Issue 00005*/
                    break;
            }
            strInput += symbol;
        } else {
            if (currentItem.equals("-0")) {
                currentItem = "0";
            }

            if (currentItem.endsWith(".")) {
                currentItem += "0";
            }

            /*if the last digit of strInput is a digit */
            if (!strInput.isEmpty() &&
                    Character.isDigit(strInput.charAt(strInput.length() - 1))) {
                strInput += currentItem;  //Expression concat the currentItem directly
                inputItems.add(removeFromItems() + currentItem);    //update the ArrayList need pop and push
                strInput += symbol;
                inputItems.add(symbol);
                resetCurrentItem();
                updateExpression();
                return;
            }

            inputItems.add(currentItem);

            /*The following statements is for expression */
            if (currentItem.charAt(0) == '-')
                currentItem = "(" + currentItem + ")";

            currentItem += symbol;
            strInput += currentItem;

            /* reset the item for next input or result */
            resetCurrentItem();
        }

        inputItems.add(symbol);
        updateExpression();
        updateItem();
    }

    public void btnPlusOnClick(View v) {
        processCurrentItem("+");
    }

    public void btnSubtractOnClick(View v) {
        processCurrentItem("-");
    }

    public void btnMultiplyOnClick(View v) {
        processCurrentItem("*");
    }

    public void btnDivideOnClick(View v) {
        processCurrentItem("/");
    }

    public void btnExpoOnClick(View v) {
        processCurrentItem("^");
    }

    public void btnPercentOnClick(View v) {
        if (isItemUpdated) {
            if (currentItem.equals("-0")) {
                currentItem = "0";
            }

            if (currentItem.endsWith(".")) {
                currentItem += "0";
            }

            double currentValue = Double.parseDouble(currentItem) / 100.0;
            currentItem = Double.toString(currentValue);

            updateItem();
        /* Issue 00001 Start */
        }else if(!result.isEmpty()){
            double currentValue = Double.parseDouble(result) / 100.0;
            currentItem = Double.toString(currentValue);
            result = currentItem;
            updateItem();
        }
        /* Issue 00001 End*/

    }

    public void btnCalcOnClick(View v) {
        if (!isItemUpdated) {
            if (strInput.isEmpty()) return;
            if (strInput.matches(".*(\\+|-|\\*|/|\\^)$")) {
                strInput = strInput.substring(0, strInput.length() - 1);
                removeFromItems();
                strInput += "=";
            } else if (strInput.endsWith("=")) {
                return;
            } else {
                strInput += "=";
            }
        } else {
            if (currentItem.equals("-0")) {
                currentItem = "0";
            }

            if (currentItem.endsWith(".")) {
                currentItem += "0";
            }

            /*if the last digit of strInput is a digit */
            if (!strInput.isEmpty() &&
                    Character.isDigit(strInput.charAt(strInput.length() - 1))) {
                strInput += currentItem;  //Expression concat the currentItem directly
                inputItems.add(removeFromItems() + currentItem);    //update the ArrayList need pop and push
                strInput += "=";
                inputItems.add("=");
                resetCurrentItem();
                updateExpression();
                return;
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

        result = calculate(inputItems);
        resultTextView.setText(result);
    }


    public String calculate(ArrayList<String> inputItems) {
        if (isTest) {
            String stack = "";
            for (int i = 0; i < inputItems.size(); i++) {
                stack += inputItems.get(i) + "|";
            }
            return stack;
        } else {
            System.out.println(inputItems);
            List<Element> test = new InputExpressionParser().parse(inputItems);

            System.out.println(test);

            InfixPostfixConverter ipc = new InfixPostfixConverter(test);

            PostfixCalculator calculator = new PostfixCalculator();

            System.out.println("compute 4");
            //System.out.println(ipc.getPostfixExpression());

            List<Element> test2 = ipc.getPostfixExpression();

            System.out.println(test2);

            String res = calculator.evaluate(test2);

            System.out.println(res);
            System.out.println("compute 5");

            return res;
        }
    }
}
ClipboardManager
