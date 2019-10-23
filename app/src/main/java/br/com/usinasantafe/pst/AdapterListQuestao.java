package br.com.usinasantafe.pst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.QuestaoBean;


/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListQuestao extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewDescrQuestao;

    public AdapterListQuestao(Context context, List itens) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_questao, null);
        textViewDescrQuestao = (TextView) view.findViewById(R.id.textViewDescrQuestao);

        QuestaoBean questaoBean = (QuestaoBean) itens.get(position);
        textViewDescrQuestao.setText(questaoBean.getDescrQuestao());

        return view;
    }

}
