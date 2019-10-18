package br.com.usinasantafe.pst.util;

import android.content.Context;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pst.control.AbordagemCTR;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public EnvioDadosServ() {

        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarCabecalho() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
        AbordagemCTR abordagemCTR = new AbordagemCTR();

        String[] dados = new String[7];

        String cabec = abordagemCTR.dadosCabecFechEnvio();
        String item = abordagemCTR.dadosItemFechEnvio();

        Log.i("PST", "CABECALHO = " + cabec);
        Log.i("PST", "ITEM = " + item);

        dados[0] = urlsConexaoHttp.getsInserirDados();
        dados[1] = cabec;
        dados[2] = item;
        dados[3] = abordagemCTR.getFoto(1);
        dados[4] = abordagemCTR.getFoto(2);
        dados[5] = abordagemCTR.getFoto(3);
        dados[6] = abordagemCTR.getFoto(4);

        ConHttpMultipartGenerico conHttpMultipartGenerico = new ConHttpMultipartGenerico();
        conHttpMultipartGenerico.execute(dados);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifCabec() {
        AbordagemCTR abordagemCTR = new AbordagemCTR();
        return abordagemCTR.verEnvioDadosCabec();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {
        if(verifCabec()){
            enviarCabecalho();
        }
    }

    public boolean verifDadosEnvio() {
        if (!verifCabec()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }
}