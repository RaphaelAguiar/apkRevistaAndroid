package com.example.fillipeteixeira.apprevista.entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fillipe Teixeira on 24/07/2016.
 */
public class Revista implements Parcelable {

    private String nome;
    private String edicao;
    private String subTitulo;
    private int qtdPaginas;
    private int imagem;
    private String caminho;
    private Boolean favoritos;
    private Boolean lida;

    protected Revista(Parcel in) {
        nome = in.readString();
        edicao = in.readString();
        subTitulo = in.readString();
        qtdPaginas = in.readInt();
        imagem = in.readInt();
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

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public Revista(String nome, String edicao, int imagem, String caminho, String subTitulo, int qtdPaginas) {
        this.nome = nome;
        this.edicao = edicao;
        this.imagem = imagem;
        this.caminho = caminho;
        favoritos = false;
        lida = false;
        this.subTitulo = subTitulo;
        this.qtdPaginas = qtdPaginas;

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

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(edicao);
        parcel.writeString(subTitulo);
        parcel.writeInt(qtdPaginas);
        parcel.writeInt(imagem);
        parcel.writeString(caminho);
    }
}
