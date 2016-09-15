package com.example.fillipeteixeira.apprevista.persistencia;

import android.os.Environment;

import com.example.fillipeteixeira.apprevista.persistencia.restful.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by raphael.aguiar on 07/09/2016.
 */
public class Dao {
    protected Dao(){

    }

    protected String getFilePath(String dir, String fileName){
        /*TODO obter nome do cliente para criar a pasta correta do aplicativo*/
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES + File.separator +
                        "apkRevista"                   + File.separator +
                        dir                            + File.separator +
                        fileName                       + ".png").getAbsolutePath();
    }

    protected File getFile(String dir, String fileName){
        return new File(getFilePath(dir,fileName));
    }

    protected FileOutputStream getFileOutputStream(String dir, String fileName) throws IOException {
        File mypath = getFile(dir,fileName);
        if(!mypath.exists()) {
            mypath.mkdirs();
            mypath.delete();
            mypath.createNewFile();
        }

        try {
            return new FileOutputStream(mypath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String readFile(String dir, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(getFilePath(dir, fileName)));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }

    protected void writeFile(String dir, String fileName, String conteudo) throws IOException {
        FileOutputStream fos    = getFileOutputStream(dir, fileName);
        byte[] retornoArrayByte = conteudo.getBytes(Charset.defaultCharset());
        fos.write(retornoArrayByte);
        fos.flush();
        fos.close();
    }

    public void get(final String                   metodo,
                      final RequestParams            request,
                      final boolean                  aguardarConclusao,
                      final AsyncHttpResponseHandler retorno){
        Thread thread = new Thread() {
            @Override
            public void run() {
                HttpUtils.get(metodo,request,retorno);
            }
        };
        thread.start();
        if(aguardarConclusao)
            while(thread.getState()!=Thread.State.TERMINATED);
    }
}
