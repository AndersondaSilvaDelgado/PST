package br.com.usinasantafe.pst;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pst.bean.variaveis.ItemAbordBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class QuestaoActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private ArrayList questaoArrayList;
    private List questaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao);

        pstContext = (PSTContext) getApplication();

        LinearLayout linearLayout =  (LinearLayout)  findViewById(R.id.layoutTela);
        Button buttonRetQuestao = (Button) findViewById(R.id.buttonRetQuestao);
        Button buttonSalvarQuestao = (Button) findViewById(R.id.buttonSalvarQuestao);

        QuestaoBean questaoBean = new QuestaoBean();
        questaoList = questaoBean.get("idTopico", pstContext.getIdTopico());

        questaoArrayList = new ArrayList();

        for(int i = 0; i < questaoList.size(); i++){
            questaoBean = (QuestaoBean) questaoList.get(i);
            questaoArrayList.add(getLayoutInflater().inflate(R.layout.activity_item_questao, null));
            View view = (View) questaoArrayList.get(i);
            TextView textViewDescrQuestao = view.findViewById(R.id.textViewDescrQuestao);
            textViewDescrQuestao.setText(questaoBean.getDescrQuestao());
            if(pstContext.getAbordagemCTR().verItemCabecAbert(questaoBean.getIdQuestao())){
                ItemAbordBean itemAbordBean = pstContext.getAbordagemCTR().getItemCabecAbert(questaoBean.getIdQuestao());
                EditText editTextQtdeSeg = view.findViewById(R.id.editTextQtdeSeg);
                EditText editTextQtdeInseg = view.findViewById(R.id.editTextQtdeInseg);
                if((itemAbordBean.getQtdeSegItemAbord() > 0))
                    editTextQtdeSeg.setText(String.valueOf(itemAbordBean.getQtdeSegItemAbord()));
                if((itemAbordBean.getQtdeInsegItemAbord() > 0))
                    editTextQtdeInseg.setText(String.valueOf(itemAbordBean.getQtdeInsegItemAbord()));
            }
            linearLayout.addView(view);
        }

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

                for(int i = 0; i < questaoArrayList.size(); i++) {
                    View view = (View) questaoArrayList.get(i);
                    EditText editTextQtdeSeg = view.findViewById(R.id.editTextQtdeSeg);
                    EditText editTextQtdeInseg = view.findViewById(R.id.editTextQtdeInseg);
                    if((editTextQtdeSeg.getText().length() > 0) || (editTextQtdeInseg.getText().length() > 0)){
                        Long seguro = 0L;
                        Long inseguro = 0L;
                        if((editTextQtdeSeg.getText().length() > 0))
                            seguro = Long.parseLong(editTextQtdeSeg.getText().toString());
                        if((editTextQtdeInseg.getText().length() > 0))
                            inseguro = Long.parseLong(editTextQtdeInseg.getText().toString());
                        QuestaoBean questaoBean = (QuestaoBean) questaoList.get(i);
                        pstContext.getAbordagemCTR().salvarItem(questaoBean.getIdQuestao(), seguro, inseguro);
                    }
                }
                questaoArrayList.clear();
                questaoList.clear();
                Intent it = new Intent(QuestaoActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed()  {
    }

}
