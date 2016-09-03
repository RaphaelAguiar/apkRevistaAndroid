package com.example.fillipeteixeira.apprevista.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.entidades.Revista;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Revista> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abas_actitivity);

    }

}
