package com.ahmad.gorentcar;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void masuk(View view) {
        Intent intent = new Intent(Login.this, LoginDua.class);
        startActivity(intent);
    }

    public void daftar(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}
