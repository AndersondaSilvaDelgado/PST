package br.com.usinasantafe.pst.bean.dao;

import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.CabAbordBean;

public class CabAbordDAO {

    public CabAbordDAO() {
    }

    public void salvarCabecAbert(CabAbordBean cabAbordBean){
        cabAbordBean.setStatusCabAbord(1L);
        cabAbordBean.insert();
    }

    public List cabecFechList() {
        CabAbordBean cabAbordBean = new CabAbordBean();
        return cabAbordBean.get("statusCabAbord", 2L);
    }

    public List cabecAbertList() {
        CabAbordBean cabAbordBean = new CabAbordBean();
        return cabAbordBean.get("statusCabAbord", 1L);
    }

    public CabAbordBean getCabecAbert(){
        CabAbordBean cabAbordBean = (CabAbordBean) cabecAbertList().get(0);
        return cabAbordBean;
    }

    public void salvarCabecFech(CabAbordBean cabAbordBean){
        cabAbordBean.setStatusCabAbord(2L);
        cabAbordBean.update();
    }

}
