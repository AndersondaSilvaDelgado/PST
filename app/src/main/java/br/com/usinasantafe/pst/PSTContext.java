package br.com.usinasantafe.pst;

import android.app.Application;

import br.com.usinasantafe.pst.control.AbordagemCTR;

public class PSTContext extends Application {

    public static String versaoAplic = "1.03";
    private AbordagemCTR abordagemCTR;
    private int posTipo;
    private Long idTopico;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AbordagemCTR getAbordagemCTR() {
        if (abordagemCTR == null)
            abordagemCTR = new AbordagemCTR();
        return abordagemCTR;
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
