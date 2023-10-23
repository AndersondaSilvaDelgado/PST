package br.com.usinasantafe.pst.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pst.control.ConfigCTR;
import br.com.usinasantafe.pst.view.MenuInicialActivity;
import br.com.usinasantafe.pst.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.pst.util.conHttp.UrlsConexaoHttp;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private ProgressDialog progressDialog;
    private Context telaAtual;
    private Class telaProx;
    private String dados;
    private String classe;
    private PostVerGenerico postVerGenerico;
    public static int status;

    public VerifDadosServ() {
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {
        try {

            ConfigCTR configCTR = new ConfigCTR();
            configCTR.recToken(result.trim(), this.telaAtual, this.telaProx, this.progressDialog);

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }
    public void salvarToken(String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dados = dados;
        this.classe = "Token";

        envioVerif();

    }

    public void envioVerif() {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(classe)};
        Map<String, Object> parametrosPost = new HashMap<>();
        parametrosPost.put("dado", this.dados);

        Log.i("PMM", "postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }


}
