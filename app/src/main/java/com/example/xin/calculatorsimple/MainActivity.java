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

public class MainActivity extends AppCompatActivity {

    String strInput;
    TextView expressionTextView;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strInput = new String();
        expressionTextView = (TextView) findViewById(R.id.expressionTextView);
        expressionTextView.setGravity(Gravity.CENTER_VERTICAL);
        expressionTextView.setHint("The input expression will show here.");

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setGravity(Gravity.CENTER_VERTICAL);
        resultTextView.setHint("The result will show here.");
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

    //* remove the last character of the input */
    public void btnClearOnClick(View view) {
        if (strInput.length() > 0)
            strInput = strInput.substring(0, strInput.length() - 1);
        updateExpression();
    }

    public void btn0OnClick(View v) {
        strInput += "0";
        updateExpression();
    }

    public void btn1OnClick(View v) {
        strInput += "1";
        updateExpression();
    }

    public void btn2OnClick(View v) {
        strInput += "2";
        updateExpression();
    }

    public void btn3OnClick(View v) {
        strInput += "3";
        updateExpression();
    }

    public void btn4OnClick(View v) {
        strInput += "4";
        updateExpression();
    }

    public void btn5OnClick(View v) {
        strInput += "5";
        updateExpression();
    }

    public void btn6OnClick(View v) {
        strInput += "6";
        updateExpression();
    }

    public void btn7OnClick(View v) {
        strInput += "7";
        updateExpression();
    }

    public void btn8OnClick(View v) {
        strInput += "8";
        updateExpression();
    }

    public void btn9OnClick(View v) {
        strInput+="9";
        updateExpression();
    }

    public void btnPlusOnClick(View v) {
        strInput+="+";
        updateExpression();
    }

    public void btnSubtractOnClick(View v){
        strInput+="-";
        updateExpression();
    }

    public void btnMultiplyOnClick(View v) {
        strInput+="*";
        updateExpression();
    }

    public void btnDivideOnClick(View v){
        strInput+="/";
        updateExpression();
    }

    public void btnModOnClick(View v){
        strInput+="%";
        updateExpression();
    }

    public void btnPointOnClick(View v){
        strInput+=".";
        updateExpression();
    }

    public void btnExpoOnClick(View v){
        strInput+="^";
        updateExpression();
    }

    public void btnCalcOnClick(View v){
        String result = calculate(strInput);
        resultTextView.setText(result);
    }



    public String calculate(String strInput){
        return "";
    }
}
