package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.SubAreaBean;

public class SubAreaDAO {

    public SubAreaDAO() {
    }

    public List<SubAreaBean> subAreaList(Long idArea){
        SubAreaBean subAreaBean = new SubAreaBean();
        return subAreaBean.get("idArea", idArea);
    }

}
