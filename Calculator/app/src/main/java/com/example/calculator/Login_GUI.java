package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;



public class Login_GUI extends AppCompatActivity {

    //DONT NOT REMOVE. Empty string to help pass passcode from login to cal activity
    public static final String Extra_Number = "nothing";


    private Button Save_button;


    //save button function takes user to calculator
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__gui);

        Save_button = findViewById(R.id.save_btn);

        Save_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                moveToCalActivity();


            }
        });

    }





    void moveToCalActivity()
    {

        EditText edittext = findViewById(R.id.num_input);    //grab user 4 digit code
        int passcode = Integer.parseInt(edittext.getText().toString()); //saves user input into variable

        Intent intent = new Intent(Login_GUI.this, MainActivity.class);  //moves from login window to cal windo
        intent.putExtra(Extra_Number,passcode);    //passes the user passcode from login to cal
        startActivity(intent);


    }

    //function to tap screen to hide keyboard
   public void hideKeyboard(View view)
    {
        InputMethodManager InputMethodManager= (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        InputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }


    //disable back button
    @Override
    public void onBackPressed()
    {

    }

}

