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
public class FavoritosFragment extends Fragment {

     ArrayList<Revista> favoritos;
    ArrayList<RevistaF> favoritosF;
     View revistasFavoritas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            revistasFavoritas = inflater.inflate(R.layout.favoritas, container, false);
            ListView listaRevistas = (ListView) revistasFavoritas.findViewById(R.id.listaFavoritos);


        if(savedInstanceState != null){
          //  favoritos = savedInstanceState.getParcelableArrayList("1");
            favoritosF = savedInstanceState.getParcelableArrayList("1");
        }
        else{
           // favoritos = RevistaDao.getInstancia().getItens(true);
            favoritosF = RevistaDaoF.getInstancia().getItensFavoritos();
        }

            //RevistaAdapter adapter = new RevistaAdapter(this.getContext(), favoritos);
            //listaRevistas.setAdapter(adapter);
            //adapter.notifyDataSetChanged();

        RevistaAdapterF adapterF = new RevistaAdapterF(this.getContext(), favoritosF);
        listaRevistas.setAdapter(adapterF);
        adapterF.notifyDataSetChanged();

        return revistasFavoritas;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("1", favoritosF);
    }
}
