package com.example.fillipeteixeira.apprevista.persistencia;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.graphics.BitmapCompat;

import com.example.fillipeteixeira.apprevista.activitys.TelaAbasActivity;
import com.example.fillipeteixeira.apprevista.persistencia.restful.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by raphael.aguiar on 07/09/2016.
 */
public class ImagemDao extends Dao{
    private static final ImagemDao instancia = new ImagemDao();

    public static ImagemDao getInstancia() {
        return instancia;
    }

    private Bitmap getLocalImage(String nomeDaRevista, int nPagina){
        return BitmapFactory.decodeFile(getFilePath(nomeDaRevista,Integer.toString(nPagina)));
    }

    private void saveToExternalStorage(String dir, String fileName,Bitmap bitmapImage){
        try {
            FileOutputStream fos = getFileOutputStream(dir,fileName);
            bitmapImage.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap retornoGet;
    public Bitmap get(String nomeDaRevista,int nPagina,boolean miniatura,boolean aguardar) {
        Bitmap retorno = getLocalImage(nomeDaRevista,nPagina);
        if(retorno==null) {
            RequestParams request = HttpUtils.getRequestParams();
            request.put("nomeDaRevista", nomeDaRevista);
            request.put("nPagina", nPagina);
            request.put("miniatura", miniatura);
            get("obterImagem", request, aguardar, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int state, Header[] headers, byte[] imagem) {
                    while(retornoGet!=null);
                    retornoGet = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
            retorno    = retornoGet;
            retornoGet = null;

            if(retorno!=null)
                saveToExternalStorage(nomeDaRevista,Integer.toString(nPagina),retorno);
        }
        return retorno;
    }
}
