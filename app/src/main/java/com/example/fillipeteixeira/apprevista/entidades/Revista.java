package com.example.fillipeteixeira.apprevista.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.fillipeteixeira.apprevista.R;
import com.example.fillipeteixeira.apprevista.persistencia.ImagemDao;

import java.util.ArrayList;

/**
 * Created by Fillipe Teixeira on 24/07/2016.
 */
public class Revista implements Parcelable {

    private String  nome;
    private int     edicao;
    private String  subTitulo;
    private int     qtdPaginas;
    private String  caminho;
    private Boolean favoritos;
    private Boolean lida;
    private ArrayList<Pagina> paginas;

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

    private void inicializarVariaveis(String nome, int edicao, String caminho, String subTitulo, int qtdPaginas, boolean favoritos, boolean lida){
        this.nome       = nome;
        this.edicao     = edicao;
        this.caminho    = caminho;
        this.subTitulo  = subTitulo;
        this.qtdPaginas = qtdPaginas;
        this.favoritos  = favoritos;
        this.lida       = lida;

        paginas = new ArrayList<Pagina>();
        for (int i = 1 ; i <= qtdPaginas; i++){
            paginas.add(new Pagina(this,i));
        }
    }

    public Revista(String nome, int edicao, String caminho, String subTitulo, int qtdPaginas) {
        inicializarVariaveis(nome,edicao,caminho,subTitulo,qtdPaginas,false,false);
    }

    protected Revista(Parcel in) {
        //Manter nesta ordem!
        String nome       = in.readString();
        int    edicao     = in.readInt();
        String subTitulo  = in.readString();
        int    qtdPaginas = in.readInt();
        String caminho    = in.readString();
        boolean favoritos = in.readByte() == 1;
        boolean lida       = in.readByte() == 1;
        inicializarVariaveis(nome,edicao,caminho,subTitulo,qtdPaginas,favoritos,lida);
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

    public Pagina getCapa() {
        return paginas.get(0);
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
        parcel.writeString(caminho);
        parcel.writeByte((byte) (favoritos ? 1 : 0));
        parcel.writeByte((byte) (lida      ? 1 : 0));
    }
}
