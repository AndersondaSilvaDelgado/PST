package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.AreaBean;

public class AreaDAO {

    public AreaDAO() {
    }

    public List<AreaBean> areaList(){
        AreaBean areaBean = new AreaBean();
        return areaBean.all();
    }

}
