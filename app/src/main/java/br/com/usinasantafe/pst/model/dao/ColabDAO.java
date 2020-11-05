package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.ColabBean;

public class ColabDAO {

    public ColabDAO() {
    }

    public ColabBean getColab(Long matricColab){
        List<ColabBean> colabList = colabList(matricColab);
        ColabBean colabBean = colabList.get(0);
        colabList.clear();
        return colabBean;
    }

    public boolean verColab(Long matricColab){
        List<ColabBean> colabList = colabList(matricColab);
        boolean ret = (colabList.size() > 0);
        colabList.clear();
        return ret;
    }

    public List<ColabBean> colabList(Long matricColab){
        ColabBean colabBean = new ColabBean();
        return colabBean.get("matricColab", matricColab);
    }

}
