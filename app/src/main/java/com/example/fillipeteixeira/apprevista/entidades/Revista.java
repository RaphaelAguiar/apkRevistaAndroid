package com.example.fillipeteixeira.apprevista.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.persistencia.ImagemDao;

/**
 * Created by Fillipe Teixeira on 24/07/2016.
 */
public class Revista implements Parcelable {

    private String  nome;
    private int     edicao;
    private String  subTitulo;
    private int     qtdPaginas;
    private Bitmap  imagem;
    private boolean imagemCarregada;
    private String  caminho;
    private Boolean favoritos;
    private Boolean lida;

    protected Revista(Parcel in) {
        nome = in.readString();
        edicao = in.readInt();
        subTitulo = in.readString();
        qtdPaginas = in.readInt();
        imagem = in.readParcelable(getClass().getClassLoader());
        caminho = in.readString();
    }

    public static final Creator<Revista> CREATOR = new Creator<Revista>() {
        @Override
        public Revista createFromParcel(Parcel in) {
            return new Revista(in);
        }

        @Override
        public Revista[] newArray(int size) {
            return new Revista[size];
        }
    };

    public String getSubTitulo() {
        return subTitulo;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public Revista(String nome, int edicao, String caminho, String subTitulo, int qtdPaginas) {
        this.nome       = nome;
        this.edicao     = edicao;
        this.caminho    = caminho;
        this.subTitulo  = subTitulo;
        this.qtdPaginas = qtdPaginas;
        favoritos       = false;
        lida            = false;
        imagemCarregada = false;

    }

    public Boolean getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Boolean favoritos) {
        this.favoritos = favoritos;
    }

    public Boolean getLida() {
        return lida;
    }

    public void setLida(Boolean lida) {
        this.lida = lida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public Bitmap getImagem() {
        if(!imagemCarregada) {
            imagem = ImagemDao.getInstancia().getCapa(getNome(), true);
            if(imagem==null){
                imagem = BitmapFactory.decodeResource(null,R.drawable.empty);
            }else{
                imagemCarregada = true;
            }
        }
        return imagem;
    }

    public String getCaminho() {
        return caminho;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeInt(edicao);
        parcel.writeString(subTitulo);
        parcel.writeInt(qtdPaginas);
        getImagem().writeToParcel(parcel,i);
        parcel.writeString(caminho);
    }
}
