package br.com.usinasantafe.pst;

import android.app.Application;

import br.com.usinasantafe.pst.control.ObservCTR;

public class PSTContext extends Application {

    public static String versaoAplic = "1.00";
    private ObservCTR observCTR;
    private int posTipo;
    private Long idTopico;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ObservCTR getObservCTR() {
        if (observCTR == null)
            observCTR = new ObservCTR();
        return observCTR;
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
