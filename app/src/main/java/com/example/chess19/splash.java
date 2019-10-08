package com.example.chess19;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    private long splashTime = 5000L; // 3 seconds

    private Handler myHandler;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myHandler = new Handler();

    final Runnable r = new Runnable() {
        public void run() {
            goToMainActivity();
        }
    };

   myHandler.postDelayed(r, splashTime);
    }

    private void goToMainActivity(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}