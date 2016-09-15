package com.example.fillipeteixeira.apprevista.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.persistencia.ImagemDao;

/**
 * Created by raphael.aguiar on 10/09/2016.
 */
public class Pagina implements Parcelable{
    private Revista revista;
    private boolean imagemCarregada;
    private boolean miniaturaCarregada;
    private int     numero;
    private Bitmap  imagem;
    private Bitmap  miniatura;

    private void inicializarVariaveis(int nPagina, Revista revista){
        imagemCarregada    = false;
        miniaturaCarregada = false;
        numero             = nPagina;
        this.revista       = revista;
    }

    Pagina(Revista revista, int nPagina){
        inicializarVariaveis(nPagina,revista);
    }

    protected Pagina(Parcel in) {
        inicializarVariaveis(/*pagina  =*/ in.readInt(),
                             /*revista =*/ (Revista) in.readParcelable(getClass().getClassLoader())
        );
    }

    public Bitmap getImagem(){
        if(!imagemCarregada){
            imagem = ImagemDao.getInstancia().get(revista.getNome(), numero, false, true);
            if(imagem==null)
                imagem = BitmapFactory.decodeResource(null, R.drawable.empty);
            else
                imagemCarregada = true;
        }
        return imagem;
    }

    public Bitmap getMiniatura(){
        if(!miniaturaCarregada){
            miniatura = ImagemDao.getInstancia().get(revista.getNome(), numero, true, true);
            if(miniatura==null)
                miniatura = BitmapFactory.decodeResource(null, R.drawable.empty);
            else
                miniaturaCarregada = true;
        }
        return miniatura;
    }

    public static final Creator<Pagina> CREATOR = new Creator<Pagina>() {
        @Override
        public Pagina createFromParcel(Parcel in) {
            return new Pagina(in);
        }

        @Override
        public Pagina[] newArray(int size) {
            return new Pagina[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numero);
        revista.writeToParcel(dest,flags);
    }

    public boolean imagemCarregada(){
        return imagemCarregada;
    }
    public boolean miniaturaCarregada(){
        return miniaturaCarregada;
    }
}
