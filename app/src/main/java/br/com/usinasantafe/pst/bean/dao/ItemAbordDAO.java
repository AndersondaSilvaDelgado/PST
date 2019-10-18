package br.com.usinasantafe.pst.bean.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.ItemAbordBean;
import br.com.usinasantafe.pst.pst.EspecificaPesquisa;

public class ItemAbordDAO {

    public ItemAbordDAO() {
    }

    public void salvarItem(ItemAbordBean itemAbordBean){
        itemAbordBean.insert();
    }

    public boolean verItemCabecAbert(Long idCabec, Long idQuestao){
        return getListItemCabecAbert(idCabec, idQuestao).size() > 0;
    }

    public List getListItemCabecAbert(Long idCabec, Long idQuestao){

        ItemAbordBean itemAbordBean = new ItemAbordBean();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idCabItemAbord");
        pesq1.setValor(idCabec);
        pesq1.setTipo(1);
        listaPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("idQuestaoItemAbord");
        pesq2.setValor(idQuestao);
        pesq2.setTipo(1);
        listaPesq.add(pesq2);

        return itemAbordBean.get(listaPesq);

    }

    public ItemAbordBean getItemCabecAberts(Long idCabec, Long idQuestao){
        return (ItemAbordBean) getListItemCabecAbert(idCabec, idQuestao).get(0);
    }

    public List getListItemCabecFech(Long idCabec){
        ItemAbordBean itemAbordBean = new ItemAbordBean();
        return itemAbordBean.get("idCabItemAbord", idCabec);
    }

}
