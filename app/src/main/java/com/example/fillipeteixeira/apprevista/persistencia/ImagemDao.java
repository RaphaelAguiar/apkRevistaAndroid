package com.example.fillipeteixeira.apprevista.persistencia;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

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

    private ImagemDao(){

    }

    public static ImagemDao getInstancia() {
        return instancia;
    }

    private String getFilePath(String dir, String fileName){
        /*TODO obter nome do cliente para criar a pasta correta do aplicativo*/
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES + File.separator +
                "apkRevista"                   + File.separator +
                dir                            + File.separator +
                fileName                       + ".png").getAbsolutePath();
    }

    private File getFile(String dir, String fileName){
        return new File(getFilePath(dir,fileName));
    }

    private Bitmap getLocalImage(String nomeDaRevista, int nPagina){
        return BitmapFactory.decodeFile(getFilePath(nomeDaRevista,Integer.toString(nPagina)));
    }

    private void saveToInternalStorage(String dir, String fileName,Bitmap bitmapImage){
        File mypath = getFile(dir,fileName);
        if(!mypath.exists()) {
            try {
                mypath.mkdirs();
                mypath.delete();
                mypath.createNewFile();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                saveToInternalStorage(nomeDaRevista,Integer.toString(nPagina),retorno);
        }
        return retorno;
    }
}
