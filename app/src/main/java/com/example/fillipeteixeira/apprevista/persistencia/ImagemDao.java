package com.example.fillipeteixeira.apprevista.persistencia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.fillipeteixeira.apprevista.persistencia.restful.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by raphael.aguiar on 07/09/2016.
 */
public class ImagemDao extends Dao{
    private static final ImagemDao instancia = new ImagemDao();

    private ImagemDao(){

    }

    public static ImagemDao getInstancia() {
        return instancia;
    }

    private Bitmap retornoMetodoGetCapa;
    public Bitmap getCapa(String nomeDaRevista,boolean aguardar) {
        RequestParams request = HttpUtils.getRequestParams();
        request.put("nomeDaRevista",nomeDaRevista);
        request.put("nPagina",1);
        request.put("miniatura",true);
        get("obterImagem",request,aguardar,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int state, Header[] headers, byte[] imagem){
                retornoMetodoGetCapa = BitmapFactory.decodeByteArray(imagem,0,imagem.length);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        Bitmap retorno       = retornoMetodoGetCapa;
        retornoMetodoGetCapa = null;
        return retorno;
    }
}
