package com.example.reatey.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText editText1;
    Button button;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //R init
        editText1 = (EditText) findViewById(R.id.number);
        button = (Button) findViewById(R.id.result);
        textView = (TextView) findViewById(R.id.showRes);
        //set listener
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String string = editText1.getText().toString();
        getValueFromEditText(string);
    }

    private void getValueFromEditText(String str) {
        if (!new MainActivity().check(str)) {
            return;
        }
        String symbol = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '/' || str.charAt(i) == '*') {
                symbol = str.charAt(i) + "";
                break;
            }
        }
        String[] strings = str.split("[" + symbol + "]");
        setResult(symbol, strings);
    }

    private boolean check(String tempString) {
        Pattern p = Pattern.compile("[0-9-]+(\\+|-|/|\\*)[0-9]+");
        Matcher m = p.matcher(tempString.replaceAll(" ", ""));
        return m.matches();
    }

    private void setResult(String symbol, String[] strings) {
        int numberOne;
        int numberTwo;

        try {
            numberOne = Integer.parseInt(strings[0].trim());
            numberTwo = Integer.parseInt(strings[1].trim());

            int res;
            switch (symbol) {
                case "+":
                    res = numberOne + numberTwo;

                    textView.setText(res);
                    break;
                case "-":
                    res = numberOne - numberTwo;
                    textView.setText(res);
                    break;
                case "*":
                    res = numberOne * numberTwo;
                    textView.setText(res);
                    break;
                case "/":
                    double resDevide = numberOne / numberTwo;
                    String strRes = Double.toString(resDevide);
                    textView.setText(strRes);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Enter only numbers", Toast.LENGTH_LONG).show();
        }

    }

}
