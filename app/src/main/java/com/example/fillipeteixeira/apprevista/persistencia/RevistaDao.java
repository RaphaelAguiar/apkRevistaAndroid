package com.example.fillipeteixeira.apprevista.persistencia;

import com.example.fillipeteixeira.apprevista.adapters.RevistaAdapter;
import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.R;

import java.util.ArrayList;

/**
 * Created by Fillipe Teixeira on 24/07/2016.
 */
public class  RevistaDao {

    public  ArrayList<Revista> lista;
    public ArrayList<Revista> listaFavoritos;
    private static RevistaDao instacia;

    private RevistaDao(){
    }

    public static RevistaDao getInstacia() {
        if (instacia == null){
            instacia = new RevistaDao();
            getInstacia().listaFavoritos = new ArrayList<Revista>();
            getInstacia().lista = new ArrayList<Revista>();
            getInstacia().lista.add(new Revista("Silvye Alves", "3ª Ediçao", R.drawable.revista06, "default", "A jornalista e apresentadora do Cidade Alerta Goiás", 145));getInstacia().lista.add(new Revista("Sabrina Sato", "1ª Edição", R.drawable.revista04, "default", "Ela transborda carisma", 200));
            getInstacia().lista.add(new Revista("Lucas Lucco", "2ª Edição", R.drawable.revista03, "default", "A dedicação e talento", 150));
            getInstacia().lista.add(new Revista("Cauã Reymond", "2ª Edição", R.drawable.revista05, "default", "Ele coleciona encantos e personagens marcantes.", 196));
            getInstacia().lista.add(new Revista("Marconi Perilo", "4ª Edição", R.drawable.revista07, "defalult", "Governador de Goiás", 79));
            getInstacia().lista.add(new Revista("Silvye Alves", "3ª Ediçao", R.drawable.revista06, "default", "A jornalista e apresentadora do Cidade Alerta Goiás", 145));
            getInstacia().lista.add(new Revista("Sabrina Sato", "1ª Edição", R.drawable.revista04, "default", "Ela transborda carisma", 200));
            getInstacia().lista.add(new Revista("Lucas Lucco", "2ª Edição", R.drawable.revista03, "default", "A dedicação e talento", 150));
            getInstacia().lista.add(new Revista("Cauã Reymond", "2ª Edição", R.drawable.revista05, "default", "Ele coleciona encantos e personagens marcantes.", 196));
            getInstacia().lista.add(new Revista("Marconi Perilo", "4ª Edição", R.drawable.revista07, "defalult", "Governador de Goiás", 79));
        }
         return instacia;
    }

    public  ArrayList<Revista> getItens(){
        return getInstacia().lista;
    }

    public void addFavoritos(Revista r){
        boolean existe = false;
        int i = 0;
        while (instacia.listaFavoritos.size() < i){
            if(r == instacia.lista.get(i)){
                existe = true;
                return;
            }
            i++;
        }
        if(!existe)
        instacia.listaFavoritos.add(r);
    }

    public void removerFavoritos(int pos){
        instacia.listaFavoritos.remove(pos);
    }

    public ArrayList<Revista> getItensFavoritos(){

        int i = 0;
        while (instacia.lista.size() > i){
            if(instacia.lista.get(i).getFavoritos() == true){
                instacia.addFavoritos(instacia.lista.get(i));
            }
            i++;
        }
        return getInstacia().listaFavoritos;
    }

}
