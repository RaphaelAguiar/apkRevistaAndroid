package com.example.fillipeteixeira.apprevista.persistencia;

import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.entidades.RevistaF;

import java.util.ArrayList;

/**
 * Created by Fillipe Teixeira on 24/07/2016.
 */
public class  RevistaDaoF {

    public  ArrayList<RevistaF> lista;
    public ArrayList<RevistaF> listaFavoritos;
    private static RevistaDaoF instacia;

    private RevistaDaoF(){
        listaFavoritos = new ArrayList<RevistaF>();
        lista          = new ArrayList<RevistaF>();
    }

    public static RevistaDaoF getInstancia() {
        if (instacia == null){
            instacia = new RevistaDaoF();
            instacia.lista.add(new RevistaF("Silvye Alves", "3ª Ediçao", R.drawable.revista06, "default", "A jornalista e apresentadora do Cidade Alerta Goiás", 145));
            instacia.lista.get(0).setFavoritos(true);
            instacia.lista.add(new RevistaF("Sabrina Sato", "1ª Edição", R.drawable.revista04, "default", "Ela transborda carisma", 200));
            instacia.lista.add(new RevistaF("Lucas Lucco", "2ª Edição", R.drawable.revista03, "default", "A dedicação e talento", 150));
            instacia.lista.add(new RevistaF("Cauã Reymond", "2ª Edição", R.drawable.revista05, "default", "Ele coleciona encantos e personagens marcantes.", 196));
            instacia.lista.add(new RevistaF("Marconi Perilo", "4ª Edição", R.drawable.revista07, "defalult", "Governador de Goiás", 79));

        }
        return instacia;
    }

    public  ArrayList<RevistaF> getItens(){
        return instacia.lista;
    }

    public void addFavoritos(RevistaF r){
        boolean existe = false;
        int i = 0;
        while (instacia.listaFavoritos.size() > i){
            if(r.getImagem() == instacia.lista.get(i).getImagem()){
                existe = true;
                return;
            }
            i++;
        }
        if(!existe)
            instacia.listaFavoritos.add(r);
    }

    public void removerFavoritos(RevistaF r){
        int i = 0;
        while (instacia.listaFavoritos.size() > i){
            if(r.getImagem() == instacia.listaFavoritos.get(i).getImagem()){
                r.setFavoritos(false);
                instacia.listaFavoritos.remove(i);
                return;
            }
            i++;
        }

    }

    public ArrayList<RevistaF> getItensFavoritos(){

        int i = 0;
        while (instacia.lista.size() > i){
            if(instacia.lista.get(i).getFavoritos() == true){
                instacia.addFavoritos(instacia.lista.get(i));
            }
            i++;
        }
        return instacia.listaFavoritos;
    }

}
