package com.example.fillipeteixeira.apprevista.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.fillipeteixeira.apprevista.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this, TelaAbasActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
