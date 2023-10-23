package br.com.usinasantafe.pst.util;

import android.content.Context;

import br.com.usinasantafe.pst.control.AbordagemCTR;
import br.com.usinasantafe.pst.util.retrofit.AbordagemEnvio;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void dadosEnvio() {

        AbordagemCTR abordagemCTR = new AbordagemCTR();
        AbordagemEnvio abordagemEnvio = new AbordagemEnvio();
        abordagemEnvio.envioDadosAbordagem(abordagemCTR.dadosFechEnvio());

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifEnvioDados() {
        AbordagemCTR abordagemCTR = new AbordagemCTR();
        return abordagemCTR.verEnvioDados();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            enviando = true;
            envioDados();
        }
        else{
            enviando = false;
        }

    }

    public void envioDados() {
        if(verifEnvioDados()){
            enviando = true;
            dadosEnvio();
        }
        else{
            enviando = false;
        }
    }

    public boolean verifDadosEnvio() {
        if (!verifEnvioDados()){
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

    public boolean isEnviando() {
        return enviando;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }
}