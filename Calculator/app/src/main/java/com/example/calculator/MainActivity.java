package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, decimal, add, subtract, divide, equal, multiply,sign,percent;
    TextView val1Screen, funcScreen, val2Screen;

    Double val1 = 0.0;
    Double val2 = 0.0;
    Character function = null;
    int decimal_pressed1 = 0;
    int decimal_pressed2 = 0;
    int passcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();//grabs passcode from login window
        passcode = intent.getIntExtra(Login_GUI.Extra_Number,0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.callayout);

        val1Screen = findViewById(R.id.val1Screen);
        funcScreen = findViewById(R.id.funcScreen);
        val2Screen = findViewById(R.id.val2Screen);

        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);

        decimal = findViewById(R.id.decimal);
        percent = findViewById(R.id.percent);
        sign = findViewById(R.id.sign);
        add = findViewById(R.id.addition);
        divide = findViewById(R.id.division);
        subtract = findViewById(R.id.subtraction);
        multiply = findViewById(R.id.multiply);
        equal = findViewById(R.id.equalSign);

        //function to display numbers on screens by switch case
        View.OnClickListener calculatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int id = view.getId();

                switch(id){
                    case R.id.b0:
                        //same logic for all numbers just different text being input
                        //if function is null we have yet to input our first value
                        //else first value has already been input (and so has function to be performed)
                        //so we are inputting second value
                        if(function == null) val1Screen.append("0");
                        else val2Screen.append("0");
                        break;

                    case R.id.b1:
                        if(function == null) val1Screen.append("1");
                        else val2Screen.append("1");
                        break;

                    case R.id.b2:
                        if(function == null) val1Screen.append("2");
                        else val2Screen.append("2");
                        break;

                    case R.id.b3:
                        if(function == null) val1Screen.append("3");
                        else val2Screen.append("3");
                        break;

                    case R.id.b4:
                        if(function == null) val1Screen.append("4");
                        else val2Screen.append("4");
                        break;

                    case R.id.b5:
                        if(function == null) val1Screen.append("5");
                        else val2Screen.append("5");
                        break;

                    case R.id.b6:
                        if(function == null) val1Screen.append("6");
                        else val2Screen.append("6");
                        break;

                    case R.id.b7:
                        if(function == null) val1Screen.append("7");
                        else val2Screen.append("7");
                        break;

                    case R.id.b8:
                        if(function == null) val1Screen.append("8");
                        else val2Screen.append("8");
                        break;

                    case R.id.b9:
                        if(function == null) val1Screen.append("9");
                        else val2Screen.append("9");
                        break;

                    case R.id.decimal: //allows only one decimal to be entered
                        if(function == null && decimal_pressed1 < 1) {
                            val1Screen.append(".");
                            decimal_pressed1 = 1;
                        }
                        if(function != null && decimal_pressed2 < 1) {
                            val2Screen.append(".");
                            decimal_pressed2 = 1;
                        }
                        break;

                    case R.id.percent:
                        //4 digit passcode followed by percent will get into hidden_app
                        int num = Integer.parseInt(val1Screen.getText().toString());

                        double numm=0.00,screen2=0.00;


                        if(num == passcode)
                        {
                            go_to_hidden_app();
                            break;
                        }
                        if(function == null)
                        {
                            //covert val1screen(type double) to decimal here
                            screen2 = Double.parseDouble(val1Screen.getText().toString())/100;
                            val1Screen.setText(Double.toString(screen2));
                            break;
                        }
                        if(function != null)
                        {
                            //convert val2screen(type double) to decimal here
                            numm = Double.parseDouble(val2Screen.getText().toString()) / 100;
                            val2Screen.setText(Double.toString(numm));

                            break;
                        }

                    case R.id.addition:
                        val1 = Double.parseDouble(val1Screen.getText().toString());
                        function = '+';
                        funcScreen.setText("+");
                        break;

                    case R.id.subtraction:
                        val1 = Double.parseDouble(val1Screen.getText().toString());
                        function = '-';
                        funcScreen.setText("-");
                        break;

                    case R.id.division:
                        val1 = Double.parseDouble(val1Screen.getText().toString());
                        function = '/';
                        funcScreen.setText("/");
                        break;

                    case R.id.multiply:
                        val1 = Double.parseDouble(val1Screen.getText().toString());
                        function = '*';
                        funcScreen.setText("*");
                        break;

                    case R.id.sign:
                        if(function == null) val1Screen.append("-");
                        else val2Screen.append("-");

                        break;

                    case R.id.equalSign:
                        //allows one decimal to be entered again
                        decimal_pressed1 = 0;
                        decimal_pressed2 = 0;

                        //save second value
                        val2 = Double.parseDouble(val2Screen.getText().toString());

                        //compute function and save result to val1
                        val1 = compute(val1,val2,function);

                        //display result as our first value to continue modifying
                        val1Screen.setText(Double.toString(val1));

                        //reset function to null and empty funcScreen
                        function = null;
                        funcScreen.setText("");

                        //reset second value to 0 and empty val2Screen
                        val2 = 0.0;
                        val2Screen.setText("");
                        break;
                }
            }
        };

        b0.setOnClickListener(calculatorListener);
        b1.setOnClickListener(calculatorListener);
        b2.setOnClickListener(calculatorListener);
        b3.setOnClickListener(calculatorListener);
        b4.setOnClickListener(calculatorListener);
        b5.setOnClickListener(calculatorListener);
        b6.setOnClickListener(calculatorListener);
        b7.setOnClickListener(calculatorListener);
        b8.setOnClickListener(calculatorListener);
        b9.setOnClickListener(calculatorListener);
        sign.setOnClickListener(calculatorListener);
        decimal.setOnClickListener(calculatorListener);
        percent.setOnClickListener(calculatorListener);
        add.setOnClickListener(calculatorListener);
        divide.setOnClickListener(calculatorListener);
        subtract.setOnClickListener(calculatorListener);
        equal.setOnClickListener(calculatorListener);
        multiply.setOnClickListener(calculatorListener);



        //function to delete last number typed on screen
        final Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if function is not null, we have already input the function to be performed
                // and are typing in the second value to val2Screen
                //
                // therefore delete last element typed into val2Screen
                if(function != null) {
                    String displayElements = val2Screen.getText().toString();
                    int numberLength = displayElements.length();

                    if (numberLength > 0) {
                        displayElements = displayElements.substring(0, numberLength - 1);
                        val2Screen.setText(displayElements);
                    }
                }
                //else if the function is null we are still typing first value on val1Screen
                //
                //delete last element of val1Screen
                else{
                    String displayElements = val1Screen.getText().toString();
                    int numberLength = displayElements.length();

                    if (numberLength > 0) {
                        displayElements = displayElements.substring(0, numberLength - 1);
                        val1Screen.setText(displayElements);
                    }
                }
            }
        });

        //function to clear the screen, and input
        final Button clearAll = findViewById(R.id.clearAll);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //reset all values to initial and clear screens.
                val1 = 0.0;
                function = null;
                val2 = 0.0;
                val1Screen.setText("");
                funcScreen.setText("");
                val2Screen.setText("");

            }
        });



    }

    //function to compute result of two values using given function
    public double compute(double val1, double val2, char function){

        double result = 0;

        switch(function){
            case '+':
                result = val1 + val2;
                break;

            case '-':
                result = val1 - val2;
                break;

            case '*':
                result = val1 * val2;
                break;

            case '/':
                result = val1 / val2;
                break;
        }

        return result;

    }

    void go_to_hidden_app()
    {
        Intent Intent = new Intent(MainActivity.this, hidden_app.class);  //moves from cal window to hidden_app
        startActivity(Intent);

        //slide out cal window and slide in hidden_app
        overridePendingTransition(R.anim.slidee_in_right,R.anim.slide_in_left);
    }


    //disable back button
    @Override
    public void onBackPressed()
    {

    }
}