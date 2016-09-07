package com.example.fillipeteixeira.apprevista.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fillipeteixeira.apprevista.adapters.RevistaAdapter;
import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.persistencia.RevistaDao;
import com.example.fillipeteixeira.apprevista.R;

import java.util.ArrayList;

public class TelaListar extends Activity {

    private ArrayList<Revista> revistas = new ArrayList<Revista>();
    ListView listaRevistas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar);

        if(savedInstanceState == null){
            revistas =  RevistaDao.getInstancia().getItens(true);
        }
        else{
           revistas = savedInstanceState.getParcelableArrayList("1");
        }

        RevistaAdapter adapter = new RevistaAdapter(this, revistas);
        listaRevistas = (ListView) findViewById(R.id.lista);
        listaRevistas.setAdapter(adapter);
        onSaveInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("1", revistas);
    }
}
