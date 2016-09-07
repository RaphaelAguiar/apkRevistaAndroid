package com.example.fillipeteixeira.apprevista.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.R;

import java.util.ArrayList;

/**
 * Created by Fillipe Teixeira on 24/07/2016.
 */
public class RevistaAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    public ArrayList<Revista> lista;
    public ArrayList<Revista> listaFavoritos;

    public ArrayList<Revista> getListaFavoritos(){
        return this.listaFavoritos;
    }

    public RevistaAdapter(Context context, ArrayList<Revista> lista) {
        inflater = LayoutInflater.from(context);
        this.lista = lista;
    }

    public void addFavoritos(Revista revista){
        this.listaFavoritos.add(revista);
    }

    public void removerFavoritos(int id){
        this.listaFavoritos.remove(id);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       // view = inflater.inflate(R.layout.item, null);
        final View v = inflater.inflate(R.layout.item_dinamico, null);

        final Revista r = this.lista.get(i);

        final TextView nome = (TextView) v.findViewById(R.id.nome);
        TextView edicao = (TextView) v.findViewById(R.id.edicao);
        ImageView capa = (ImageView) v.findViewById(R.id.capa);
        TextView  subtitulo = (TextView) v.findViewById(R.id.txtSubtitulo);
        TextView qtdPaginas = (TextView) v.findViewById(R.id.txtQtPaginas);
        final ImageView estrela = (ImageView) v.findViewById(R.id.estrela);



     //   Button button = (Button) v.findViewById(R.id.btnLer);
      //  final CheckBox checkBox = (CheckBox) v.findViewById(R.id.favoritos);
        Boolean favoritos = false;

     //   nome.setText(e.getNome());
     //   imagem.setImageResource(e.getImagem());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        Bitmap bm = r.getImagem();
        //bm = bm.createScaledBitmap(bm, 80, 90, true);

        if (r.getFavoritos()){
            estrela.setImageResource(R.drawable.star_gold);
        }
        else{
            estrela.setImageResource(R.drawable.star_white1);
        }

        estrela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!r.getFavoritos()){
                    estrela.setImageResource(R.drawable.star_gold);
                    r.setFavoritos(true);
                }
                else{
                    estrela.setImageResource(R.drawable.star_white1);
                    r.setFavoritos(false);
                }
            }
        });

        nome.setText(r.getNome());
        edicao.setText("Edição: " + r.getEdicao());
        capa.setImageBitmap(r.getImagem());
        subtitulo.setText(r.getSubTitulo());
        qtdPaginas.setText(String.valueOf(r.getQtdPaginas())+" páginas");
      //  capa.setImageBitmap(bm);

        return v;
    }
}
