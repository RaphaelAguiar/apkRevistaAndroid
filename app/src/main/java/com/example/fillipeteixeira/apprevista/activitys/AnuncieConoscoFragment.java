package com.example.fillipeteixeira.apprevista.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fillipeteixeira.apprevista.R;

/**
 * Created by Fillipe Teixeira on 21/08/2016.
 */
public class AnuncieConoscoFragment extends Fragment {

    EditText nome;
    EditText nomeEmpresa;
    EditText email;
    EditText fone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View anuncieConosco = inflater.inflate(R.layout.anuncie_conosco, container, false);
        nome = (EditText) anuncieConosco.findViewById(R.id.nome);
        nomeEmpresa = (EditText) anuncieConosco.findViewById(R.id.nomeDaEmpresa);
        email = (EditText) anuncieConosco.findViewById(R.id.email);
        fone = (EditText) anuncieConosco.findViewById(R.id.telefone);
        return anuncieConosco;
    }


}
