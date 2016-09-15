package com.example.fillipeteixeira.apprevista.persistencia;

import com.example.fillipeteixeira.apprevista.entidades.Revista;
import com.example.fillipeteixeira.apprevista.persistencia.restful.HttpUtils;
import com.loopj.android.http.TextHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RevistaDao extends Dao {
    private static final RevistaDao instancia = new RevistaDao();

    public ArrayList<Revista> lista;
    public ArrayList<Revista> listaFavoritos;

    private RevistaDao() {
        super();
        listaFavoritos = new ArrayList<Revista>();
        lista          = new ArrayList<Revista>();
    }

    public static RevistaDao getInstancia() {
        return instancia;
    }

    public void addRetornoAListaDeRevistas(JSONArray retorno) throws JSONException {
        for (int i = 0; i < retorno.length(); i++) {
            JSONObject obj = retorno.getJSONObject(i);

            String nomeDaRevista = obj.getString("nome");
            String subTitulo = obj.getString("subTitulo");
            int nPaginas = obj.getInt("nPaginas");
            int edicao = obj.getInt("edicao");

            lista.add(new Revista(nomeDaRevista,
                                  edicao,
                                  "default",
                                  subTitulo,
                                  nPaginas)
            );
        }
    }

    public ArrayList<Revista> getItens(boolean aguardar) {
        final String dir      = "configuracoes";
        final String fileName = "metaDados.json";

        if (lista.isEmpty()) {
            RequestParams request = HttpUtils.getRequestParams();
            get("obterMetaDados", request,aguardar,new TextHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String retorno) {
                    try {
                        writeFile(dir,fileName,retorno);
                        JSONArray json = new JSONArray(retorno);
                        addRetornoAListaDeRevistas(json);
                    } catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    try {
                        String retorno = readFile(dir,fileName);
                        addRetornoAListaDeRevistas(new JSONArray(retorno));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });

        }
        return lista;
    }

    public void addFavoritos(Revista r){
        boolean existe = false;
        int i = 0;
        while (instancia.listaFavoritos.size() < i){
            if(r == instancia.lista.get(i)){
                existe = true;
                return;
            }
            i++;
        }
        if(!existe)
        instancia.listaFavoritos.add(r);
    }

    public void removerFavoritos(int pos){
        instancia.listaFavoritos.remove(pos);
    }

    public ArrayList<Revista> getItensFavoritos() {
        int i = 0;
        while (instancia.lista.size() > i) {
            if (instancia.lista.get(i).getFavoritos() == true) {
                instancia.addFavoritos(instancia.lista.get(i));
            }
            i++;
        }
        return getInstancia().listaFavoritos;
    }
}
