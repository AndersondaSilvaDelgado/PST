package br.com.usinasantafe.pst.model.dao;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.QuestaoBean;

public class QuestaoDAO {

    public QuestaoDAO() {
    }

    public List<QuestaoBean> questaoList(Long idTopico){
        QuestaoBean questaoBean = new QuestaoBean();
        return questaoBean.get("idTopico", idTopico);
    }

    public QuestaoBean getQuestao(Long idQuestao){
        QuestaoBean questaoBean = new QuestaoBean();
        List<QuestaoBean> questaoList = questaoBean.get("idQuestao", idQuestao);
        questaoBean = questaoList.get(0);
        questaoList.clear();
        return questaoBean;
    }

}
