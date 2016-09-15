package com.example.fillipeteixeira.apprevista.persistencia.restful;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

public class HttpUtils {
    private static final String          ENDERECO = "http://apkrevista.hopto.org:8080/apkRevista";
    private static final String          CLIENTE  = "clienteTeste";
    private static final SyncHttpClient client    = new SyncHttpClient();
    
    public static RequestParams getRequestParams(){
        RequestParams retorno = new RequestParams();
        retorno.put("cliente",CLIENTE);
        return retorno;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return ENDERECO + "/" + relativeUrl;
    }
}