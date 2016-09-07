package com.example.fillipeteixeira.apprevista.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.persistencia.RevistaDao;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        RevistaDao.getInstancia();
        finish();
        Intent intent = new Intent();
        intent.setClass(SplashScreenActivity.this, TelaAbasActivity.class);
        startActivity(intent);
    }
}
