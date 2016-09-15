package com.example.fillipeteixeira.apprevista.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fillipeteixeira.apprevista.adapters.RevistaAdapter;
import com.example.fillipeteixeira.apprevista.adapters.RevistaAdapterF;
import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.entidades.RevistaF;
import com.example.fillipeteixeira.apprevista.persistencia.RevistaDao;
import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.persistencia.RevistaDaoF;

import java.util.ArrayList;

/**
 * Created by Fillipe Teixeira on 21/08/2016.
 */
public class TodasRevistasFragment extends Fragment {

    private ArrayList<Revista> revistas;
    private ArrayList<RevistaF> revistasf;
    View todasRevistas;
    RevistaAdapter adapter;
    RevistaAdapterF adapterF;
    ListView listaRevistas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            //revistas = savedInstanceState.getParcelableArrayList("1");
            revistasf = savedInstanceState.getParcelableArrayList("1");
        }
        else{
            //revistas = RevistaDao.getInstancia().getItens(true);
            revistasf = RevistaDaoF.getInstancia().getItens();
        }

            todasRevistas = inflater.inflate(R.layout.todasrevistas, container, false);
            listaRevistas = (ListView) todasRevistas.findViewById(R.id.lista);

            //adapter = new RevistaAdapter(this.getContext(), revistas);
            //listaRevistas.setAdapter(adapter);

            adapterF = new RevistaAdapterF(this.getContext(), revistasf);
            listaRevistas.setAdapter(adapterF);

            adapterF.notifyDataSetChanged();


        return todasRevistas;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putParcelableArrayList("1", revistas);
        outState.putParcelableArrayList("1", revistasf);
    }
}
