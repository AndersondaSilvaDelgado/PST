package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.QuestaoBean;

public class QuestaoActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private ArrayList questaoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao);

        pstContext = (PSTContext) getApplication();

        LinearLayout linearLayout =  (LinearLayout)  findViewById(R.id.layoutTela);

        QuestaoBean questaoBean = new QuestaoBean();
        List questaoList = questaoBean.get("idTopico", pstContext.getIdTopico());

        questaoArrayList = new ArrayList();

        for(int i = 0; i < questaoList.size(); i++){
            questaoBean = (QuestaoBean) questaoList.get(i);
            questaoArrayList.add(getLayoutInflater().inflate(R.layout.activity_item_questao, null));
            View view = (View) questaoArrayList.get(i);
            TextView textViewDescrQuestao = view.findViewById(R.id.textViewDescrQuestao);
            textViewDescrQuestao.setText(questaoBean.getDescrQuestao());
            linearLayout.addView(view);
        }

        View viewBotao = getLayoutInflater().inflate(R.layout.activity_botao_questao, null);
        linearLayout.addView(viewBotao);

        Button buttonRetQuestao = (Button) viewBotao.findViewById(R.id.buttonRetQuestao);
        Button buttonSalvarQuestao = (Button) viewBotao.findViewById(R.id.buttonSalvarQuestao);

        buttonRetQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(QuestaoActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSalvarQuestao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(QuestaoActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed()  {
    }

}
