package br.com.usinasantafe.pst.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pst.MenuInicialActivity;
import br.com.usinasantafe.pst.model.pst.GenericRecordable;
import br.com.usinasantafe.pst.model.bean.variaveis.AtualAplicBean;
import br.com.usinasantafe.pst.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.pst.util.conHttp.UrlsConexaoHttp;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private ProgressDialog progressDialog;
    private String tipo;
    private MenuInicialActivity menuInicialActivity;
    private PostVerGenerico postVerGenerico;

    public VerifDadosServ() {
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {
        try {

        if (!result.equals("")) {
                if (this.tipo.equals("Atualiza")) {
                    String verAtualizacao = result.trim();
                    if (verAtualizacao.equals("S")) {
                        AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                        atualizarAplicativo.setContext(this.menuInicialActivity);
                        atualizarAplicativo.execute();
                    } else {
                        this.menuInicialActivity.startTimer();
                    }
                }
            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setVersaoAtual(versaoAplic);

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

}
