package br.com.usinasantafe.pst;

import android.app.Application;

import br.com.usinasantafe.pst.control.AbordagemCTR;
import br.com.usinasantafe.pst.control.ConfigCTR;

public class PSTContext extends Application {

    private ConfigCTR configCTR;
    private AbordagemCTR abordagemCTR;
    private int posTipo;
    private Long idTopico;

    public static String versaoWS = "1.06";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AbordagemCTR getAbordagemCTR() {
        if (abordagemCTR == null)
            abordagemCTR = new AbordagemCTR();
        return abordagemCTR;
    }

    public ConfigCTR getConfigCTR() {
        if(configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }


    public int getPosTipo() {
        return posTipo;
    }

    public void setPosTipo(int posTipo) {
        this.posTipo = posTipo;
    }

    public Long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }
}
