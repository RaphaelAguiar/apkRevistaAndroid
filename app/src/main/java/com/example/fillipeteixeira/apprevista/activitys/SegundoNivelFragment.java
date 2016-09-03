package com.example.fillipeteixeira.apprevista.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.R;

import java.util.ArrayList;

/**
 * Created by Fillipe Teixeira on 13/08/2016.
 */
public class SegundoNivelFragment extends Fragment {

    private static final String EXTRA_TEXTO = "texto";
    private static final String EXTRA_COR_BG = "corBg";
    private static final String EXTRA_COR_TEXTO = "corTexto";
    private ArrayList<Revista> revistas = new ArrayList<Revista>();

    public static SegundoNivelFragment novaInstacia(String text, int background, int textColor){
        Bundle params = new Bundle();
        params.putString(EXTRA_TEXTO, text);
        params.putInt(EXTRA_COR_BG, background);
        params.putInt(EXTRA_COR_TEXTO, textColor);

        SegundoNivelFragment snf = new SegundoNivelFragment();
        snf.setArguments(params);
        return snf;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle params = getArguments();
        String texto = params.getString(EXTRA_TEXTO);
        int bgColor = params.getInt(EXTRA_COR_BG);
        int textColor = params.getInt(EXTRA_COR_TEXTO);

        View layout = inflater.inflate(R.layout.activity_tela_listar, container, false);
        layout.setBackgroundColor(bgColor);


//
//        TextView txt = (TextView) layout.findViewById(R.id.textView);
//        txt.setText(texto);
//        txt.setTextColor(textColor);

        return  layout;
    }
}
