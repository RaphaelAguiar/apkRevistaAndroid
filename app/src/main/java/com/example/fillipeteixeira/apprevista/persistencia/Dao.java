package com.example.fillipeteixeira.apprevista.persistencia;

import com.example.fillipeteixeira.apprevista.persistencia.restful.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by raphael.aguiar on 07/09/2016.
 */
public class Dao {
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
