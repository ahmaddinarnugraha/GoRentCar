package com.ahmad.gorentcar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class Splashscreen extends AppCompatActivity {

    private int waktu_loading=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent home = new Intent(Splashscreen.this, Login.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);

    }
}
