package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import mode.ModeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Реализация паузы на скрине приложения.
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(5000);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreenActivity.this,
                            ModeActivity.class));
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}