package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import br.com.usinasantafe.pst.util.Tempo;

public class CabecAbordDAO {

    public CabecAbordDAO() {
    }

    public void salvarCabecAbert(CabecAbordBean cabecAbordBean){
        cabecAbordBean.setStatusCabecAbord(1L);
        cabecAbordBean.setDthrCabecAbord(Tempo.getInstance().dataComHora());
        cabecAbordBean.insert();
    }

    public List<CabecAbordBean> cabecFechList() {
        CabecAbordBean cabecAbordBean = new CabecAbordBean();
        return cabecAbordBean.get("statusCabecAbord", 2L);
    }

    public List<CabecAbordBean> cabecAbertList() {
        CabecAbordBean cabecAbordBean = new CabecAbordBean();
        return cabecAbordBean.get("statusCabecAbord", 1L);
    }

    public CabecAbordBean getCabecAbert(){
        CabecAbordBean cabecAbordBean = (CabecAbordBean) cabecAbertList().get(0);
        return cabecAbordBean;
    }

    public void salvarCabecFech(CabecAbordBean cabecAbordBean){
        cabecAbordBean.setStatusCabecAbord(2L);
        cabecAbordBean.update();
    }

    public void delCabec(Long idCabAbord) {
        CabecAbordBean cabecAbordBean = new CabecAbordBean();
        List cabecList = cabecAbordBean.get("idCabecAbord", idCabAbord);
        cabecAbordBean = (CabecAbordBean) cabecList.get(0);
        cabecAbordBean.delete();
        cabecList.clear();
    }

}
