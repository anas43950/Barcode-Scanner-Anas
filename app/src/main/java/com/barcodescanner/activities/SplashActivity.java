package com.barcodescanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.barcodescanner.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MyApplication my = MyApplication.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(SplashActivity.this, MainActivity.class);
                my.appOpenAdManager.showAdIfAvailable(SplashActivity.this, mainActivityIntent);
//                finish();
            }
        }, 1500);
    }
}