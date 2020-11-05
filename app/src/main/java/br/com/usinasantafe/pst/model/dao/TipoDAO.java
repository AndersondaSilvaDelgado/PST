package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.TipoBean;

public class TipoDAO {

    public TipoDAO() {
    }

    public TipoBean getTipo(int posicao){
        TipoBean tipoBean = new TipoBean();
        List<TipoBean> tipoList = tipoBean.all();
        tipoBean = tipoList.get(posicao);
        tipoList.clear();
        return  tipoBean;
    }

    public TipoBean getTipo(Long idTipo){
        TipoBean tipoBean = new TipoBean();
        List<TipoBean> tipoList = tipoBean.get("idTipo", idTipo);
        tipoBean = tipoList.get(0);
        tipoList.clear();
        return  tipoBean;
    }

}
