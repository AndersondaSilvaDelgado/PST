package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.TurnoBean;

public class TurnoDAO {

    public TurnoDAO() {
    }

    public List<TurnoBean> turnoList(){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.all();
    }

}
