package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class hidden_app extends AppCompatActivity {

    private Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_app);
        //button from hidden app to add pics
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open_Password_Activity();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open_Pic_Activity();
            }
        });
    }
    public void Open_Password_Activity()
    {
        Intent intent = new Intent(this, Passwords.class);
        startActivity(intent);
    }

    public void Open_Pic_Activity()
    {
        Intent intent = new Intent (this, store_files.class);
        startActivity(intent);
    }

    //function to tap screen to hide keyboard
    public void hideKeyboard1(View view)
    {
        InputMethodManager InputMethodManager= (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        InputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
