package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.TopicoBean;

public class TopicoDAO {

    public TopicoDAO() {
    }

    public List<TopicoBean> topicoList(Long idTipo){
        TopicoBean topicoBean = new TopicoBean();
        return topicoBean.get("idTipo", idTipo);
    }

    public TopicoBean getTopico(Long idTopico){
        TopicoBean topicoBean = new TopicoBean();
        List<TopicoBean> topicoList = topicoBean.get("idTopico", idTopico);
        topicoBean = topicoList.get(0);
        topicoList.clear();
        return topicoBean;
    }

}
