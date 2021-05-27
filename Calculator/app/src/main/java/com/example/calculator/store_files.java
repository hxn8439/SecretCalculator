package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class store_files extends AppCompatActivity {


    ImageView mImageview;
    Button mChooseBtn;


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    //from heere


    //from here


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_files);

        //VIEWS
        mImageview = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.add_pic);



        //handle button click
        mChooseBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);

                    }
                    else {
                        //permission already granted
                        pickImageFromGallery();

                    }
                } else {

                }
            }
        });
    }


        private void pickImageFromGallery()
        {
            //intent to pick image
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, IMAGE_PICK_CODE);

        }


        //handle result of runtime permission

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int[] grantResults)
        {
            switch(requestCode)
            {
                case PERMISSION_CODE: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //permission was granted
                        pickImageFromGallery();
                    } else {
                        //permission was denied
                        Toast.makeText(this, "Permssion denied", Toast.LENGTH_SHORT).show();
                    }
                    //break;
                    //default:
                    //   super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }

//handle result of pick image

    @Override
    protected void onActivityResult (int requestCode, int  resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            //setimage to image view
            mImageview.setImageURI(data.getData());
        }



    }





}
