package br.com.usinasantafe.pst.bean.variaveis;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualAplicBean {

    private Long matricFuncAtual;
    private String versaoAtual;
    private String versaoNova;

    public AtualAplicBean() {
    }

    public Long getMatricFuncAtual() {
        return matricFuncAtual;
    }

    public void setMatricFuncAtual(Long matricFuncAtual) {
        this.matricFuncAtual = matricFuncAtual;
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

    public String getVersaoNova() {
        return versaoNova;
    }

    public void setVersaoNova(String versaoNova) {
        this.versaoNova = versaoNova;
    }
}
