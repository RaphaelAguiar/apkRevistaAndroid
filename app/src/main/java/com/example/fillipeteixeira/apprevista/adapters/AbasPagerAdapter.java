package com.example.fillipeteixeira.apprevista.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fillipeteixeira.apprevista.activitys.AnuncieConoscoFragment;
import com.example.fillipeteixeira.apprevista.activitys.FavoritosFragment;
import com.example.fillipeteixeira.apprevista.activitys.TodasRevistasFragment;
import com.example.fillipeteixeira.apprevista.R;

import java.util.Locale;

/**
 * Created by Fillipe Teixeira on 13/08/2016.
 */
public class AbasPagerAdapter extends FragmentPagerAdapter {

    String[] titulosAbas;
    TypedArray bgColors;
    TypedArray textColors;

    public AbasPagerAdapter(Context ctx, FragmentManager fragmentManager) {
        super(fragmentManager);
        titulosAbas = ctx.getResources().getStringArray(R.array.sessoes);
        bgColors = ctx.getResources().obtainTypedArray(R.array.cores_bg);
        textColors = ctx.getResources().obtainTypedArray(R.array.cores_texto);
    }



    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
                return new TodasRevistasFragment();
           case 1:
               return new FavoritosFragment();
           case 2:
               return new AnuncieConoscoFragment();
           default:
               return new TodasRevistasFragment();
       }

//        SegundoNivelFragment fragment =
//                SegundoNivelFragment.novaInstacia(
//                 titulosAbas[position],
//                 R.color.primary,
//                 R.color.primary_text
//                );
//        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        return titulosAbas[position].toUpperCase(l);
    }
}
