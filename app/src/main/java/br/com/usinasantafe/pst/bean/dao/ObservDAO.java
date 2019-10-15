package br.com.usinasantafe.pst.bean.dao;

import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.CabObservBean;

public class ObservDAO {

    public ObservDAO() {
    }

    public List cabFechList() {
        CabObservBean cabObservBean = new CabObservBean();
        return cabObservBean.get("statusCabObserv", 2L);
    }

}
